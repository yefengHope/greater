package com.hf.adminWeb.config.security;

import com.hf.adminWeb.security.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * <p>@Title Spring Security配置 </p>
 * <p>@Description  配置流程
 * <li>基础配置</li>
 * <li>扩展配置 ，实现自己的UserAuthService和PermissionEvaluator，分别用于自定义Principle, Authority和Permission。</li>
 * </p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/7/3 15:24 创建日期</p>
 */


@EnableWebSecurity      // 禁用Boot的默认Security配置，配合@Configuration启用自定义配置（需要扩展WebSecurityConfigurerAdapter）
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用Security注解，例如最常用的@PreAuthorize
public class MultiHttpSecurityConfig {

    @Resource
    private CustomUserDetailsServiceImpl customUserDetailsService;

    /**
     * 通过重载，配置user-detail服务
     * configure(AuthenticationManagerBuilder): 身份验证配置，用于注入自定义身份验证Bean和密码校验规则
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // demo ：在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为USER。
        // auth.inMemoryAuthentication()
        //         .withUser("user").password("password").roles("USER");
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        //不加密
        // auth.userDetailsService(customUserDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Configuration
    @Order(1)  // 2
    public static class AdminWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Resource
        private MyJsonSuccesAuthenticationSuccessHandler myJsonSuccesAuthenticationSuccessHandler;
        @Resource
        private MyJsonFailAuthenticationSuccessHandler myJsonFailAuthenticationSuccessHandler;

        /**
         * 通过重载，配置如何通过拦截器保护请求
         * configure(HttpSecurity): Request层面的配置，对应XML Configuration中的<http>元素
         *
         * @param http HttpSecurity
         * @throws Exception Exception
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            /*Spring Boot不允许加载iframe问题解决*/
//            http.headers().frameOptions().disable(); // 所有网页都可以被iframe
            http.headers().frameOptions().sameOrigin()
                    .httpStrictTransportSecurity().disable(); // 允许同源网页可以被iframe
            // authorizeRequests()定义哪些URL需要被保护、哪些不需要被保护
            // /和/home不需要任何认证就可以访问，其他的路径都必须通过身份验证
            // defaultSuccessUrl 登录成功页
            http.authorizeRequests()
                    .antMatchers("/", "/login*", "/home", "/demo/**")
                    .permitAll().anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/toLogin.htm").permitAll()
                    .loginProcessingUrl("/login.htm")
                    .usernameParameter("userName")
                    .passwordParameter("userPwd")
                    // .defaultSuccessUrl("/user/page.htm")//登陆成功路径
                    // .defaultSuccessUrl("/admin/index.do")//登陆成功路径
                    .successHandler(myJsonSuccesAuthenticationSuccessHandler)
                    .failureHandler(myJsonFailAuthenticationSuccessHandler)
                    // .failureUrl("/login/failure.do")//登陆失败路径
                    .and().httpBasic() // 模态登录弹出框
                    .and().rememberMe().tokenValiditySeconds(604800).key("secondsKey") //记住密码，一周
                    .and().csrf()
                    .and()
                    .logout()
                    .logoutUrl("/admin/logout.do")
                    // .logoutSuccessUrl("/toLogin.htm") //返回的json，所以产生了问题，似乎这个设置无效
                    .permitAll();

            http.authorizeRequests()
                    // session time out
                    .and().sessionManagement()
                    .invalidSessionUrl("/security/session_timeout.do");


        /*

        HttpSecurity http;
        http.authorizeRequests()
        .antMatchers("/", "/home").permitAll()
        .antMatchers("/admin/**").access("hasRole('ADMIN')")
        .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
        .and().formLogin().loginPage("/login")
        .usernameParameter("ssoId").passwordParameter("password")
        .and().csrf()
        .and().exceptionHandling().accessDeniedPage("/Access_Denied");
        */

        }

        /**
         *  通过重载，配置Spring Security的Filter链
         * configure(WebSecurity): Web层面的配置，一般用来配置无需安全检查的路径
         *
         * @param web WebSecurity
         * @throws Exception Exception
         */
        @Override
        public void configure(WebSecurity web) throws Exception {

            web.ignoring().antMatchers("/plugs/**", "/html_model/hplus/**"
                    , "/js/**", "/css/**", "/font/**", "/images/**"
                    , "/**/favicon.ico");
        }
    }

    // @Configuration
    // @Order(1)  // 2
    // public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
    //     protected void configure(HttpSecurity http) throws Exception {
    //         http
    //                 .antMatcher("/api/**")  // 3
    //                 .authorizeRequests()
    //                 .anyRequest().hasRole("ADMIN")
    //                 .and()
    //                 .httpBasic();
    //     }
    // }
    //
    // @Configuration // 4
    // public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    //
    //     @Override
    //     protected void configure(HttpSecurity http) throws Exception {
    //         http
    //                 .authorizeRequests()
    //                 .anyRequest().authenticated()
    //                 .and()
    //                 .formLogin();
    //     }
    // }


    // public static void main(String[] args) {
    //     String pwd = "123456";
    //     BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //     String hashedPassword = passwordEncoder.encode(pwd);
    //     System.out.println(hashedPassword);
    //     boolean is = passwordEncoder.matches(pwd, hashedPassword);
    //     System.out.println(is);
    //
    //     // Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
    //     // String hashedPassword = passwordEncoder.encodePassword("123456","");
    //     // System.out.println(hashedPassword);
    // }
}

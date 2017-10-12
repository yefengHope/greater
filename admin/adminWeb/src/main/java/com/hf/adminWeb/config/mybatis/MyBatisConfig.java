package com.hf.adminWeb.config.mybatis;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * mybatis分页配置
 * Created by rain on 2017/3/13.
 */


// @MapperScan("com.hf.adminDao.dao")
//@PropertySources({ @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true), @PropertySource(value = "file:./application.properties", ignoreResourceNotFound = true) })
@Configuration
@EnableTransactionManagement
@ConditionalOnClass({ SqlSessionFactory.class, SqlSessionFactoryBean.class })
//@ConditionalOnBean(DataSource.class)
@EnableConfigurationProperties(MybatisProperties.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MyBatisConfig implements TransactionManagementConfigurer {


    private static final Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);

    @Autowired
    private JdbcConfig jdbcConfig;

    @Autowired
    DataSource dataSource;

    @Bean
    public DataSource createDataSource() throws SQLException {
        return DataSourceBuilder.create(Thread.currentThread().getContextClassLoader())
                .driverClassName(jdbcConfig.getDriverClass())
                .url(jdbcConfig.url)
                .username(jdbcConfig.userName)
                .password(jdbcConfig.password).build();
    }


    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }


    /**
     * 分页插件
     *
     * @param dataSource
     * @return
     * @author SHANHY
     * @create  2016年2月18日
     */
    @Bean
    public PageHelper pageHelper(DataSource dataSource) {
        logger.info("注册MyBatis分页插件PageHelper");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }

    @PropertySource(value = "classpath:application.properties")
    @Component
    static class JdbcConfig {
        //数据库用户名
        @Value("${spring.datasource.username}")
        private String userName;
        //        * 驱动名称         */
        @Value("${spring.datasource.driverClassName}")
        private String driverClass;
        //       * 数据库连接url         */
        @Value("${spring.datasource.url}")
        private  String url;
        //         * 数据库密码         */
        @Value("${spring.datasource.password}")
        private String password;

        public String getUserName() {
            return userName;
        }
        public void setUserName(String userName) {
            this.userName = userName;
        }
        public String getDriverClass() {
            return driverClass;
        }
        public void setDriverClass(String driverClass) {
            this.driverClass = driverClass;
        }
        public String getUrl() {
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    }
}
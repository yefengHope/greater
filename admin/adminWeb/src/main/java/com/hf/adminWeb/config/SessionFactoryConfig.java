package com.hf.adminWeb.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * mybatis 扫描路径设置
 * Created by rain on 2017/3/13.
 */

// @Configuration
public class SessionFactoryConfig {


    /**
     * mybatis 配置路径
     **/
    private static String MYBATIS_CONFIG = "mybatis/mybatis-config.xml";
    /**
     * mybatis mapper resource 路径
     **/
    private static String MAPPER_PATH = "mybatis/mapper/**.xml";

    @Resource
    private DataSource dataSource;

    private String typeAliasPackage = "com.fengyu.system.entity";

    /**
     * 创建sqlSessionFactoryBean 实例
     * 并且设置configtion 如驼峰命名.等等
     * 设置mapper 映射路径
     * 设置datasource数据源
     * @deprecated 新配置在MybatisAutoConfiguration.java
     * @return
     */
    // @Bean
    // public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
    //     SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    //     // 设置mybatis configuration 扫描路径
    //     sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
    //     // 添加mapper 扫描路径
    //     PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
    //     String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;
    //     sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
    //     // 设置datasource
    //     sqlSessionFactoryBean.setDataSource(dataSource);
    //     // 设置typeAlias 包扫描路径
    //     sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);
    //     return sqlSessionFactoryBean;
    // }

}

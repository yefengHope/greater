package com.hf.adminWeb.config.flowEngine;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * <p>@Title activiti配置文件 </p>
 * <p>@Description 类功能描述（功能，作用）,描述过多时可以换行</p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author hanfeng</p>
 * <p>@date 2017/7/7 11:26 创建日期</p>
 * <p>hanfeng@dgg.com 作者的公司邮箱</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {

    // @Resource
    // private DataSource dataSource;
    //
    // // @Resource
    // // private PlatformTransactionManager platformTransactionManager;
    //
    // static final String NAME = "master";
    // //注入数据源和事务管理器
    // @Bean
    // public SpringProcessEngineConfiguration springProcessEngineConfiguration(
    //         // @Qualifier(NAME + "DataSource") DataSource dataSource,
    //         @Qualifier(NAME + "TransactionManager") PlatformTransactionManager transactionManager,
    //         SpringAsyncExecutor springAsyncExecutor) throws IOException {
    //     return this.baseSpringProcessEngineConfiguration(dataSource, transactionManager, springAsyncExecutor);
    // }

    @Autowired
    public PlatformTransactionManager transactionManager;

    @Autowired
    public DataSource dataSource;

    @Bean
    public SpringProcessEngineConfiguration getProcessEngineConfiguration() {
        SpringProcessEngineConfiguration engineConfiguration = new SpringProcessEngineConfiguration();
        engineConfiguration.setDataSource(dataSource);
        engineConfiguration.setTransactionManager(transactionManager);
        // <!-- 设置建表策略，如果没有表，自动创建表 -->
        engineConfiguration.setDatabaseSchema("ACT");
        engineConfiguration.setDatabaseSchemaUpdate("true");
        return engineConfiguration;
    }

}

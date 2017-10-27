package com.hf.engine.code;

import com.hf.engine.code.djerp.MainCreate;
import com.hf.engine.code.djerp.config.CodeFactoryConfig;
import org.springframework.beans.PropertyAccessorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.util.PropertyPlaceholderHelper;

@SpringBootApplication
@PropertySource("classpath:codeFactoryDjConfig.properties")
public class DjerpApplication {

	// 获取含自定义properties文件内容所有
	// http://blog.csdn.net/qq_30051139/article/details/72885674
	// http://blog.csdn.net/qq496013218/article/details/75146757
	// http://duyunfei.iteye.com/blog/903557

	@Autowired
	private ConfigurableEnvironment configurableEnvironment;

	public void getPropertySoure() {

		System.out.println(configurableEnvironment.getPropertySources());
	}

	public static void main(String[] args) {

		SpringApplication.run(DjerpApplication.class, args);
		CodeFactoryConfig.configProp("codeFactoryDjConfig");
		CodeFactoryConfig.getConfig();
		MainCreate.createFile();
	}
}

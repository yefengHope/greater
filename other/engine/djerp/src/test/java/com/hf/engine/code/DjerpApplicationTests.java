package com.hf.engine.code;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DjerpApplicationTests {

	@Autowired
	private ConfigurableEnvironment configurableEnvironment;

	@Test
	public void getPropertySoure() {

		System.out.println(configurableEnvironment.getPropertySources());
	}

	@Test
	public void contextLoads() {
	}

}

package com.mdvns.mdvn.file.papi;

import com.mdvns.mdvn.file.papi.config.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MdvnFilePapiApplicationTests {

	@Autowired
	private WebConfig webConfig;

	@Test
	public void contextLoads() {
	}

	@Test
	public void WebConfigTest() {
		System.out.print("获取到的URL为：{}"+webConfig.getSaveAttchInfoUrl());
	}

}

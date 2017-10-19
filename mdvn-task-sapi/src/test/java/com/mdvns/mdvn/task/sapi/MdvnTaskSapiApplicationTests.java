package com.mdvns.mdvn.task.sapi;

import com.mdvns.mdvn.task.sapi.service.impl.TaskServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MdvnTaskSapiApplicationTests {

	@Test
	public void contextLoads() {
		System.out.print(System.currentTimeMillis());
	}

	@Test
	public void testRetriTaskList() {
		TaskServiceImpl taskService = new TaskServiceImpl();
	}


}

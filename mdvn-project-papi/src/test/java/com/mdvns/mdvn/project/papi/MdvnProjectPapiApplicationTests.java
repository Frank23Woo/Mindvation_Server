package com.mdvns.mdvn.project.papi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MdvnProjectPapiApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Test
	public void test() throws ParseException {
		String dateTime = "2017-12-05 13:30:45";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime));
		System.out.println("日期[?-?-?]对应毫秒：" + calendar.getTimeInMillis());
	}
//	@Test
//	public void test() {
//		long millisecond = 1483159625851l;
//		Date date = new Date(millisecond);
//		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss SSS a");
//		System.out.println("毫秒[1483159625851]对应日期时间字符串：" + format.format(date));
//	}
}

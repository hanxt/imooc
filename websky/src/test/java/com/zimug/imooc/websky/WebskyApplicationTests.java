package com.zimug.imooc.websky;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebskyApplicationTests {

	@Test
	public void contextLoads() {

		String str1 = "2009-08-01 23:23:23我是2009-08-01";

		System.out.println(str1.replaceAll("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})","$1$2$3$4$5$6")
				.replaceAll("(\\d{4})-(\\d{2})-(\\d{2})","$1$2$3"));


	}

}

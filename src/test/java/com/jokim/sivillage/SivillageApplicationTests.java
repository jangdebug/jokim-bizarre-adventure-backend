package com.jokim.sivillage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SivillageApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Hello World");
	}

	@BeforeEach
	void SetUp(){
		System.out.println("Hello World!!");
	}

}

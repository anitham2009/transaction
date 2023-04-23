package com.app.transaction;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionApplicationTests {

	@Test
	void contextLoads() {
		TransactionApplication.main(new String[] {});
		assertTrue(true);
	}

}

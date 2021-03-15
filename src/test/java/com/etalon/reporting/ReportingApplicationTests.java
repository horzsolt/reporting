package com.etalon.reporting;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.etalon.reporting.web.controller.ReportController;

@SpringBootTest
class ReportingApplicationTests {

	@Autowired
	private ReportController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}

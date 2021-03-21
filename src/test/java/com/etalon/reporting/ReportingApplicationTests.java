package com.etalon.reporting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.etalon.reporting.web.controller.ReportController;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReportingApplicationTests {

	static final Logger logger = LoggerFactory.getLogger(ReportingApplicationTests.class);
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ReportController controller;

	HttpHeaders headers = new HttpHeaders();

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void subscriberReport() {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/subscriberreport/1"),
				HttpMethod.GET, entity, String.class);

		File file = restTemplate.execute("/subscriberreport/1", HttpMethod.GET, null, clientHttpResponse -> {
			File ret = File.createTempFile("download", "tmp");
			StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
			return ret;
		});

		Assert.notNull(file, "Report is null");
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}

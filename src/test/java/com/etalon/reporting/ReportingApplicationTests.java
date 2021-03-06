package com.etalon.reporting;

import com.etalon.reporting.entity.Subscriber;
import com.etalon.reporting.repository.SubscriberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.etalon.reporting.web.controller.ReportController;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReportingApplicationTests extends BaseTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ReportController controller;

	HttpHeaders headers = new HttpHeaders();

	@Autowired
	protected ReportingApplicationTests(SubscriberRepository subscriberRepository) {
		super(subscriberRepository);
	}

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void subscriberReport() throws IOException {

		Subscriber sut = getTestSubscriber();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		File file = restTemplate.execute("/subscriberreport/" + sut.getId(), HttpMethod.GET, null, clientHttpResponse -> {
			File tempFile = File.createTempFile("download", "tmp");
			StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(tempFile));
			return tempFile;
		});

		List<Boolean> result = checkStringsInPdfFile(Arrays.asList( new String[] {sut.getEmail(), sut.getFirstName(), sut.getLastName()}), file.getAbsolutePath());
		Assert.isTrue(checkGreenResult(result), "No full match");
	}
}

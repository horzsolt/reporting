package com.etalon.reporting.config;

import com.etalon.reporting.repository.ReportRepository;
import com.etalon.reporting.repository.SubscriberRepository;
import com.etalon.reporting.service.ReportService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportingConfig {

    @Bean
    public ReportService reportService(ReportRepository reportRepository, SubscriberRepository subscriberRepository) {
        return new ReportService(reportRepository, subscriberRepository);
    }
}

package com.etalon.reporting.config;

import com.etalon.reporting.entity.SubscriberEntity;
import com.etalon.reporting.repository.ReportRepository;
import com.etalon.reporting.repository.SubscriberRepository;
import com.etalon.reporting.service.ReportService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class ReportingConfig {

    @Bean
    public ReportService reportService(ReportRepository reportRepository, SubscriberRepository subscirberRepository) {
        return new ReportService(reportRepository, subscirberRepository);
    }
}

package com.etalon.reporting.service;

import com.etalon.reporting.repository.ReportRepository;
import com.etalon.reporting.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final SubscriberRepository subscriberRepository;

    public List<JasperPrint> generateAllSubscriberReports() {
        return subscriberRepository.findAllSubscribers().stream()
                .map(subscriber ->
                        reportRepository.getSubscriberReport(subscriber.getId()))
                .collect(Collectors.toList());
    }

    public Optional<JasperPrint> generateSubscriberReport(String id) {
        return subscriberRepository.findById(id).map(subscriber ->
                reportRepository.getSubscriberReport(subscriber.getId()));
    }
}

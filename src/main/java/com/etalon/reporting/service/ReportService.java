package com.etalon.reporting.service;

import com.etalon.reporting.entity.Subscriber;
import com.etalon.reporting.repository.ReportRepository;
import com.etalon.reporting.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final SubscriberRepository subscriberRepository;

    public JasperPrint generateSubscriberReport(String id) {
        Optional<Subscriber> subs = subscriberRepository.findById(id).stream().findFirst();
        Subscriber subscriber = subs.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found"));
        return reportRepository.getSubscriberReport(subscriber.getEmail(), subscriber.getFirstName() + " " + subscriber.getLastName());
    }
}

package com.etalon.reporting.repository;

import com.etalon.reporting.entity.Subscriber;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class ReportRepository {

    @Autowired SubscriberRepository subscriberRepository;

    @SneakyThrows
    public JasperPrint getSubscriberReport(String id) {

        try {

            File file = ResourceUtils.getFile("classpath:SubscriberReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            Map<String, Object> parameters = new HashMap<>();

            Subscriber subscriberEntity =  subscriberRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscriber not found"));

            parameters.put("email", subscriberEntity.getEmail());
            parameters.put("name", subscriberEntity.getFirstName() + " " + subscriberEntity.getLastName());

            return JasperFillManager.fillReport(jasperReport, parameters);

        } catch (Exception e) {
            log.error("getSubscriberReport failed", e);
            throw e;
        }
    }
}

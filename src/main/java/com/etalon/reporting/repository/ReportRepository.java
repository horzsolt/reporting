package com.etalon.reporting.repository;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ReportRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @SneakyThrows
    public JasperPrint getSubscriberReport(String id) {

        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            File file = ResourceUtils.getFile("classpath:SubscriberReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("id", id);

            return JasperFillManager.fillReport(jasperReport, parameters, connection);
        } catch (Exception e) {
            log.error("g1etSubscriberReport failed", e);
            throw e;
        }
    }
}

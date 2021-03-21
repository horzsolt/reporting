package com.etalon.reporting.web.controller;

import com.etalon.reporting.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReportController {

    private final ReportService reportService;

    @RequestMapping(value = "/subscriberreport/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public ResponseEntity<InputStreamResource> subscriberReport(@PathVariable("id") String id, HttpServletResponse response) throws JRException {

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=subscriberreport.pdf");

        JasperPrint report = reportService.generateSubscriberReport(id);

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            JasperExportManager.exportReportToPdfStream(report, outStream);
        } catch (JRException e) {
            log.error("", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found");
        }

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(new ByteArrayInputStream(outStream.toByteArray())));
    }
}

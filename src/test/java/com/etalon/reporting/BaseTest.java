package com.etalon.reporting;

import com.etalon.reporting.entity.Subscriber;
import com.etalon.reporting.repository.SubscriberRepository;
import lombok.extern.slf4j.Slf4j;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseTest {

    @Autowired
    private final SubscriberRepository subscriberRepository;

    protected BaseTest(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    protected Subscriber getTestSubscriber() {

        Subscriber dummy = new Subscriber();
        dummy.setEmail("dummy@dummy.com");
        dummy.setFirstName("Jakab");
        dummy.setLastName("Gipsz");

        return subscriberRepository.findById("1").orElseThrow(() -> new RuntimeException("Test user couldn't be found."));
    }

    protected List<Boolean> checkStringsInPdfFile(List<String> strings, String path) throws IOException {

        PDDocument document = null;

        try {
            File file = new File(path);
            document = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String docuText = pdfStripper.getText(document).toLowerCase(Locale.ROOT);
            return strings.stream().map(stringElement ->
                    docuText.contains(stringElement.toLowerCase(Locale.ROOT))).collect(Collectors.toList());
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                document.close();
            } catch (IOException e) {
                log.warn("", e);
            }
        }
    }

    protected boolean checkGreenResult(List<Boolean> result) {
        return result.stream().allMatch(n -> n);
    }

}

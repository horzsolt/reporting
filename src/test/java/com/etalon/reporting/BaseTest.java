package com.etalon.reporting;

import lombok.extern.slf4j.Slf4j;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
public class BaseTest {

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
                /*error: cannot find symbol
                                log.warn("", e);
                                ^

                 */
            }
        }
    }

    protected boolean checkGreenResult(List<Boolean> result) {
        return result.stream().allMatch(n -> n);
    }

}

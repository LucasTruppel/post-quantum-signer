package br.ufsc.seguranca.signaturecomparator.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SignatureComparatorReport {
    public static void compareSignatures(String message, int[] signatureTimes, String[] algorithms, int[] keySize) {

        List<HashMap<String,Long>> comparasionResults = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            boolean isPostQuantum = (i >= 2);
            comparasionResults.add(SignatureReport.generateReport(isPostQuantum, signatureTimes[i], algorithms[i],
                    message.getBytes(), keySize[i]));
        }

        try {
            SheetCreator.createSheet(comparasionResults, algorithms);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

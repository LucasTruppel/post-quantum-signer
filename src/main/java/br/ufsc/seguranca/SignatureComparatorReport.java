package br.ufsc.seguranca;

import org.openquantumsafe.Common;
import org.openquantumsafe.Sigs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SignatureComparatorReport {
    public static void compareSignatures(String message, int[] signatureTimes) {

        String[] algorithms = {"SHA256withRSA", "SHA256withECDSA", "Dilithium5", "Falcon-1024", "SPHINCS+-SHAKE-256s-simple"};
        List<HashMap<String,Long>> comparasionResults = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            comparasionResults.add(SignatureReport.generateReport(signatureTimes[i], algorithms[i], message.getBytes()));
        }

        try {
            SheetCreator.createSheet(comparasionResults, algorithms);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
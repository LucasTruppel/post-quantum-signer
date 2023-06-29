package br.ufsc.seguranca;

import org.openquantumsafe.Common;
import org.openquantumsafe.Sigs;


public class SignatureComparatorReport {
    public static void compareSignatures(String message, int[] signatureTimes) {

        String[] postQuantumAlgorithms = {"Dilithium5", "Falcon-1024", "SPHINCS+-SHAKE-256s-simple"};
        String[] classicalSignatureAlgorithms = {"SHA256withRSA", "SHA256withECDSA"};

        for (int i = 0; i < 5; i++) {
            if (i < 2) {
                System.out.println(SignatureReport.generateReport(signatureTimes[i], classicalSignatureAlgorithms[i],
                        message.getBytes()));
            } else {
                System.out.println(SignatureReport.generateReport(signatureTimes[i], postQuantumAlgorithms[i-2],
                        message.getBytes()));
            }
        }

    }
}
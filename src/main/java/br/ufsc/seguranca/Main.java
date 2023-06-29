package br.ufsc.seguranca;

import org.openquantumsafe.Common;
import org.openquantumsafe.Sigs;


public class Main {
    public static void main(String[] args) {
        System.out.println("Supported signatures:");
        Common.print_list(Sigs.get_supported_sigs());
        System.out.println();

        System.out.println("Enabled signatures:");
        Common.print_list(Sigs.get_enabled_sigs());
        System.out.println();

        System.out.println("Report:");
        int executionTimes = 10;
        String message = "A message!";

        String[] postQuantumAlgorithms = {"Dilithium5", "Falcon-1024", "SPHINCS+-SHAKE-256s-simple"};
        for (String algorithm : postQuantumAlgorithms) {
            System.out.println(SignatureReport.generateReport(executionTimes, algorithm, message.getBytes()));
        }
        String[] classicalSignatureAlgorithms = {"SHA256withRSA", "SHA256withECDSA"};
        for (String algorithm : classicalSignatureAlgorithms) {
            System.out.println(SignatureReport.generateReport(executionTimes, algorithm, message.getBytes()));
        }

    }
}
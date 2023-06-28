package br.ufsc.seguranca;

import org.openquantumsafe.Common;
import org.openquantumsafe.Sigs;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Supported signatures:");
        Common.print_list(Sigs.get_supported_sigs());
        System.out.println();

        System.out.println("Enabled signatures:");
        Common.print_list(Sigs.get_enabled_sigs());
        System.out.println();

        SignatureReport.generateReport(3, "Dilithium5", "A message!".getBytes());

    }
}
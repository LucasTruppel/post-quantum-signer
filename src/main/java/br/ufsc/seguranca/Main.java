package br.ufsc.seguranca;

import org.openquantumsafe.Common;
import org.openquantumsafe.Signature;
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


            PostQuantumSignature postQuantumSignature1 = new PostQuantumSignature("Dilithium5");
        byte[] message1 = "A message!".getBytes();
        postQuantumSignature1.generateKeyPair();
        byte[] signature1 = postQuantumSignature1.sign(message1);
        postQuantumSignature1.verify(message1, signature1);
        System.out.println(Arrays.toString(postQuantumSignature1.getTimeResult()));

        PostQuantumSignature postQuantumSignature2 = new PostQuantumSignature("Falcon-1024");
        byte[] message2 = "A message!".getBytes();
        postQuantumSignature2.generateKeyPair();
        byte[] signature2 = postQuantumSignature2.sign(message2);
        postQuantumSignature2.verify(message2, signature2);
        System.out.println(Arrays.toString(postQuantumSignature2.getTimeResult()));

        PostQuantumSignature postQuantumSignature3 = new PostQuantumSignature("SPHINCS+-SHAKE-256s-simple");
        byte[] message3 = "A message!".getBytes();
        postQuantumSignature3.generateKeyPair();
        byte[] signature3 = postQuantumSignature3.sign(message3);
        postQuantumSignature3.verify(message3, signature3);
        System.out.println(Arrays.toString(postQuantumSignature3.getTimeResult()));

    }
}
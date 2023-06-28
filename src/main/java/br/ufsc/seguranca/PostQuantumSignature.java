package br.ufsc.seguranca;

import org.openquantumsafe.Common;
import org.openquantumsafe.Signature;
import org.openquantumsafe.Sigs;

public class PostQuantumSignature {

    private final String signatureAlgorithm;
    private final Signature signer;
    private long timeKeyParGenration;
    private byte[] publicKey;
    private byte[] privateKey;

    public PostQuantumSignature(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
        this.signer = new Signature(signatureAlgorithm);
    }

    public void generateKeyPair() {
        long t = System.currentTimeMillis();
        publicKey = signer.generate_keypair();
        timeKeyParGenration = System.currentTimeMillis() - t;
        privateKey = signer.export_secret_key();
    }

    public void sing(byte[] message) {
        // TODO
    }

}



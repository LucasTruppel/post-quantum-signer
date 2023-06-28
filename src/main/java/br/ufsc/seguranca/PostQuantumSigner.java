package br.ufsc.seguranca;

import org.openquantumsafe.Signature;

public class PostQuantumSigner {

    private final String signatureAlgorithm;
    private final Signature signer;
    private long timeKeyParGenration;
    private long timeSignature;
    private long timeVerify;
    private byte[] publicKey;
    private byte[] privateKey;

    public PostQuantumSigner(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
        this.signer = new Signature(signatureAlgorithm);
    }

    public byte[][] generateKeyPair() {
        long time = System.nanoTime();
        publicKey = signer.generate_keypair();
        timeKeyParGenration = System.nanoTime() - time;
        privateKey = signer.export_secret_key();
        return new byte[][]{publicKey, privateKey};
    }

    public byte[] sign(byte[] message) {
        long time = System.nanoTime();
        byte[] signature = signer.sign(message);
        timeSignature = System.nanoTime() - time;
        return signature;
    }

    public boolean verify(byte[] message, byte[] signature) {
        long time = System.nanoTime();
        boolean isValid = signer.verify(message, signature, publicKey);
        timeVerify = System.nanoTime() - time;
        return isValid;
    }

    public long[] getTimeResult() {
        return new long[]{timeKeyParGenration, timeSignature, timeVerify};
    }

    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

}



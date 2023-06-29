package br.ufsc.seguranca.signaturecomparator.signature;

import org.openquantumsafe.Signature;

public class PostQuantumSigner implements Signer {

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

    @Override
    public byte[][] generateKeyPair() {
        long time = System.nanoTime();
        publicKey = signer.generate_keypair();
        timeKeyParGenration = System.nanoTime() - time;
        privateKey = signer.export_secret_key();
        return new byte[][]{publicKey, privateKey};
    }

    @Override
    public byte[] sign(byte[] message) {
        long time = System.nanoTime();
        byte[] signature = signer.sign(message);
        timeSignature = System.nanoTime() - time;
        return signature;
    }

    @Override
    public boolean verify(byte[] message, byte[] signature) {
        long time = System.nanoTime();
        boolean isValid = signer.verify(message, signature, publicKey);
        timeVerify = System.nanoTime() - time;
        return isValid;
    }

    @Override
    public long[] getTimeResult() {
        return new long[]{timeKeyParGenration, timeSignature, timeVerify};
    }

    @Override
    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    @Override
    public byte[] getPublicKey() {
        return publicKey;
    }

    @Override
    public byte[] getPrivateKey() {
        return privateKey;
    }

}

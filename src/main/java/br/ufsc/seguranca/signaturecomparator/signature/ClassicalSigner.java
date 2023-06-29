package br.ufsc.seguranca.signaturecomparator.signature;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;

public class ClassicalSigner implements Signer {

    String signatureAlgorithm;
    KeyPairGenerator keyPairGenerator;
    KeyPair keyPair;
    private long timeKeyParGenration;
    private long timeSignature;
    private long timeVerify;

    public ClassicalSigner(String signatureAlgorithm, int keySize) {
        this.signatureAlgorithm = signatureAlgorithm;
        String keyPairAlgorithm = (signatureAlgorithm.equals("SHA256withECDSA")) ? "EC" : "RSA";
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(keyPairAlgorithm, new BouncyCastleProvider());
            keyPairGenerator.initialize(keySize);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[][] generateKeyPair() {
        long time = System.nanoTime();
        keyPair = keyPairGenerator.generateKeyPair();
        timeKeyParGenration = System.nanoTime() - time;
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();

        return new byte[][]{publicKeyBytes, privateKeyBytes};
    }

    @Override
    public byte[] sign(byte[] message) {
        byte[] signatureBytes;
        try {
            Signature signature = Signature.getInstance(signatureAlgorithm, new BouncyCastleProvider());
            signature.initSign(keyPair.getPrivate());
            signature.update(message);
            long time = System.nanoTime();
            signatureBytes = signature.sign();
            timeSignature = System.nanoTime() - time;
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new RuntimeException(e);
        }
        return signatureBytes;
    }

    @Override
    public boolean verify(byte[] message, byte[] signature) {
        Signature sig = null;
        boolean isValid = false;
        try {
            sig = Signature.getInstance(signatureAlgorithm);
            sig.initVerify(keyPair.getPublic());
            sig.update(message);
            long time = System.nanoTime();
            isValid = sig.verify(signature);
            timeVerify = System.nanoTime() - time;
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new RuntimeException(e);
        }
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
        return keyPair.getPublic().getEncoded();
    }

    @Override
    public byte[] getPrivateKey() {
        return keyPair.getPrivate().getEncoded();
    }

}

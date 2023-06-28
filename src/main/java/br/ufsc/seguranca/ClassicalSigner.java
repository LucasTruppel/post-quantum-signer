package br.ufsc.seguranca;

public class ClassicalSigner implements Signer{

    @Override
    public byte[][] generateKeyPair() {
        return new byte[0][];
    }

    @Override
    public byte[] sign(byte[] message) {
        return new byte[0];
    }

    @Override
    public boolean verify(byte[] message, byte[] signature) {
        return false;
    }

    @Override
    public long[] getTimeResult() {
        return new long[0];
    }

    @Override
    public String getSignatureAlgorithm() {
        return null;
    }

    @Override
    public byte[] getPublicKey() {
        return new byte[0];
    }

    @Override
    public byte[] getPrivateKey() {
        return new byte[0];
    }
}

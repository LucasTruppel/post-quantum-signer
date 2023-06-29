package br.ufsc.seguranca.signaturecomparator.signature;

public interface Signer {

    public byte[][] generateKeyPair();
    public byte[] sign(byte[] message);
    public boolean verify(byte[] message, byte[] signature);
    public long[] getTimeResult();
    public String getSignatureAlgorithm();
    public byte[] getPublicKey();
    public byte[] getPrivateKey();

}

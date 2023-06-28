package br.ufsc.seguranca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class SignatureReport {

    public static void generateReport(int executionTimes, String signatureAlgorithm, byte[] message) {
        List<Long> keyGenTime = new ArrayList<>();
        List<Long> signatureTime = new ArrayList<>();
        List<Long> verifyTime = new ArrayList<>();
        for (int i = 0; i < executionTimes + 1; i ++) {
            PostQuantumSigner postQuantumSigner = new PostQuantumSigner(signatureAlgorithm);
            byte[][] keyPair = postQuantumSigner.generateKeyPair();
            //System.out.println(keyPair[0].length + ", " + keyPair[1].length);
            byte[] signature = postQuantumSigner.sign(message);
            //System.out.println(signature.length);
            postQuantumSigner.verify(message, signature);

            long[] time = postQuantumSigner.getTimeResult();
            keyGenTime.add(time[0]);
            signatureTime.add(time[1]);
            verifyTime.add(time[2]);
        }
        keyGenTime.remove(0);
        System.out.println(keyGenTime);
        float mean = SignatureReport.calculateMean(keyGenTime);
        System.out.println(SignatureReport.calculateMean(keyGenTime));
        System.out.println(SignatureReport.calculateStandardDeviation(keyGenTime, mean));
    }

    private static long calculateMean(List<Long> dataList) {
        float sum = 0;
        for (Long data : dataList) {
            sum += data;
        }
        return (long) (sum/dataList.size());
    }

    private static long calculateStandardDeviation(List<Long> dataList, float mean) {
        float sum = 0;
        for (Long data : dataList) {
            sum += (data - mean) * (data - mean);
        }
        return (long) Math.sqrt(sum/dataList.size());
    }

}

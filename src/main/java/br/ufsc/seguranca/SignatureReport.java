package br.ufsc.seguranca;

import java.util.*;

public class SignatureReport {
    private static final String[] postQuantumAlgorithms = {"Dilithium5", "Falcon-1024", "SPHINCS+-SHAKE-256s-simple"};

    public static HashMap<String, Long> generateReport(int executionTimes, String signatureAlgorithm, byte[] message) {
        List<Long> keyGenTime = new ArrayList<>();
        List<Long> signatureTime = new ArrayList<>();
        List<Long> verifyTime = new ArrayList<>();
        HashMap<String,Long> reportInfo = new HashMap<>();

        boolean isPostQuantum = Arrays.asList(postQuantumAlgorithms).contains(signatureAlgorithm);
        for (int i = 0; i < executionTimes + 1; i ++) {
            Signer signer;
            if (isPostQuantum) {
                signer = new PostQuantumSigner(signatureAlgorithm);
            } else {
                signer = new ClassicalSigner(signatureAlgorithm);
            }
            byte[][] keyPair = signer.generateKeyPair();
            byte[] signature = signer.sign(message);
            signer.verify(message, signature);

            if (i == 0) {
                reportInfo.put("publicKeySize", (long) keyPair[0].length);
                reportInfo.put("privateKeySize", (long) keyPair[1].length);
                reportInfo.put("signatureSize", (long) signature.length);
            } else {
                long[] time = signer.getTimeResult();
                keyGenTime.add(time[0]);
                signatureTime.add(time[1]);
                verifyTime.add(time[2]);
            }
        }

        float meanKeyGenTime = SignatureReport.calculateMean(keyGenTime);
        reportInfo.put("keyGenTimeMean", (long) meanKeyGenTime);
        reportInfo.put("keyGenTimeStandardDeviation",
                SignatureReport.calculateStandardDeviation(keyGenTime, meanKeyGenTime));

        float meanSignatureTime = SignatureReport.calculateMean(signatureTime);
        reportInfo.put("signatureTimeMean", (long) meanSignatureTime);
        reportInfo.put("signatureTimeStandardDeviation",
                SignatureReport.calculateStandardDeviation(signatureTime, meanSignatureTime));

        float meanVerifyTime = SignatureReport.calculateMean(verifyTime);
        reportInfo.put("verifyTimeMean", (long) meanVerifyTime);
        reportInfo.put("verifyTimeStandardDeviation",
                SignatureReport.calculateStandardDeviation(verifyTime, meanVerifyTime));

        return reportInfo;
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

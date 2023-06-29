package br.ufsc.seguranca;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SignerApplication {
    private JButton compareButton;
    private JPanel panelMain;
    private JTextField rsaQuantity;
    private JTextField ecdsaQuantity;
    private JTextField dilithiumQuantity;
    private JTextField falconQuantity;
    private JTextField sphincsQuantity;
    private JTextField messageToSign;
    private JComboBox rsaKeySize;
    private JComboBox ecKeySize;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox rsaAlgorithmBox;
    private JComboBox ecdsaAlgorithmBox;
    private JComboBox dilithiumAlgorithmBox;
    private JComboBox falconAlgorithmBox;
    private JComboBox sphincsAlgorithmBox;

    public SignerApplication() {
    compareButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String message = messageToSign.getText();
            int[] signatureTimes = {Integer.parseInt(rsaQuantity.getText()),
                                    Integer.parseInt(ecdsaQuantity.getText()),
                                    Integer.parseInt(dilithiumQuantity.getText()),
                                    Integer.parseInt(falconQuantity.getText()),
                                    Integer.parseInt(sphincsQuantity.getText())};
            String[] algorithms = {(String) rsaAlgorithmBox.getSelectedItem(),
                    (String) ecdsaAlgorithmBox.getSelectedItem(),
                    (String) dilithiumAlgorithmBox.getSelectedItem(),
                    (String) falconAlgorithmBox.getSelectedItem(),
                    ((String) sphincsAlgorithmBox.getSelectedItem()) + "-simple"};
            int[] keySize = {Integer.parseInt((String) rsaKeySize.getSelectedItem()),
                    Integer.parseInt((String) ecKeySize.getSelectedItem()), 0, 0, 0};
            System.out.println(Arrays.toString(keySize));
            SignatureComparatorReport.compareSignatures(message, signatureTimes, algorithms, keySize);
            JOptionPane.showMessageDialog(null, "Hello");
        }
    });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new SignerApplication().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}

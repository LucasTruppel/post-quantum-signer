package br.ufsc.seguranca;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignerApplication {
    private JButton compareButton;
    private JPanel panelMain;
    private JTextField rsaQuantity;
    private JTextField ecdsaQuantity;
    private JTextField dilithiumQuantity;
    private JTextField falconQuantity;
    private JTextField sphincsQuantity;
    private JTextField messageToSign;

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
            SignatureComparatorReport.compareSignatures(message, signatureTimes);
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

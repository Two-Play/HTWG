package aufgabe8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class Rechner extends JFrame {

    private JPanel panel;
    private JTextField textFieldOperandX;
    private JLabel operandX;
    private JTextField textFieldOperandY;
    private JLabel operandY;
    private JTextField textFieldResultat;
    private JLabel resultat;
    private JRadioButton degRadioButton;
    private JRadioButton radRadioButton;
    private JCheckBox hellesDisplayCheckBox;
    private JButton clearButton;
    private JButton buttonPlus;
    private JButton buttonMal;
    private JButton buttonMinus;
    private JButton buttonGeteilt;
    private JButton sinButton;
    private JButton cosButton;
    private JButton sqrButton;
    private JButton log2Button;

    public Rechner() {

        buttonPlus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (checkInput()){
                    final double x = Double.parseDouble(textFieldOperandX.getText());
                    final double y = Double.parseDouble(textFieldOperandY.getText());

                    double result = x + y;
                    String resultString = String.format(Locale.US, "%.2f", result);
                    textFieldResultat.setText(resultString);
                }
            }
        });

        buttonMal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (checkInput()){
                    final double x = Double.parseDouble(textFieldOperandX.getText());
                    final double y = Double.parseDouble(textFieldOperandY.getText());

                    double result = x * y;
                    String resultString = String.format(Locale.US, "%.2f", result);
                    textFieldResultat.setText(resultString);
                }

            }
        });

        buttonMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (checkInput()){
                    final double x = Double.parseDouble(textFieldOperandX.getText());
                    final double y = Double.parseDouble(textFieldOperandY.getText());

                    double result = x - y;
                    String resultString = String.format(Locale.US, "%.2f", result);
                    textFieldResultat.setText(resultString);
                }
            }
        });

        buttonGeteilt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (checkInput()){
                    final double x = Double.parseDouble(textFieldOperandX.getText());
                    final double y = Double.parseDouble(textFieldOperandY.getText());

                    double result = x / y;
                    String resultString = String.format(Locale.US, "%.2f", result);
                    textFieldResultat.setText(resultString);
                }

            }
        });

        sinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                textFieldOperandY.setText("0");

                if (checkInput()){
                    final double x = Double.parseDouble(textFieldOperandX.getText());

                    double result = 0;

                    if (degRadioButton.isSelected()) {
                        result = Math.sin(Math.toRadians(x));
                    } else {
                        result = Math.sin(x);
                    }
                    String resultString = String.format(Locale.US, "%.2f", result);
                    textFieldResultat.setText(resultString);
                }
            }
        });

        cosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                textFieldOperandY.setText("0");

                if (checkInput()){
                    final double x = Double.parseDouble(textFieldOperandX.getText());

                    double result = 0;

                    if (degRadioButton.isSelected()) {
                        result = Math.cos(Math.toRadians(x));
                    } else {
                        result = Math.cos(x);
                    }
                    String resultString = String.format(Locale.US, "%.2f", result);
                    textFieldResultat.setText(resultString);
                }
            }
        });

        sqrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (checkInput()){
                    final double x = Double.parseDouble(textFieldOperandX.getText());
                    final double y = Double.parseDouble(textFieldOperandY.getText());

                    double result = Math.pow(x, y);
                    String resultString = String.format(Locale.US, "%.2f", result);
                    textFieldResultat.setText(resultString);
                }
            }
        });

        log2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                textFieldOperandY.setText("0");

                if (checkInput()){
                    final double x = Double.parseDouble(textFieldOperandX.getText());

                    double result = Math.log(x) / Math.log(2);
                    String resultString = String.format(Locale.US, "%.2f", result);
                    textFieldResultat.setText(resultString);
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                textFieldOperandX.setText("0");
                textFieldOperandY.setText("0");
                textFieldResultat.setText("0");
            }
        });
        hellesDisplayCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (hellesDisplayCheckBox.isSelected()) {
                    textFieldOperandY.setBackground(java.awt.Color.WHITE);
                    textFieldOperandX.setBackground(java.awt.Color.WHITE);
                    textFieldResultat.setBackground(java.awt.Color.WHITE);

                    textFieldOperandY.setForeground(Color.BLACK);
                    textFieldOperandX.setForeground(Color.BLACK);
                    textFieldResultat.setForeground(Color.BLACK);

                } else {
                    textFieldOperandY.setBackground(Color.BLACK);
                    textFieldOperandX.setBackground(Color.BLACK);
                    textFieldResultat.setBackground(Color.BLACK);

                    textFieldOperandY.setForeground(Color.YELLOW);
                    textFieldOperandX.setForeground(Color.YELLOW);
                    textFieldResultat.setForeground(Color.YELLOW);
                }
            }
        });
    }

    // wenn y oder x leer sind, dann soll eine Fehlermeldung ausgegeben werden
    private boolean checkInput() {
        if (textFieldOperandX.getText().isEmpty() || textFieldOperandY.getText().isEmpty()) {
            System.out.println("Bitte geben Sie Operand X und Y ein!");
            JOptionPane.showMessageDialog(this, "Bitte geben Sie Operand X und Y ein!");
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        Rechner rechnerWindow = new Rechner();
        rechnerWindow.setContentPane(rechnerWindow.panel);
        rechnerWindow.setTitle("Rechner");
        rechnerWindow.setSize(600, 400);
        rechnerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rechnerWindow.setVisible(true);

    }
}

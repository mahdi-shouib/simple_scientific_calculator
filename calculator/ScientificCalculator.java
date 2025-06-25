package com.mycompany.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ScientificCalculator extends JFrame {
    private JTextField firstNumberField, secondNumberField, resultField;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 2));

        firstNumberField = new JTextField();
        secondNumberField = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        topPanel.add(new JLabel("1st:"));
        topPanel.add(firstNumberField);
        topPanel.add(new JLabel("2nd:"));
        topPanel.add(secondNumberField);
        topPanel.add(new JLabel("Result:"));
        topPanel.add(resultField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 5, 5, 5));

        String[] buttons = {
                "x+y", "x-y", "x*y", "x/y", "Clear",
                "x%y", "sin x", "cos x", "tan x", "nCr",
                "x!", "cosec x", "sec x", "cot x", "nPr",
                "√x", "x^2", "x^3", "x^y", "x^-1",
                "e", "e^x", "log x", "ln x", "|x|",
                "π", "x*π", "2*x", "10^x", "Exit"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            try {
                double num1 = firstNumberField.getText().isEmpty() ? 0 : Double.parseDouble(firstNumberField.getText());
                double num2 = secondNumberField.getText().isEmpty() ? 0 : Double.parseDouble(secondNumberField.getText());
                double result = 0;

                switch (command) {
                    case "Clear":
                        firstNumberField.setText("");
                        secondNumberField.setText("");
                        resultField.setText("");
                        return;
                    case "Exit":
                        System.exit(0);
                        break;
                    case "x+y":
                        result = num1 + num2;
                        break;
                    case "x-y":
                        result = num1 - num2;
                        break;
                    case "x*y":
                        result = num1 * num2;
                        break;
                    case "x/y":
                        result = (num2 != 0) ? num1 / num2 : Double.NaN;
                        break;
                    case "x%y":
                        result = num1 % num2;
                        break;
                    case "sin x":
                        result = Math.sin(Math.toRadians(num1));
                        break;
                    case "cos x":
                        result = Math.cos(Math.toRadians(num1));
                        break;
                    case "tan x":
                        result = Math.tan(Math.toRadians(num1));
                        break;
                    case "nCr":
                        result = factorial(num1) / (factorial(num2) * factorial(num1 - num2));
                        break;
                    case "nPr":
                        result = factorial(num1) / factorial(num1 - num2);
                        break;
                    case "x!":
                        result = factorial(num1);
                        break;
                    case "cosec x":
                        result = 1 / Math.sin(Math.toRadians(num1));
                        break;
                    case "sec x":
                        result = 1 / Math.cos(Math.toRadians(num1));
                        break;
                    case "cot x":
                        result = 1 / Math.tan(Math.toRadians(num1));
                        break;
                    case "√x":
                        result = Math.sqrt(num1);
                        break;
                    case "x^2":
                        result = Math.pow(num1, 2);
                        break;
                    case "x^3":
                        result = Math.pow(num1, 3);
                        break;
                    case "x^y":
                        result = Math.pow(num1, num2);
                        break;
                    case "x^-1":
                        result = 1 / num1;
                        break;
                    case "e":
                        result = Math.E;
                        break;
                    case "e^x":
                        result = Math.exp(num1);
                        break;
                    case "log x":
                        result = Math.log10(num1);
                        break;
                    case "ln x":
                        result = Math.log(num1);
                        break;
                    case "|x|":
                        result = Math.abs(num1);
                        break;
                    case "π":
                        result = Math.PI;
                        break;
                    case "x*π":
                        result = num1 * Math.PI;
                        break;
                    case "2*x":
                        result = 2 * num1;
                        break;
                    case "10^x":
                        result = Math.pow(10, num1);
                        break;
                    default:
                        resultField.setText("Invalid Operation");
                        return;
                }

                resultField.setText(Double.isNaN(result) ? "Error" : round(result, 14) + "");
            } catch (Exception ex) {
                resultField.setText("Error");
            }
        }
    }

    private double factorial(double num) {
        if (num < 0) return Double.NaN;
        double result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

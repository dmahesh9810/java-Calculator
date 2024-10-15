import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorUI extends JFrame implements ActionListener {

    private JTextField textField;
    private JButton[] numberButtons = new JButton[10];
    private JButton addButton, subButton, mulButton, divButton, equButton, clrButton;
    private JPanel panel;

    private double num1 = 0, num2 = 0;
    private char operator;

    public class SimpleCalculator implements CalculatorOperations {

        @Override
        public double add(double a, double b) {
            return a + b;
        }

        @Override
        public double subtract(double a, double b) {
            return a - b;
        }

        @Override
        public double multiply(double a, double b) {
            return a * b;
        }

        @Override
        public double divide(double a, double b) {
            if (b == 0) {
                throw new ArithmeticException("Division by zero is not allowed.");
            }
            return a / b;
        }
    }

    SimpleCalculator calculator = new SimpleCalculator();

    public CalculatorUI() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(270, 350);
        setLayout(null);

        textField = new JTextField();
        textField.setBounds(30, 5, 200, 40);
        textField.setFont(new Font("Arial", Font.PLAIN, 25));
        textField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        textField.setHorizontalAlignment(JTextField.RIGHT);
        add(textField);

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        equButton = new JButton("=");
        clrButton = new JButton("C");

        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        equButton.addActionListener(this);
        clrButton.addActionListener(this);

        panel = new JPanel();
        panel.setBounds(30, 80, 200, 200);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(clrButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        add(panel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == clrButton) {
            textField.setText("");
        }

        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }

        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }

        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }

        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+':
                    textField.setText(String.valueOf(calculator.add(num1, num2)));
                    break;
                case '-':
                    textField.setText(String.valueOf(calculator.subtract(num1, num2)));
                    break;
                case '*':
                    textField.setText(String.valueOf(calculator.multiply(num1, num2)));
                    break;
                case '/':
                    try {
                        textField.setText(String.valueOf(calculator.divide(num1, num2)));
                    } catch (ArithmeticException ex) {
                        textField.setText("Error");
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new CalculatorUI();
    }
}

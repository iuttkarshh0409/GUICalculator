import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuiCalculator extends JFrame implements ActionListener {
    JTextField display;
    String input = "";

    public GuiCalculator() {
        setTitle("GUI Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        // Updated labels for buttons
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "x²", "√", "1/x", "AC"
        };

        for (String b : buttons) {
            JButton button = new JButton(b);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "AC":
                input = "";
                display.setText("");
                break;
            case "=":
                try {
                    double result = evaluateExpression(input);
                    display.setText(String.valueOf(result));
                    input = String.valueOf(result);
                } catch (Exception ex) {
                    display.setText("Error");
                    input = "";
                }
                break;
            case "x²":
                try {
                    double val = Double.parseDouble(input);
                    double result = Math.pow(val, 2);
                    display.setText(String.valueOf(result));
                    input = String.valueOf(result);
                } catch (Exception ex) {
                    display.setText("Error");
                    input = "";
                }
                break;
            case "√":
                try {
                    double val = Double.parseDouble(input);
                    double result = Math.sqrt(val);
                    display.setText(String.valueOf(result));
                    input = String.valueOf(result);
                } catch (Exception ex) {
                    display.setText("Error");
                    input = "";
                }
                break;
            case "1/x":
                try {
                    double val = Double.parseDouble(input);
                    double result = 1 / val;
                    display.setText(String.valueOf(result));
                    input = String.valueOf(result);
                } catch (Exception ex) {
                    display.setText("Error");
                    input = "";
                }
                break;
            default:
                input += command;
                display.setText(input);
        }
    }

    private double evaluateExpression(String expr) {
        double result = 0;
        if (expr.contains("+")) {
            String[] parts = expr.split("\\+");
            result = Double.parseDouble(parts[0]) + Double.parseDouble(parts[1]);
        } else if (expr.contains("-")) {
            String[] parts = expr.split("-");
            result = Double.parseDouble(parts[0]) - Double.parseDouble(parts[1]);
        } else if (expr.contains("*")) {
            String[] parts = expr.split("\\*");
            result = Double.parseDouble(parts[0]) * Double.parseDouble(parts[1]);
        } else if (expr.contains("/")) {
            String[] parts = expr.split("/");
            result = Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);
        } else {
            result = Double.parseDouble(expr);
        }
        return result;
    }

    public static void main(String[] args) {
        new GuiCalculator();
    }
}

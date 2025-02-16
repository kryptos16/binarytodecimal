import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class binaryto {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppGUI::new);
    }
}

class AppGUI {
    private JFrame frame;
    private JTextField binaryInput;
    private JLabel decimalOutput;
    private DefaultListModel<String> conversionModel;
    private List<String> conversions;

    public AppGUI() {
        frame = new JFrame("Binary to Decimal Converter");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        binaryInput = new JTextField(10);
        JButton convertButton = new JButton("Convert");
        decimalOutput = new JLabel("Decimal: ");
        
        convertButton.addActionListener(e -> convertBinary());
        topPanel.add(new JLabel("Binary:"));
        topPanel.add(binaryInput);
        topPanel.add(convertButton);
        topPanel.add(decimalOutput);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());
        conversionModel = new DefaultListModel<>();
        JList<String> conversionList = new JList<>(conversionModel);
        JScrollPane scrollPane = new JScrollPane(conversionList);

        JButton addConversion = new JButton("Add Conversion");
        JButton editConversion = new JButton("Edit Selected");
        JButton removeConversion = new JButton("Remove Selected");

        addConversion.addActionListener(e -> addConversion());
        editConversion.addActionListener(e -> editConversion(conversionList));
        removeConversion.addActionListener(e -> removeConversion(conversionList));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addConversion);
        buttonPanel.add(editConversion);
        buttonPanel.add(removeConversion);

        middlePanel.add(scrollPane, BorderLayout.CENTER);
        middlePanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);

        conversions = new ArrayList<>();
        frame.setVisible(true);
    }

    private void convertBinary() {
        String binaryStr = binaryInput.getText();
        try {
            int decimal = Integer.parseInt(binaryStr, 2);
            decimalOutput.setText("Decimal: " + decimal);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid binary number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addConversion() {
        String binaryStr = binaryInput.getText();
        try {
            int decimal = Integer.parseInt(binaryStr, 2);
            String conversion = binaryStr + " = " + decimal;
            conversions.add(conversion);
            conversionModel.addElement(conversion);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid binary number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editConversion(JList<String> conversionList) {
        int selectedIndex = conversionList.getSelectedIndex();
        if (selectedIndex != -1) {
            String newBinary = JOptionPane.showInputDialog(frame, "Edit binary number:", conversions.get(selectedIndex).split(" = ")[0]);
            if (newBinary != null && !newBinary.trim().isEmpty()) {
                try {
                    int decimal = Integer.parseInt(newBinary, 2);
                    String updatedConversion = newBinary + " = " + decimal;
                    conversions.set(selectedIndex, updatedConversion);
                    conversionModel.set(selectedIndex, updatedConversion);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Invalid binary number!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void removeConversion(JList<String> conversionList) {
        int selectedIndex = conversionList.getSelectedIndex();
        if (selectedIndex != -1) {
            conversions.remove(selectedIndex);
            conversionModel.remove(selectedIndex);
        }
    }
}

package rs.dragan.ministore.ui;

import rs.dragan.ministore.model.Product;

import javax.swing.*;
import java.awt.*;

public class ProductDialog extends JDialog {

    private JTextField idField;
    private JTextField nameField;
    private JTextField priceField;
    private Product product;

    public ProductDialog(JFrame parent) {
        super(parent, "Dodaj proizvod", true);

        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Naziv:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Cena:"));
        priceField = new JTextField();
        add(priceField);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Otkaži");

        okButton.addActionListener(e -> onOK());
        cancelButton.addActionListener(e -> dispose());

        add(okButton);
        add(cancelButton);
    }

    private void onOK() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());

            if (name.isBlank()) {
                JOptionPane.showMessageDialog(this, "Naziv ne sme biti prazan!");
                return;
            }

            product = new Product(id, name, price);
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID i cena moraju biti brojevi!");
        }
    }

    public Product getProduct() {
        return product;
    }
}


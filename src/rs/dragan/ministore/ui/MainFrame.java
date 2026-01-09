package rs.dragan.ministore.ui;

import rs.dragan.ministore.model.Product;
import rs.dragan.ministore.service.Store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {

    private Store store;
    private JTable table;
    private DefaultTableModel tableModel;

    public MainFrame() {
        store = new Store();

        setTitle("MiniStore");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
        loadProducts();
    }

    private void initUI() {
        tableModel = new DefaultTableModel(new Object[]{"ID", "Naziv", "Cena"}, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        JButton addButton = new JButton("Dodaj");
        JButton deleteButton = new JButton("Obriši");
        JButton refreshButton = new JButton("Osveži");

        addButton.addActionListener(e -> addProduct());
        deleteButton.addActionListener(e -> deleteProduct());
        refreshButton.addActionListener(e -> loadProducts());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(refreshButton);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void loadProducts() {
        tableModel.setRowCount(0); // obriši tabelu

        for (Product p : store.getAllProducts()) {
            tableModel.addRow(new Object[]{
                    p.getId(),
                    p.getName(),
                    p.getPrice()
            });
        }
    }

    private void addProduct() {
        ProductDialog dialog = new ProductDialog(this);
        dialog.setVisible(true);

        Product p = dialog.getProduct();
        if (p != null) {
            store.addProduct(p);
            loadProducts();
        }
    }

    private void deleteProduct() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Moraš izabrati proizvod!");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        store.deleteById(id);
        loadProducts();
    }
}

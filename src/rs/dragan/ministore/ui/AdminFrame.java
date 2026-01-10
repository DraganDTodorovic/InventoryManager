package rs.dragan.ministore.ui;

import rs.dragan.ministore.model.User;
import rs.dragan.ministore.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminFrame extends JFrame {

    private UserService userService = new UserService();
    private JTable table;
    private DefaultTableModel model;

    public AdminFrame() {
        setTitle("Admin Panel - Users");
        setSize(400, 300);
        setLocationRelativeTo(null);

        initUI();
        loadUsers();
    }

    private void initUI() {
        model = new DefaultTableModel(new Object[]{"Username"}, 0);
        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);

        JButton addButton = new JButton("Add User");
        addButton.addActionListener(e -> addUser());

        JPanel bottom = new JPanel();
        bottom.add(addButton);

        add(scroll, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private void loadUsers() {
        model.setRowCount(0);

        for (User u : userService.getAllUsers()) {
            model.addRow(new Object[]{u.getUsername()});
        }
    }

    private void addUser() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        Object[] message = {
                "Username:", usernameField,
                "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add user", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String u = usernameField.getText();
            String p = new String(passwordField.getPassword());

            if (u.isBlank() || p.isBlank()) {
                JOptionPane.showMessageDialog(this, "Polja ne smeju biti prazna!");
                return;
            }

            boolean ok = userService.addUser(u, p);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Korisnik dodat!");
                loadUsers();
            } else {
                JOptionPane.showMessageDialog(this, "Korisnik već postoji!");
            }
        }
    }
}


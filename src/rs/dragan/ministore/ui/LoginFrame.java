package rs.dragan.ministore.ui;

import rs.dragan.ministore.service.UserService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserService userService = new UserService();

    public LoginFrame() {
        setTitle("Uloguj se");
        setSize(300, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Korisničko ime:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Lozinka:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        loginButton.addActionListener(e -> login());
        exitButton.addActionListener(e -> System.exit(0));

        add(loginButton);
        add(exitButton);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (userService.login(username, password)) {
            JOptionPane.showMessageDialog(this, "Uspešan login!");
            dispose(); // zatvori login
            new MainFrame().setVisible(true); // otvori aplikaciju
        } else {
            JOptionPane.showMessageDialog(this, "Pogrešan username ili password!");
        }
    }
}

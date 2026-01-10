package rs.dragan.ministore.service;

import rs.dragan.ministore.model.Role;
import rs.dragan.ministore.model.User;

import java.io.*;
import java.util.ArrayList;

public class UserService {

    private static final String FILE_NAME = "users.dat";
    private ArrayList<User> users;

    public UserService() {
        load();
        if (users.isEmpty()) {
            // default admin
            users.add(new User("admin", "admin", Role.ADMIN));

        }
    }

    private void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            users = new ArrayList<>();
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            users = (ArrayList<User>) in.readObject();
        } catch (Exception e) {
            users = new ArrayList<>();
            e.printStackTrace();
        }
    }

    public User login(String username, String password) {

        String uInput = username.trim().toLowerCase();

        for (User u : users) {
            if (u.getUsername().trim().toLowerCase().equals(uInput)
                    && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }



    public ArrayList<User> getAllUsers() {
        return users;
    }

    public boolean addUser(String username, String password, Role role) {

        String newUsername = username.trim().toLowerCase();

        for (User u : users) {
            String existing = u.getUsername().trim().toLowerCase();

            if (existing.equals(newUsername)) {
                return false; // već postoji
            }
        }

        users.add(new User(username.trim(), password, role));
        save();
        return true;
    }


}


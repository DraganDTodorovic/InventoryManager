package rs.dragan.ministore.service;

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
            users.add(new User("admin", "admin"));
            save();
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

    public boolean login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}


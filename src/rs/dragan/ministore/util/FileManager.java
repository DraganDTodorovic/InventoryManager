package rs.dragan.ministore.util;

import rs.dragan.ministore.model.Product;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static final String FILE_NAME = "products.dat";

    // Snimanje liste u fajl
    public static void save(ArrayList<Product> products) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(products);
        } catch (IOException e) {
            System.out.println("Greška pri snimanju fajla!");
            e.printStackTrace();
        }
    }

    // Učitavanje liste iz fajla
    public static ArrayList<Product> load() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Product>) in.readObject();
        } catch (Exception e) {
            System.out.println("Greška pri učitavanju fajla!");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}


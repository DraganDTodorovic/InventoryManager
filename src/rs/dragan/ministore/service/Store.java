package rs.dragan.ministore.service;

import rs.dragan.ministore.model.Product;
import rs.dragan.ministore.util.FileManager;

import java.util.ArrayList;

public class Store {

    private ArrayList<Product> products;

    public Store() {
        products = FileManager.load();  // učitavanje iz fajla pri startu
    }

    public void addProduct(Product product) {
        products.add(product);
        FileManager.save(products);
        System.out.println("Proizvod dodat i snimljen!");
    }

    public void listProducts() {
        if (products.isEmpty()) {
            System.out.println("Nema proizvoda u prodavnici.");
            return;
        }

        for (Product p : products) {
            System.out.println(p);
        }
    }

    public void searchByName(String name) {
        boolean found = false;

        for (Product p : products) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(p);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Nijedan proizvod nije pronađen.");
        }
    }

    public void deleteById(int id) {
        Product toRemove = null;

        for (Product p : products) {
            if (p.getId() == id) {
                toRemove = p;
                break;
            }
        }

        if (toRemove != null) {
            products.remove(toRemove);
            FileManager.save(products);
            System.out.println("Proizvod obrisan i snimljen.");
        } else {
            System.out.println("Proizvod sa tim ID-jem ne postoji.");
        }
    }

    public ArrayList<Product> getAllProducts() {
        return products;
    }
}

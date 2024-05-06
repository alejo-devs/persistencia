package com.mycompany.persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class Stock {

    private ArrayList<Products> products = new ArrayList<>();
    private final String JSON_FILE = "products.json";

    public Stock() {
        loadProductsFromJSON();
    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }

    public void loadProductsFromJSON() {
        try (Reader reader = new FileReader(JSON_FILE)) {
            JSONParser parser = new JSONParser();
            JSONArray productsArray = (JSONArray) parser.parse(reader);
            for (Object obj : productsArray) {
                JSONObject productJson = (JSONObject) obj;
                String name = (String) productJson.get("name");
                double price = (double) productJson.get("price");
                int amount = ((Long) productJson.get("amount")).intValue();
                Products product = new Products(price, name, amount);
                products.add(product);
            }
            System.out.println("Products loaded from JSON file.");
        } catch (IOException | ParseException e) {
            System.err.println("Error loading products from JSON: " + e.getMessage());
        }
    }

    public void saveProductsToJSON() {
        JSONArray productsArray = new JSONArray();
        for (Products product : products) {
            JSONObject productJson = new JSONObject();
            productJson.put("name", product.getName());
            productJson.put("price", product.getPrice());
            productJson.put("amount", product.getAmount());
            productsArray.add(productJson);
        }
        try (FileWriter file = new FileWriter(JSON_FILE)) {
            file.write(productsArray.toJSONString());
            System.out.println("Products saved to JSON file.");
        } catch (IOException e) {
            System.err.println("Error saving products to JSON: " + e.getMessage());
        }
    }

    public void showProducts() {
        System.out.println("Product\t\tAmount\t\tPrice");
        for (Products product : products) {
            System.out.printf("%-15s %-10d %-10.2f\n", product.getName(), product.getAmount(), product.getPrice());
        }
    }

    public void addAmount(Scanner scan) {
        String productName = getInputString(scan, "Enter the name of the Product");
        Products product = findProduct(productName);
        if (product != null) {
            int units = getInputInt(scan, "Enter the number of units");
            if (units > 0) {
                product.setAmount(product.getAmount() + units);
            } else {
                System.out.println("Invalid amount.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    public void removeAmount(Scanner scan) {
        String productName = getInputString(scan, "Enter the name of the Product");
        Products product = findProduct(productName);
        if (product != null) {
            int units = getInputInt(scan, "Enter the number of units");
            if (units > 0 && units <= product.getAmount()) {
                product.setAmount(product.getAmount() - units);
            } else {
                System.out.println("Invalid quantity or not enough quantity in inventory.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    public void delete(Scanner scan) {
        String productName = getInputString(scan, "Enter the name of the Product to delete");
        Products product = findProduct(productName);
        if (product != null) {
            products.remove(product);
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void filter(Scanner scan) {
        String productName = getInputString(scan, "Enter the name of the Product to filter");
        Products product = findProduct(productName);
        if (product != null) {
            System.out.println("Product\t\tAmount\t\tPrice");
            System.out.printf("%-15s %-10d %-10.2f\n", product.getName(), product.getAmount(), product.getPrice());
        } else {
            System.out.println("Product not found.");
        }
    }

    public void options(Scanner scan) {
        int option;
        System.out.println("Welcome");
        do {
            System.out.println("Type the option you want to make");
            System.out.println("1. View inventory");
            System.out.println("2. Add units");
            System.out.println("3. Remove units");
            System.out.println("4. Delete Product");
            System.out.println("5. Filter product");
            System.out.println("6. Exit");
            option = getInputInt(scan, "");
            switch (option) {
                case 1 -> showProducts();
                case 2 -> addAmount(scan);
                case 3 -> removeAmount(scan);
                case 4 -> delete(scan);
                case 5 -> filter(scan);
                case 6 -> saveProductsToJSON();
                default -> System.out.println("Please enter a valid option");
            }
            System.out.println("---------------------------------------");
        } while (option != 6);
    }

    private String getInputString(Scanner scan, String prompt) {
        System.out.println(prompt);
        return scan.nextLine();
    }

    private int getInputInt(Scanner scan, String prompt) {
        int value = 0;
        boolean valid = false;
        do {
            try {
                System.out.println(prompt);
                value = scan.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                scan.next();
            }
        } while (!valid);
        return value;
    }

    private Products findProduct(String productName) {
        for (Products product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }
}



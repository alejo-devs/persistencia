package com.mycompany.persistencia;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class json {

    private static final String FILE_PATH = "products.json";
    private static final Gson gson = new Gson();

    public static void saveProducts(ArrayList<Products> products) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(products, writer);
        } catch (IOException e) {
        }
    }

    public static ArrayList<Products> loadProducts() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<ArrayList<Products>>() {
            }.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}



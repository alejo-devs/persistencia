
package com.mycompany.persistencia;
/*import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;*/
import java.util.Scanner;
/*
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
*/
        
 
public class Persistencia {

    public static void main(String[] args) {
        Stock stock = new Stock();
       Scanner scanner = new Scanner(System.in);
        stock.options(scanner);
        stock.saveProductsToJSON();
    }
}

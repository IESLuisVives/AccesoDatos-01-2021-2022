package es.joseluisgs.dam.utils;

import es.joseluisgs.dam.model.Product;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Util {

    public static List<Product> getProducts() {
        // Cargamos el archivo ubicado en la carpeta resources.
        Path path = Paths.get("data/products.csv");
        try {
            final List<String> lines = Files.readAllLines(Paths.get("data/products.csv"), StandardCharsets.UTF_8);
            // lines.forEach(System.out::println);
            List<Product>  products = new ArrayList<>();
            for(int i=1; i<lines.size(); i++) {
                // System.out.println("Proceso la linea: " + (i-1));
                StringTokenizer tokenizer = new StringTokenizer(lines.get(i) , ",");
                Product product = new Product();
                product.setId(Integer.parseInt(tokenizer.nextToken()));
                product.setName(tokenizer.nextToken());
                product.setSupplier(Integer.parseInt(tokenizer.nextToken()));
                product.setCategory(Integer.parseInt(tokenizer.nextToken()));
                tokenizer.nextToken(); //saltamos la columna quantityPerUnit
                product.setUnitPrice(Double.parseDouble(tokenizer.nextToken()));
                product.setUnitsInStock(Integer.parseInt(tokenizer.nextToken()));
                // System.out.println(product.toString());
                products.add(product);
            }
            // products.forEach(System.out::println);
            return products;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

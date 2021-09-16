package es.joseluisgs.dam;

import es.joseluisgs.dam.model.Product;
import es.joseluisgs.dam.utils.Util;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public List<String> sorted(){
        List<Product> products = Util.getProducts();
        Stream<String> streams = products.stream().map(Product::getName);
        return streams.collect(Collectors.toList());
    }
    public static void main(String[] args)  {
//        Main main = new Main();
//        main.sorted().forEach(System.out::println);

        // Cargamos la lista de productos
        List<Product> products = Util.getProducts();
        // Creamos el flujo
        System.out.println("Todos los productos");
        Stream<Product> s1 = products.stream(); //flujo de elementos Product
        s1.forEach(product -> System.out.println(product)); // imprime la lista de productos
        // productStream.forEach(System.out::println); esta linea es equivalente a la anterior
        System.out.println();

        // Consultas Simpples
        // select name from products
        System.out.println("Nombre de los productos");
        Stream<String> s2 = products.stream().map(Product::getName);
        s2.forEach(System.out::println);
        System.out.println();

        // Filtrado
        //   select name from products where units_in_stock < 10
        System.out.println("Nombre de los productos con unidades en stock mayor de 10");
        Stream<String> s3 = products.stream().filter(p -> p.getUnitsInStock()<10).map(Product::getName);
        s3.forEach(product -> System.out.println(product)); //imprime el resultado en consola
        System.out.println();
    }

}
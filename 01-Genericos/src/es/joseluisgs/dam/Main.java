package es.joseluisgs.dam;

import es.joseluisgs.dam.tdas.Cola;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("GENERICOS");

        Persona p1 = new Persona(20, "Pepe");
        Persona p2 = new Persona(18, "Ana");
        Persona p3 = new Persona(25, "Luis");
        Persona p4 = new Persona(30, "Vives");

        Cola<Persona> cola = new Cola<>();
        cola.encolar(p1);
        cola.encolar(p2);
        cola.encolar(p3);
        cola.encolar(p4);

        for (Persona p: cola) {
            System.out.println(p);
        }

        System.out.println("COMPARABLE");
        List<Persona> lista = new ArrayList<>();
        lista.add(p1);
        lista.add(p2);
        lista.add(p3);
        lista.add(p4);

        Collections.sort(lista);
        for (Persona p: lista) {
            System.out.println(p);
        }

        System.out.println("COMPARATOR");
        PersonaNombreComparator pComparator = new PersonaNombreComparator();
        Collections.sort(lista, pComparator);

        for (Persona p: lista) {
            System.out.println(p);
        }

        // Con Funcional
        System.out.println("COMPARATOR2");
        Comparator<Persona> viejuno = (v1, v2) -> Integer.compare(v2.edad, v1.edad);
        Collections.sort(lista, viejuno);

        for (Persona p: lista) {
            System.out.println(p);
        }
    }
}

package es.joseluisgs.dam;

import es.joseluisgs.dam.model.Cancion;

import java.util.*;
import java.util.stream.Collectors;

public class ConsultasCanciones {
    List<Cancion> canciones;

    public ConsultasCanciones() {
        // Cargamos la lista de productos
        canciones = new ArrayList<>(
                Arrays.asList(
                        new Cancion("Te Equivocas", "Jon Carlo"),
                        new Cancion("Solo tu", "Orlando Elizalde"),
                        new Cancion("La Mano de Dios", "Jon Carlo"),
                        new Cancion("Como una hoja de papel", "Evelyn Vasquez"),
                        new Cancion("Siempre te amare", "Darwin Lechler"),
                        new Cancion("Brilla", "Darwin Lechler"),
                        new Cancion("Sin amor", "Carlos & Carito"),
                        new Cancion("Te amare", "Joan Sanchez"),
                        new Cancion("Cuanto me ama", "Orlando Elizalde")
                )
        );
        procesarStreams();
    }

    private void procesarStreams() {
        filtradoDatos();

        filtradoStream();

        realizandoTranformacion();

        cancionesPorCantante();

        quitandoDuplicados();

        contandoElementos();

        agrupandoPorCantante();


    }

    private void filtradoDatos() {
        // Filtrando elementos forma tradicional
        for (Cancion cancion : filtrarCantante(canciones, "Jon Carlo")) {
            System.out.println("Tradicional: " + cancion);
        }
    }

    private void filtradoStream() {
        // Filtrando elementos con Stream
        List<Cancion> listadoCanciones = filtrarCantanteStream(canciones, "Jon Carlo");
        listadoCanciones.forEach(c -> System.out.println("Stream: " + c));
    }

    private void cancionesPorCantante() {
        List<String> listadoTitulos = obtenerCancionesPorCantante(canciones, "Jon Carlo");
    }


    public List<Cancion> filtrarCantante(List<Cancion> canciones, String cantante) {
        List<Cancion> listaFiltrada = new LinkedList<>();
        for (Cancion cancion : canciones) {
            if (cancion.getCantante().equals(cantante))
                listaFiltrada.add(cancion);
        }
        return listaFiltrada;
    }

    private void realizandoTranformacion() {
        List<Cancion> listadoCanciones;

        listadoCanciones = filtrarCantanteMayuscula(canciones, "Jon Carlo");
        listadoCanciones.forEach(c -> System.out.println("Stream Map: " + c));
    }

    public List<Cancion> filtrarCantanteStream(List<Cancion> canciones, String cantante) {
        return canciones.stream()
                .filter(c -> c.getCantante().equals(cantante))
                .collect(Collectors.toList());
    }

    public List<Cancion> filtrarCantanteMayuscula(List<Cancion> canciones, String cantante) {
        return canciones.stream()
                .filter(c -> c.getCantante().equals(cantante))
                .map(c -> {
                            c.setCantante(c.getCantante().toUpperCase());
                            return c;
                        }
                )
                .collect(Collectors.toList());
    }

    public List<String> obtenerCancionesPorCantante(List<Cancion> canciones, String cantante) {
        return canciones.stream()
                .filter(c -> c.getCantante().equals(cantante))
                .map(c -> c.getTitulo().toUpperCase())
                .collect(Collectors.toList());
    }

    private void quitandoDuplicados() {
        //Esta canción ya existe pero la agregaremos nuevamente
        canciones.add(new Cancion("Siempre te amare", "Darwin Lechler"));
        // aplicamos el distinct
        canciones.stream().distinct().collect(Collectors.toList()).forEach(System.out::println);
    }

    private void contandoElementos() {
        // Contando elementos
        long count = canciones.stream()
                .filter(c -> c.getCantante().equalsIgnoreCase("Jon Carlo"))
                .count();
        System.out.println("Jon Carlo aparece: " + count + " veces");
    }

    private void agrupandoPorCantante() {
        Map<String, Long> counted = canciones.stream().collect(
                Collectors.groupingBy(
                        c -> c.getCantante(), Collectors.counting()
                )
        );
        System.out.println(counted);
    }
}

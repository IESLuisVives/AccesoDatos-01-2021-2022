package es.joseluisgs.dam.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import model.RegistroDatos;
import model.TemperaturaHora;
import util.Temperatura;

public class ServicioClimatologia {

	List<RegistroDatos> datos;

	public ServicioClimatologia(List<RegistroDatos> datos) {
		this.datos = datos;
	}

	/*
	 * M�todo que devuelve las temperaturas m�ximas agrupadas por provincia
	 */
	public Map<String, Optional<TemperaturaHora>> temperaturaMaximaProvincias() {
		return temperaturaMaxOMinProvincias(Temperatura.MAXIMA);
	}

	/*
	 * M�todo que devuelve las temperaturas m�nimas agrupadas por provincia
	 */

	public Map<String, Optional<TemperaturaHora>> temperaturaMinimaProvincias() {
		return temperaturaMaxOMinProvincias(Temperatura.MINIMA);
	}

	/*
	 * M�todo auxiliar, que utilizan las dos anteriores, que sirva para realizar
	 * el agrupamiento, seg�n si es escoge m�ximo o m�nimo.
	 */
	private Map<String, Optional<TemperaturaHora>> temperaturaMaxOMinProvincias(Temperatura tipo) {
		// Utilizamos el operador ternario para construir el collector.
		Collector<RegistroDatos, ?, Optional<TemperaturaHora>> c = (tipo == Temperatura.MAXIMA)
				? Collectors.mapping(RegistroDatos::getMaxima,
						Collectors.maxBy((m1, m2) -> Float.compare(m1.getTemperatura(), m2.getTemperatura())))
				: Collectors.mapping(RegistroDatos::getMinima,
						Collectors.minBy((m1, m2) -> Float.compare(m1.getTemperatura(), m2.getTemperatura())));
		// Usamos el colector para agrupar		
		return datos.stream().collect(Collectors.groupingBy(RegistroDatos::getProvincia, c));
	}

	/*
	 * M�todo que calcula las temperaturas medias agrupadas por provincia
	 */
	public Map<String, Double> temperaturaMediaProvincias() {

		
		return datos.stream()
				.collect(Collectors.toMap(Function.identity(),
						(RegistroDatos r) -> (r.getMaxima().getTemperatura() + r.getMinima().getTemperatura()) / 2))
				.entrySet().stream()
				.collect(Collectors.groupingBy((Map.Entry<RegistroDatos, Float> r) -> r.getKey().getProvincia(),
						TreeMap::new,
						Collectors.averagingDouble(((Map.Entry<RegistroDatos, Float> r) -> r.getValue()))));

	}

	/*
	 * M�todo que filtra los datos para obtener los de una provincia en particular
	 */
	public List<RegistroDatos> datosProvincia(String provincia) {

		return datos.stream().filter(p -> p.getProvincia().equalsIgnoreCase(provincia)).collect(Collectors.toList());
	}

	/*
	 * M�todo que obtiene la temperatura m�xima total
	 */
	public Optional<RegistroDatos> maximaTotal() {

		return datos.stream()
				.max((r1, r2) -> Float.compare(r1.getMaxima().getTemperatura(), r2.getMaxima().getTemperatura()));

	}

	/*
	 * M�todo que obtiene la temperatura m�nima total
	 */
	public Optional<RegistroDatos> minimaTotal() {

		return datos.stream()
				.min((r1, r2) -> Float.compare(r1.getMinima().getTemperatura(), r2.getMinima().getTemperatura()));

	}

}

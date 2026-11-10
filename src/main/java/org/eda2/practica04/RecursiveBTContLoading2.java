package org.eda2.practica04;

import java.util.ArrayList;
import java.util.TreeMap;

public class RecursiveBTContLoading2 {
	
	/** 
	 * Mapa con clave peso total y valor un ArrayList de contenedores cargados en el buque.
	 * Almacena todas las soluciones óptimas encontradas.
	 * La mejor solución será la que tenga el mayor peso, es decir, la entrada con la clave más alta.
	 */
	private static TreeMap<Integer, ArrayList<Contenedor>> sol = new TreeMap<>(); 
	

	/**
	 * Método recursivo de Backtracking para encontrar la mejor manera de cargar contenedores en un buque.
	 * 
	 * @param lvl Nivel actual en la recursión, que representa el índice del contenedor en la lista.
	 * @param remainingWeight Peso restante que puede ser cargado en el buque.
	 * @param pact Peso actual acumulado de los contenedores cargados.
	 * @param voa Mejor peso encontrado hasta ahora (valor óptimo actual).
	 * @param b El objeto Buque que contiene la capacidad y los contenedores.
	 * @param contLoaded Lista de contenedores que se han cargado hasta el momento.
	 */
	
	public static void btRec02(int lvl, int remainingWeight, int pact, int voa, Buque b, ArrayList<Contenedor> contLoaded) {
		if(contLoaded == null) contLoaded = new ArrayList<>();
		if(pact + remainingWeight <= voa) return; //no hay mas mejora
		
		if(lvl > b.getContenedores().size() - 1) { //verifica si se ha alcanzado el final de la lista, empezando en el 0 (1 iter no queda aqui)
			if(pact > voa) { 
				voa = pact; //se llega a la solucion optima = peso actual (PARA CADA SOLUCION, HAY MUCHAS LLAMADAS)
	            ArrayList<Contenedor> contLoadedCopy = new ArrayList<>(contLoaded);
				sol.put(voa, contLoadedCopy);
			}
			return;
		}
		
		if(pact + b.arrayPesos()[lvl] <= b.getCapacidad()) {
			pact += b.arrayPesos()[lvl];
			contLoaded.add(b.getContenedores().get(lvl));
			btRec02(lvl + 1, remainingWeight - b.arrayPesos()[lvl], pact, voa, b, contLoaded);
			pact -= b.arrayPesos()[lvl];
			contLoaded.remove(contLoaded.size() - 1);
		}
		btRec02(lvl + 1, remainingWeight - b.arrayPesos()[lvl], pact, voa, b, contLoaded);
	}
	
	/**
	 * Obtiene el mapa de soluciones encontradas.
	 * 
	 * @return Un TreeMap donde la clave es el peso total y el valor es un ArrayList de contenedores cargados.
	 */
	
	public static TreeMap<Integer, ArrayList<Contenedor>> getSol() {
		return sol;
	}
}

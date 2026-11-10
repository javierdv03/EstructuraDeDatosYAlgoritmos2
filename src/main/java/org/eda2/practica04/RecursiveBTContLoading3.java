package org.eda2.practica04;
import java.util.TreeMap;

import java.util.ArrayList;

/**
 * Esta clase implementa un algoritmo de Backtracking recursivo para la carga de contenedores en un buque.
 * Utiliza arrays adicionales para rastrear el estado de las soluciones.
 */

public class RecursiveBTContLoading3 {
	
	/** 
     * Mapa con clave peso total y valor un ArrayList de contenedores cargados en el buque.
     * Almacena todas las soluciones óptimas encontradas.
     */
	
	private static TreeMap<Integer, ArrayList<Contenedor>> sol = new TreeMap<>();
	
	 /**
     * Método recursivo de Backtracking para encontrar la mejor manera de cargar contenedores en un buque.
     * 
     * @param lvl Nivel actual en la recursión, que representa el índice del contenedor en la lista.
     * @param rw Peso restante que puede ser cargado en el buque.
     * @param pact Peso actual acumulado de los contenedores cargados.
     * @param voa Mejor peso encontrado hasta ahora (valor óptimo actual).
     * @param b El objeto Buque que contiene la capacidad y los contenedores.
     * @param s Array que indica si un contenedor en la posición i ha sido seleccionado (1) o no (0).
     * @param soa Array que almacena la mejor solución encontrada.
     */
	public static void btRec03(int lvl, int rw, int pact, int voa, Buque b, int[] s, int[] soa) {
		if(pact + rw <= voa) return;
		
		if(lvl > b.getContenedores().size() - 1) {
			if(pact > voa) {
				voa = pact;
				for(int i = 0; i < b.getContenedores().size(); i++) {
					soa[i] = s[i];
				}
				ArrayList<Contenedor> temp = new ArrayList<>();
				for(int i = 0 ; i < soa.length; i++) {
					if(soa[i] == 1) {
						temp.add(b.getContenedores().get(i));
					}
				}
				sol.put(voa, temp);	
				return;
			}
		}
		
		if(pact + b.arrayPesos()[lvl] <= b.getCapacidad()) {
			pact += b.arrayPesos()[lvl];
			s[lvl] = 1;
			btRec03(lvl + 1, rw - b.arrayPesos()[lvl], pact, voa, b, s, soa);
			pact -= b.arrayPesos()[lvl];
			s[lvl] = 0;
		}
		btRec03(lvl + 1, rw - b.arrayPesos()[lvl], pact, voa, b, s, soa);
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

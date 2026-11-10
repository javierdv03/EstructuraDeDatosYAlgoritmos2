package org.eda2.practica04;

/**
 * Clase RecursiveBTContLoading1 que implementa un algoritmo de backtracking recursivo para la carga de contenedores en un buque.
 */

public class RecursiveBTContLoading1 {
	
		/**
		 * Método que encuentra el peso máximo de los contenedores que pueden ser cargados en el buque.
		 *
		 * @param pesoMax Capacidad máxima del buque.
		 * @param pesos Array con los pesos de los contenedores.
		 * @return El peso máximo de los contenedores que pueden ser cargados en el buque.
		 */
	
	    public static int encontrarPesoMax(int pesoMax, int[] pesos) {
	        int pesoTotal =  backtrack(pesos, pesoMax, 0, 0, 0);
	        System.out.println("El peso máximo que puede ser cargado: " + pesoTotal);
	        return pesoTotal;
	    }

	    /**
		 * Método auxiliar que implementa la lógica del algoritmo de backtracking recursivo.
		 *
		 * @param pesos Array con los pesos de los contenedores.
		 * @param pesoMax Capacidad máxima del buque.
		 * @param actual Índice del contenedor actual.
		 * @param pesoActual Peso total de los contenedores ya cargados en el buque.
		 * @param mejorPeso Mejor peso encontrado hasta ahora.
		 * @return El mejor peso encontrado.
		 */
	    
	    private static int backtrack(int[] pesos, int pesoMax, int actual, int pesoActual, int mejorPeso) {
	        if (actual == pesos.length) {
	            if (pesoActual <= pesoMax && pesoActual > mejorPeso) {
	                mejorPeso = pesoActual;
	            }
	            return mejorPeso;
	        }
	        
	        mejorPeso = backtrack(pesos, pesoMax, actual + 1, pesoActual, mejorPeso);

	        pesoActual += pesos[actual];
	        if (pesoActual <= pesoMax) {
	            mejorPeso = backtrack(pesos, pesoMax, actual + 1, pesoActual, mejorPeso);
	        }  

	        return mejorPeso;
	    }
}


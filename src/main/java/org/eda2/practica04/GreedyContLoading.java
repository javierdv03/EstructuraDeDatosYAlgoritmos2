package org.eda2.practica04;

import java.util.PriorityQueue;

/**
 * La clase GreedyContLoading proporciona un método estático para cargar contenedores en un buque utilizando el algoritmo Greedy.
 */
public class GreedyContLoading {
	
	/**
	 * Carga contenedores en un buque utilizando el algoritmo Greedy.
	 * @param buque El buque en el que se cargarán los contenedores.
	 */
	  public static void cargarBuque(Buque buque) {
	        int capacidadBuque = buque.getCapacidad();
	        PriorityQueue<Contenedor> colaPrioridad = new PriorityQueue<>(buque.getContenedores());

	        int pesoTotal = 0;
	        int contenedoresCargados = 0;

	        while (!colaPrioridad.isEmpty() && pesoTotal < capacidadBuque) {
	             Contenedor contenedor = colaPrioridad.poll(); 
	            if (pesoTotal + contenedor.getPeso() <= capacidadBuque) {
	                pesoTotal += contenedor.getPeso();
	                contenedoresCargados++;
	                System.out.println("Contenedor cargado: " + contenedor );
	            } else {
	                System.out.println("No se puede cargar el contenedor de " + contenedor.getPeso() + " kg, capacidad insuficiente.");
	                break; 
	            }
	        }

	        System.out.println("Peso total cargado: " + pesoTotal + "kg");
	        System.out.println("Total de contenedores cargados: " + contenedoresCargados);
	    }
	}

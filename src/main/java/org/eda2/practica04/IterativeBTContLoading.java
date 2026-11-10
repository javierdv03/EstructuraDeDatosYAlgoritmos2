package org.eda2.practica04;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase IterativeBTContLoading que implementa un algoritmo de backtracking iterativo para la carga de contenedores en un buque.
 */

public class IterativeBTContLoading {
	
	// Número de contenedores
	private static int n;
	// Pesos de los contenedores
	private static int[] p;
	// Capacidad del buque
	private static int P;
	// Peso actual
	private static int pact;
	// Valor óptimo actual
	private static int voa;
	// Solución actual // Solución óptima actual
	private static int[] s;
	private static List<Contenedor> soa;
	
	/**
	 * Método principal que implementa el algoritmo de backtracking iterativo.
	 *
	 * @param pesos Array con los pesos de los contenedores.
	 * @param capacidad Capacidad total del buque.
	 * @param contenedores Lista de contenedores.
	 * @return El peso total óptimo de los contenedores cargados en el buque.
	 */
	
	public static int btIter01(int[] pesos, int capacidad, List<Contenedor> contenedores){
		n = pesos.length;
		p = pesos;
		P = capacidad;
		pact = 0; 
		voa = 0; 
		s = new int[n];
		soa = new ArrayList<>(); 
		
		int pesoRestante = 0;
		for (int i = 0; i < n; i++){
			pesoRestante += p[i];
			s[i] = -1;
		}
		
		btIter01(0, pesoRestante, contenedores);
		
		System.out.println("Contenedores cargados:");
        for (Contenedor contenedor : soa) {
            System.out.println("Contenedor " + contenedor.getID() + ": " + contenedor.getPeso() + " kg");
        }
        System.out.println("Peso total cargado: " + voa + "kg");
        System.out.println("Total de contenedores cargados: " + soa.size());

		return voa; 
	}

	/**
	 * Método auxiliar que implementa la lógica del algoritmo de backtracking iterativo.
	 *
	 * @param nivel Nivel actual en el árbol de soluciones.
	 * @param pesoRestante Peso restante de los contenedores que aún no se han considerado.
	 * @param contenedores Lista de contenedores.
	 */
	
	private static void btIter01(int nivel, int pesoRestante, List<Contenedor> contenedores) {
		do{
			s[nivel]++;
			if(s[nivel] == 1){
				pact += p[nivel];
			}else{
				pesoRestante -= p[nivel];
			}
			
			if(nivel == n-1 && pact <= P && pact > voa) {
				voa = pact;
				soa.clear();
				for(int i = 0; i < n; i++){
					if (s[i] == 1) {
                        soa.add(contenedores.get(i));
				}
			}
		}

			if(nivel < n - 1 && pact <= P && pact + pesoRestante > voa){
				nivel++;
			}else{
				while(nivel > -1 && s[nivel] == 1){
					pact -= p[nivel];
					s[nivel] = -1;
					pesoRestante += p[nivel];
					nivel --;
				}
			}
		}while(nivel>-1);
	}

}

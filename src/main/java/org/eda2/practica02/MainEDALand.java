package org.eda2.practica02;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Clase principal para la ejecución de algoritmos en un grafo.
 */
public class MainEDALand {
	
	/**
	 * Ruta del directorio de entrada.
	 */
	private static String directorioEntrada = System.getProperty("user.dir") + File.separator
            + "src" + File.separator + "main" + File.separator
            + "java" + File.separator + "org" + File.separator
            + "eda2" + File.separator + "practica02" + File.separator;
	
	/**
	 * Ejecuta pruebas de rendimiento para varios algoritmos en un grafo.
	 */
	public static void ejecutarTiempos() {
		
		String origenAleatorio = "";
		
		System.out.println("Ejecutando pruebas de rendimiento...\n");
		
		System.out.println("=====Pruebas para el resto de algoritmos=====\n");
		
		for(int n = 100; n <= 10000; n+=100) {
			AlgoritmosEDALand grafo = new AlgoritmosEDALand(RedAleatoria.generadorDeRedAleatoria(n));
			origenAleatorio = String.valueOf((int) (Math.random() * n) + 1);
			
			System.out.println("Tiempo de Prim sin cola de prioridad para " + n + 
					" vértices y " + grafo.getNetwork().numberOfEdges() + 
					" aristas: " + TiempoAlgoritmos.medirPrimSinPQ(grafo, origenAleatorio) + " ms");
			
			System.out.println("Tiempo de Prim con cola de prioridad para " + n + 
					" vértices y " + grafo.getNetwork().numberOfEdges() + 
					" aristas: " + TiempoAlgoritmos.medirPrimConPQ(grafo, origenAleatorio) + " ms");
			
			System.out.println("Tiempo de Kruskal para " + n + 
					" vértices y " + grafo.getNetwork().numberOfEdges() + 
					" aristas: " + TiempoAlgoritmos.medirKruskal(grafo) + " ms");
			
			System.out.print("Tiempo de TSPGreedy para " + n + 
					" vértices y " + grafo.getNetwork().numberOfEdges() + 
					" aristas: ");
			
			try {
				System.out.println(TiempoAlgoritmos.medirTSPGreedy(grafo, grafo.getNetwork(), origenAleatorio) + " ms");
				
			}catch(RuntimeException e) {
				System.out.println("Camino no encontrado");
			}
			
			System.out.println();
		}
		
System.out.println("=====Pruebas para el algoritmo TSPGreedy Óptimo=====\n");
		
		for(int n = 3; n <= 15; n+=2) {
			AlgoritmosEDALand grafo = new AlgoritmosEDALand(RedAleatoria.generadorDeRedAleatoria(n));
			origenAleatorio = String.valueOf((int) (Math.random() * n) + 1);
			
			System.out.print("Tiempo de TSPGreedy Óptimo para " + n + 
					" vértices y " + grafo.getNetwork().numberOfEdges() + 
					" aristas: ");
			try {
				System.out.println(TiempoAlgoritmos.medirTSPGreedyOptimo(grafo.getNetwork(), origenAleatorio) + " ms\n");
				
			}catch(RuntimeException e) {
				System.out.println("Camino no encontrado");
			}
		}
		
	}
	
	/**
	 * Muestra el resultado de un algoritmo Prim, que es un árbol de expansión mínima (MST).
	 * @param res Lista de aristas que forman el MST.
	 */
	public static void mostrarPrim(ArrayList<Arista> res) {
		Double pesoMAX = 0.;
		for(int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i) + "\n");
			pesoMAX += res.get(i).getPeso();
		}
		
		System.out.println("Coste total del MST -> " + pesoMAX);

	}
	
	/**
	 * Muestra el resultado de un algoritmo Kruskal, que es un árbol de expansión mínima (MST).
	 * @param res Conjunto de aristas que forman el MST.
	 */
	public static void mostrarKruskal(TreeSet<Arista> res) {
		
		System.out.println("Solución para el algoritmo de Kruskal\n");
		Double pesoMAX = 0.;
		for(Arista a : res) {
			System.out.println(a + "\n");
			pesoMAX += a.getPeso();
		}
		
		System.out.println("Coste total del MST -> " + pesoMAX);
		
	}
	
	/**
	 * Muestra el resultado de un algoritmo TSP (Travelling Salesman Problem).
	 * @param res Lista de aristas que forman el camino del TSP.
	 */
	public static void mostrarTSP(ArrayList<Arista> res) {
		
		System.out.println("Camino de Aristas -> \n");
		
		for(int i = 0; i < res.size(); i++) {
			System.out.println();
			if(i == res.size() - 1) {
				System.out.println("Camino más largo desde el origen ==> " + res.get(i));
			} else {
				System.out.println(res.get(i) + "\n");
			}
		}

		
	}
	
	/**
	 * Muestra el resultado de un algoritmo TSPOptimo.
	 * @param res Lista de aristas que forman el camino óptimo del TSP.
	 */
	public static void mostrarTSPOptimo(ArrayList<Arista> res) {
		System.out.println("Camino de Aristas -> \n");
		
		for(int i = 0; i < res.size(); i++) {
			if(i == res.size() - 1) {
				System.out.println("Camino más largo desde el origen ==> " + res.get(i));
			}else {
				System.out.println(res.get(i)+ "\n");
			}
		}
	}
	
	/**
	 * Método principal de la aplicación. Proporciona un menú interactivo para ejecutar varios algoritmos en un grafo.
	 * @param args Argumentos de la línea de comandos. No se utilizan en este método.
	 */
	public static void main(String[] args) {
		boolean salir = false;
		AlgoritmosEDALand edaLand = new AlgoritmosEDALand();
		String origen = "";
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		while(!salir) {
		System.out.println("=======================================");
		System.out.println("                 MENU                ");
		System.out.println("=======================================");
		System.out.println("1: Cargar archivo");
		System.out.println("2: Generar red aleatoria");
		System.out.println("3: Ejecutar algoritmo de Prim sin cola de prioridad");
		System.out.println("4: Ejecutar algoritmo de Prim con cola de prioridad");
		System.out.println("5: Ejecutar algoritmo de Kruskal");
		System.out.println("6: Ejecutar algoritmo de TPSGreedy");
		System.out.println("7: Ejecutar algoritmo de TPSGreedy Óptimo");
		System.out.println("8: Ejecutar pruebas de rendimiento");
		System.out.println("9: Salir\n");

		int eleccion = sc.nextInt();
		
		
			switch(eleccion) {
			case 1: 
				System.out.println("Archivo a cargar:");
				System.out.println("1: graphEDAland");
				System.out.println("2: graphEDAlandLarge");
				System.out.println("3: graphEDAlandNewroads");
				System.out.println("4: Salir");
				System.out.print("Respuesta -> ");
				eleccion = sc.nextInt();
				if(eleccion < 1 || eleccion > 5) {
					System.out.println("Incorrecto");
					break;
				}
				String name = "";
				if(eleccion == 1 || eleccion == 2) name = eleccion == 1 ? "graphEDAland.txt" : "graphEDAlandLarge.txt";
				else name = "graphEDAlandNewroads.txt";
				opcion = eleccion;
				edaLand.load(directorioEntrada + name);
				if(eleccion != 4) System.out.println("Archivo cargado correctamente");
				break;
			case 2:
				System.out.println("Numero de vértices de la red aleatoria");
				System.out.print("Respuesta -> ");
				eleccion = sc.nextInt();
				if(eleccion <= 0) {
					System.out.println("Número incorrecto");
					break;
				}
				edaLand = new AlgoritmosEDALand(RedAleatoria.generadorDeRedAleatoria(eleccion));
				break;
			case 3:
                System.out.println("Elige un origen de entre los siguientes vértices disponibles:\n");
				
				for(String it : edaLand.getNetwork()) {
					System.out.println(it);
				}
                System.out.print("Respuesta -> ");
				
				origen = sc.next();
				mostrarPrim(edaLand.primSinPQ(origen));
				if(opcion == 1 || opcion == 2) Utilities.saveAsDot(edaLand.getNetwork(), edaLand.primSinPQ(origen), "ArchivoDOTGenerado.dot", origen, opcion);
				break;
			case 4:
                System.out.println("Elige un origen de entre los siguientes vértices disponibles:\n");
				
				for(String it : edaLand.getNetwork()) {
					System.out.println(it);
				}
                System.out.print("Respuesta -> ");
				
				origen = sc.next();
				mostrarPrim(edaLand.primConPQ(origen));
				if(opcion == 1 || opcion == 2) Utilities.saveAsDot(edaLand.getNetwork(), edaLand.primConPQ(origen), "ArchivoDOTGenerado.dot", origen, opcion);
				break;
			case 5: mostrarKruskal(edaLand.Kruskal()); 
			if(opcion == 1 || opcion == 2) Utilities.saveAsDotKruskal(edaLand.getNetwork(), edaLand.Kruskal(), "ArchivoDOTGenerado.dot", opcion);
			break;
			case 6: 
				System.out.println("Elige un origen de entre los siguientes vértices disponibles:\n");
				
				for(String it : edaLand.getNetwork()) {
					System.out.println(it);
				}
                System.out.print("Respuesta -> ");
				
				origen = sc.next();
				try {
					mostrarTSP(edaLand.TSPGreedy(edaLand.getNetwork(), origen));	
				}catch(RuntimeException e) {
					System.out.println("No existe camino para este grafo");
					continue;
				}
				
				break;
			case 7: 
				System.out.println("Elige un origen de entre los siguientes vértices disponibles:\n");
				
				for(String it : edaLand.getNetwork()) {
					System.out.println(it);
				}
                System.out.print("Respuesta -> ");
				
				origen = sc.next();
				try {
					mostrarTSPOptimo(AlgoritmosEDALand.TSPSimplePaths(edaLand.getNetwork(), origen));
				}catch(RuntimeException e) {
					System.out.println("No existe camino para este grafo");
					continue;
				}
				break;
			case 8:
				ejecutarTiempos();	
				break;
			case 9: 
				salir = true;
				break;
				
			}
			
		}
		sc.close();


	}

}
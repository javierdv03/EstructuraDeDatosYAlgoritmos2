package org.eda2.practica04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * La clase Main proporciona un programa interactivo para probar diferentes algoritmos de carga de contenedores en un buque.
 */
public class Main {
	
	private static Buque buque;
	
	/**
	 * Método principal que inicia el programa interactivo.
	 * @param args Los argumentos de la línea de comandos (no utilizados).
	 */
	 public static void main(String[] args) {
		 
		 
		 Scanner scanner = new Scanner(System.in);
	        int option = 0;

	        while (option != 8) {
	            System.out.println("===================================");
	            System.out.println("\t      MENÚ");
	            System.out.println("===================================");
	            System.out.println("1. Crear Buque y Contenedores");
	            System.out.println("2. Ejecutar algoritmo Greedy");
	            System.out.println("3. Ejecutar algoritmo Recursivo1");
	            System.out.println("4. Ejecutar algoritmo Recursivo2");
	            System.out.println("5. Ejecutar algoritmo Recursivo3");
	            System.out.println("6. Ejecutar algoritmo Iterativo");
	            System.out.println("7. Pruebas de rendimiento");
	            System.out.println("8. Salir");
	            System.out.print("Seleccione una opción: ");

	            option = scanner.nextInt();

	            switch (option) {
	                case 1: buque = crearBuqueDesdeEntrada();
	                PruebasAlgoritmo.setBuque(buque);
                    PruebasAlgoritmo.setAlgoritmoSeleccionado(-1);
	                    break;
	                case 2:   if (buque != null) {
	                	PruebasAlgoritmo.setAlgoritmoSeleccionado(2);
                        ejecutarAlgoritmoGreedy(buque);
                    } else {
                        System.out.println("Primero debe crear el buque y los contenedores (opción 1).");
                    }
	                    break;
	                case 3:  if (buque != null) {
	                	PruebasAlgoritmo.setAlgoritmoSeleccionado(3);
                        ejecutarAlgoritmoRecursivo1(buque);
                    } else {
                        System.out.println("Primero debe crear el buque y los contenedores (opción 1).");
                    }
	                    break;
	                case 4: if (buque != null) {
	                	PruebasAlgoritmo.setAlgoritmoSeleccionado(4);
	                    ejecutarAlgoritmoRecursivo2(buque);
	                } else {
	                    System.out.println("Primero debe crear el buque y los contenedores (opción 1).");
	                }
	                    break;
	                case 5:if (buque != null) {
	                	PruebasAlgoritmo.setAlgoritmoSeleccionado(5);
	                	ejecutarAlgoritmoRecursivo3(buque);
                    } else {
                        System.out.println("Primero debe crear el buque y los contenedores (opción 1).");
                    }  	
	                    break;
	                case 6:  if (buque != null) {
	                	PruebasAlgoritmo.setAlgoritmoSeleccionado(6);
                        ejecutarAlgoritmoIterativo(buque);
                    } else {
                        System.out.println("Primero debe crear el buque y los contenedores (opción 1).");
                    }
	                    break;
	                case 7: 	         
	                	PruebasAlgoritmo.realizarPruebasRendimiento();
	                	 break;
	                case 8:
	                    System.out.println("Saliendo...");
	                    break;
	                default:
	                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
	            }
	            System.out.println();
	        }

	        scanner.close();
	    }
	 
	/**
	 * Crea un objeto Buque con capacidades y contenedores ingresados por el usuario.
	 * @return El buque creado.
	 */
	   public static Buque crearBuqueDesdeEntrada() {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Ingrese la capacidad del buque: ");
	        int capacidadBuque = scanner.nextInt();

	        System.out.print("Ingrese el número de contenedores: ");
	        int numContenedores = scanner.nextInt();

	        ArrayList<Contenedor> contenedores = new ArrayList<>();

	        for (int i = 0; i < numContenedores; i++) {
	            System.out.print("Ingrese el peso del contenedor " + (i + 1) + ": ");
	            int peso = scanner.nextInt();
	            contenedores.add(new Contenedor(peso, i + 1));
	        }

	        return new Buque(capacidadBuque, contenedores);
	    }
	 
	/**
	 * Ejecuta el algoritmo Greedy para cargar el buque.
	 * @param buque El buque en el que se cargarán los contenedores.
	 */
	   public static void ejecutarAlgoritmoGreedy(Buque buque) {
	        GreedyContLoading.cargarBuque(buque);
	   }
	   
	/**
	 * Ejecuta el algoritmo Recursivo1 para cargar el buque.
	 * @param buque El buque en el que se cargarán los contenedores.
	 */
	   public static void ejecutarAlgoritmoRecursivo1(Buque buque) {
		   int capacidadBuque = buque.getCapacidad();
	        int[] pesosContenedores = new int[buque.getContenedores().size()];
	        for (int i = 0; i < buque.getContenedores().size(); i++) {
	            pesosContenedores[i] = buque.getContenedores().get(i).getPeso();
	        }
	   

	        RecursiveBTContLoading1.Result resultado = RecursiveBTContLoading1.encontrarPesoMax(capacidadBuque, pesosContenedores);
	        int pesoTotal = resultado.getPesoMaximo();
	        List<Integer> contenedoresCargadosIndices = resultado.getContenedores();

	        List<Contenedor> contenedores = buque.getContenedores();
	        int totalContenedoresCargados = 0;

	        for (int i : contenedoresCargadosIndices) {
	            Contenedor contenedor = contenedores.get(i);
	            System.out.println("Contenedor cargado: " + contenedor);
	            totalContenedoresCargados++;
	        }

	        System.out.println("Peso total cargado: " + pesoTotal + "kg");
	        System.out.println("Total de contenedores cargados: " + totalContenedoresCargados);
	    }
	   
	/**
	 * Ejecuta el algoritmo Recursivo2 para cargar el buque.
	 * @param buque El buque en el que se cargarán los contenedores.
	 */
	   public static void ejecutarAlgoritmoRecursivo2(Buque buque) {
		    RecursiveBTContLoading2.btRec02(0, buque.getPesosContenedores(), 0, 0, buque, null);
		    Integer pesoTotal = RecursiveBTContLoading2.getSol().lastKey();
		    ArrayList<Contenedor> contenedoresLoaded = RecursiveBTContLoading2.getSol().lastEntry().getValue();
		    if (pesoTotal != null && contenedoresLoaded != null) {			    
			    for(Contenedor c : contenedoresLoaded) {
		            System.out.println("Contenedor cargado: " + c);
			    }
		    	System.out.println("Peso total cargado: " + pesoTotal + "kg");
		        System.out.println("Total de contenedores cargados: " + contenedoresLoaded.size());
			    
		    }
		    
		} 
	   
	/**
	 * Ejecuta el algoritmo Recursivo3 para cargar el buque.
	 * @param buque El buque en el que se cargarán los contenedores.
	 */
	   public static void ejecutarAlgoritmoRecursivo3(Buque buque) {
		    int[] s = new int[buque.arrayPesos().length];
		    int[] soa = new int[buque.arrayPesos().length];
		    RecursiveBTContLoading3.btRec03(0, buque.getPesosContenedores(), 0, 0, buque, s, soa);
		    Integer pesoTotal = RecursiveBTContLoading3.getSol().lastKey();
		    ArrayList<Contenedor> contenedoresLoaded = RecursiveBTContLoading3.getSol().lastEntry().getValue();
		    if (pesoTotal != null && contenedoresLoaded != null) {			    
			    for(Contenedor c : contenedoresLoaded) {
		            System.out.println("Contenedor cargado: " + c);
			    }
		    	System.out.println("Peso total cargado: " + pesoTotal + "kg");
		        System.out.println("Total de contenedores cargados: " + contenedoresLoaded.size());
			    
		    }
		    
		}
	   
	/**
	 * Ejecuta el algoritmo Iterativo para cargar el buque.
	 * @param buque El buque en el que se cargarán los contenedores.
	 */
	   public static void ejecutarAlgoritmoIterativo(Buque buque) {
	        int capacidad = buque.getCapacidad();
	        int[] pesos = buque.getContenedores().stream().mapToInt(Contenedor::getPeso).toArray();
	        List<Contenedor> contenedores = buque.getContenedores();
	        int mejorValor = IterativeBTContLoading.btIter01(pesos, capacidad, contenedores);
	        System.out.println("Mejor valor (peso óptimo) utilizando el algoritmo iterativo: " + mejorValor);
	    }
	   }

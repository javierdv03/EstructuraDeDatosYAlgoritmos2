package org.eda2.practica03;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

public class MainKnapsack {
	
	private static Knapsack algoritmosMochila;
	static Scanner sc;
	
	private static String directorioEntrada = System.getProperty("user.dir") 
			+ File.separator + "src" + File.separator + "main" + File.separator + "java"
			+ File.separator +"org"
			+ File.separator + "eda2" 
			+ File.separator + "practica03" 
			+ File.separator;
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		int eleccion = 0;
		do {
			menu();
			eleccion = PruebasRendimiento.leerEntero("Seleccione una opción:", 1, 12);
		} while (ejecutar(eleccion));
		sc.close();
	}

	private static void menu() {
		System.out.println("==========================================");
		System.out.println("                 MENU");
		System.out.println("==========================================");
		System.out.println("1.  Cargar archivo");
		System.out.println("2.  Crear mochila manual");
		System.out.println("3.  Generar mochila aleatoria");
		System.out.println("4.  Algoritmo Camion Recursive");
		System.out.println("5.  Algoritmo Camion Table");
		System.out.println("6.  Algoritmo Camion DP");
		System.out.println("7.  Algoritmo Camion DP2");
		System.out.println("8.  Algoritmo Camion DP3");
		System.out.println("9.  Algoritmo Camion DPMP");
		System.out.println("10. Algoritmo Greedy");
		System.out.println("11. Ejecutar pruebas de rendimiento");
		System.out.println("12. Salir");

	}
	
	private static boolean ejecutar(int opcion) {
		switch (opcion) {
		case 1: load(); break;
		case 2: KnapsackManual(); break;
		case 3: KnapsackAleatorio(); break;
		case 4: ejecutarRecursivo(); break;
		case 5: ejecutarCamionTable(); break;
		case 6: ejecutarDP(); break;
		case 7: ejecutarDP2(); break;
		case 8: ejecutarDP3(); break;
		case 9: ejecutarDPMD(); break;
		case 10: ejecutarGreedy(); break;
		case 11: ejecutarPruebasRendimiento(); break;
		case 12: return false;
		default: System.out.println("Opción no válida");
		}
		return true;
	}
	
	private static void load() {
		File dir = new File(directorioEntrada);
		File[] archivos = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String nombreTXT) {
				return nombreTXT.toLowerCase().endsWith(".txt");
			}
		});

		for (int i = 0; i < archivos.length; i++) {
			System.out.println((i + 1) + ". " +	archivos[i].getName());
		}
		int i = PruebasRendimiento.leerEntero("Seleccione el archivo: ", 1, archivos.length);
		File archivoSeleccionado = archivos[i - 1];
		String filenombre = archivoSeleccionado.getAbsolutePath();
		int pos = filenombre.lastIndexOf("_");
		filenombre = filenombre.substring(0, pos);
		algoritmosMochila = new Knapsack(filenombre);
		System.out.println("Archivo cargado correctamente");

	}
	
	private static void KnapsackAleatorio() {
		System.out.println("Generando mochila aleatoria...");
		algoritmosMochila = PruebasRendimiento.generarKnapsackAleatorio();
		System.out.println("Mochila generada correctamente");
		System.out.println("Objetos creados:\n");
		System.out.println(algoritmosMochila);
	}
	
	private static void KnapsackManual() {
		System.out.println("Introducción manual de datos");
		algoritmosMochila = PruebasRendimiento.generarKnapsackManual();
		System.out.println(algoritmosMochila);
		System.out.println("Mochila generada correctamente");
	}
	
	private static boolean comprobarKnapsack() {
		if(algoritmosMochila == null || algoritmosMochila.getObjetos().size() == 0) {
			System.err.println("Carga un archivo de datos o genera un escenario");
			return false;
		}
		return true;
	}
	
	private static void ejecutarRecursivo() {
		if(!comprobarKnapsack()) return;
		System.out.println();
		System.out.println("Version recursiva");
		System.out.println();
		System.out.println("BENEFICIO TOTAL: " + algoritmosMochila.camionRecursive(algoritmosMochila.getN(), 
				algoritmosMochila.getP(), algoritmosMochila.getWeigths(), algoritmosMochila.getProfits()));
	}
	
	private static void ejecutarCamionTable() {
		if(!comprobarKnapsack()) return;
		System.out.println();
		System.out.println("Version memoria");
		System.out.println();
		algoritmosMochila.camionTable(algoritmosMochila.getN(), algoritmosMochila.getP(),
									  algoritmosMochila.getWeigths(), algoritmosMochila.getProfits());;
		algoritmosMochila.imprimirTabla();
		int[] sol = algoritmosMochila.obtenersolucionOptimaDinamica();
		algoritmosMochila.ImprimirSolucionEnteros(sol);
	}
	
	private static void ejecutarDP() {
		if(!comprobarKnapsack()) return;
		System.out.println("Version DP");
		algoritmosMochila.camionDP();
		algoritmosMochila.imprimirTabla();
		int[] sol = algoritmosMochila.obtenerSolucionOptimaRecursiva();
		algoritmosMochila.ImprimirSolucionEnteros(sol);
	}
	
	private static void ejecutarDP2() {
		if(!comprobarKnapsack()) return;
		System.out.println("Version DP2");
		algoritmosMochila.camionDP2();
		algoritmosMochila.imprimirTabla();
		int[] sol = algoritmosMochila.obtenerSolucionOptimaDinamicaIterativa();
		algoritmosMochila.ImprimirSolucionEnteros(sol);
	}
	
	private static void ejecutarDP3() {
		if(!comprobarKnapsack()) return;
		System.out.println("Version DP3");
		System.out.println(algoritmosMochila.camionDP3());
		algoritmosMochila.imprimirArray();
		System.out.println("BENEFICIO TOTAL: " + algoritmosMochila.getArray()[algoritmosMochila.getArray().length - 1]);
	}
	
	private static void ejecutarDPMD() {
		if(!comprobarKnapsack()) return;
		System.out.println("Version DPMP");
		algoritmosMochila.camionDPMP();
		int[] sol = algoritmosMochila.mejorSolucion();
		algoritmosMochila.ImprimirSolucionEnteros(sol);
	}
	
	private static void ejecutarGreedy() {
		if(!comprobarKnapsack()) return;
		System.out.println("Version Greedy");
		double[] sol = algoritmosMochila.Knapsackgreedy();
		algoritmosMochila.ImprimirObjetosDouble(sol);
	}
	
	private static void ejecutarPruebasRendimiento() {
		System.out.println("1. N constante P variable");
		System.out.println("2. N variable P constante");
		System.out.println("3. Volver");
		int opcion = PruebasRendimiento.leerEntero("Seleccione una opción:", 1, 3);
		switch (opcion) {
		case 1: PruebasRendimiento.pruebasParaNConstanteYPVariable(); break;
		case 2: PruebasRendimiento.pruebasParaNVariableYPConstante(); break;
		}
	}

}

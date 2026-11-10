package org.eda2.practica03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase Knapsack que representa una mochila para el problema de la mochila (Knapsack problem).
 */
public class Knapsack {
	
	private List<Objeto> objetos; // Lista de objetos que puede contener la mochila
	private int P; // Capacidad máxima de la mochila
	private double[][] tabla; // Tabla para el algoritmo de programación dinámica
	private double[] array; // Array para el algoritmo de programación dinámica

	/**
	 * Constructor por defecto. Inicializa una mochila vacía con capacidad 0.
	 */
	public Knapsack() {
		this.objetos = new ArrayList<>();
		this.P=0;
	}

	/**
	 * Constructor que inicializa una mochila vacía con capacidad P.
	 * @param P Capacidad de la mochila.
	 */
	public Knapsack(int P) {
		this();
		this.P=P;
	}

	/**
	 * Constructor que inicializa una mochila con una lista de objetos y una capacidad P.
	 * @param beneficios Array de beneficios de los objetos.
	 * @param pesos Array de pesos de los objetos.
	 * @param P Capacidad de la mochila.
	 */
	public Knapsack(double[] beneficios, int[] pesos, int P) {
		this(P);
		for (int i = 0; i < beneficios.length; i++) {
			Objeto obj = new Objeto("Objeto "+i, pesos[i], beneficios[i]);
			objetos.add(obj);
		}
	}

	/**
	 * Constructor que inicializa una mochila a partir de un archivo.
	 * @param nombreArchivo Nombre del archivo que contiene los datos de los objetos y la capacidad de la mochila.
	 */
	public Knapsack(String nombreArchivo) {
		this.objetos = new ArrayList<>();
		Scanner sc;
		try {
			File f = new File(nombreArchivo+"_c.txt");
			sc = new Scanner(f);
			this.P = Integer.parseInt(sc.nextLine().trim());
			sc.close();
			f = new File(nombreArchivo+"_w.txt");
			sc = new Scanner(f);
			int i = 1;
			while(sc.hasNextLine()) {
				this.objetos.add(new Objeto("Objeto "+i, Double.parseDouble(sc.nextLine().trim()), 0));
				i++;
			}
			sc.close();
			f = new File(nombreArchivo+"_p.txt");
			sc = new Scanner(f);
			i = 0;
			while (sc.hasNextLine()) {
				this.objetos.get(i).setBeneficio(Double.parseDouble(sc.nextLine().trim()));
				i++;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para añadir un objeto a la mochila.
	 * @param obj Objeto a añadir a la mochila.
	 */
	public void add(Objeto obj) {
		this.objetos.add(obj);
	}

	/**
	 * Método para eliminar un objeto de la mochila.
	 * @param obj Objeto a eliminar de la mochila.
	 */
	public void remove(Objeto obj) {
		this.objetos.remove(obj);
	}

	/**
	 * Método para obtener una representación en cadena de la mochila.
	 * @return Cadena que representa la mochila.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Los objetos de la MOCHILA (").append(this.P).append(") son los siguientes:\n");
		for (Objeto objeto : objetos) {
			sb.append(objeto.toString()).append("\n");
		}
		return sb.toString();
	}

	

	/**
	 * Método setter para establecer la lista de objetos de la mochila.
	 * @param objects Lista de objetos a establecer en la mochila.
	 */
	public void setObjetos(List<Objeto> objects) {
		this.objetos = objects;
	}

	/**
	 * Método getter para obtener la lista de objetos de la mochila.
	 * @return Lista de objetos de la mochila.
	 */
	public List<Objeto> getObjetos() {
		return objetos;
	}

	/**
	 * Método para obtener el número de objetos de la mochila.
	 * @return Número de objetos de la mochila.
	 */
	public int getN() {
		return this.objetos.size();
	}

	/**
	 * Método getter para obtener la capacidad de la mochila.
	 * @return Capacidad de la mochila.
	 */
	public int getP() {
		return P;
	}

	/**
	 * Método setter para establecer la capacidad de la mochila.
	 * @param p Capacidad a establecer en la mochila.
	 */
	public void setP(int p) {
		P = p;
	}

	

	/**
	 * Método para obtener los pesos de los objetos de la mochila.
	 * @return Array de pesos de los objetos de la mochila.
	 */
	public int[] getWeigths() {
		int[] p = new int[getN()];
		for (int i = 0; i < objetos.size(); i++) {
			p[i] = (int) objetos.get(i).getPeso();
		}
		return p;
	}

	/**
	 * Método para obtener los beneficios de los objetos de la mochila.
	 * @return Array de beneficios de los objetos de la mochila.
	 */
	public double[] getProfits() {
		double[] p = new double[getN()];
		for (int i = 0; i < objetos.size(); i++) {
			p[i] = objetos.get(i).getBeneficio();
		}
		return p;
	}

	

	/**
	 * Método getter para obtener la tabla del algoritmo de programación dinámica.
	 * @return Tabla del algoritmo de programación dinámica.
	 */
	public double[][] getTabla() {
		return tabla;
	}

	/**
	 * Método getter para obtener el array del algoritmo de programación dinámica.
	 * @return Array del algoritmo de programación dinámica.
	 */
	public double[] getArray() {
		return array;
	}

	/**
	 * Método recursivo para resolver el problema de la mochila.
	 * @param n Número de objetos.
	 * @param P Capacidad de la mochila.
	 * @param p Array de pesos de los objetos.
	 * @param b Array de beneficios de los objetos.
	 * @return El beneficio máximo que se puede obtener.
	 */
	public double camionRecursive(int n, int P, int p[], double b[]) {
		if(P == 0 || n == 0) {
			return 0;
		}
		if(P < p[n-1]) {
			return camionRecursive(n-1, P, p, b);
		}
		return Math.max(camionRecursive(n-1, P, p, b), camionRecursive(n-1, P - p[n-1], p, b) + b[n-1]);
	}
	
	/**
	 * Método para obtener la solución óptima del problema de la mochila utilizando programación dinámica.
	 * @return Array de soluciones óptimas.
	 */
	public int[] obtenersolucionOptimaDinamica() {
		int n = tabla.length-1;
		int[] sol = new int[n];
		int aux = P;
		for (int i = n; i >= 2; i--) {
			if(tabla[i-1][aux] == tabla[i][aux]) {
				sol[i-1] = 0;
			}else {
				sol[i-1] = 1;
				aux -= objetos.get(i-1).getPeso();
			}
		}
		if(tabla[1][aux] != -1) {
			sol[0] = 1;
		}else {
			sol[0] = 0;
		}
		return sol;
	}
	
	/**
	 * Método recursivo para resolver el problema de la mochila con memorización.
	 * @param n Número de objetos.
	 * @param P Capacidad de la mochila.
	 * @param p Array de pesos de los objetos.
	 * @param b Array de beneficios de los objetos.
	 * @return El beneficio máximo que se puede obtener.
	 */
	public double camionRec(int n, int P, int p[], double b[]) {
		if(P == 0 || n == 0) {
			return 0;
		}
		if(tabla[n][P] != -1) {
			return tabla[n][P];
		}
		if(P < p[n-1]) {
			return tabla[n][P] = camionRec(n-1, P, p, b);
		}
		double beneficio_Incluido = camionRec(n-1, P - p[n-1],  p, b) + b[n-1];
		double beneficio_No_Incluido = camionRec(n-1, P, p, b);
		return tabla[n][P] = Math.max(beneficio_Incluido, beneficio_No_Incluido);
	}
	
	/**
	 * Método para inicializar la tabla de programación dinámica y llamar al método recursivo.
	 * @param n Número de objetos.
	 * @param P Capacidad de la mochila.
	 * @param p Array de pesos de los objetos.
	 * @param b Array de beneficios de los objetos.
	 * @return El beneficio máximo que se puede obtener.
	 */
	public double camionTable(int n, int P, int p[], double b[]) {
		int i;
		int j;
		tabla = new double[n+1][P+1];
		for (i = 0; i < tabla.length; i++) {
			for (j = 0; j < tabla[0].length; j++) {
				tabla[i][j] = -1;
			}
		}
		return camionRec(n, P, p, b);
	}
	
	/**
	 * Método para resolver el problema de la mochila utilizando programación dinámica.
	 * @return El beneficio máximo que se puede obtener.
	 */
	public double camionDP() {
		return camionDP(getP(), getWeigths(), getProfits());
	}
	
	/**
	 * Método para resolver el problema de la mochila utilizando programación dinámica.
	 * @param P Capacidad de la mochila.
	 * @param p Array de pesos de los objetos.
	 * @param b Array de beneficios de los objetos.
	 * @return El beneficio máximo que se puede obtener.
	 */
	public double camionDP(int P, int p[], double b[]) {
		int indiceItem;
		int pesoActual;
		int numItems = b.length;
		double[][] matrizBeneficios = new double[numItems+1][P+1];
		
		for (indiceItem = 1; indiceItem <= numItems; indiceItem++) {
			for (pesoActual = 1; pesoActual < p[indiceItem-1]; pesoActual++) {
				matrizBeneficios[indiceItem][pesoActual] = matrizBeneficios[indiceItem-1][pesoActual];
			}
			for (pesoActual = p[indiceItem-1]; pesoActual <= P; pesoActual++) {
				double beneficio_Incluido = matrizBeneficios[indiceItem-1][pesoActual-p[indiceItem-1]] + b[indiceItem-1];
				double beneficio_No_Incluido = matrizBeneficios[indiceItem-1][pesoActual];
				matrizBeneficios[indiceItem][pesoActual] = Math.max(beneficio_Incluido, beneficio_No_Incluido);
			}
		}
		
		return matrizBeneficios[numItems][P];
	}
	
	/**
	 * Método para obtener la solución óptima del problema de la mochila utilizando recursión.
	 * @return Array de soluciones óptimas.
	 */
	public int[] obtenerSolucionOptimaRecursiva() {
		int numItems = this.getN();
		int capacidad = this.getP();
		int[] pesos = this.getWeigths();
		double[] beneficios = this.getProfits();
		int[] solucion = new int[numItems];
		test(numItems, capacidad, pesos, beneficios, solucion);
		return solucion;
	}
	
	/**
	 * Método auxiliar para probar la solución óptima del problema de la mochila.
	 * @param j Índice del objeto.
	 * @param c Capacidad restante de la mochila.
	 * @param p Array de pesos de los objetos.
	 * @param b Array de beneficios de los objetos.
	 * @param sol Array de soluciones óptimas.
	 */
	public void test(int j, int c, int[] p, double[] b, int[] sol) {
		if(j > 0) {
			if(c < p[j-1]) {
				test(j-1, c, p, b, sol);
			}else {
				if(tabla[j-1][c-p[j-1]] + b[j-1] > tabla[j-1][c]) {
					test(j-1, c-p[j-1], p, b, sol);
					sol[j-1] = 1;
				}else {
					test(j-1, c, p, b, sol);
				}
			}
		}
		
	}
		
	/**
	 * Método para resolver el problema de la mochila utilizando programación dinámica.
	 * @return El beneficio máximo que se puede obtener.
	 */
	public double camionDP2() {
		return camionDP2(getP(), getWeigths(), getProfits());
	}
	
	/**
	 * Método para resolver el problema de la mochila utilizando programación dinámica.
	 * @param P Capacidad de la mochila.
	 * @param p Array de pesos de los objetos.
	 * @param b Array de beneficios de los objetos.
	 * @return El beneficio máximo que se puede obtener.
	 */
	public double camionDP2(int P, int p[], double b[]) {
		int nunmItems = b.length;
		tabla = new double[nunmItems+1][P+1];
		
		for (int i = 1; i <= nunmItems; i++) {
			for (int j = 1; j <= P; j++) {
				tabla[i][j] = tabla[i-1][j];
				if(p[i-1] <= j) {
					double beneficio_Incluido = tabla[i-1][j-p[i-1]] + b[i-1];
					double beneficio_No_Incluido = tabla[i][j];
					tabla[i][j] = Math.max(beneficio_Incluido, beneficio_No_Incluido);
				}
			}
		}
		
		return tabla[nunmItems][P];
	}
	
	/**
	 * Método para obtener la solución óptima del problema de la mochila utilizando programación dinámica iterativa.
	 * @return Array de soluciones óptimas.
	 */
	public int[] obtenerSolucionOptimaDinamicaIterativa() {
		int[] sol = new int[getN()];
		int j = tabla[0].length - 1;
		String mochila = "";
		for (int i = tabla.length - 1; i > 0; i--) {
			if (tabla[i][j] != tabla[i - 1][j]) {
				sol[i-1] = 1;
				mochila = "1, "+mochila;
				j -= objetos.get(i-1).getPeso();
			}else {
				sol[i-1] = 0;
				mochila = "-, "+mochila;
			}
		}
		return sol;
	}
	
	/**
	 * Método para resolver el problema de la mochila utilizando programación dinámica.
	 * @return El beneficio máximo que se puede obtener.
	 */
	public double camionDP3() {
		return camionDP3(getP(), getWeigths(), getProfits());
	}
	
	/**
	 * Método para resolver el problema de la mochila utilizando programación dinámica.
	 * @param P Capacidad de la mochila.
	 * @param p Array de pesos de los objetos.
	 * @param b Array de beneficios de los objetos.
	 * @return El beneficio máximo que se puede obtener.
	 */
	public double camionDP3(int P, int p[], double b[]) {
		int numItems = b.length;
		array = new double[P+1];
		
		for (int i = 1; i <= numItems; i++) {
			for (int j = P; j >= 0; j--) {
				if(p[i-1] <= j) {
					array[j] = Math.max(array[j], array[j - p[i-1]]+b[i-1]);
				}
			}
		}
		return array[P];
	}
	
	/**
	 * Método para resolver el problema de la mochila utilizando programación dinámica con múltiples objetos de cada tipo.
	 * @return El beneficio máximo que se puede obtener.
	 */
	public double camionDPMP(){
		array = new double[P + 1];
		
		int n = this.objetos.size();
		for (int i = 0; i <= P; i++) {
			for (int j = 0; j < n; j++) {
				if(this.objetos.get(j).getPeso()  <= i) {
					array[i] = Math.max(array[i], array[i - (int)(this.objetos.get(j).getPeso()) ]+this.objetos.get(j).getBeneficio());
				}
			}
		}
		return array[array.length-1];
	}
	
	/**
	 * Método para obtener la mejor solución del problema de la mochila.
	 * @return Array de la mejor solución.
	 */
	public int[] mejorSolucion() {
		int numItems = this.objetos.size();
		int capacidad = P;
		double pesoMin = 0;
		int[] sol = new int[numItems];
		while(pesoMin <= 0) {
			double valorMax = 0;
			int pos = -1;
			for (int i = numItems-1; i >= 0; i--) {
				if(capacidad - this.objetos.get(i).getPeso()  >= 0) {
					double valorNuevo = array[capacidad - (int)this.objetos.get(i).getPeso() ] + this.objetos.get(i).getBeneficio();
					if(valorNuevo > valorMax) {
						valorMax = valorNuevo;
						pos = i;
					}
				}
			}
			if(pos == -1) {
				break;
			}
			sol[pos]++;
			capacidad -= this.objetos.get(pos).getPeso() ;
		}
		return sol;
	}
	
	/**
	 * Método para resolver el problema de la mochila utilizando un algoritmo voraz.
	 * @return Array de soluciones óptimas.
	 */
	public double[] Knapsackgreedy() {
	    int peso = 0;
	    boolean[] usado = new boolean[getN()];
	    double[] sol = new double[getN()];
	    for (int k = 0; k < getN(); k++) {
	        double maxBeneficio = 0;
	        int pos = -1;
	        for (int i = 0; i < getN(); i++) {
	            if (usado[i]) continue;
	            double rel = objetos.get(i).getBeneficio() / objetos.get(i).getPeso();
	            if (rel > maxBeneficio) {
	                maxBeneficio = rel;
	                pos = i;
	            }
	        }
	        if (pos == -1) break; // No se encontró ningún objeto que se pudiera añadir
	        usado[pos] = true;
	        if (peso + objetos.get(pos).getPeso() <= P) {
	            sol[pos] = 1;
	            peso += objetos.get(pos).getPeso();
	        } else {
	            sol[pos] = (P - peso) / objetos.get(pos).getPeso();
	            peso += sol[pos] * objetos.get(pos).getPeso();
	            break;
	        }
	    }
	    return sol;
	}


	
	/**
	 * Método para imprimir la tabla de programación dinámica.
	 */
	public void imprimirTabla() {
		for (int i = 0; i < tabla.length; i++) {
			for (int j = 0; j < tabla[0].length; j++) {
				if(tabla[i][j] == -1.0) {
					System.out.printf("%-5s", "-");
				}else {
					System.out.printf("%-5.1f", tabla[i][j]);
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * Método para imprimir el array de programación dinámica.
	 */
	public void imprimirArray() {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if(i < array.length-1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}
	
	/**
	 * Método para imprimir la solución óptima del problema de la mochila.
	 * @param sol Array de soluciones óptimas.
	 */
	public void ImprimirSolucionEnteros(int[] sol) {
		System.out.println("La capacidad es:  ("+P+")");
		double beneficio = 0;
		int peso = 0;
		for (int i = 0; i < sol.length; i++) {
			if(sol[i] > 0) {
				System.out.println(objetos.get(i) + (sol[i] != 1 ? " q="+sol[i] : ""));
				beneficio += objetos.get(i).getBeneficio() * sol[i];
				peso += objetos.get(i).getPeso() * sol[i];
			}
		}
		System.out.println("El beneficio total del camión es: " + beneficio+"€");
		System.out.println("El peso total del camión es: "+peso+"kg");
	}
	
	/**
	 * Método para imprimir los objetos de la mochila y su beneficio total.
	 * @param sol Array de soluciones óptimas.
	 */
	public void ImprimirObjetosDouble(double[] sol) {
		System.out.println("La capacidad es: ("+P+")");
		double beneficio = 0;
		double peso = 0;
		for (int i = 0; i < sol.length; i++) {
			if(sol[i] > 0) {
				System.out.println(objetos.get(i) + (sol[i] != 1 ? " q="+sol[i] : ""));
				beneficio += objetos.get(i).getBeneficio() * sol[i];
				peso += objetos.get(i).getPeso() * sol[i];
			}
		}
		System.out.println("El beneficio total del camión es: " + beneficio+"€");
		System.out.println("El peso total del camión es: "+peso+"kg");
	}
	
	/**
	 * Método para imprimir la solución del problema de la mochila.
	 * @param sol Array de soluciones óptimas.
	 */
	public void printArraySolution(double[] sol) {
		System.out.println("Solución: ");
		for (int i = 0; i < sol.length; i++) {
			System.out.print(sol[i]);
			if(i < sol.length-1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}

}
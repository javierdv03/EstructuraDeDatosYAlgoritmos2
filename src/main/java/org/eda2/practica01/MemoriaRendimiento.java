package org.eda2.practica01;

public class MemoriaRendimiento {

	public static void main(String[] args){
		int n = 8192;
		TorneoTenis caso1 = new TorneoTenis(new int[n + 1][n]);
		TorneoTenis.torneoRecursivoCaso1(caso1.getTabla(), n);
		double mem = calcularMemoria(caso1.getTabla());
		System.out.printf("La tabla ocupa %.4f MB de memoria prinicipal para el caso 1\n", mem);
		
		n = 8190;
		TorneoTenis caso2Par = new TorneoTenis(new int[n + 1][n]);
		TorneoTenis.torneoRecursivoCaso2(caso2Par.getTabla(), n);
		mem = calcularMemoria(caso2Par.getTabla());
		System.out.printf("La tabla ocupa %.4f MB de memoria prinicipal para el caso 2 par\n", mem);
		
		n = 8191;
		TorneoTenis caso2Impar = new TorneoTenis(new int[n + 2][n + 1]);
		TorneoTenis.torneoRecursivoCaso2(caso2Impar.getTabla(), n);
		mem = calcularMemoria(caso2Impar.getTabla());
		System.out.printf("La tabla ocupa %.4f MB de memoria prinicipal para el caso 2 impar\n", mem);
		
		n = 8192;
		TorneoTenis caso3 = new TorneoTenis(new int[n][n + 1]);
		TorneoTenis.torneoRecursivoCaso3(caso3.getTabla(), n);
		mem = calcularMemoria(caso3.getTabla());
		System.out.printf("La tabla ocupa %.4f MB de memoria prinicipal para el caso 3", mem);
	}

	private static double calcularMemoria(int[][] m){
	double t = m.length * m[0].length * (double) Integer.SIZE / 8.0;
	double conversionAMB = t / Math.pow(1024, 2);
	return conversionAMB;
 }
	
}
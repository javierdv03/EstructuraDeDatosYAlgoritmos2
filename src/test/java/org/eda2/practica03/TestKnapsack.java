package org.eda2.practica03;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class TestKnapsack {
	
	private static String directorioEntrada = System.getProperty("user.dir") 
			+ File.separator + "src" + File.separator + "main" + File.separator + "java"
			+ File.separator +"org"
			+ File.separator + "eda2" 
			+ File.separator + "practica03" 
			+ File.separator;
	
	@Test
	void testCamionRecursive() {
		for(int i = 1; i <= 8; i++) {
		
		Knapsack ks = new Knapsack(directorioEntrada+"p0" + String.valueOf(i));
		double realvalue = loadValue("p0" + String.valueOf(i));
		double value = ks.camionRecursive(ks.getN(), ks.getP(), ks.getWeigths(), ks.getProfits());
		assertTrue(realvalue == value);
		
		}
	}
	
	@Test
	void testCamionTable() {
		for(int i = 1; i <= 8; i++) {
			
		Knapsack ks = new Knapsack(directorioEntrada+"p0" + String.valueOf(i));
		double value = ks.camionTable(ks.getN(), ks.getP(), ks.getWeigths(), ks.getProfits());
		int[] realsol = loadSol("p0" + String.valueOf(i));
		double realvalue = loadValue("p0" + String.valueOf(i));
		int[] sol = ks.obtenerSolucionOptimaRecursiva();
		assertTrue(realvalue == value);
		assertTrue(realsol.length == sol.length);
		assertArrayEquals(realsol, sol);
		
		}
	}

	@Test
	void testCamionDP() {
		for(int i = 1; i <= 8; i++) {
		Knapsack ks = new Knapsack(directorioEntrada+"p0" + String.valueOf(i));
		ks.camionTable(ks.getN(), ks.getP(), ks.getWeigths(), ks.getProfits());
		int[] realsol = loadSol("p0" + String.valueOf(i));
		double realvalue = loadValue("p0" + String.valueOf(i));
		double value = ks.camionDP();
		int[] sol = ks.obtenerSolucionOptimaRecursiva();
		
		assertTrue(realvalue == value);
		assertTrue(realsol.length == sol.length);
		assertArrayEquals(realsol, sol);
		
		}
	}
	
	@Test
	void testCamionDP2() {
		for(int i = 1; i <= 8; i++) {
		Knapsack ks = new Knapsack(directorioEntrada+"p0" + String.valueOf(i));
		ks.camionTable(ks.getN(), ks.getP(), ks.getWeigths(), ks.getProfits());

		int[] realsol = loadSol("p0" + String.valueOf(i));
		double realvalue = loadValue("p0" + String.valueOf(i));
		double value = ks.camionDP2();
		int[] sol = ks.obtenerSolucionOptimaDinamicaIterativa();
		
		assertTrue(realvalue == value);
		assertTrue(realsol.length == sol.length);
		assertArrayEquals(realsol, sol);
		
		}
	}
	
	@Test
	void testCamionDP3() {
		for(int i = 1; i <= 8; i++) {
		Knapsack ks = new Knapsack(directorioEntrada+"p0" + String.valueOf(i));
		double realvalue = loadValue("p0" + String.valueOf(i));
		double value = ks.camionDP3();
		assertTrue(realvalue == value);
		
		}
	}
	
	@Test
	void testCamionDPMP() {
		double[] solucionesOptimasParaNTiposObj = new double[7];
		solucionesOptimasParaNTiposObj[0] = 644.0;
		solucionesOptimasParaNTiposObj[1] = 51.0;
		solucionesOptimasParaNTiposObj[2] = 155.0;
		solucionesOptimasParaNTiposObj[3] = 107.0;
		solucionesOptimasParaNTiposObj[4] = 1410.0;
		solucionesOptimasParaNTiposObj[5] = 1768.0;
		solucionesOptimasParaNTiposObj[6] = 1488.0;

		for(int i = 1; i <= 7; i++) {
	    Knapsack ks = new Knapsack(directorioEntrada+"p0" + String.valueOf(i));
		double realvalue = solucionesOptimasParaNTiposObj[i - 1] ;
		double value = ks.camionDPMP();
		assertTrue(realvalue == value);
		}
	}
	
	@Test
	void testCamionGreedy() {	
		int[] lengthSoluciones = new int[8];
		lengthSoluciones[0] = 5;
		lengthSoluciones[1] = 3;
		lengthSoluciones[2] = 3;
		lengthSoluciones[3] = 3;
		lengthSoluciones[4] = 3;
		lengthSoluciones[5] = 4;
		lengthSoluciones[6] = 8;
		lengthSoluciones[7] = 14;
		for(int i = 1; i <= 8; i++) {
		Knapsack ks = new Knapsack(directorioEntrada+"p0" + String.valueOf(i));
		double[] sol = ks.Knapsackgreedy();
		int value = 0;
		int realvalue = lengthSoluciones[i - 1];
		for(int j = 0; j < sol.length; j++) {
			if(sol[j] == 0.) continue;
			value++;
		}
		assertTrue(realvalue == value);
		}
	}
	
	
	
	private int[] loadSol(String basename) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			Scanner sc = new Scanner(new File(directorioEntrada+basename+"_s.txt"));
			while(sc.hasNextLine()) {
				list.add(Integer.parseInt(sc.nextLine()));
			}
			int[] sol = new int[list.size()];
			for (int i = 0; i < list.size(); i++) {
				sol[i] = list.get(i);
			}
			sc.close();
			return sol;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private double loadValue(String basename) {
		List<Integer> list = new ArrayList<Integer>();
		double value = 0;
		try {
			Scanner sc = new Scanner(new File(directorioEntrada+basename+"_s.txt"));
			Scanner sc2 = new Scanner(new File(directorioEntrada+basename+"_p.txt"));
			while(sc.hasNextLine()) {
				int x = Integer.parseInt(sc.nextLine());
				double p = Double.parseDouble(sc2.nextLine());
				list.add(x);
				if(x == 1) {
					value+=p;
				}
			}
			sc.close();
			sc2.close();
			return value;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
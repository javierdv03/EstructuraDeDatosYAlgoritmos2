package org.eda2.practica03;

public class PruebasRendimiento {
	
	public static String leerTexto(String msg) {
		do {
			System.out.println(msg);
			String opcion = MainKnapsack.sc.nextLine();
			if(!opcion.trim().isEmpty()) {
				return opcion.trim();
			}else {
				System.err.println("Error: Debe introducir un texto válido");
			}
		} while (true);
	}
	
	public static double leerReal(String msg, double min, double max) {
		do {
			System.out.println(msg);
			try {
				double opcion = Double.parseDouble(MainKnapsack.sc.nextLine());
				if(opcion >= min && opcion <= max) {
					return opcion;
				}else {
					System.err.println("Error: Debe introducir una opcion entre "+min+" y "+max);
				}
			} catch (NumberFormatException e) {
				System.err.println("Error: Debe introducir un numero");
			}
		} while (true);
	}
	
	public static int leerEntero(String msg, int min, int max) {
		do {
			System.out.println(msg);
			try {
				int opcion = Integer.parseInt(MainKnapsack.sc.nextLine());
				if(opcion  >= min && opcion <= max) {
					return opcion;
				}else {
					System.err.println("Error: Debe introducir una opcion entre "+min+" y "+max);
				}
			} catch (NumberFormatException e) {
				System.err.println("Error: Debe introducir un numero");
			}
		} while (true);
	}
	
	public static Knapsack generarKnapsackAleatorio() {
		int n = PruebasRendimiento.leerEntero("Introduzca el número de objetos: ", 1, Integer.MAX_VALUE);
		int P = PruebasRendimiento.leerEntero("Introduzca la capacidad máxima de la mochila: ", 0, Integer.MAX_VALUE);
		Knapsack ks = new Knapsack(P);
		for (int i = 0; i < n; i++) {
			Objeto obj = new Objeto("Objeto "+(i+1), (int)(Math.random()*P), (int)(Math.random()*100));
			ks.add(obj);
		}
		return ks;
	}
	
	public static Knapsack generarKnapsackManual() {
		int n = leerEntero("Introduzca el número de objetos: ", 1, Integer.MAX_VALUE);
		int P = leerEntero("Introduzca la capacidad máxima de la mochila: ", 0, Integer.MAX_VALUE);
		Knapsack ks = new Knapsack(P);
		for (int i = 0; i < n; i++) {
			String nombre = leerTexto("Introduzca el nombre del objeto "+(i+1));
			double weight = leerEntero("Introduzca el peso entero del objeto "+(i+1), 0, Integer.MAX_VALUE);
			double profit = leerReal("Introduzca el beneficio del objeto "+(i+1), 0, Double.MAX_VALUE);
			ks.add(new Objeto(nombre, weight, profit));
		}
		return ks;
	}
	
	public static void pruebasParaNConstanteYPVariable() {
    long start = 0;
    long end = 0;
    long sum = 0;
    int runs = 10;
    Knapsack ks;
    int n = 100;
    System.out.println("Rendimiento para mochila de "+n+" objetos");
    System.out.println("P\tRec\tRec2\tDP\tDP2\tDP3\tDPMD\tGreedy");
    for (int P = 1; P < 1000; P++) {
        ks = new Knapsack(P);
        for (int i = 0; i < n; i++) {
            Objeto obj = new Objeto("Objeto "+(i+1), (int)(Math.random()*P), (int)(Math.random()*100));
            ks.add(obj);
        }
        System.out.print(n+"\t");
        
        sum = 0;
        for (int i = 0; i < runs; i++) {
            start = System.nanoTime();
            ks.camionRecursive(ks.getN(), ks.getP(), ks.getWeigths(), ks.getProfits());
            end = System.nanoTime();
            sum += (end-start);
        }
        System.out.print(sum/runs + "\t");
        
        sum = 0;
        for (int i = 0; i < runs; i++) {
            start = System.nanoTime();
            ks.camionTable(ks.getN(), ks.getP(), ks.getWeigths(), ks.getProfits());
            end = System.nanoTime();
            sum += (end-start);
        }
        System.out.print(sum/runs + "\t");
        
        sum = 0;
        for (int i = 0; i < runs; i++) {
            start = System.nanoTime();
            ks.camionDP();
            end = System.nanoTime();
            sum += (end-start);
        }
        System.out.print(sum/runs + "\t");
        
        sum = 0;
        for (int i = 0; i < runs; i++) {
            start = System.nanoTime();
            ks.camionDP2();
            end = System.nanoTime();
            sum += (end-start);
        }
        System.out.print(sum/runs + "\t");
        
        sum = 0;
        for (int i = 0; i < runs; i++) {
            start = System.nanoTime();
            ks.camionDP3();
            end = System.nanoTime();
            sum += (end-start);
        }
        System.out.print(sum/runs + "\t");
        
        sum = 0;
        for (int i = 0; i < runs; i++) {
            start = System.nanoTime();
            ks.camionDPMP();
            end = System.nanoTime();
            sum += (end-start);
        }
        System.out.print(sum/runs + "\t");
        
        sum = 0;
        for (int i = 0; i < runs; i++) {
            start = System.nanoTime();
            ks.Knapsackgreedy();
            end = System.nanoTime();
            sum += (end-start);
        }
        System.out.print(sum/runs + "\n");
    }
}
	
	public static void pruebasParaNVariableYPConstante() {
    long start = 0;
    long end = 0;
    long sum = 0;
    int numRuns = 10;
    Knapsack ks;
    int P = 10;
    System.out.println("Rendimiento para mochila de capacidad "+P);
    System.out.println("N\tRec\tRec2\tDP\tDP2\tDP3\tDPMD\tGreedy");
    for (int n = 1; n < 1000; n++) {
        ks = new Knapsack(P);
        for (int i = 0; i < n; i++) {
            Objeto obj = new Objeto("Objeto "+(i+1), (int)(Math.random()*P), (int)(Math.random()*100));
            ks.add(obj);
        }
        System.out.print(n+"\t");
        
        sum = 0;
        for (int run = 0; run < numRuns; run++) {
            start = System.nanoTime();
            ks.camionRecursive(ks.getN(), ks.getP(), ks.getWeigths(), ks.getProfits());
            end = System.nanoTime();
            sum += (end-start);
        }
        System.out.print(sum/numRuns+"\t");
        
        sum = 0;
        for (int run = 0; run < numRuns; run++) {
            start = System.nanoTime();
            ks.camionTable(ks.getN(), ks.getP(), ks.getWeigths(), ks.getProfits());
            end = System.nanoTime();
            sum += (end-start);
        }
        System.out.print(sum/numRuns+"\t");
        
        sum = 0;
        for (int run = 0; run < numRuns; run++) {
            start = System.nanoTime();
            ks.camionDP();
            end = System.nanoTime();
            sum += (end-start);
        }
        System.out.print(sum/numRuns+"\t");
        
        sum = 0;
        for (int run = 0; run < numRuns; run++) {
            start = System.nanoTime();
            ks.camionDP2();
            end = System.nanoTime();
            sum += (end-start);
        }
        System.out.print(sum/numRuns+"\t");
        
        sum = 0;
        for (int run = 0; run < numRuns; run++) {
            start = System.nanoTime();
            ks.camionDP3();
            end = System.nanoTime();
            sum += (end-start);
        }
        System.out.print(sum/numRuns+"\t");
        
        sum = 0;
        for (int run = 0; run < numRuns; run++) {
            start = System.nanoTime();
            ks.camionDPMP();
            end = System.nanoTime();
            sum += (end-start);
        }
        System.out.print(sum/numRuns+"\t");
        
        sum = 0;
        for (int run = 0; run < numRuns; run++) {
            start = System.nanoTime();
            ks.Knapsackgreedy();
            end = System.nanoTime();
            sum += (end-start);
        }
        System.out.print(sum/numRuns+"\n");
    }
}



}

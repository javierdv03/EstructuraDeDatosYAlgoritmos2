package org.eda2.practica01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PruebaRendimiento {
    public static void main(String[] args) {
      
        int maximo = 20000; 
        TorneoTenis torneo = new TorneoTenis(new int[maximo+1][maximo]);
        int iteraciones = 10;  
      
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tiempos.txt"))) {
            for (int n = 2; n <= maximo; n *= 2) {  
                double totalTiempoMilisegundos = 0;
                for (int i = 0; i < iteraciones; i++) {
                    long inicio = System.nanoTime();
                    TorneoTenis.torneoRecursivoCaso1(torneo.getTabla(), n);
                    long fin = System.nanoTime();

                    long tiempoNanoSegundos = fin - inicio;
                    totalTiempoMilisegundos += tiempoNanoSegundos / 1_000_000.0;
                }
                double avgTiempoMilisegundos = totalTiempoMilisegundos / iteraciones;
                String output;
                if (avgTiempoMilisegundos > 1000) {
                    double avgTiempoSegundos = avgTiempoMilisegundos / 1000.0;
                    output = "El tiempo medio de ejecución para n = " + n + " es " + avgTiempoSegundos + " segundos (CASO 1).\n";
                } else {
                    output = "El tiempo medio de ejecución para n = " + n + " es " + avgTiempoMilisegundos + " milisegundos (CASO 1).\n";
                }
                writer.write(output);
            }
            
            writer.write("\n");
            
            for (int n = 2; n <= maximo; n *= 2) {
                double totalTiempoMilisegundos = 0;
                for (int i = 0; i < iteraciones; i++) {
                    long inicio = System.nanoTime();
                    TorneoTenis.torneoRecursivoCaso2(torneo.getTabla(), n);
                    long fin = System.nanoTime();

                    long tiempoNanoSegundos = fin - inicio;
                    totalTiempoMilisegundos += tiempoNanoSegundos / 1_000_000.0;
                }
                double avgTiempoMilisegundos = totalTiempoMilisegundos / iteraciones;
                String output;
                if (avgTiempoMilisegundos > 1000) {
                    double avgTiempoSegundos = avgTiempoMilisegundos / 1000.0;
                    output = "El tiempo medio de ejecución para n = " + n + " es " + avgTiempoSegundos + " segundos (CASO 2 PAR).\n";
                } else {
                    output = "El tiempo medio de ejecución para n = " + n + " es " + avgTiempoMilisegundos + " milisegundos (CASO 2 PAR).\n";
                }
                writer.write(output);
            }
            writer.write("\n");
            
            for (int n = 3; n <= maximo; n *= 3) {
            	double totalTiempoMilisegundos = 0;
            	for (int i = 0; i < iteraciones; i++) {
            	long inicio = System.nanoTime();
                TorneoTenis.torneoRecursivoCaso2(torneo.getTabla(), n);
                long fin = System.nanoTime();

                long tiempoNanoSegundos = fin - inicio;
                 totalTiempoMilisegundos += tiempoNanoSegundos / 1_000_000.0;
            	}
                double avgTiempoMilisegundos = totalTiempoMilisegundos / iteraciones;
                String output;
                if (avgTiempoMilisegundos > 1000) {
                    double avgtiempoSegundos = avgTiempoMilisegundos / 1000.0;
                    output = "El tiempo de ejecución para n = " + n + " es " + avgtiempoSegundos + " segundos (CASO 2 IMPAR).\n";
                } else {
                    output = "El tiempo de ejecución para n = " + n + " es " + avgTiempoMilisegundos + " milisegundos (CASO 2 IMPAR).\n";
                }
                writer.write(output);
            }
            writer.write("\n");
            
            for (int n = 2; n <= maximo; n *= 2) {
                double totalTiempoMilisegundos = 0;
                for (int i = 0; i < iteraciones; i++) {
                    long inicio = System.nanoTime();
                    TorneoTenis.torneoRecursivoCaso3(torneo.getTabla(), n);
                    long fin = System.nanoTime();

                    long tiempoNanoSegundos = fin - inicio;
                    totalTiempoMilisegundos += tiempoNanoSegundos / 1_000_000.0;
                }
                double avgTiempoMilisegundos = totalTiempoMilisegundos / iteraciones;
                String output;
                if (avgTiempoMilisegundos > 1000) {
                    double avgTiempoSegundos = avgTiempoMilisegundos / 1000.0;
                    output = "El tiempo medio de ejecución para n = " + n + " es " + avgTiempoSegundos + " segundos (CASO 3).\n";
                } else {
                    output = "El tiempo medio de ejecución para n = " + n + " es " + avgTiempoMilisegundos + " milisegundos (CASO 3).\n";
                }
                writer.write(output);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("He terminado");
    }
}
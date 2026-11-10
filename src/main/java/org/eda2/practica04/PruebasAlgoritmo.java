package org.eda2.practica04;

import java.util.List;

/**
 * La clase PruebasAlgoritmo proporciona métodos estáticos para realizar pruebas de rendimiento
 * de diferentes algoritmos de carga de contenedores en un buque.
 */
public class PruebasAlgoritmo {

    private static Buque buque;
    private static int algoritmoSeleccionado = -1;

    /**
     * Establece el buque en el que se realizarán las pruebas de rendimiento.
     * @param buque El buque en el que se realizarán las pruebas.
     */
    public static void setBuque(Buque buque) {
        PruebasAlgoritmo.buque = buque;
    }

    /**
     * Establece el algoritmo seleccionado para las pruebas de rendimiento.
     * @param algoritmo El número que identifica el algoritmo seleccionado.
     */
    public static void setAlgoritmoSeleccionado(int algoritmo) {
        PruebasAlgoritmo.algoritmoSeleccionado = algoritmo;
    }

    /**
     * Realiza pruebas de rendimiento del algoritmo seleccionado.
     * Imprime el tiempo que tarda en ejecutarse el algoritmo.
     */
    public static void realizarPruebasRendimiento() {
        if (buque == null) {
            System.out.println("Primero debe crear el buque y los contenedores.");
            return;
        }

        if (algoritmoSeleccionado == -1) {
            System.out.println("Primero debe seleccionar y ejecutar un algoritmo.");
            return;
        }

        int capacidadBuque = buque.getCapacidad();
        int[] pesosContenedores = new int[buque.getContenedores().size()];
        for (int i = 0; i < buque.getContenedores().size(); i++) {
            pesosContenedores[i] = buque.getContenedores().get(i).getPeso();
        }

        switch (algoritmoSeleccionado) {
            case 2:
                long startTime = System.nanoTime();
                GreedyContLoading.cargarBuque(buque);
                long endTime = System.nanoTime();
                System.out.println("El algoritmo Greedy tarda " + (endTime - startTime) + " nanosegundos");
                break;
            case 3:
                startTime = System.nanoTime();
                RecursiveBTContLoading1.encontrarPesoMax(capacidadBuque, pesosContenedores);
                endTime = System.nanoTime();
                System.out.println("El algoritmo Recursivo1 tarda " + (endTime - startTime) + " nanosegundos");
                break;
            case 4:
                startTime = System.nanoTime();
                RecursiveBTContLoading2.btRec02(0, capacidadBuque, 0, 0, buque, null);
                endTime = System.nanoTime();
                System.out.println("El algoritmo Recursivo2 tarda " + (endTime - startTime) + " nanosegundos");
                break;
            case 5:
                startTime = System.nanoTime();
                int[] solucionRecursivo3 = new int[buque.getContenedores().size()];
                int[] soaRecursivo3 = new int[buque.getContenedores().size()];
                RecursiveBTContLoading3.btRec03(0, capacidadBuque, 0, 0, buque, solucionRecursivo3, soaRecursivo3);
                endTime = System.nanoTime();
                System.out.println("El algoritmo Recursivo3 tarda " + (endTime - startTime) + " nanosegundos");
                break;
            case 6:
                startTime = System.nanoTime();
                IterativeBTContLoading.btIter01(pesosContenedores, capacidadBuque, buque.getContenedores());
                endTime = System.nanoTime();
                System.out.println("El algoritmo Iterativo tarda " + (endTime - startTime) + " nanosegundos");
                break;
            default:
                System.out.println("Algoritmo no válido o no implementado para pruebas de rendimiento.");
        }
    }
}

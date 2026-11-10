package org.eda2.practica04;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Clase ContLoadingTest que contiene pruebas unitarias para los algoritmos de carga de contenedores.
 */

public class ContLoadingTest {

    /**
     * Prueba el algoritmo GreedyContLoading.
     */
	
    @Test
    public void testGreedyContLoading() {
        ArrayList<Contenedor> contenedores = new ArrayList<>();
        Contenedor contenedor1 = new Contenedor(10, 1);
        Contenedor contenedor2 = new Contenedor(20, 2);
        Contenedor contenedor3 = new Contenedor(40, 3);
        Contenedor contenedor4 = new Contenedor(51, 4);
        Contenedor contenedor5 = new Contenedor(51, 5);
        contenedores.add(contenedor1);
        contenedores.add(contenedor2);
        contenedores.add(contenedor3);
        contenedores.add(contenedor4);
        contenedores.add(contenedor5);

        Buque buque = new Buque(50, contenedores);

        // Redirigir la salida estándar a un ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Ejecutar el algoritmo
        GreedyContLoading.cargarBuque(buque);

        // Restaurar la salida estándar original
        System.setOut(originalOut);

        // Obtener la salida impresa
        String output = outputStream.toString();

        // Verificar si la salida contiene la información esperada
        assertEquals(2, contenedoresCargados(output), "El número de contenedores cargados no es el esperado");
    }

    /**
     * Método auxiliar para extraer el número de contenedores cargados de la salida.
     *
     * @param output La salida del algoritmo.
     * @return El número de contenedores cargados.
     */    
    private int contenedoresCargados(String output) {
        String[] lines = output.split("\n");
        for (String line : lines) {
            if (line.startsWith("Total de contenedores cargados:")) {
                return Integer.parseInt(line.split(":")[1].trim());
            }
        }
        return 0; // Si no se encuentra la línea, devolvemos 0
    }


     /**
      * Prueba el algoritmo RecursiveBTContLoading1.
      */
    
    @Test
    public void testRecursiveBTContLoading1() {
        int pesoMax = 5;
        int[] weights = {3, 1, 5, 6};
        int expectedMaxWeight = 5; 

        int result = RecursiveBTContLoading1.encontrarPesoMax(pesoMax, weights);

        assertEquals(expectedMaxWeight, result, "El peso máximo calculado no es el esperado");
    }
    
    /**
     * Prueba el algoritmo RecursiveBTContLoading2.
     */
    
    @Test
    public void testRecursiveBTContLoading2() {  	
        // Crea una instancia de Buque con datos de prueba (reemplaza con datos reales)
        ArrayList<Contenedor> contenedores = new ArrayList<>();
        Contenedor contenedor1 = new Contenedor(10, 1);
        Contenedor contenedor2 = new Contenedor(20, 2);
        Contenedor contenedor3 = new Contenedor(10, 3);
        contenedores.add(contenedor1);
        contenedores.add(contenedor2);
        contenedores.add(contenedor3);
        Buque buque = new Buque(50, contenedores);

        // Ejecuta el algoritmo
        RecursiveBTContLoading2.btRec02(0, buque.getCapacidad(), 0, 0, buque, null);

        // Obtiene la mejor solución del TreeMap
        TreeMap<Integer, ArrayList<Contenedor>> sol = RecursiveBTContLoading2.getSol();
        Map.Entry<Integer, ArrayList<Contenedor>> mejorSolucion = sol.lastEntry();

        // Verifica el resultado esperado (reemplaza con los valores esperados reales)
        assertEquals(40, mejorSolucion.getKey(), "El peso total no es el esperado");
        assertEquals(3, mejorSolucion.getValue().size(), "El número de contenedores cargados no es el esperado");
    }
    
    /**
     * Prueba el algoritmo RecursiveBTContLoading3.
     */
    
    @Test
    public void testRecursiveBTContLoading3() {
        // Crea una instancia de Buque con datos de prueba (reemplaza con datos reales)
        ArrayList<Contenedor> contenedores = new ArrayList<>();
        Contenedor contenedor1 = new Contenedor(10, 1);
        Contenedor contenedor2 = new Contenedor(20, 2);
        Contenedor contenedor3 = new Contenedor(40, 3);
        contenedores.add(contenedor1);
        contenedores.add(contenedor2);
        contenedores.add(contenedor3);
        Buque buque = new Buque(50, contenedores);

        // Ejecuta el algoritmo
        int[] s = new int[buque.arrayPesos().length];
        int[] soa = new int[buque.arrayPesos().length];
        RecursiveBTContLoading3.btRec03(0, buque.getCapacidad(), 0, 0, buque, s, soa);

        // Obtiene la mejor solución del TreeMap
        TreeMap<Integer, ArrayList<Contenedor>> sol = RecursiveBTContLoading3.getSol();
        Map.Entry<Integer, ArrayList<Contenedor>> mejorSolucion = sol.lastEntry();

        // Verifica el resultado esperado (reemplaza con los valores esperados reales)
        assertEquals(50, mejorSolucion.getKey(), "El peso total no es el esperado");
        assertEquals(2, mejorSolucion.getValue().size(), "El número de contenedores cargados no es el esperado");
    }
    
    /**
     * Prueba el algoritmo IterativeBTContLoading.
     */
    
    @Test
    public void testIterativeBTContLoading() {
        // Crea una instancia de Buque con datos de prueba (reemplaza con datos reales)
        ArrayList<Contenedor> contenedores = new ArrayList<>();
        Contenedor contenedor1 = new Contenedor(10, 1);
        Contenedor contenedor2 = new Contenedor(20, 2);
        Contenedor contenedor3 = new Contenedor(600, 3);
        contenedores.add(contenedor1);
        contenedores.add(contenedor2);
        contenedores.add(contenedor3);
        Buque buque = new Buque(50, contenedores);

        // Ejecuta el algoritmo
        int result = IterativeBTContLoading.btIter01(buque.arrayPesos(), buque.getCapacidad(), contenedores);
    
        // Verifica el resultado esperado (reemplaza con los valores esperados reales)
        assertEquals(30, result, "El peso total no es el esperado");
    }
}

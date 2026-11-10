package org.eda2.practica02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Clase de pruebas unitarias para la clase AlgoritmosEDALand.
 */
public class AlgoritmosEDALandTest {
	
	/**
	 * Prueba el método PrimSinPQ de la clase AlgoritmosEDALand.
	 */
	@Test
	public void testPrimSinPQ() {
		
	    AlgoritmosEDALand algoritmos = new AlgoritmosEDALand();

	    Network<String> network = new Network<>();

	    network.addVertex("A");
	    network.addVertex("B");
	    network.addVertex("C");
	    network.addVertex("D");
	    network.addVertex("E");
	    network.addVertex("F");

	    network.addEdge("A", "B", 1.0);
	    network.addEdge("B", "C", 2.0);
	    network.addEdge("A", "D", 4.0);
	    network.addEdge("B", "E", 3.0);
	    network.addEdge("B", "D", 2.0);
	    network.addEdge("C", "F", 5.0);
	    network.addEdge("E", "F", 3.0);

	    algoritmos = new AlgoritmosEDALand(network);

	    ArrayList<Arista> result = algoritmos.primSinPQ("A");

	    assertEquals(5, result.size());
	    
	    assertTrue(result.contains(new Arista("A", "B", 1.0)));
	    assertTrue(result.contains(new Arista("B", "C", 2.0)));
	    assertTrue(result.contains(new Arista("B", "D", 2.0)));
	    assertTrue(result.contains(new Arista("B", "E", 3.0)));
	    assertTrue(result.contains(new Arista("E", "F", 3.0)));
	    
	}

	/**
	 * Prueba el método PrimConPQ de la clase AlgoritmosEDALand.
	 */
    @Test
    public void testPrimConPQ() {
    	
        AlgoritmosEDALand algoritmos = new AlgoritmosEDALand();

        Network<String> network = new Network<>();

        network.addVertex("A");
        network.addVertex("B");
        network.addVertex("C");
        network.addVertex("D");
        network.addVertex("E");
        network.addVertex("F");

        network.addEdge("A", "B", 1.0);
        network.addEdge("B", "C", 2.0);
        network.addEdge("A", "D", 4.0);
        network.addEdge("B", "E", 3.0);
        network.addEdge("B", "D", 2.0);
        network.addEdge("C", "F", 5.0);
        network.addEdge("E", "F", 3.0);

        algoritmos = new AlgoritmosEDALand(network);

        ArrayList<Arista> result = algoritmos.primConPQ("A");

        assertEquals(5, result.size());

        assertTrue(result.contains(new Arista("A", "B", 1.0)));
	    assertTrue(result.contains(new Arista("B", "C", 2.0)));
	    assertTrue(result.contains(new Arista("B", "D", 2.0)));
	    assertTrue(result.contains(new Arista("B", "E", 3.0)));
	    assertTrue(result.contains(new Arista("E", "F", 3.0)));
    }

    /**
     * Prueba el método Kruskal de la clase AlgoritmosEDALand.
     */
    @Test
    public void testKruskal() {
    	
        AlgoritmosEDALand algoritmos = new AlgoritmosEDALand();

        Network<String> network = new Network<>();

        network.addVertex("A");
        network.addVertex("B");
        network.addVertex("C");
        network.addVertex("D");
        network.addVertex("E");
        network.addVertex("F");

        network.addEdge("A", "B", 1.0);
        network.addEdge("B", "C", 2.0);
        network.addEdge("A", "D", 4.0);
        network.addEdge("B", "E", 3.0);
        network.addEdge("B", "D", 2.0);
        network.addEdge("C", "F", 5.0);
        network.addEdge("E", "F", 3.0);

        algoritmos = new AlgoritmosEDALand(network);

        TreeSet<Arista> result = algoritmos.Kruskal();

        assertEquals(5, result.size());

        assertTrue(result.contains(new Arista("A", "B", 1.0)));
	    assertTrue(result.contains(new Arista("B", "C", 2.0)));
	    assertTrue(result.contains(new Arista("B", "D", 2.0)));
	    assertTrue(result.contains(new Arista("B", "E", 3.0)));
	    assertTrue(result.contains(new Arista("E", "F", 3.0)));
    }
    
    /**
     * Prueba el método TSPGreedy de la clase AlgoritmosEDALand cuando no hay solución.
     */
    @Test
    public void testTSPGreedySinSolucion() {
    	
	    Network<String> network = new Network<>();

	    network.addVertex("A");
	    network.addVertex("B");
	    network.addVertex("C");
	    network.addVertex("D");
	    network.addVertex("E");
	    network.addVertex("F");

	    network.addEdge("A", "B", 1.0);
	    network.addEdge("B", "C", 2.0);
	    network.addEdge("A", "D", 4.0);
	    network.addEdge("B", "E", 3.0);
	    network.addEdge("B", "D", 2.0);
	    network.addEdge("C", "F", 5.0);
	    network.addEdge("E", "F", 3.0);
	    
	    assertThrows(RuntimeException.class, () -> {
	    	AlgoritmosEDALand algoritmos = new AlgoritmosEDALand(network);
	        algoritmos.TSPGreedy(network, "A");
	    });
    }
    
    /**
     * Prueba el método TSPGreedy de la clase AlgoritmosEDALand cuando hay una solución.
     */
    @Test
    public void testTSPGreedyConSolucion() {
        AlgoritmosEDALand algoritmos = new AlgoritmosEDALand();

        Network<String> network = new Network<>();

        network.addVertex("A");
        network.addVertex("B");
        network.addVertex("C");
        network.addVertex("D");
        network.addVertex("E");

        network.addEdge("A", "B", 1.0);
        network.addEdge("A", "C", 2.0);
        network.addEdge("A", "D", 3.0);
        network.addEdge("A", "E", 4.0);
        network.addEdge("B", "C", 5.0);
        network.addEdge("B", "D", 6.0);
        network.addEdge("B", "E", 7.0);
        network.addEdge("C", "D", 8.0);
        network.addEdge("C", "E", 9.0);
        network.addEdge("D", "E", 10.0);

        algoritmos = new AlgoritmosEDALand(network);

        ArrayList<Arista> result = algoritmos.TSPGreedy(network, "A");

        assertEquals(5, result.size());

        assertTrue(result.contains(new Arista("A", "B", 1.0)));
        assertTrue(result.contains(new Arista("B", "C", 5.0)));
        assertTrue(result.contains(new Arista("C", "D", 8.0)));
        assertTrue(result.contains(new Arista("D", "E", 10.0)));
        assertTrue(result.contains(new Arista("A", "E", 24.0)));

    }
}

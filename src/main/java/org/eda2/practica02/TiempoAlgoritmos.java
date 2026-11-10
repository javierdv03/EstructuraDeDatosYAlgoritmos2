package org.eda2.practica02;

/**
 * La clase TiempoAlgoritmos proporciona métodos estáticos para medir el tiempo de ejecución y el uso de memoria de varios algoritmos.
 */
public class TiempoAlgoritmos {

	private static final int numEjecuciones = 10;
    
	/**
	 * Mide el tiempo de ejecución del algoritmo de Prim sin cola de prioridad y registra el uso de memoria.
	 * 
	 * @param algoritmos El objeto AlgoritmosEDALand que contiene la implementación del algoritmo.
	 * @param origen El origen desde el cual se ejecutará el algoritmo.
	 * @return El tiempo promedio de ejecución del algoritmo en milisegundos.
	 */
	public static float medirPrimSinPQ(AlgoritmosEDALand algoritmos, String origen) {
	    float tiempoTotal = 0;
	    long memTotal = 0;
	    for(int i = 0; i < numEjecuciones; i++) {
	        System.gc();
	        long startTime = System.currentTimeMillis();
	        long startMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	        algoritmos.primSinPQ(origen);
	        long endTime = System.currentTimeMillis();
	        long endMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	        tiempoTotal += (endTime - startTime);
	        memTotal += (endMem - startMem);
	    }
	    System.out.println("Memoria utilizada en PrimSinPQ: " + memTotal/numEjecuciones + " bytes");
	    return tiempoTotal/numEjecuciones;
	}

	/**
	 * Mide el tiempo de ejecución del algoritmo de Prim con cola de prioridad y registra el uso de memoria.
	 * 
	 * @param algoritmos El objeto AlgoritmosEDALand que contiene la implementación del algoritmo.
	 * @param origen El origen desde el cual se ejecutará el algoritmo.
	 * @return El tiempo promedio de ejecución del algoritmo en milisegundos.
	 */
	public static float medirPrimConPQ(AlgoritmosEDALand algoritmos, String origen) {
	    float tiempoTotal = 0;
	    long memTotal = 0;
	    for(int i = 0; i < numEjecuciones; i++) {
	        System.gc();
	        long startTime = System.currentTimeMillis();
	        long startMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	        algoritmos.primConPQ(origen);
	        long endTime = System.currentTimeMillis();
	        long endMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	        tiempoTotal += (endTime - startTime);
	        memTotal += (endMem - startMem);
	    }
	    System.out.println("Memoria utilizada en PrimConPQ: " + memTotal/numEjecuciones + " bytes");
	    return tiempoTotal/numEjecuciones;
	}

	/**
	 * Mide el tiempo de ejecución del algoritmo de Kruskal y registra el uso de memoria.
	 * 
	 * @param algoritmos El objeto AlgoritmosEDALand que contiene la implementación del algoritmo.
	 * @return El tiempo promedio de ejecución del algoritmo en milisegundos.
	 */
	public static float medirKruskal(AlgoritmosEDALand algoritmos) {
	    float tiempoTotal = 0;
	    long memTotal = 0;
	    for(int i = 0; i < numEjecuciones; i++) {
	        System.gc();
	        long startTime = System.currentTimeMillis();
	        long startMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	        algoritmos.Kruskal();
	        long endTime = System.currentTimeMillis();
	        long endMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	        tiempoTotal += (endTime - startTime);
	        memTotal += (endMem - startMem);
	    }
	    System.out.println("Memoria utilizada en Kruskal: " + memTotal/numEjecuciones + " bytes");
	    return tiempoTotal/numEjecuciones;
	}

	/**
	 * Mide el tiempo de ejecución del algoritmo del Problema del Viajante (TSP) utilizando un enfoque Greedy
	 * y registra el uso de memoria.
	 * 
	 * @param algoritmos El objeto AlgoritmosEDALand que contiene la implementación del algoritmo.
	 * @param netDeCiudades La red de ciudades sobre la cual se ejecutará el algoritmo.
	 * @param cOrigen El origen desde el cual se iniciará el recorrido.
	 * @return El tiempo promedio de ejecución del algoritmo en milisegundos.
	 */
	public static float medirTSPGreedy(AlgoritmosEDALand algoritmos, Network<String> netDeCiudades, String cOrigen) {
	    float tiempoTotal = 0;
	    long memTotal = 0;
	    for(int i = 0; i < numEjecuciones; i++) {
	        System.gc();
	        long startTime = System.currentTimeMillis();
	        long startMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	        algoritmos.TSPGreedy(netDeCiudades, cOrigen);
	        long endTime = System.currentTimeMillis();
	        long endMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	        tiempoTotal += (endTime - startTime);
	        memTotal += (endMem - startMem);
	    }
	    System.out.println("Memoria utilizada en TSPGreedy: " + memTotal/numEjecuciones + " bytes");
	    return tiempoTotal/numEjecuciones;
	}

	/**
	 * Mide el tiempo de ejecución del algoritmo del Problema del Viajante (TSP) utilizando un enfoque de búsqueda exhaustiva
	 * y registra el uso de memoria.
	 * 
	 * @param netDeCiudades La red de ciudades sobre la cual se ejecutará el algoritmo.
	 * @param cOrigen El origen desde el cual se iniciará el recorrido.
	 * @return El tiempo promedio de ejecución del algoritmo en milisegundos.
	 */
	public static float medirTSPGreedyOptimo(Network<String> netDeCiudades, String cOrigen) {
	    float tiempoTotal = 0;
	    long memTotal = 0;
	    for(int i = 0; i < numEjecuciones; i++) {
	        System.gc();
	        long startTime = System.currentTimeMillis();
	        long startMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	        AlgoritmosEDALand.TSPSimplePaths(netDeCiudades, cOrigen);
	        long endTime = System.currentTimeMillis();
	        long endMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	        tiempoTotal += (endTime - startTime);
	        memTotal += (endMem - startMem);
	    }
	    System.out.println("Memoria utilizada en TSPGreedyOptimo: " + memTotal/numEjecuciones + " bytes");
	    return tiempoTotal/numEjecuciones;
	}

}

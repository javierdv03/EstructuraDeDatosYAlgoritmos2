package org.eda2.practica02;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.io.File;

/**
 * AlgoritmosEDALand contiene implementaciones de algoritmos relacionados con grafos que hemos determinado
 * con la clase Network sacada de EDA1. Trata de hallar el arbol de expansión mínima de dichos grafos con algoritmos diferentes,
 * como son el algoritmo de Prim y Kruskal. 
 * 
 * Los algoritmos TSP tratan de buscar otro objetivo distinto. El algoritmo
 * TSPGreedy es un algoritmo voraz que busca el camino mas corto que conecta todos los vertices del grafo una sola vez,
 * mientras que el TSP de caminos simples busca el mismo objetivo, solo que con menos porcentaje de error y de una manera
 * mas optima.
 */
public class AlgoritmosEDALand {
	
	private Network<String> net = new Network<>();
	private PriorityQueue<Arista> pq = new PriorityQueue<>();

	
	/**
     * Carga una red desde un archivo.
     * 
     * @param nombreArchivo El nombre del archivo que contiene la red.
     * @return True si la carga se realizó con éxito, false si ocurrió un error.
     */
	
	public boolean load(String nombreArchivo) {
		Scanner scan = null;
		this.net.clear();
		int nVertices = 0;
		int cont = 0;
		try {
			scan = new Scanner(new File (nombreArchivo));
			scan.nextLine();
			nVertices = Integer.parseInt(scan.nextLine());
		}catch(IOException e) {
			return false;
		}
		while(scan.hasNextLine()) {
			String linea = scan.nextLine().trim();
			String[] tokens = linea.split(" ");
			if(cont < nVertices) {
				this.net.addVertex(linea);
			}
			else if(cont == nVertices) {
				cont++;
				continue;
				
			}
			else {
			this.net.addEdge(tokens[0], tokens[1], Double.parseDouble(tokens[2]));
			this.pq.add(new Arista(tokens[0], tokens[1], Double.parseDouble(tokens[2])));
			}
			cont++;
	}
		scan.close();
		return true;
	}
	
	/**
     * Devuelve la red cargada.
     * 
     * @return La red cargada.
     */
	
	public Network<String> getNetwork() {
		return this.net;
	}
	
	/**
     * Constructor de AlgoritmosEDALand con una red dada.
     * 
     * @param red La red dada.
     */
	
	public AlgoritmosEDALand(Network<String> red) {
		this.net = red;
	}
	
	/**
     * Constructor de AlgoritmosEDALand con una red y una cola de prioridad de aristas dadas.
     * 
     * @param red  La red dada.
     * @param cola La cola de prioridad de aristas dada.
     */
	
	public AlgoritmosEDALand(Network<String> red, PriorityQueue<Arista> cola) {
		this.net = red;
		this.pq = cola;
	}
	
	/**
     * Constructor por defecto de AlgoritmosEDALand.
     */
	
	public AlgoritmosEDALand() {
		
	}
	
	/**
	 * Implementación del algoritmo de Prim sin usar una cola de prioridad.
	 * 
	 * El algoritmo comienza desde un vértice de origen y explora los vecinos de cada vértice, 
	 * eligiendo la arista de peso mínimo en cada paso para expandir el árbol de expansión mínima. 
	 * 
	 * Dado que no se utiliza una cola de prioridad para seleccionar la arista de peso mínimo en cada 
	 * iteración, esta implementación tiene una complejidad temporal mayor en comparación con la versión 
	 * que utiliza una cola de prioridad. 
	 * 
	 * @param origen El vértice de origen para comenzar el algoritmo.
	 * @return Un ArrayList que contiene las aristas del árbol de expansión mínima.
	 */
	
    public ArrayList<Arista> primSinPQ(String origen) {
		
		ArrayList<Arista> sol = new ArrayList<>();
		ArrayList<Arista> aristasActivadas = new ArrayList<>();
		TreeSet<String> verticesSol = new TreeSet<>();
		
		verticesSol.add(origen);
		
		String curr = origen;
		
		while(sol.size() < net.numberOfVertices() - 1) {
			TreeSet<String> neighbors = net.getNeighbors(curr);
			for(String destino : neighbors) {
				if(verticesSol.contains(destino)) continue;
				Double peso = net.getWeight(curr, destino);
				Arista a = new Arista(curr, destino, peso);
				aristasActivadas.add(a);
			}
			
			Arista aristaMenor = new Arista();
			Double wAux = Double.MAX_VALUE;
			
			for(Arista ar : aristasActivadas) {
				Double wTemp = ar.getPeso();
				if(wTemp < wAux) {
					aristaMenor = ar;
					wAux = ar.getPeso();
				}
			}
			
			sol.add(aristaMenor);
			aristasActivadas.remove(aristaMenor);
			verticesSol.add(aristaMenor.getvDestino());
			String vActual = aristaMenor.getvDestino();
			
			TreeSet<Arista> setAux = new TreeSet<>();
			
			for(Arista a : aristasActivadas) {
				if(verticesSol.contains(a.getvOrigen()) && verticesSol.contains(a.getvDestino())) {
					setAux.add(a);
				}
			}
		     
			for(Arista a : setAux) {
				aristasActivadas.remove(a);
			}
			curr = vActual;
			
	}	
	return sol;	
}
	
    /**
     * Implementación del algoritmo de Prim usando una cola de prioridad. Logra el mismo objetivo que el algoritmo
     * sin cola de prioridad y de una manera más eficiente
     * 
     * @param origen El vértice de origen para comenzar el algoritmo.
     * @return Un ArrayList que contiene las aristas del árbol de expansión mínima.
     */
    
	public ArrayList<Arista> primConPQ(String origen) {
		
		ArrayList<Arista> sol = new ArrayList<>();
		PriorityQueue<Arista> aristasActivadas = new PriorityQueue<>();
		TreeSet<String> verticesSol = new TreeSet<>();
        
		verticesSol.add(origen);
		
		String curr = origen;
		
		while(sol.size() < net.numberOfVertices() - 1) {
			TreeSet<String> neighbors = net.getNeighbors(curr);
			for(String destino : neighbors) {
				if(verticesSol.contains(destino)) continue;
				Double peso = net.getWeight(curr, destino);
				aristasActivadas.add(new Arista(curr, destino, peso));
			}
		
			Arista aristaMenor = aristasActivadas.poll();
			sol.add(aristaMenor);
			verticesSol.add(aristaMenor.getvDestino());
			
            TreeSet<Arista> setAux = new TreeSet<>();
			
			for(Arista a : aristasActivadas) {
				if(verticesSol.contains(a.getvOrigen()) && verticesSol.contains(a.getvDestino())) {
					setAux.add(a);
				}
			}
			
			for(Arista a : setAux) {
				aristasActivadas.remove(a);
			}
			curr = aristaMenor.getvDestino();
		}
		return sol;
	} 
	
	/**
	 * Implementación del algoritmo de Kruskal para encontrar el árbol de expansión mínimo (MST) de una red.
	 * 
	 * @return Un conjunto de aristas que forman el árbol de expansión mínimo (MST).
	 */
    public TreeSet<Arista> Kruskal() {
		
		TreeSet<Arista> sol = new TreeSet<>();
		HashMap<String, String> verticesDesde = new HashMap<>();
		PriorityQueue<Arista> pq = new PriorityQueue<>();
		HashMap<String, Integer> gruposTamano = new HashMap<>();
		TreeMap<String, TreeMap<String, Double>> graph = net.getGraph();
		
		for(Entry<String, TreeMap<String, Double>> it : graph.entrySet()) {
			String from = it.getKey();
			verticesDesde.put(from, from);
			gruposTamano.put(from, 1);
			
			for(Entry<String, Double> it2 : it.getValue().entrySet()) {
				String to = it2.getKey();
				Double peso = it2.getValue();
				if(from.compareTo(to) > 0) continue;
				pq.add(new Arista(from, to, peso));
			}
			
		}
		
		while(sol.size() < graph.size() - 1) {
			Arista aTemp = pq.poll();
			String raiz1 = buscarRaiz(verticesDesde, aTemp.getvOrigen());
			String raiz2 = buscarRaiz(verticesDesde, aTemp.getvDestino());
			
			if(!raiz1.equals(raiz2)) {
				sol.add(aTemp);
				int tamano1 = gruposTamano.get(raiz1);
				int tamano2 = gruposTamano.get(raiz2);
				
				if(tamano1 < tamano2) {
					verticesDesde.put(raiz1, raiz2);
					gruposTamano.put(raiz2, tamano1 + tamano2);
				} else {
					verticesDesde.put(raiz2, raiz1);
					gruposTamano.put(raiz1, tamano1 + tamano2);
				}
			}
		}
		return sol;
	}
     
    
    private String buscarRaiz(HashMap<String, String> verticesDesde, String vertice) {
		
		String raiz = verticesDesde.get(vertice);
		if(!raiz.equals(vertice)) {
			raiz = buscarRaiz(verticesDesde, raiz);
		}
		verticesDesde.put(vertice, raiz);
		return raiz;
	}
}
   /**
	 * Implementa el algoritmo Greedy para resolver el problema del viajante de comercio (TSP).
	 * @param netDeCiudades El grafo que representa las ciudades y las conexiones entre ellas.
	 * @param cOrigen La ciudad de origen.
	 * @return Una lista de aristas que representan el camino más corto que visita todas las ciudades una vez y vuelve a la ciudad de origen.
	 */
    public ArrayList<Arista> TSPGreedy(Network<String> netDeCiudades, String cOrigen) {
    	ArrayList<Arista> res = new ArrayList<>();
    	HashSet<String> cVisitadas = new HashSet<>();
    	Double pesoMax = 0.;
    	String cActual = cOrigen;
    	cVisitadas.add(cActual);
    	
    	while(cVisitadas.size() < netDeCiudades.numberOfVertices()) {
    		String cSiguiente = verticeMasCercano(netDeCiudades, cVisitadas, cActual);
    		if(cSiguiente == null) throw new RuntimeException("No existe solución para este problema");
       		pesoMax += netDeCiudades.getWeight(cActual, cSiguiente);
    		res.add(new Arista(cActual, cSiguiente, netDeCiudades.getWeight(cActual, cSiguiente)));
    		cVisitadas.add(cSiguiente);
    		cActual = cSiguiente;
    	}
    	
    	res.add(new Arista(cOrigen, cActual, pesoMax));
    	return res;
    }
	
    /**
	 * Encuentra el vértice más cercano a un vértice dado que aún no ha sido visitado.
	 * @param netDeCiudades El grafo que representa las ciudades y las conexiones entre ellas.
	 * @param cVisitadas Un conjunto de ciudades que ya han sido visitadas.
	 * @param cActual La ciudad actual.
	 * @return La ciudad más cercana a la ciudad actual que aún no ha sido visitada.
	 */
	private String verticeMasCercano(Network<String> netDeCiudades, HashSet<String> cVisitadas, String cActual) {
		Double min = Double.MAX_VALUE;
		String ciudadConMenosCoste = null;
		
		for(String cVecinas : netDeCiudades.getNeighbors(cActual)) {
			if(!cVisitadas.contains(cVecinas)) {
				Double peso = netDeCiudades.getWeight(cActual, cVecinas);
				if(peso < min) {
					min = peso;
					ciudadConMenosCoste = cVecinas;
				}
			}
		}
		return ciudadConMenosCoste;
	}
	
	/**
	 * Implementa un algoritmo para resolver el problema del viajante de comercio (TSP) utilizando todos los caminos simples.
	 * @param netDeCiudades El grafo que representa las ciudades y las conexiones entre ellas.
	 * @param cOrigen La ciudad de origen.
	 * @return Una lista de aristas que representan el camino más corto que visita todas las ciudades una vez y vuelve a la ciudad de origen.
	 */
	public static ArrayList<Arista> TSPSimplePaths(Network<String> netDeCiudades, String cOrigen) {
	    ArrayList<String> vertices = new ArrayList<>(netDeCiudades.vertexSet());
	    vertices.remove(cOrigen);
	    return TSPSimplePathsAux(netDeCiudades, cOrigen, vertices, new ArrayList<String>(), new ArrayList<Arista>(), Double.MAX_VALUE);
	}

	/**
	 * Método auxiliar para el algoritmo TSPSimplePaths.
	 * @param netDeCiudades El grafo que representa las ciudades y las conexiones entre ellas.
	 * @param cOrigen La ciudad de origen.
	 * @param vertices Una lista de vértices que aún no han sido visitados.
	 * @param currentPath El camino actual.
	 * @param bestPath El mejor camino encontrado hasta ahora.
	 * @param bestCost El coste del mejor camino encontrado hasta ahora.
	 * @return Una lista de aristas que representan el camino más corto que visita todas las ciudades una vez y vuelve a la ciudad de origen.
	 */
	private static ArrayList<Arista> TSPSimplePathsAux(Network<String> netDeCiudades, String cOrigen, ArrayList<String> vertices, ArrayList<String> currentPath, ArrayList<Arista> bestPath, double bestCost) {
	    currentPath.add(cOrigen);
	    if (vertices.size() == 0) {
	        ArrayList<Arista> path = new ArrayList<>();
	        String lastVertex = currentPath.get(0);
	        double totalCost = 0.0;
	        for (int i = 1; i < currentPath.size(); i++) {
	            String vertex = currentPath.get(i);
	            if (netDeCiudades.containsEdge(lastVertex, vertex)) {
	                double edgeWeight = netDeCiudades.getWeight(lastVertex, vertex);
	                path.add(new Arista(lastVertex, vertex, edgeWeight));
	                totalCost += edgeWeight;
	                lastVertex = vertex;
	            } else {
	                if (bestPath.isEmpty()) {
	                	throw new RuntimeException("No existe solución para este problema");
	                } else {
	                    return bestPath;
	                }
	            }
	        }
	        if (netDeCiudades.containsEdge(lastVertex, currentPath.get(0))) {
	            double edgeWeight = netDeCiudades.getWeight(lastVertex, currentPath.get(0));
	            path.add(new Arista(lastVertex, currentPath.get(0), edgeWeight));
	            totalCost += edgeWeight;
	            if (totalCost < bestCost) {
	                bestPath = path;
	                bestCost = totalCost;
	            }
	        }
	    } else {
	        for (int i = 0; i < vertices.size(); i++) {
	            String vertex = vertices.remove(i);
	            bestPath = TSPSimplePathsAux(netDeCiudades, vertex, vertices, currentPath, bestPath, bestCost);
	            vertices.add(i, vertex);
	        }
	    }
	    currentPath.remove(currentPath.size() - 1);
	    return bestPath;
	}
}

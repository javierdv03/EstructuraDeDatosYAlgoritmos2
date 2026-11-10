/**
 * Network representa una estructura de grafo que puede ser dirigida o no dirigida,
 * consiste en vertices conectados con otros del grafo formando aristas con un determinado peso.
 * 
 * @param <Vertex> El tipo de vértices genérico, que debe implementar la interfaz Comparable.
 */

package org.eda2.practica02;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;


public class Network<Vertex extends Comparable<Vertex>> implements Iterable<Vertex> {

	private boolean directed; 	// directed = false (unDirected), directed = true (DiGraph)
	
	protected TreeMap<Vertex, TreeMap<Vertex, Double>> adjacencyMap; 
	
	/**
     * Constructor de una Network vacio.
     */
	public Network(){
		this.directed = false;
		this.adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>();
	}
	
	/**
     * Devuelve el mapa de adyacencia que representa el grafo.
     * 
     * @return El mapa de adyacencia del grafo.
     */
	
	public TreeMap<Vertex, TreeMap<Vertex, Double>> getGraph() {
		return this.adjacencyMap;
	}
	
	/**
     * Establece la propiedad dirigido de la red.
     * 
     * @param uD_Or_D True si la red es dirigida, false si es no dirigida.
     */
	
  	public void setDirected(boolean uD_Or_D) {
  		this.directed = uD_Or_D;
  	}
  	
  	/**
     * Comprueba si la red es dirigida.
     * 
     * @return True si la red es dirigida, false de lo contrario.
     */

  	public boolean getDirected() {
  		return this.directed;
  	}
  	
  	/**
     * Comprueba si la red está vacía.
     * 
     * @return True si la red está vacía, false de lo contrario.
     */

  	public boolean isEmpty() {
    	return this.adjacencyMap.isEmpty();
  	} 
  	
  	/**
     * Borra la red.
     */

  	public void clear() {
		this.adjacencyMap.clear();
	}
  	
  	/**
     * Devuelve el número de vértices en la red.
     * 
     * @return El número de vértices en la red.
     */

  	public int numberOfVertices() {
    	return this.adjacencyMap.size();
  	} 
  	
  	/**
     * Devuelve el número de aristas en la red.
     * 
     * @return El número de aristas en la red.
     */

  	public int numberOfEdges() {
  		int count = 0;
  		for (TreeMap<Vertex, Double> itMap : this.adjacencyMap.values())
  			count += itMap.size();
  		return count;
  	} 
  	
  	/**
     * Comprueba si un vértice está presente en la red.
     * 
     * @param vertice El vértice a comprobar.
     * @return True si el vértice está presente, false de lo contrario.
     */

  	public boolean containsVertex(Vertex vertex) {
    	return this.adjacencyMap.containsKey(vertex);
  	} 
  	
  	/**
     * Comprueba si una arista entre dos vértices está presente en la red.
     * 
     * @param v1 El primer vértice.
     * @param v2 El segundo vértice.
     * @return True si la arista está presente, false de lo contrario.
     */
  	
  	public boolean containsEdge(Vertex v1, Vertex v2) {
  		TreeMap<Vertex, Double> neighbors = this.adjacencyMap.get(v1);
  		if (neighbors == null) return false;
    	return neighbors.containsKey(v2);
  	} 
  	
  	/**
     * Obtiene el peso de la arista entre dos vértices.
     * 
     * @param v1 El primer vértice.
     * @param v2 El segundo vértice.
     * @return El peso de la arista, -1 si la arista no existe.
     */

  	public double getWeight (Vertex v1, Vertex v2) {
  		TreeMap<Vertex, Double> neighbors = this.adjacencyMap.get(v1);
  		if (neighbors == null) return -1;
  		Double weight = neighbors.get(v2);
  		return weight == null ? -1 : weight;
   	} 
  	
  	/**
     * Establece el peso de la arista entre dos vértices.
     * 
     * @param v1 El primer vértice.
     * @param v2 El segundo vértice.
     * @param w El peso a establecer.
     * @return El peso anterior, -1 si la arista no existe.
     */

  	public double setWeight (Vertex v1, Vertex v2, double w) {
  		TreeMap<Vertex, Double> neighbors = this.adjacencyMap.get(v1);
  		if (neighbors == null) return -1;
  		Double oldWeight = neighbors.get(v2);
  		return oldWeight == null ? -1 : neighbors.put(v2, w);
	}
  	
  	/**
     * Comprueba si dos vértices son adyacentes en la red.
     * 
     * @param v1 El primer vértice.
     * @param v2 El segundo vértice.
     * @return True si los vértices son adyacentes, false de lo contrario.
     */

  	public boolean isAdjacent (Vertex v1, Vertex v2) {
  		TreeMap<Vertex, Double> neighbors = this.adjacencyMap.get(v1);
  		return neighbors == null ? false : neighbors.containsKey(v2);
	}
  	
  	/**
     * Agrega un vértice a la red.
     * 
     * @param vertice El vértice a agregar.
     * @return True si el vértice se agregó correctamente, false si ya existe.
     */

  	public boolean addVertex(Vertex vertex) {
        if (this.adjacencyMap.containsKey(vertex)) return false;
        this.adjacencyMap.put(vertex, new TreeMap<Vertex, Double>());
        return true;
  	} 
  	
  	/**
     * Agrega una arista con peso entre dos vértices en la red.
     * 
     * @param v1 El primer vértice.
     * @param v2 El segundo vértice.
     * @param w El peso de la arista.
     * @return True si la arista se agregó correctamente, false si uno o ambos vértices no existen.
     */

  	public boolean addEdge(Vertex v1, Vertex v2, double w) {
  		if (!containsVertex(v1) || !containsVertex(v2)) return false;
  		this.adjacencyMap.get(v1).put(v2, w);
       	if (!this.directed) {
        	this.adjacencyMap.get(v2).put(v1, w);
       	}
    	return true;
  	}
  	
  	/**
     * Elimina un vértice y todas las aristas incidentes en él de la red.
     * 
     * @param vertex El vértice a eliminar.
     * @return True si el vértice se eliminó correctamente, false si no existe.
     */
  	
  	public boolean removeVertex(Vertex vertex) {
        if (!containsVertex(vertex)) return false;
        for (TreeMap<Vertex, Double> itMap: this.adjacencyMap.values()) {
        	itMap.remove(vertex);
        } 
        this.adjacencyMap.remove(vertex);
        return true;
   	} 
  	
  	/**
     * Elimina la arista entre dos vértices de la red.
     * 
     * @param v1 El primer vértice.
     * @param v2 El segundo vértice.
     * @return True si la arista se eliminó correctamente, false si no existe.
     */

  	public boolean removeEdge (Vertex v1, Vertex v2) {
    	if (!containsEdge(v1,v2)) return false;
    	this.adjacencyMap.get(v1).remove(v2);
    	if (!this.directed) {
        	this.adjacencyMap.get(v2).remove(v1);    		
    	}
    	return true;
  	} 
  	
  	/**
     * Devuelve un conjunto ordenado de los vértices en la red.
     * 
     * @return Un conjunto ordenado de los vértices en la red.
     */
  	
  	public TreeSet<Vertex> vertexSet() {
    	return new TreeSet<Vertex>(this.adjacencyMap.keySet());
  	}
  	
  	/**
     * Obtiene un conjunto ordenado de los vecinos de un vértice dado en la red.
     * 
     * @param v El vértice del que se desean obtener los vecinos.
     * @return Un conjunto ordenado de los vecinos del vértice dado.
     */

  	public TreeSet<Vertex> getNeighbors(Vertex v) {
  		TreeMap<Vertex, Double> neighbors = this.adjacencyMap.get(v);
  		return neighbors == null ? null : new TreeSet<>(neighbors.keySet());
  	}
  	
  	/**
     * ToString de la clase Network, que es el ToString de un TreeMap.
     */

  	@Override
  	public String toString() {
    	return this.adjacencyMap.toString();
  	} 
  	
  	/**
     * Iterador de la clase network. Itera sobre el keySet de vertices de la red.
     */

	@Override
	public Iterator<Vertex> iterator() {
        return this.adjacencyMap.keySet().iterator();
  	} 

 }

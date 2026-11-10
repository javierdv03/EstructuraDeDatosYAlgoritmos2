package org.eda2.practica02;

/**
 * Arista representa una conexión entre dos vértices en nuestra Network.
 * Contiene información sobre el vértice de origen, el vértice de destino y el peso de la arista.
 */

public class Arista implements Comparable<Arista> {

	private String vOrigen;
	private String vDestino;
	private Double peso;
	
	/**
     * Constructor de Arista.
     * 
     * @param origen El vértice de origen.
     * @param destino El vértice de destino.
     * @param peso El peso de la arista.
     */
	
	public Arista(String origen, String destino, Double peso) {
		this.vDestino = destino;
		this.vOrigen = origen;
		this.peso = peso;
	}
	
	/**
     * Constructor por defecto de Arista.
     */
	
	public Arista() {
		
	}
	
	/**
     * ToString de la clase Arista
     * 
     */
	
	public String toString() {
		return this.vOrigen + " -> " + this.vDestino + ", peso= " + this.peso;
	}
	
	/**
     * Devuelve el vértice de origen de la arista.
     * 
     * @return El vértice de origen de la arista.
     */

	public String getvOrigen() {
		return vOrigen;
	}
	
	/**
     * Establece el vértice de origen de la arista.
     * 
     * @param vOrigen El vértice de origen a establecer.
     */

	public void setvOrigen(String vOrigen) {
		this.vOrigen = vOrigen;
	}
	
	/**
     * Devuelve el vértice de destino de la arista.
     * 
     * @return El vértice de destino de la arista.
     */

	public String getvDestino() {
		return vDestino;
	}
	
	/**
     * Establece el vértice de destino de la arista.
     * 
     * @param vDestino El vértice de destino a establecer.
     */

	public void setvDestino(String vDestino) {
		this.vDestino = vDestino;
	}
	
	/**
     * Devuelve el peso de la arista.
     * 
     * @return El peso de la arista.
     */

	public Double getPeso() {
		return peso;
	}
	
	/**
     * Establece el peso de la arista.
     * 
     * @param peso El peso de la arista a establecer.
     */

	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	/**
     * Comprueba si esta arista es igual a otro objeto.
     * 
     * @param obj El objeto con el que se comparará.
     * @return True si esta arista es igual al objeto especificado, false de lo contrario.
     */
	
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    Arista otraArista = (Arista) obj;
	    return vOrigen.equals(otraArista.vOrigen) && vDestino.equals(otraArista.vDestino);
	}
	
	/**
     * Compara esta arista con otra arista.
     * 
     * @param otraArista La otra arista con la que se comparará.
     * @return Un valor negativo si esta arista es menor que la otra arista,
     *         cero si son iguales, y un valor positivo si esta arista es mayor que la otra arista.
     */

	@Override
	public int compareTo(Arista otraArista) {
	    int cmpPeso = peso.compareTo(otraArista.peso);
	    if (cmpPeso == 0) {
	        int cmpOrigen = vOrigen.compareTo(otraArista.vOrigen);
	        if (cmpOrigen == 0) {
	            return vDestino.compareTo(otraArista.vDestino);
	        }
	        return cmpOrigen;
	    }
	    return cmpPeso;
	}
}

package org.eda2.practica04;

/**
 * La clase Contenedor representa un contenedor con un peso específico y un identificador único.
 * Implementa la interfaz Comparable para poder comparar contenedores según su peso.
 */
public class Contenedor implements Comparable<Contenedor> {
	
	private int peso;
	private int id;
	
	/**
	 * Constructor que crea un nuevo contenedor con un peso y un identificador específicos.
	 *
	 * @param p  el peso del contenedor
	 * @param id el identificador del contenedor
	 */
	public Contenedor(int p, int id) {
		this.peso = p;
		this.id = id;
	}
	
	/**
	 * Constructor por defecto que crea un nuevo contenedor con peso 0 e identificador 1.
	 */
	public Contenedor() {
		this.peso = 0;
		this.id = 1;
	}

	/**
	 * Devuelve el peso del contenedor.
	 *
	 * @return el peso del contenedor
	 */
	public int getPeso() {
		return peso;
	}

	/**
	 * Establece el peso del contenedor.
	 *
	 * @param peso el nuevo peso del contenedor
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	/**
	 * Devuelve el identificador del contenedor.
	 *
	 * @return el identificador del contenedor
	 */
	public int getID() {
		return id;
	}

	/**
	 * Compara este contenedor con otro contenedor en función de su peso.
	 *
	 * @param c el contenedor a comparar
	 * @return un número negativo, cero o un número positivo si este contenedor es menor, igual o mayor que el contenedor especificado
	 */
	@Override
	public int compareTo(Contenedor c) {
		return Integer.compare(this.peso, c.peso);
	}

	/**
	 * Determina si este contenedor es igual a otro objeto.
	 *
	 * @param obj el objeto a comparar con este contenedor
	 * @return true si este contenedor es igual al objeto especificado; false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Contenedor otro = (Contenedor) obj;
		
		return this.compareTo(otro) == 0;
	}
	
	/**
	 * Devuelve una representación en cadena de este contenedor.
	 *
	 * @return una cadena que representa este contenedor
	 */
	public String toString() {
		return "Contenedor " + this.id + ": " + this.peso + " kg";
	}
}

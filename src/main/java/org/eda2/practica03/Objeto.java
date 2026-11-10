package org.eda2.practica03;

import java.util.Objects;

/**
 * Clase que representa un objeto.
 * Cada objeto tiene como atributo un nombre, un peso, un beneficio y una cantidad.
 */

public class Objeto {

    /** Atributo nombre. */
    private String nombre; 
    
    /** Atributo peso. */
    private double peso;
    
    /** Atributo beneficio. */
    private double beneficio; 
    
    /** Atributo cantidad. */
    private double cantidad; 

    /**
     * Constructor de la clase Objeto. La cantidad se establece por defecto a 1
     *
     * @param nombre   
     * @param peso 
     * @param beneficio 
     */
    public Objeto(String nombre, double peso, double beneficio) {
        this.nombre = nombre;
        this.peso = peso;
        this.beneficio = beneficio;
        this.cantidad = 1;
    }

    /**
     * Getter del nombre
     *
     * @return nombre.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Getter del peso
     *
     * @return peso.
     */
    public double getPeso() {
        return this.peso;
    }

    /**
     * Getter del beneficio
     *
     * @return beneficio.
     */
    public double getBeneficio() {
        return this.beneficio;
    }
    
    /**
     * Getter de la cantidad
     *
     * @return cantidad.
     */
    public double getCantidad() {
        return this.cantidad;
    }

    /**
     * Setter del beneficio.
     *
     * @param beneficio.
     */
    public void setBeneficio(double beneficio) {
        this.beneficio = beneficio;
    }

    /**
     * Setter de la cantidad.
     *
     * @param cantidad.
     */
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }


	/**
	 * Compara este objeto con otro objeto para verificar si son iguales.
	 * Dos objetos son iguales si ambos tienen el mismo nombre.
	 * 
	 * @param obj.
	 * @return true si son iguales y false si no lo son.
	*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Objeto otro = (Objeto) obj;
		return Objects.equals(this.nombre, otro.nombre);
	}

	/**
     * ToString de un objeto.
     *
     * @return string en funcion del nombre, peso, cantidad y beneficio.
     */
    @Override
    public String toString() {
        if(this.cantidad == 1) {
            return String.format( nombre +" | Beneficio: " + beneficio*cantidad +" | Peso: " + peso*cantidad);
        } else {
            return String.format( nombre +" | Beneficio: " + beneficio*cantidad +" | Peso: " + peso*cantidad + " | Cantidad: "+ cantidad);
        }
    }
}
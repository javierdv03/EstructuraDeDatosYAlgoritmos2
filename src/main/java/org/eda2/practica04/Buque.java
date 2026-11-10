package org.eda2.practica04;

import java.util.ArrayList;

/**
 * La clase Buque representa un buque que contiene contenedores.
 * Cada buque tiene una capacidad máxima y una lista de contenedores.
 */
public class Buque {
	
	 private int capacidad;
	    private ArrayList<Contenedor> contenedores;

	    /**
	     * Crea un nuevo buque con la capacidad especificada y la lista de contenedores.
	     *
	     * @param capacidad    la capacidad máxima del buque
	     * @param contenedores la lista de contenedores en el buque
	     */
	    
	    public Buque(int capacidad, ArrayList<Contenedor> contenedores) {
	        this.capacidad = capacidad;
	        this.contenedores = contenedores;
	    }
	    
	    /**
	     * Obtiene la capacidad del buque.
	     *
	     * @return la capacidad del buque
	     */

	    public int getCapacidad() {
	        return capacidad;
	    }
	    
	    /**
	     * Obtiene la lista de contenedores en el buque.
	     *
	     * @return la lista de contenedores
	     */

	    public ArrayList<Contenedor> getContenedores() {
	        return contenedores;
	    }
	    
	    /**
	     * Devuelve un arreglo de enteros que representa los pesos de cada contenedor en el buque.
	     *
	     * @return un arreglo de enteros con los pesos de los contenedores
	     */
	    public int[] arrayPesos() {
	    	int[] res = new int[contenedores.size()];
	    	int i = 0;
	    	
	    	for(Contenedor c : contenedores) {
	    		res[i] = c.getPeso();
	    		i++;
	    	}
	    	
	    	return res;
	    }
	    
	    /**
	     * Calcula el peso total de todos los contenedores en el buque.
	     *
	     * @return el peso total de los contenedores
	     */
	    
	    public int getPesosContenedores() {
	    	int res = 0;
	    	
	    	for(int p : arrayPesos()) {
	    		res += p;
	    	}
	    	
	    	return res;
	    }

	}



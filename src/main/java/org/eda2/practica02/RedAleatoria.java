package org.eda2.practica02;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.TreeSet;


/**
 * La clase RedAleatoria representa un generador de redes aleatorias.
 * Permite crear una red aleatoria de vértices y aristas, y generar un archivo
 * que describe la estructura de la red.
 */
public class RedAleatoria {
	
	private static String directorioEntrada = System.getProperty("user.dir") + File.separator
            + "src" + File.separator + "main" + File.separator
            + "java" + File.separator + "org" + File.separator
            + "eda2" + File.separator + "practica02" + File.separator;
	
	/**
	 * Constructor por defecto de la clase RedAleatoria.
	 */
	public RedAleatoria() {
		
	}
	
	/**
	 * Genera una red aleatoria con el número especificado de vértices.
	 * 
	 * @param numVertices El número de vértices que se generarán en la red.
	 * @return La red aleatoria generada, o null si no se puede garantizar la conectividad.
	 */
	 public static Network<String> generadorDeRedAleatoria(int numVertices) {
	    	
	    	Network<String> redSol = new Network<>();
	    	TreeSet<Arista> aristas = new TreeSet<>();
	    	
	    	for(int i = 1; i <= numVertices; i++) {
	    		redSol.addVertex(String.valueOf(i));
	    	}
	    	
	    	int maxAristas = (numVertices * (numVertices - 1)) / 2;
	    	
	    	for(int i = 0; i < maxAristas; i++) {
	    		String vOrigenAleatorio = "";
	    		String vDestinoAleatorio = "";
	    		while(true) {
		    		vOrigenAleatorio = String.valueOf((int)(Math.random() * numVertices) + 1);
		    		TreeSet<String> neighborsOrigen = redSol.getNeighbors(vOrigenAleatorio);
	    			vDestinoAleatorio = String.valueOf((int)(Math.random() * numVertices) + 1);
	    			TreeSet<String> neighborsDestino = redSol.getNeighbors(vDestinoAleatorio);
	    			if(vOrigenAleatorio.equals(vDestinoAleatorio) || neighborsOrigen.contains(vDestinoAleatorio) || neighborsDestino.contains(vOrigenAleatorio)) continue;
	    			else break;
	    			

	    		}
	    		Integer peso =  (int) (1 + (Math.random() * 9999));
	    		Double pesoAleatorio = 0.;
	    		pesoAleatorio += peso;
	    		redSol.addEdge(vOrigenAleatorio, vDestinoAleatorio, pesoAleatorio);
	    		aristas.add(new Arista(vOrigenAleatorio, vDestinoAleatorio, pesoAleatorio));
	    	}
	    	
	    	return comprobarConectividadRed(redSol, "1", aristas) == true ? redSol : null;
	    }
	    
	 /**
	  * Comprueba la conectividad de la red aleatoria utilizando un vértice como punto de partida.
	  * 
	  * @param redAleatoria La red aleatoria a comprobar.
	  * @param origen El vértice de partida para la comprobación de conectividad.
	  * @param ts El conjunto de aristas de la red.
	  * @return true si la red es completamente conectada, false de lo contrario.
	  */
	    private static boolean comprobarConectividadRed(Network<String> redAleatoria, String origen, TreeSet<Arista> ts) {
	    	String actual = origen;
	    	HashSet<String> verVisitados = new HashSet<>();
	    	
	    	while(verVisitados.size() < redAleatoria.numberOfVertices()) {
	    		if (!redAleatoria.containsVertex(actual)) return false;
	    		TreeSet<String> neighbors = redAleatoria.getNeighbors(actual);
	    		verVisitados.addAll(neighbors);
	    		int actualValor = Integer.valueOf(actual) + 1;
	    		actual = String.valueOf(actualValor);
	    	}
	    	
	    	String ruta = directorioEntrada + "graphEDAlandAleatorio.txt";
	    	
	    	return generarArchivo(redAleatoria, ruta, ts);
	    }
	    
	    /**
	     * Genera un archivo que describe la estructura de la red.
	     * 
	     * @param net La red a describir en el archivo.
	     * @param ruta La ruta donde se generará el archivo.
	     * @param ts El conjunto de aristas de la red.
	     * @return true si el archivo se generó correctamente, false si hubo algún error.
	     */
	    public static boolean generarArchivo(Network<String> net, String ruta, TreeSet<Arista> ts) {
	    	try(BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
	    		String salida = "";
	    		salida = net.getDirected() == true ? "1" : "0";
	    		bw.write(salida);
	    		bw.newLine();
	    		salida = net.numberOfVertices() + "";
	    		bw.write(salida);
	    		bw.newLine();
	    		
	    		for(String it : net) {
	    			salida = it;
	    			bw.write(salida);
	    			bw.newLine();
	    		}
	    		salida = ts.size() + "";
	    		bw.write(salida);
	    		bw.newLine();
	    		
	    		for(Arista ar : ts) {
	    			salida = ar.getvOrigen() + " " + ar.getvDestino() + " " + ar.getPeso();
	    			bw.write(salida);
	    			bw.newLine();
	    		}
	    		
	    	}catch(IOException e ) {
	    		e.printStackTrace();
	    	}
	    	
	    	System.out.println("Red generada correctamente");
	    	System.out.println("Archivo generado correctamente");
	    	return true;
	    }
}

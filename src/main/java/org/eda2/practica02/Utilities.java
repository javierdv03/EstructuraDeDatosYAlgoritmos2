package org.eda2.practica02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.Map.Entry;

/**
 * Clase de utilidades para operaciones relacionadas con grafos.
 */
public class Utilities {
	
	/**
	 * Guarda un grafo en formato DOT, resaltando las aristas dadas.
	 * @param net El grafo a guardar.
	 * @param res Las aristas a resaltar.
	 * @param filename El nombre del archivo donde se guardará el grafo.
	 * @param origen El nodo de origen para resaltar.
	 * @param opcion La opción para determinar qué archivo de vértices cargar.
	 */
	public static void saveAsDot(Network<String> net, ArrayList<Arista> res, String filename, String origen, Integer opcion) {
		String folder = System.getProperty("user.dir")
				+File.separator+"src"
				+File.separator + "main"
				+File.separator + "java"
				+File.separator+"org"
				+File.separator+"eda2"
				+File.separator+"practica02"
				+File.separator;
		HashMap<String, String> nodePosition = new HashMap<>();
		
		
		try {
			
		if(opcion == 1) {
			
			Scanner sc = new Scanner(new File(folder+"graphEDAlandVertices.txt"));
			sc.nextLine(); //Saltamos la primera linea
			while(sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				if(line.isEmpty()) continue;
				String[] tokens = line.split(" ");
				nodePosition.put(tokens[0], ", pos=\""+tokens[1]+","+tokens[2]+"!\"");
			}
			sc.close();
		} else {
			
			Scanner sc = new Scanner(new File(folder+"graphEDAlandLargeVertices.txt"));
			sc.nextLine(); //Saltamos la primera linea
			while(sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				if(line.isEmpty()) continue;
				String[] tokens = line.split(" ");
				nodePosition.put(tokens[0], ", pos=\""+tokens[1]+","+tokens[2]+"!\"");
			}
			sc.close();
			
		}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		String graphname = filename.substring(0,filename.lastIndexOf("."));
		
		StringBuilder sb = new StringBuilder();
		sb.append("digraph graph_"+graphname+" {\r\n"
				+ "	layout=dot\r\n"
				+ "	fontname=\"Helvetica,Arial,sans-serif\"\r\n"
				+ "	node [fontname=\"Helvetica,Arial,sans-serif\", fontsize=19]\r\n"
				+ "	edge [fontname=\"Helvetica,Arial,sans-serif\"]\r\n"
				+ "	rankdir=LR;\r\n"
				+ "\r\n"
				+ "    node [style=filled, shape=circle, height=0.25]\r\n"
				+ "    edge [dir=none]\r\n"
				+ "\r\n"
				+ "	// nodes\n");
		for (String s : net.vertexSet()) {
			String pos = nodePosition.get(s);
			pos = pos == null ? "" : pos;
			if(s.equals(origen)) {
				sb.append("    ").append(s).append(" [xlabel=\"").append(s).append("\"").append(pos).append(", label=\"\", fillcolor=\"#2ca02c\"]");
			}else {
				sb.append("    ").append(s).append(" [xlabel=\"").append(s).append("\"").append(pos).append(", label=\"\"]");
			}
			sb.append("\n");
		}
		sb.append("\r\n"
				+ "	// edges\r\n"
				+ "\n");
		for (Entry<String, TreeMap<String, Double>> it: net.getGraph().entrySet()) {
			String from = it.getKey();
			for (Entry<String, Double> it2 : it.getValue().entrySet()) {
				String to = it2.getKey();
				Double w = it2.getValue();
				if(from.compareTo(to) > 0) continue;
				if(res.contains(new Arista(from, to, 0.)) || res.contains(new Arista(to, from, 0.))) {
					sb.append("    ").append(from).append(" -> ").append(to).append(" [label=\"").append(String.format("%.1f", w).replace(",", ".")).append("\" fontcolor=\"blue\", color=\"blue\" penwidth=3.0]");
				}
				else {
					sb.append("    ").append(from).append(" -> ").append(to).append(" [label=\"").append(String.format("%.1f", w).replace(",", ".")).append("\" fontcolor=\"lightgray\", color=\"lightgray\"]");
				}
				sb.append("\n");
			}
		}
		sb.append("}");
		try {
			PrintWriter pw = new PrintWriter(new File(folder+filename));
			pw.print(sb.toString());
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Guarda un grafo en formato DOT, resaltando las aristas dadas por el algoritmo de Kruskal.
	 * @param net El grafo a guardar.
	 * @param res Las aristas a resaltar.
	 * @param filename El nombre del archivo donde se guardará el grafo.
	 * @param opcion La opción para determinar qué archivo de vértices cargar.
	 */
	public static void saveAsDotKruskal(Network<String> net, TreeSet<Arista> res, String filename, Integer opcion) {
		String folder = System.getProperty("user.dir")
				+File.separator+"src"
				+File.separator + "main"
				+File.separator + "java"
				+File.separator+"org"
				+File.separator+"eda2"
				+File.separator+"practica02"
				+File.separator;
		HashMap<String, String> nodePosition = new HashMap<>();
		
		
		try {
			
		if(opcion == 1) {
			
			Scanner sc = new Scanner(new File(folder+"graphEDAlandVertices.txt"));
			sc.nextLine(); //Saltamos la primera linea
			while(sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				if(line.isEmpty()) continue;
				String[] tokens = line.split(" ");
				nodePosition.put(tokens[0], ", pos=\""+tokens[1]+","+tokens[2]+"!\"");
			}
			sc.close();
		} else {
			
			Scanner sc = new Scanner(new File(folder+"graphEDAlandLargeVertices.txt"));
			sc.nextLine(); //Saltamos la primera linea
			while(sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				if(line.isEmpty()) continue;
				String[] tokens = line.split(" ");
				nodePosition.put(tokens[0], ", pos=\""+tokens[1]+","+tokens[2]+"!\"");
			}
			sc.close();
			
		}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		String graphname = filename.substring(0,filename.lastIndexOf("."));
		
		StringBuilder sb = new StringBuilder();
		sb.append("digraph graph_"+graphname+" {\r\n"
				+ "	layout=dot\r\n"
				+ "	fontname=\"Helvetica,Arial,sans-serif\"\r\n"
				+ "	node [fontname=\"Helvetica,Arial,sans-serif\", fontsize=19]\r\n"
				+ "	edge [fontname=\"Helvetica,Arial,sans-serif\"]\r\n"
				+ "	rankdir=LR;\r\n"
				+ "\r\n"
				+ "    node [style=filled, shape=circle, height=0.25]\r\n"
				+ "    edge [dir=none]\r\n"
				+ "\r\n"
				+ "	// nodes\n");
		for (String s : net.vertexSet()) {
			String pos = nodePosition.get(s);
			pos = pos == null ? "" : pos;
			sb.append("    ").append(s).append(" [xlabel=\"").append(s).append("\"").append(pos).append(", label=\"\"]");
			sb.append("\n");
		}
		sb.append("\r\n"
				+ "	// edges\r\n"
				+ "\n");
		for (Entry<String, TreeMap<String, Double>> it: net.getGraph().entrySet()) {
			String from = it.getKey();
			for (Entry<String, Double> it2 : it.getValue().entrySet()) {
				String to = it2.getKey();
				Double w = it2.getValue();
				if(from.compareTo(to) > 0) continue;
				if(res.contains(new Arista(from, to, w)) || res.contains(new Arista(to, from, w))) {
					sb.append("    ").append(from).append(" -> ").append(to).append(" [label=\"").append(String.format("%.1f", w).replace(",", ".")).append("\" fontcolor=\"blue\", color=\"blue\" penwidth=3.0]");
				}
				else {
					sb.append("    ").append(from).append(" -> ").append(to).append(" [label=\"").append(String.format("%.1f", w).replace(",", ".")).append("\" fontcolor=\"lightgray\", color=\"lightgray\"]");
				}
				sb.append("\n");
			}
		}
		sb.append("}");
		try {
			PrintWriter pw = new PrintWriter(new File(folder+filename));
			pw.print(sb.toString());
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
}

package org.eda1.practica03.ejercicio03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eda1.edaAuxiliar.Greater;

public class ProcesarTuits {
	private TreeMap<Persona,ArrayList<String>> tuits = null;
	
	public ProcesarTuits() {
		tuits = new TreeMap<Persona,ArrayList<String>>();
	}
	
	public void load(String directorioEntrada, String file) throws FileNotFoundException {
		Scanner scan = null;
		String lineIn;
		String[] items;
		boolean seccionUsuarios = false; //Indicamos si estamos cargando datos de usuarios (@usuarios) o tuits (@datos)
		 	 	
		this.tuits.clear();
		
		try {
			scan = new Scanner(new File(directorioEntrada + file));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		while(scan.hasNextLine()){
			lineIn = scan.nextLine().trim();
			if (lineIn.isEmpty()) continue;
			if (lineIn.startsWith("%")) continue;
			if (lineIn.toLowerCase().equals("@usuarios")) {
				seccionUsuarios = true;
				continue;
			}
			if (lineIn.toLowerCase().equals("@datos")) {
				seccionUsuarios = false;
				continue;
			}
			items = lineIn.split(" ");
			if(seccionUsuarios == true)
			{
				addPersona(items);
			}else {
				addMensajes(directorioEntrada, items);
			}
			
		}
		scan.close();
	}
	public boolean addPersona(String...items) {
		Persona persona = null;
		String nombreUsuario = items[1].toLowerCase();
		switch (items[0].toLowerCase()) {
		case "tipo_a" : persona = new Persona_A(nombreUsuario, Integer.parseInt(items[2]));break;//...); break; 
		case "tipo_b" : persona = new Persona_B(nombreUsuario, items[2]);break;//...); break;
		case "tipo_c" :	String [] telefonos = new String[items.length-2];
						for(int i = 0; i<telefonos.length; i++) {
							telefonos[i] = items[i+2];
						}
						persona = new Persona_C(nombreUsuario, telefonos); break;//...); break;
		}
		if (tuits.containsKey(persona)) return false;
		tuits.put(persona, new ArrayList<String>());
		return true;
	}
	
	public boolean addMensajes(String directorioEntrada, String...items) {
		Scanner scanTuits = null;
		String lineIn = "";
		ArrayList<String> mensajesPersona = tuits.get(new Persona(items[0].toLowerCase()));//...
		if (mensajesPersona == null) return false; //no existe la persona
		try {
			scanTuits = new Scanner(new File(directorioEntrada + items[1]));
		}catch (Exception e){
			System.out.println(e.getMessage());
			System.exit(-1);
		}
		while(scanTuits.hasNextLine()) {
			//Patron split --> "[ ,.!?¿¡_*$]+". Almacenamos las palabras...
			//...
			lineIn = scanTuits.nextLine();
			String[] aux = lineIn.split("[ ,.!?¿¡_*$]+");
			for(String iter : aux)
			{
				mensajesPersona.add(iter);
			}
			
		}
		return true;
	}

	public TreeMap<Integer, TreeSet<String>> getFrecuenciaPalabras(String nombre) {
		TreeMap<String, Integer> palabraFrecuencia = null;
		TreeMap<Integer, TreeSet<String>> frecuencias = null;
		Integer cont = null;
		
		ArrayList<String> palabras = tuits.get(new Persona(nombre.toLowerCase()));//...
		
		if (palabras == null) return null;
		palabraFrecuencia = new TreeMap<String, Integer>();
	
		for (String palabra : palabras) {
			//...
			if(palabra.isEmpty()) continue;
			cont = palabraFrecuencia.get(palabra);
			palabraFrecuencia.put(palabra, cont == null?1:cont+1);
		}
		
		frecuencias = new TreeMap<Integer, TreeSet<String>>(new Greater<Integer>());//...
		
		for (Entry<String, Integer> it : palabraFrecuencia.entrySet()){
				//...
			if(!frecuencias.containsKey(it.getValue()))
			{
				frecuencias.put(it.getValue(), new TreeSet<String>());
			}
			frecuencias.get(it.getValue()).add(it.getKey());
		}
		return frecuencias;
	}
	
	public int size() {
		return tuits.size();
	}
	
	public void clear() {
		tuits.clear();
	}
	
	public String toStringId() {
		TreeSet<String> resultado = new TreeSet<String>();
		//...
		for(Entry<Persona, ArrayList<String>> iter : tuits.entrySet()) {
			resultado.add(iter.getKey().toString());
		}
		return resultado.toString();
	}
	
	@Override
	public String toString() {
		return tuits.keySet().toString();
	}
}
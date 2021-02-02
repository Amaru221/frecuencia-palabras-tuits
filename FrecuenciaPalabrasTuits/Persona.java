package org.eda1.practica03.ejercicio03;

public class Persona implements Comparable<Persona>{
	private String nombre;
	private int id;
	public static int numPersonas = 0;
	
	public Persona(String nombre) {
		this.nombre = new String(nombre).toLowerCase();
		id = numPersonas++;
	}
	
	@Override
	public String toString() {
		return this.id + ".- " + this.nombre;
	}

	@Override
	public int compareTo(Persona o) {
		//Orden natural: nombre (less)
		return this.nombre.compareTo(o.nombre);
	}
	
}

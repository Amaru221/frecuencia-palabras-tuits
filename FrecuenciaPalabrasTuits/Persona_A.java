package org.eda1.practica03.ejercicio03;

public class Persona_A extends Persona {
	private int edad;
	
	public Persona_A(String nombre, int edad) {
		super(nombre);
		this.edad = edad;
	}
	
	@Override
	public String toString() {
		return super.toString() + " (" + this.edad + ")";
	}
}

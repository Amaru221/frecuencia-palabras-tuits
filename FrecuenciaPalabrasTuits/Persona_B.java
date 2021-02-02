package org.eda1.practica03.ejercicio03;

public class Persona_B extends Persona {
	public String ciudad;
	
	public Persona_B(String nombre, String ciudad) {
		super(nombre);
		this.ciudad = new String(ciudad);
	}
	
	@Override
	public String toString() {
		return super.toString() +" ("+ this.ciudad + ")";
	}
}

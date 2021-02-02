package org.eda1.practica03.ejercicio03;

import java.util.ArrayList;

public class Persona_C extends Persona {
	private ArrayList<String> telefonos = null;  
	
	public Persona_C(String nombre, String...telefonos) {
		super(nombre);
		this.telefonos = new ArrayList<String>();
		if (telefonos == null) return;
		
		for(int i = 0; i<telefonos.length;i++)
		{
			this.telefonos.add(telefonos[i]);
		}
	}
	
	@Override
	public String toString() {
		return (telefonos.size()==1 )? super.toString()+ " (1 telefono)" : super.toString()+" ("+this.telefonos.size() +" telefonos)";
 	}
}

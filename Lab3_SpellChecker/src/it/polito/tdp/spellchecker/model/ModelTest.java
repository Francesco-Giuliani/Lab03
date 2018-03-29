package it.polito.tdp.spellchecker.model;
import java.util.*;
public class ModelTest {

	public static void main(String[] args) throws DizionarioVuotoException {
		// TODO Auto-generated method stub
		Modello mod = new Modello();
		
		mod.setLingua("Italian");
		mod.setTesto("Ciodede a tutti!");
		
		System.out.println(mod.listaErroriToString());
		
		//System.out.println("Tempo di esecuzione: "+mod.()+" s");
	}

}

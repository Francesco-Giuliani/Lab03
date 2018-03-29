package it.polito.tdp.spellchecker.model;
import java.util.*;

public class Dizionario {

	private String nomeDizionario;
	private List<String> listaParole = new LinkedList<String>();
	
	//SERVONO I METODI DEL DIZIONARIO
	//IMPOSTARE IL NOME (ad es. dizionario italiano)
	//IMPOSTARE I METODI PER CARICARE GLI ELENCHI DI PAROLE da file o altro
	
	public Dizionario(String nomeDizionario) {
		this.nomeDizionario = nomeDizionario;
	}

	public String getNomeDizionario() {
		return nomeDizionario;
	}

	public void setNomeDizionario(String nomeDizionario) {
		this.nomeDizionario = nomeDizionario;
	}

	public List<String> getListaParole() {
		return listaParole;
	}

	public void setListaParole(List<String> listaParole) {
		this.listaParole = listaParole;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Dizionario))
			return false;
		else {
			Dizionario altro = (Dizionario)obj;
			if(this.nomeDizionario.compareTo(altro.getNomeDizionario())==0)
				return true;
			else 
				return false;
		}
	}
	
	
}

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
	public boolean dichotomicContains(String s) {
		int 	potenzaDi2=1; 
		int		posizione= (int)this.listaParole.size()/2 +1;
		int		intervallo= this.listaParole.size()-posizione;
		
		for(int i=0; (Math.pow(2, i)-this.listaParole.size())<=0;i++)
			potenzaDi2 = i+1;
		
//		System.out.println("potenza di 2 = " +potenzaDi2);
//		System.out.println("2^"+potenzaDi2+ " = "+Math.pow(2, potenzaDi2));
//		System.out.println("size di lista parole = "+this.listaParole.size() );
		
		for(int i=0; i<=potenzaDi2; i++) {
			//System.out.println("i = "+i);
//			System.out.println("parola in posizione "+posizione+": "+this.listaParole.get(posizione));
//			System.out.println("intervallo: "+intervallo);
			
			if(s.compareTo(this.listaParole.get(posizione))==0)
				return true;
			
			if(s.compareTo(this.listaParole.get(posizione))>0) {
				posizione = posizione + (int)(intervallo /2) +1;
				//System.out.println(">1");
			}
			if(s.compareTo(this.listaParole.get(posizione))<0) {
				posizione = posizione - (int)(intervallo /2) +1;
				//System.out.println("<1");
			}
			intervallo = (int)(intervallo/2) +1;
		}
		return false;
	}

	
}

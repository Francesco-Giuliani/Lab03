package it.polito.tdp.spellchecker.model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DizionarioIndicizzato {

	private String nomeDizionario;
//	private List<String> listaParole = new LinkedList<String>();
	private Map<Character, List<String>> mappaIndici = new TreeMap<>();
	
	//SERVONO I METODI DEL DIZIONARIO
	//IMPOSTARE IL NOME (ad es. dizionario italiano)
	//IMPOSTARE I METODI PER CARICARE GLI ELENCHI DI PAROLE da file o altro
	
	public DizionarioIndicizzato(String nomeDizionario) {
		this.nomeDizionario = nomeDizionario;
	}

	public String getNomeDizionario() {
		return nomeDizionario;
	}

	public void setNomeDizionario(String nomeDizionario) {
		this.nomeDizionario = nomeDizionario;
	}

//	public List<String> getListaParole() {
//		return listaParole;
//	}

//	public void setListaParole(List<String> listaParole) {
//		this.listaParole = listaParole;
//	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof DizionarioIndicizzato))
			return false;
		else {
			DizionarioIndicizzato altro = (DizionarioIndicizzato)obj;
			if(this.nomeDizionario.compareTo(altro.getNomeDizionario())==0)
				return true;
			else 
				return false;
		}
	}

	public void caricaFile() {
		String percorsoFile =  "C:\\Users\\96fra_000\\git\\Lab03\\Lab3_SpellChecker\\rsc\\"+this.nomeDizionario+".txt";
		
		try {
			FileReader r = new FileReader(percorsoFile);
			BufferedReader br = new BufferedReader(r);
			String voceDiz;
			List<String> listaParoleInizioConIndice = new ArrayList<>();
			char indice='a';
			
			while((voceDiz = br.readLine())!=null) {
				voceDiz=voceDiz.toLowerCase();
				char inizVoceDiz = voceDiz.charAt(0);
				
				if(indice!=inizVoceDiz) {
					this.mappaIndici.put((Character)indice, listaParoleInizioConIndice);
					listaParoleInizioConIndice = new LinkedList<String>();
					
					indice=inizVoceDiz;
				}
				listaParoleInizioConIndice.add(voceDiz);
			}
			
			br.close();
			r.close();								
		
		} catch (FileNotFoundException e) {
			System.out.println("Dizionario con il nome/lingua richiesta non presente in rsc come txt file.");
			e.printStackTrace();
		}catch( IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public Map<Character, List<String>> getMappaIndici() {
		return mappaIndici;
	}
	public List<String> getListaParoleAIndice(char indice) {
		return mappaIndici.get((Character)indice);
	}
}
	
	


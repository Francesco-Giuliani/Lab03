package it.polito.tdp.spellchecker.model;

import java.util.*;

public class Modello {

	private List<String> listaLingue = new ArrayList<String>(); //TODO CERCA DIFFERENZA FRA LINKED E ARRAY LIST
	private List<DizionarioIndicizzato> listaDizionari = new ArrayList<DizionarioIndicizzato>();
	private List<String> listaErrori = new ArrayList<String>();
	private String testo;
	private String lingua;
	
	public  Modello() {
		listaLingue.add("English");
		listaLingue.add("Italian");
		listaLingue.add("Italian_back");
	}
	
	/**
	 * Prepara il testo passato al Modello trasformandolo tutto in minuscolo ed eliminando caratteri speciali
	 */
	public void preparaTesto() {
		this.testo = this.testo.toLowerCase();
		this.testo =this.testo.replace("\n", " ");
		this.testo =this.testo.replace("\r", " ");
		this.testo =this.testo.replace("\t", " ");
		this.testo = this.testo.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
		this.testo =this.testo.replaceAll("\\?", "");
		this.testo =this.testo.replaceAll("'", "");
		this.testo=this.testo.replaceAll("  ", " ");
	}
	
	/**
	 * Il metodo esegue lo spell check del testo impostato come variabile del Modello 
	 * misurando il proprio tempo di esecuzione in secondi.
	 * Considera le variabili testo e lingua della classe Modello correttamente impostate.
	 * Ritorna una lista delle stringhe errate nel testo fornito.
	 * 
	 * @return listaParoleSbagliate
	 * @throws DizionarioVuotoException 
	 */
	public LinkedList<String> spellCheck() throws DizionarioVuotoException {
		
		try {
				
			if(this.lingua == null)
				throw new NessunaLinguaSelezionataException();
			if(this.testo == null)
				throw new NessunTestoSelezionatoException();
			
			DizionarioIndicizzato dizionarioSelezionato = this.caricaDizionario(lingua);
			String[] arrTesto;
			LinkedList<String> listaParoleSbagliate = new LinkedList<String>();
			LinkedList<String> giaControllate = new LinkedList<String>();
			
			this.preparaTesto();
			arrTesto = testo.split(" ");
		
			for(int i=0; i<arrTesto.length; i++) {
				String parola = arrTesto[i].trim(); //elimino eventuali ulteriori spazi dalle parole
				if(!giaControllate.contains(parola)) {
					
					if(dizionarioSelezionato.getListaParoleAIndice(parola.charAt(0)).contains(parola))
						giaControllate.add(parola);
					else
						listaParoleSbagliate.add(parola);
				}
			}
			this.listaErrori=listaParoleSbagliate;
			return listaParoleSbagliate;
		}
		catch(NessunaLinguaSelezionataException nlse) {
			nlse.printStackTrace();
		}catch(NessunTestoSelezionatoException ntse) {
			ntse.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Se il dizionario è già presente nella lista di dizionari del Modello ritorna il dizionario con il nome richiesto.
	 * Altrimenti carica un nuovo dizionario leggendo dai file .txt nella cartella rsc con percorso 
	 * "C:\\Users\\96fra_000\\git\\Lab03\\Lab3_SpellChecker\\rsc\<NOMEFILE>.txt" e ritorna questo aggiungendolo alla lista.
	 * Se listaDizionari nel Modello contiene già un dizionario con il nome passato 
	 * come parametro il metodo non lo carica nuovamente e non lo riaggiunge alla listaDizionari di modello.
	 * NOTA: non esegue il controllo/conversione di upper o lower case
	 * @param nomeDizionario
	 * @return dizionario
	 * @throws DizionarioVuotoException 	
	 */
	
	public DizionarioIndicizzato caricaDizionario(String nomeDizionario) {
		DizionarioIndicizzato dizionario = new DizionarioIndicizzato(nomeDizionario);
		
		if(!this.listaDizionari.contains(dizionario)) {
			dizionario.caricaFile();
			this.listaDizionari.add(dizionario);
			return dizionario;
		}
		return this.listaDizionari.get(this.listaDizionari.indexOf(dizionario));
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getLingua() {
		return lingua;
	}

	public void setLingua(String lingua) {
		this.lingua = lingua;
	}

	public List<DizionarioIndicizzato> getListaDizionari() {
		return listaDizionari;
	}
	
	public List<String> getListaErrori() {
		return listaErrori;
	}

	public List<String> getListaLingue() {
		return listaLingue;
	}

	public String listaErroriToString() {
		String risultato="";
		if(this.listaErrori.size()!=0) 
			for(String s: this.listaErrori)
				risultato += s+"\n";
		else
			risultato += "Nessun errore. Testo corretto.";
		return risultato;
	}
	
}

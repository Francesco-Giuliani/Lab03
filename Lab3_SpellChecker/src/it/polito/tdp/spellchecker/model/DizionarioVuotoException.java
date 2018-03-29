package it.polito.tdp.spellchecker.model;

@SuppressWarnings("serial")
public class DizionarioVuotoException extends Exception {

	public DizionarioVuotoException() {
		System.out.println("Attenzione: la lista parole del dizionario selezionato è vuota.");
	}
}

package it.polito.tdp.spellchecker.model;
import java.util.*;
public class ModelTest {

	public static void main(String[] args) throws DizionarioVuotoException {
		// TODO Auto-generated method stub
		Modello mod = new Modello();
		DizionarioIndicizzato diz = new DizionarioIndicizzato("Italian");
		
		mod.setLingua("Italian");
		mod.setTesto("ciao mamma, come stai?");
		
		mod.spellCheck();
		diz = mod.getListaDizionari().get(mod.getListaDizionari().indexOf(diz));
		System.out.println("--> Errori:\n"+mod.listaErroriToString());
		
		}
	}


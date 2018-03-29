/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;
import java.util.*;
import it.polito.tdp.spellchecker.model.*;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


public class SpellCheckerController {

	private Modello modello;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbBxLingue"
    private ComboBox<String> cmbBxLingue; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="btnClearText"
    private Button btnClearText; // Value injected by FXMLLoader

    @FXML
    private Label lblWrongWords;

    @FXML
    private Label lblTextErrors;

    @FXML
    private Label lblCompletedInSec;
    
    @FXML
    private TextArea txtErrorsArea;

    @FXML
    private TextArea txtTextArea;
    
    @FXML
    void doClearText(ActionEvent event) {
    	this.txtTextArea.clear();
    	this.txtErrorsArea.clear();
    	this.lblCompletedInSec.setText("");
    	this.lblTextErrors.setText("");
    	this.cmbBxLingue.setValue(null);
    }


    @FXML
    void doSpellCheck(ActionEvent event) throws DizionarioVuotoException {
    	
    	try {
	    	if(this.cmbBxLingue.getValue()!=null && this.txtTextArea.getText()!=null && this.txtTextArea.getText().compareTo("")!=0) {
	    		this.modello.setTesto(this.txtTextArea.getText());
	    		this.modello.setLingua(this.cmbBxLingue.getValue());
	    	}
	    	else 
	    		throw new InvalidParameterException("Attenzione: inserire un testo nella prima area di testo e/o selezionare la lingua\n.");
	    	
	    	double tempoEsecuzioneSpellCheckSec = 0;
	    	long tempoIniziale= System.nanoTime();
	    	
	    	this.lblTextErrors.setText("The text contains "+this.modello.spellCheck().size()+" errors");	    	
	    	this.txtErrorsArea.appendText("In the selected language ("+this.modello.getLingua()+") there are the following missspelled words:\n"+this.modello.listaErroriToString()+"\n");
	    	
	    	tempoEsecuzioneSpellCheckSec = (double)(System.nanoTime()-tempoIniziale)/(Math.pow(10.0, 9.0));
	    	
	    	
	    	this.lblCompletedInSec.setText("Spell check completed in "+tempoEsecuzioneSpellCheckSec+" seconds");
	    	
    	}catch(InvalidParameterException ipe) {
    		this.txtErrorsArea.appendText("Eccezione parametro invalido! Attenzione: inserire del testo nella prima area di testo.\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	  assert cmbBxLingue != null : "fx:id=\"cmbBxLingue\" was not injected: check your FXML file 'SpellChecker.fxml'.";
          assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
          assert lblWrongWords != null : "fx:id=\"lblWrongWords\" was not injected: check your FXML file 'SpellChecker.fxml'.";
          assert lblTextErrors != null : "fx:id=\"lblTextErrors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
          assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'SpellChecker.fxml'.";
          assert lblCompletedInSec != null : "fx:id=\"lblCompletedInSec\" was not injected: check your FXML file 'SpellChecker.fxml'.";    
          assert txtTextArea != null : "fx:id=\"txtTextArea\" was not injected: check your FXML file 'SpellChecker.fxml'.";
          assert txtErrorsArea != null : "fx:id=\"txtErrorsArea\" was not injected: check your FXML file 'SpellChecker.fxml'.";
          this.lblCompletedInSec.setText("");
          this.lblTextErrors.setText("");
          this.cmbBxLingue.setValue(null);
    }
    
    public void setModello(Modello modello) {
		this.modello = modello;
		this.cmbBxLingue.getItems().clear();
		this.cmbBxLingue.getItems().addAll(this.modello.getListaLingue());
		this.cmbBxLingue.setValue(this.modello.getListaLingue().get(0));
    }
}


package it.polito.tdp.newufosightings;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.newufosightings.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtAnno;

    @FXML
    private Button btnSelezionaAnno;

    @FXML
    private TextField txtGiorni;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private TextField txtT1;

    @FXML
    private TextField txtAlfa;

    @FXML
    private Button btnSimula;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	Integer anno = -1;
    	try {
    		anno = Integer.parseInt(txtAnno.getText());
    	}
    	catch(Exception e) {
    		txtResult.appendText("inserisci un numero tra 1906 e 2014");
    	}
    	if(anno<1906 || anno>2014) {
    		txtResult.appendText("inserisci un numero tra 1906 e 2014");
    		return;
    	}
    	Integer giorni = -1;
    	try {
    		giorni = Integer.parseInt(txtGiorni.getText());
    	}
    	catch(Exception e) {
    		txtResult.appendText("inserisci un numero");
    	}
    	if(giorni<=0) {
    		txtResult.appendText("inserisci un numero");
    		return;
    	}
    	this.model.creaGrafo(anno, giorni);
    	txtResult.appendText("nodi: "+this.model.getGrafo().vertexSet().size()+"\n");
    	txtResult.appendText("archi: "+this.model.getGrafo().edgeSet().size()+"\n");
    	for(String s : this.model.getGrafo().vertexSet()) {
    		double somma = 0;
    		for(DefaultWeightedEdge e : this.model.getGrafo().edgesOf(s)) {
    			somma = somma + this.model.getGrafo().getEdgeWeight(e);
    		}
    		txtResult.appendText(s+" "+somma+"\n");
    	}
    	btnSimula.setDisable(false);
    }

    @FXML
    void doSelezionaAnno(ActionEvent event) {

    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	Integer anno = -1;
    	try {
    		anno = Integer.parseInt(txtAnno.getText());
    	}
    	catch(Exception e) {
    		txtResult.appendText("inserisci un numero tra 1906 e 2014");
    	}
    	if(anno<1906 || anno>2014) {
    		txtResult.appendText("inserisci un numero tra 1906 e 2014");
    		return;
    	}
    	Integer giorni = -1;
    	try {
    		giorni = Integer.parseInt(txtGiorni.getText());
    	}
    	catch(Exception e) {
    		txtResult.appendText("inserisci un numero");
    	}
    	if(giorni<=0) {
    		txtResult.appendText("inserisci un numero");
    		return;
    	}
    	Integer t1 = -1;
    	try {
    		t1 = Integer.parseInt(txtT1.getText());
    	}
    	catch(Exception e) {
    		txtResult.appendText("inserisci un numero intero positivo");
    	}
    	if(t1<=0) {
    		txtResult.appendText("inserisci un numero intero positivo");
    		return;
    	}
    	Integer t2 = -1;
    	try {
    		t2 = Integer.parseInt(txtAlfa.getText());
    	}
    	catch(Exception e) {
    		txtResult.appendText("inserisci un numero intero positivo");
    	}
    	if(t2<=0) {
    		txtResult.appendText("inserisci un numero intero positivo");
    		return;
    	}
    	this.model.getSim().init(this.model, t1, t2, anno, giorni);
    	this.model.getSim().run();
    	for(String s : this.model.getSim().getEmergencyMap().keySet()) {
    		txtResult.appendText(s+" "+this.model.getSim().getEmergencyMap().get(s)+"\n");
    	}
    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSelezionaAnno != null : "fx:id=\"btnSelezionaAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtGiorni != null : "fx:id=\"txtGiorni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtT1 != null : "fx:id=\"txtT1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAlfa != null : "fx:id=\"txtAlfa\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		btnSimula.setDisable(true);
	}
    
}

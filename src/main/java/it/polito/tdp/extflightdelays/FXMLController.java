package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private Button btnCreaGrafo;

    @FXML
    private ComboBox<String> cmbBoxStati;

    @FXML
    private Button btnVisualizzaVelivoli;

    @FXML
    private TextField txtT;

    @FXML
    private TextField txtG;

    @FXML
    private Button btnSimula;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	this.model.creaGrafo();
    	txtResult.appendText("nodi: "+this.model.getGrafo().vertexSet().size()+"\n");
    	txtResult.appendText("archi: "+this.model.getGrafo().edgeSet().size()+"\n");
    	btnVisualizzaVelivoli.setDisable(false);
    	btnSimula.setDisable(false);
    	cmbBoxStati.getItems().addAll(this.model.getGrafo().vertexSet());
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	Integer G = -1;
    	try {
    		G = Integer.parseInt(txtG.getText());
    	}
    	catch(Exception e) {
    		txtResult.appendText("inserisci un numero");
    	}
    	if(G<=0) {
    		txtResult.appendText("inserisci un numero intero > 0");
    		return;
    	}
    	Integer T = -1;
    	try {
    		T = Integer.parseInt(txtT.getText());
    	}
    	catch(Exception e) {
    		txtResult.appendText("inserisci un numero");
    	}
    	if(T<=0) {
    		txtResult.appendText("inserisci un numero intero > 0");
    		return;
    	}
    	String s = cmbBoxStati.getValue();
    	if(s == null) {
    		txtResult.appendText("scegli uno stato");
    		return;
    	}
    	this.model.getSim().init(this.model, G, T, s);
    	this.model.getSim().run();
    	for(String stato : this.model.getSim().getIdMap().keySet()) {
    		txtResult.appendText(stato+" "+this.model.getSim().getIdMap().get(stato)+"\n");
    	}
    }

    @FXML
    void doVisualizzaVelivoli(ActionEvent event) {
    	txtResult.clear();
    	String s = cmbBoxStati.getValue();
    	if(s == null) {
    		txtResult.appendText("scegli uno stato");
    		return;
    	}
    	txtResult.appendText("stato scelto: "+s+"\n");
    	for(DefaultWeightedEdge e : this.model.velivoli(s)) {
    		txtResult.appendText(this.model.getGrafo().getEdgeTarget(e)+" "+this.model.getGrafo().getEdgeWeight(e)+"\n");
    	}
    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbBoxStati != null : "fx:id=\"cmbBoxStati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnVisualizzaVelivoli != null : "fx:id=\"btnVisualizzaVelivoli\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtT != null : "fx:id=\"txtT\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtG != null : "fx:id=\"txtG\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	btnVisualizzaVelivoli.setDisable(true);
    	btnSimula.setDisable(true);
    }
}

package it.polito.tdp.formula1;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.formula1.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> boxCircuit;

    @FXML
    private ComboBox<?> boxDriver;

    @FXML
    private ComboBox<?> boxSeason;

    @FXML
    private TextArea txtResult;

	 @FXML
    void doFantaGara(ActionEvent event) {

    }

    @FXML
    void doInfoGara(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxCircuit != null : "fx:id=\"boxCircuit\" was not injected: check your FXML file 'Formula1.fxml'.";
        assert boxDriver != null : "fx:id=\"boxDriver\" was not injected: check your FXML file 'Formula1.fxml'.";
        assert boxSeason != null : "fx:id=\"boxSeason\" was not injected: check your FXML file 'Formula1.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Formula1.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model ;
		
	}
}

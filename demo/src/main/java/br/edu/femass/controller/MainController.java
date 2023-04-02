package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController implements Initializable {
      
    @FXML
    private void handleBtnEspecialidadeAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Especialidade.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        Stage stage = new Stage();
        stage.setTitle("Cadastro de Especialidades");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleBtnMedicoAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }

    @FXML
    private void handleBtnPlanoAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }

    @FXML
    private void handleBtnPacienteAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }

    @FXML
    private void handleBtnConsultaAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}

package br.edu.femass.Util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerta {

    public Alerta(){
    }

    public static void exibir(String mensagem){
        Alert alerta = new Alert(AlertType.ERROR);
        alerta.setTitle(mensagem);
        alerta.show();
    }
    
}

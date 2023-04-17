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

    public static void exibirInformacao(String mensagem){
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(mensagem);
        alerta.show();
    }

    public static void exibirAlerta(String mensagem){
        Alert alerta = new Alert(AlertType.WARNING);
        alerta.setTitle(mensagem);
        alerta.show();
    }
    
}

package br.edu.femass.controller;

import br.edu.femass.model.Especialidade;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class EspecialidadeController {
    
    @FXML
    private ListView<Especialidade> listaEspecialidade;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtTitulo;

    @FXML
    private void btnLimpar_Click(){
        txtTitulo.setText("");
    }

    @FXML
    private void btnExcluir_Click(){
        System.out.println("Excluidinho");
    }

    @FXML
    private void btnGravar_Click(){
        Especialidade esp = new Especialidade(txtTitulo.getText());
        gravar(esp);
        txtId.setText(String.valueOf(esp.getUltimoId()+1L));
        btnLimpar_Click();
    }

    @FXML
    private void listaCliente_keyPressed(){
        System.out.println("clicooou");
    }

    @FXML
    private void listaCliente_mouseClicked(){
        System.out.println("clicouuu");
    }
}

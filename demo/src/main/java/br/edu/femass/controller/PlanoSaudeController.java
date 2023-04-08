package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.Util.Alerta;
import br.edu.femass.dao.PlanoSaudeDao;
import br.edu.femass.model.PlanoSaude;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PlanoSaudeController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exibirPlanosSaude();
    }

    @FXML
    private ListView<PlanoSaude> listaPlanoSaude;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNome;

    private PlanoSaudeDao planoSaudeDao = new PlanoSaudeDao();
    
    @FXML
    private void btnLimpar_Click(){
        txtId.setText("");
        txtNome.setText("");
    }

    @FXML
    private void btnExcluir_Click() throws StreamWriteException, DatabindException, IOException{
        try{PlanoSaude planoSaude = listaPlanoSaude.getSelectionModel().getSelectedItem();
            if(!planoSaudeDao.excluir(planoSaude)){
                Alerta.exibir("Não foi possível exibir o cliente selecionado!");
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        exibirPlanosSaude();
    }

    @FXML
    private void btnGravar_Click(){
        try{
            if(txtNome.getText().length()<1){
                Alerta.exibir("Campo \"Título\" não pode ser vazio!");
                return;
            }
            PlanoSaude planoSaude = new PlanoSaude(txtNome.getText());
            if(!planoSaudeDao.gravar(planoSaude)){
                Alerta.exibir("Não foi possível gravar o plano de saúde!");
                return;
            }
            exibirPlanosSaude();
            btnLimpar_Click();   
            txtId.setText(String.valueOf(planoSaude.getId() + 1L));
        }catch(Exception e){
            Alerta.exibir(e.getMessage());
        }  
    }

    @FXML
    private void listaCliente_keyPressed(KeyEvent event){
        exibirDados();
    }

    @FXML
    private void listaCliente_mouseClicked(MouseEvent event){
        exibirDados();
    }

    private void exibirDados(){
        PlanoSaude planoSaude = listaPlanoSaude.getSelectionModel().getSelectedItem();
        if(planoSaude!=null){
            txtId.setText(planoSaude.getId().toString());
            txtNome.setText(planoSaude.getnome());
        }
    }

    private void exibirPlanosSaude(){
        try{
            ObservableList<PlanoSaude> data = FXCollections.observableArrayList(planoSaudeDao.buscarAtivos());
            listaPlanoSaude.setItems(data);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    
}

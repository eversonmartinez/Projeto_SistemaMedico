package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.Util.Alerta;
import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.model.Especialidade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class EspecialidadeController implements Initializable{
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        exibirEspecialidades();
    }
    
    @FXML
    private ListView<Especialidade> listaEspecialidade;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtTitulo;

    EspecialidadeDao especialidadeDao = new EspecialidadeDao();
    
    @FXML
    private void btnLimpar_Click(){
        txtTitulo.setText("");
    }

    @FXML
    private void btnExcluir_Click(){
        Especialidade especialidade = listaEspecialidade.getSelectionModel().getSelectedItem();
        if (especialidade==null)
            return;
        else{
            try{
                if(!especialidadeDao.excluir(especialidade))
                    Alerta.exibir("Não foi possível excluir a especialidade selecionada!");
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        exibirEspecialidades();
    }

    @FXML
    private void btnGravar_Click(){
        try{Especialidade especialidade = new Especialidade(txtTitulo.getText());
        if(!especialidadeDao.gravar(especialidade)){
            Alerta.exibir("Não foi possível gravar o médico");
            return;
        }
        txtId.setText(String.valueOf(especialidade.getId()+1L));
        btnLimpar_Click();
        exibirEspecialidades();
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
        Especialidade especialidade = listaEspecialidade.getSelectionModel().getSelectedItem();
        if(especialidade!=null){
            txtId.setText(especialidade.getId().toString());
            txtTitulo.setText(especialidade.getTitulo());
        }
    }

    private void exibirEspecialidades(){
        try{ObservableList<Especialidade> data = FXCollections.observableArrayList(especialidadeDao.buscarAtivos());
        listaEspecialidade.setItems(data);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}

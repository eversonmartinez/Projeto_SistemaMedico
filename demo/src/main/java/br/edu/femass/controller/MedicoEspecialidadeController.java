package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.Util.Alerta;
import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.model.Especialidade;
import br.edu.femass.model.Medico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class MedicoEspecialidadeController implements Initializable{
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        exibirMedicos();
        ccbMedico.setOnAction(this::exibirEspecialidades);
        exibirCboEspecialidades();
    }
    
    @FXML
    private ListView<Especialidade> listaEspecialidade;

    @FXML
    private ComboBox<Especialidade> cboEspecialidade;

    @FXML
    private ChoiceBox<Medico> ccbMedico;

    EspecialidadeDao especialidadeDao = new EspecialidadeDao();

    @FXML
    private void btnExcluir_Click(){
        Medico medico = ccbMedico.getValue();
        Especialidade especialidade = listaEspecialidade.getSelectionModel().getSelectedItem();
        
        if (especialidade==null)
            return;
        else{
            try{
                medico.excluirEspecialidade(especialidade);
                if(!medicoDao.editar(medico))
                    Alerta.exibir("Não foi possível excluir o especialidade selecionada!");
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        //exibirMedicos();
        exibirEspecialidades(new ActionEvent());
        
    }

    @FXML
    private void btnGravar_Click(){
        try{
            if(cboEspecialidade.getSelectionModel().getSelectedItem()==null){
                Alerta.exibir("Selecione uma especialidade!");
                return;
            }
            Medico medico = ccbMedico.getSelectionModel().getSelectedItem();
            Especialidade especialidade = cboEspecialidade.getSelectionModel().getSelectedItem();
            medico.adicionarEspecialidade(especialidade);
            if(!medicoDao.editar(medico)){
                Alerta.exibir("Não foi possível gravar a nova especialidade");
                return;
            }
            cboEspecialidade.getSelectionModel().select(null);
            ccbMedico.getSelectionModel().select(medico);
            exibirEspecialidades(new ActionEvent());
            
        }catch(Exception e){
            Alerta.exibir(e.getMessage());
        }
    }

    @FXML
    private void listaCliente_keyPressed(KeyEvent event){
        
    }

    @FXML
    private void listaCliente_mouseClicked(MouseEvent event){
        
    }

    private void exibirEspecialidades(ActionEvent event){
        try{
            Medico medico = ccbMedico.getValue();
            ObservableList<Especialidade> data = FXCollections.observableArrayList(medico.getEspecialidades());
            listaEspecialidade.setItems(data);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    MedicoDao medicoDao = new MedicoDao();
    private void exibirMedicos(){
        try{ObservableList<Medico> data = FXCollections.observableArrayList(medicoDao.buscarAtivos());
            ccbMedico.setItems(data);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void exibirCboEspecialidades(){
        try{
            ObservableList<Especialidade> data = FXCollections.observableArrayList(especialidadeDao.buscarAtivos());
            cboEspecialidade.setItems(data);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

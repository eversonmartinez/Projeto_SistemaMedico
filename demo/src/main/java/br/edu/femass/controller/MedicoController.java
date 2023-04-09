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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class MedicoController implements Initializable {

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        exibirMedicos();
        exibirEspecialidades();
    }

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtCrm;

    @FXML
    private ComboBox<Especialidade> CboEspecialidade;

    @FXML
    private ListView<Medico> listaMedico;

    MedicoDao medicoDao = new MedicoDao();
    
    @FXML
    private void btnLimpar_Click(){
        txtId.setText("");
        txtNome.setText("");
        txtCpf.setText("");
        txtCrm.setText("");
        CboEspecialidade.getSelectionModel().select(null);
    }

    @FXML
    private void btnExcluir_Click(){
        Medico medico = listaMedico.getSelectionModel().getSelectedItem();
        if (medico==null)
            return;
        else{
            try{
                if(!medicoDao.excluir(medico))
                    Alerta.exibir("Não foi possível excluir o médico selecionado!");
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        exibirMedicos();
        exibirEspecialidades();
    }

    @FXML
    private void btnGravar_Click(){
        try{
            if(txtNome.getText().length()<1){
                Alerta.exibir("Campo \"Título\" não pode ser vazio!");
                return;
            }
            if(txtCrm.getText().length()<1){
                Alerta.exibir("Campo \"Título\" não pode ser vazio!");
                return;
            }
            Medico medico = new Medico(txtCpf.getText(), txtNome.getText(), txtCrm.getText(), CboEspecialidade.getSelectionModel().getSelectedItem());
        if(!medicoDao.gravar(medico)){
            Alerta.exibir("Não foi possível gravar o médico");
            return;
        }
        btnLimpar_Click();
        txtId.setText(String.valueOf(medico.getId()+1L));
        exibirEspecialidades();
        exibirMedicos();
        }catch(Exception e){
            Alerta.exibir(e.getMessage());
        }
    }

    private void exibirMedicos(){
        try{ObservableList<Medico> data = FXCollections.observableArrayList(medicoDao.buscarAtivos());
            listaMedico.setItems(data);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    EspecialidadeDao especialidadeDao = new EspecialidadeDao();
    private void exibirEspecialidades(){
        try{ObservableList<Especialidade> data = FXCollections.observableArrayList(especialidadeDao.buscarAtivos());
            CboEspecialidade.setItems(data);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void listaMedico_keyPressed(KeyEvent event){
        exibirDados();
    }

    @FXML
    private void listaMedico_mouseClicked(MouseEvent event){
        exibirDados();
    }

    private void exibirDados(){
        Medico medico = listaMedico.getSelectionModel().getSelectedItem();
        if(medico!=null){
            txtId.setText(medico.getId().toString());
            txtNome.setText(medico.getNome());
            txtCpf.setText(medico.getCpf());
            txtCrm.setText(medico.getCrm());
            CboEspecialidade.getSelectionModel().select(medico.getEspecialidades().get(0));
        }
    }

    
}

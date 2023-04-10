package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import br.edu.femass.Util.Alerta;
import br.edu.femass.dao.AgendaDao;
import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.model.Agenda;
import br.edu.femass.model.Especialidade;
import br.edu.femass.model.Medico;
import br.edu.femass.model.Paciente;

public class AgendaController implements Initializable {

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
    
    @FXML
    private TextField txtId;

    @FXML
    private ComboBox<Medico> CboMedico;

    @FXML
    private ComboBox<Especialidade> CboEspecialidade;
  
    @FXML
    private ComboBox<Paciente> CboPaciente;

    @FXML
    private TextField txtData;

    @FXML
    private TextField txtHora;


    @FXML
    private ListView<Agenda> listaAgenda;

    MedicoDao medicoDao = new MedicoDao();
    AgendaDao agendaDao = new AgendaDao();
    PacienteDao pacienteDao = new PacienteDao();
    EspecialidadeDao especialidadeDao = new EspecialidadeDao();
    
    @FXML
    private void btnLimpar_Click(){
        txtId.setText("");
        txtData.setText("");
        txtHora.setText("");
        CboMedico.getSelectionModel().select(null);
        CboEspecialidade.getSelectionModel().select(null);
        CboPaciente.getSelectionModel().select(null);
    }

    @FXML
    private void btnExcluir_Click(){
        Agenda agenda = listaAgenda.getSelectionModel().getSelectedItem();
        if (agenda==null)
            return;
        else{
            try{
                if(!agendaDao.excluir(agenda))
                    Alerta.exibir("Não foi possível excluir a agenda selecionada!");
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        exibirAgendas();          
        //exibirEspecialidades();criar esses métodos
        //exibirMedicos();
        //exibirPacientes();
    }

    @FXML
    private void btnGravar_Click(){
        try{
            if(txtData.getText().length()<1){
                Alerta.exibir("Campo \"Data\" não pode ser vazio!");
                return;
            }
            if(txtHora.getText().length()<1){
                Alerta.exibir("Campo \"Hora\" não pode ser vazio!");
                return;
            }
            //verificar campos de combobox
            Agenda agenda = new Agenda(CboPaciente.getSelectionModel().getSelectedItem(), CboMedico.getSelectionModel().getSelectedItem(), CboEspecialidade.getSelectionModel()
            .getSelectedItem(), LocalDateTime.now());
            //criar conversão dos campos strings para o tipo data usando formatter
        if(!agendaDao.gravar(agenda)){
            Alerta.exibir("Não foi possível gravar a agenda");
            return;
        }
        btnLimpar_Click();
        txtId.setText(String.valueOf(agenda.getId()+1L));
        exibirEspecialidades();
        //exibirMedicos();
        //exibirAgendas();
        //exibirPacientes();
        }catch(Exception e){
            Alerta.exibir(e.getMessage());
        }
    }

    private void exibirAgendas(){
        try{ObservableList<Agenda> data = FXCollections.observableArrayList(agendaDao.buscarAtivos());
            listaAgenda.setItems(data);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void exibirEspecialidades(){
        try{ObservableList<Especialidade> data = FXCollections.observableArrayList(especialidadeDao.buscarAtivos());
            CboEspecialidade.setItems(data);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void listaAgenda_keyPressed(KeyEvent event){
        exibirDados();
    }

    @FXML
    private void listaAgenda_mouseClicked(MouseEvent event){
        exibirDados();
    }

    private void exibirDados(){
        Agenda agenda = listaAgenda.getSelectionModel().getSelectedItem();
        if(agenda!=null){
            txtId.setText(agenda.getId().toString());
            //txtData.setText(agenda.getData());    criar conversores de data para string na tela
            //txtHora.setText(agenda.getData(txtCrm.setText(medico.getCrm());
            CboMedico.getSelectionModel().select(agenda.getMedico());
            CboEspecialidade.getSelectionModel().select(agenda.getEspecialidade());
            CboPaciente.getSelectionModel().select(agenda.getPaciente());
        }
    }

    @FXML
    private void handleBtnPacienteAction() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Paciente.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        Stage stage = new Stage();
        stage.setTitle("Cadastro de Pacientes");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField txtFiltroData;

    @FXML
    private ComboBox<Medico> cboFiltroMedico;

    @FXML
    private TextField txtInfoId;

    @FXML
    private TextField txtInfoEspecialidade;

    @FXML
    private TextField txtInfoPaciente;

    @FXML
    private TextField txtInfoData;

    @FXML
    private TextField txtInfoHora;

    @FXML
    private TextField txtRelatorio;

    @FXML
    private ListView<Agenda> listaRelatorio;

    @FXML
    private void btnPesquisar_Click(){
        exibirDados();
    }

    @FXML
    private void listaRelatorio_keyPressed(KeyEvent event){
        exibirDados();
    }

    @FXML
    private void listaRelatorio_mouseClicked(MouseEvent event){
        exibirDados();
    }

   
}

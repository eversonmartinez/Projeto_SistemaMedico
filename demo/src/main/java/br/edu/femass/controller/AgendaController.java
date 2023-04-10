package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        exibirAgendas();
        exibirMedicos();
        exibirEspecialidades();
        exibirPacientes();
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
        
        txtFiltroData.setText("");
        cboFiltroMedico.getSelectionModel().select(null);
        txtInfoId.setText("");
        txtInfoData.setText("");
        txtInfoHora.setText("");
        txtInfoMedico.setText("");
        txtInfoPaciente.setText("");
        txtInfoPlanoSaude.setText("");
        txtInfoEspecialidade.setText("");   
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
        exibirMedicos();
        exibirEspecialidades();
        exibirPacientes();
    }
    
    @FXML
    private void btnGravar_Click(){
        try{
            if(txtData.getText().length()<1){
                Alerta.exibir("Campo \"DATA\" não pode ser vazio!");
                return;
            }
            if((txtData.getText().charAt(2)!='/') || (txtData.getText().charAt(5)!='/') || txtData.getText().length()>11 || txtData.getText().length()<10){
                Alerta.exibir("Campo \"DATA\" está no formato errado!");
                return;
            }
            if(txtHora.getText().length()<1){
                Alerta.exibir("Campo \"HORA\" não pode ser vazio!");
                return;
            }
            if((txtHora.getText().charAt(2)!=':') || txtHora.getText().length()>6 || txtHora.getText().length()<5){
                Alerta.exibir("Campo \"HORA\" está no formato errado!");
                return;
            }
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyyHH:mm");
            Agenda agenda = new Agenda(CboPaciente.getSelectionModel().getSelectedItem(), CboMedico.getSelectionModel().getSelectedItem(), CboEspecialidade.getSelectionModel()
            .getSelectedItem(), LocalDateTime.parse(txtData.getText() + txtHora.getText(), fmt));
        if(!agendaDao.gravar(agenda)){
            Alerta.exibir("Não foi possível gravar a agenda");
            return;
        }
        btnLimpar_Click();
        txtId.setText(String.valueOf(agenda.getId()+1L));
        exibirAgendas();          
        exibirMedicos();
        exibirEspecialidades();
        exibirPacientes();
        }catch(Exception e){
            Alerta.exibir(e.getMessage());
            e.printStackTrace();
        }
    }

    private void exibirAgendas(){
        try{ObservableList<Agenda> data = FXCollections.observableArrayList(agendaDao.buscarAtivos());
            listaAgenda.setItems(data);
            listaRelatorio.setItems(data);
            txtRelatorio.setText("Consultas de todas as datas");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void exibirMedicos(){
        try{ObservableList<Medico> data = FXCollections.observableArrayList(medicoDao.buscarAtivos());
            CboMedico.setItems(data);
            cboFiltroMedico.setItems(data);
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

    private void exibirPacientes(){
        try{ObservableList<Paciente> data = FXCollections.observableArrayList(pacienteDao.buscarAtivos());
            CboPaciente.setItems(data);
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
            DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter fmt3 = DateTimeFormatter.ofPattern("HH:mm");
            txtData.setText(agenda.getHorario().format(fmt2));
            txtHora.setText(agenda.getHorario().format(fmt3));
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
    private TextField txtInfoMedico;
    
    @FXML
    private TextField txtInfoPaciente;

    @FXML
    private TextField txtInfoPlanoSaude;

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
        try{
            if(txtFiltroData.getText().length()==10){
                    ObservableList<Agenda> data = FXCollections.observableArrayList(agendaDao.buscarData(LocalDate.parse(txtFiltroData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"))));  
                    listaRelatorio.setItems(data);
                    txtRelatorio.setText("Consultas de " + txtFiltroData.getText());
                    if(cboFiltroMedico.getSelectionModel().getSelectedItem()!=null){
                        data=FXCollections.observableArrayList(agendaDao.buscarMedico(listaRelatorio.getItems(), cboFiltroMedico.getSelectionModel().getSelectedItem()));
                        listaRelatorio.setItems(data);
                   }
            }
            else if(cboFiltroMedico.getSelectionModel().getSelectedItem()!=null){
                exibirAgendas();
                ObservableList<Agenda> data=FXCollections.observableArrayList(agendaDao.buscarMedico(listaRelatorio.getItems(), cboFiltroMedico.getSelectionModel().getSelectedItem()));
                listaRelatorio.setItems(data);
                txtRelatorio.setText("Consultas para " + cboFiltroMedico.getSelectionModel().getSelectedItem().getNome());
           }

           else{
                exibirAgendas();
           }
        }


        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void listaRelatorio_keyPressed(KeyEvent event){
        exibirDadosRelatorio();
    }

    @FXML
    private void listaRelatorio_mouseClicked(MouseEvent event){
        exibirDadosRelatorio();
    }

    private void exibirDadosRelatorio(){
        Agenda agenda = listaRelatorio.getSelectionModel().getSelectedItem();
        if(agenda!=null){
            txtInfoId.setText(agenda.getId().toString());
            DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter fmt3 = DateTimeFormatter.ofPattern("HH:mm");
            txtInfoData.setText(agenda.getHorario().format(fmt2));
            txtInfoHora.setText(agenda.getHorario().format(fmt3));
            txtInfoMedico.setText(agenda.getMedico().getNome());
            txtInfoPaciente.setText(agenda.getPaciente().getNome());
            txtInfoPlanoSaude.setText(agenda.getPaciente().getPlanoSaudes().get(0).getnome());
            txtInfoEspecialidade.setText(agenda.getEspecialidade().getTitulo());
        }
    }
   
}

package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.Util.Alerta;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.dao.PlanoSaudeDao;
import br.edu.femass.model.Paciente;
import br.edu.femass.model.PlanoSaude;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PacienteController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exibirPacientes();
        exibirPlanosSaude();
    }

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCpf;

    @FXML
    private ComboBox<PlanoSaude> cboPlanoSaude;
    
    @FXML
    private ListView<Paciente> listaPaciente;

    private PacienteDao pacienteDao = new PacienteDao();

    @FXML
    private void btnNovo_Click(){
        txtId.setText("");
        txtNome.setText("");
        txtCpf.setText("");
        cboPlanoSaude.getSelectionModel().select(null);
        listaPaciente.getSelectionModel().select(null);
    }

    @FXML
    private void btnExcluir_Click(){
        try{
            Paciente pacienteSelecionado = listaPaciente.getSelectionModel().getSelectedItem();
            if(!pacienteDao.excluir(pacienteSelecionado)){
                Alerta.exibir("Não foi possível excluir o paciente selecionado!");
                return;
            }
            exibirPacientes();
            btnNovo_Click();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnGravar_Click(){
        try{
            if(listaPaciente.getSelectionModel().getSelectedItem()==null){    //inserção de um novo objeto
                if(txtNome.getText().length()<1){
                    Alerta.exibir("Campo \"Nome\" não pode ser vazio!");
                    return;
                }
                Paciente pacienteSelecionado = new Paciente(txtCpf.getText(), txtNome.getText(), cboPlanoSaude.getSelectionModel().getSelectedItem());
                if(!pacienteDao.gravar(pacienteSelecionado)){
                    Alerta.exibir("Não foi possível gravar o paciente!");
                    return;
                }
                exibirPacientes();
                btnNovo_Click();
                txtId.setText(String.valueOf(pacienteSelecionado.getId() + 1L));
                Alerta.exibirInformacao("Novo Paciente Salvo com Sucesso!");
            }
            else{   //edição de um objeto já existente
                Paciente paciente = listaPaciente.getSelectionModel().getSelectedItem();
                //paciente.setPlanoSaude(cboPlanoSaude.getSelectionModel().getSelectedItem());
                Alerta.exibirAlerta("Edição apenas para o campo de Plano de Saúde");
                PlanoSaude planoSaude = cboPlanoSaude.getSelectionModel().getSelectedItem();
                if(!pacienteDao.editar(paciente, planoSaude)){
                    Alerta.exibir("Alteração inválida");
                    return;
                }
                    
                btnNovo_Click();
                exibirPacientes();
                exibirPlanosSaude(); 
                Alerta.exibirInformacao("Paciente Editado com Sucesso!");
            }
        }catch(Exception e){
            Alerta.exibir(e.getMessage());
        }
    }
    
    private void exibirPacientes(){
        try{
            ObservableList<Paciente> data = FXCollections.observableArrayList(pacienteDao.buscarAtivos());
            listaPaciente.setItems(data);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    PlanoSaudeDao planoSaudeDao = new PlanoSaudeDao();
    private void exibirPlanosSaude(){
        try{
            ObservableList<PlanoSaude> data = FXCollections.observableArrayList(planoSaudeDao.buscarAtivos());
            cboPlanoSaude.setItems(data);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void listaPaciente_keyPressed(KeyEvent event){
        exibirDados();
    }

    @FXML
    private void listaPaciente_mouseClicked(MouseEvent event){
        exibirDados();
    }

    private void exibirDados(){
        Paciente pacienteSelecionado = listaPaciente.getSelectionModel().getSelectedItem();
        if (pacienteSelecionado!=null){
            txtId.setText(pacienteSelecionado.getId().toString());
            txtNome.setText(pacienteSelecionado.getNome());
            txtCpf.setText(pacienteSelecionado.getCpf());
            cboPlanoSaude.getSelectionModel().select(pacienteSelecionado.getPlanoSaude());
        }
    }

    

}

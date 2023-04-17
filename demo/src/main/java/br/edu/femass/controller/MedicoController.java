package br.edu.femass.controller;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private void btnNovo_Click(){
        txtId.setText("");
        txtNome.setText("");
        txtCpf.setText("");
        txtCrm.setText("");
        CboEspecialidade.getSelectionModel().select(null);
        listaMedico.getSelectionModel().select(null);
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
    private void btnEspecialidade_Click() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MedicoEspecialidade.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        Stage stage = new Stage();
        stage.setTitle("Cadastro de Pacientes");
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("images/icon.png")));
        stage.setScene(scene);
        stage.show();

        exibirMedicos();
    }

    @FXML
    private void btnGravar_Click(){
        try{
            if(listaMedico.getSelectionModel().getSelectedItem()==null){    //inserção de um novo objeto
                    
                if(txtNome.getText().length()<1){
                    Alerta.exibir("Campo \"Nome\" não pode ser vazio!");
                    return;
                }
                if(txtCrm.getText().length()<1){
                    Alerta.exibir("Campo \"CRM\" não pode ser vazio!");
                    return;
                }
                
                Medico medico = new Medico(txtCpf.getText(), txtNome.getText(), txtCrm.getText(), CboEspecialidade.getSelectionModel().getSelectedItem());
            if(!medicoDao.gravar(medico)){
                Alerta.exibir("Não foi possível gravar o médico");
                return;
            }
                limparCampos();
                txtId.setText(String.valueOf(medico.getId()+1L));
                exibirEspecialidades();
                exibirMedicos();
                Alerta.exibirInformacao("Novo Médico Salvo com Sucesso!");
            }

            else{   //edição de um objeto já existente
                Medico medico = listaMedico.getSelectionModel().getSelectedItem();
                medico.setCrm(txtCrm.getText());
                if(medico.getEspecialidades().get(0) != CboEspecialidade.getSelectionModel().getSelectedItem())
                    medico.adicionarEspecialidade(CboEspecialidade.getSelectionModel().getSelectedItem());
                Alerta.exibirAlerta("Edição apenas para os campos CRM e Especialidades");
                if(!medicoDao.editar(medico, CboEspecialidade.getSelectionModel().getSelectedItem(), txtCrm.getText())){
                    Alerta.exibir("Alteração Inválida");
                    exibirMedicos();
                    return;
                }
                btnNovo_Click();
                exibirMedicos();
                exibirEspecialidades(); 
                Alerta.exibirInformacao("Objeto Editado com Sucesso!");
            }
        }catch(Exception e){
            Alerta.exibir(e.getMessage());
        }
    }

    private void limparCampos(){
        txtId.setText("");
        txtNome.setText("");
        txtCpf.setText("");
        txtCrm.setText("");
        CboEspecialidade.getSelectionModel().select(null);
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

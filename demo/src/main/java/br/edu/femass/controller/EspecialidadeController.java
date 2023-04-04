package br.edu.femass.controller;

import br.edu.femass.Util.Alerta;
import br.edu.femass.dao.EspecialidadeDao;
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

    EspecialidadeDao especialidadeDao = new EspecialidadeDao();

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
        try{Especialidade esp = new Especialidade(txtTitulo.getText());
        if(!especialidadeDao.gravar(esp)){
            Alerta.exibir("Não foi possível gravar o cliente");
            return;
        }
        txtId.setText(String.valueOf(esp.getId()+1L));
        btnLimpar_Click();
        exibirEspecialidades();
        }catch(Exception e){
            Alerta.exibir(e.getMessage());
        }
    }

    @FXML
    private void listaCliente_keyPressed(){
        System.out.println("clicooou");
    }

    @FXML
    private void listaCliente_mouseClicked(){
        System.out.println("clicouuu");
    }

    private void exibirEspecialidades(){
        
    }
}

package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MedicoTest {
    
    private Medico medico;

    @BeforeEach
    void setup(){
        medico = new Medico("16234498043", "Alan", "CRM/RJ 123456", new Especialidade("Cardiologista"));
    }

   /*@Test Fiz esse teste apenas inicialmente, ele não é assertivo quando lidamos com mais de uma instâncias nos testes
   void testAtualizarUltimoId() {
        Medico medico2 = new Medico("16234498043", "Brian", "CRM/RJ 123456", new Especialidade("Ortopedista"));
        assertEquals(2L, medico2.getId());
    }*/

    @Test
    void testEquals() { //testando se um médico com o mesmo cpf será considerado igual (retorno true)
        Medico medico2 = new Medico("16234498043", "Alan", "CRM/RJ 123456", new Especialidade("Cardiologista"));
        assertEquals(true, medico2.equals(medico));
    }

    @Test
    void testAdicionarEspecialidade() { //testando se ao adicionar uma especialidade ela poderá ser encontrada na lista
        medico.adicionarEspecialidade(new Especialidade("Ortopedista"));
        assertEquals("Ortopedista", medico.getEspecialidades().get(1).getTitulo()); //como so adicionei 1, testei no índex 1
    }

    @Test
    void testExcluirEspecialidade() {   //testando se ao excluir uma especialidade, ela não conseguirá mais ser excluida (sinal de sucesso)
        Especialidade especialidade = new Especialidade("Ortopedista");
        medico.adicionarEspecialidade(especialidade);
        medico.excluirEspecialidade(especialidade);     
        assertEquals(false, medico.getEspecialidades().remove(especialidade)); //retorna false pois essa especialidade não está mais na lista
    }

    @Test
    void testPossuiEspecialidadePrimeiraPosicao(){
        assertEquals(true, medico.possuiEspecialidade(new Especialidade("Cardiologista")));
    }

    @Test
    void testPossuiEspecialidadeOutraPosicao(){
        medico.adicionarEspecialidade(new Especialidade("Ortopedista"));
        medico.adicionarEspecialidade(new Especialidade("Cirurgião"));
        assertEquals(true, medico.possuiEspecialidade(new Especialidade("Cirurgião")));
    }

    //Os demais sao apenas testes básicos de getters and setters
    @Test
    void testGetCpf() {
        assertEquals("16234498043", medico.getCpf());
    }

    @Test
    void testGetCrm() {
        assertEquals("CRM/RJ 123456", medico.getCrm());
    }

    @Test
    void testGetNome() {
        assertEquals("Alan", medico.getNome());
    }
}

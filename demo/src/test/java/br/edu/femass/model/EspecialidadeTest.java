package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EspecialidadeTest {
    private Especialidade especialidade;
    
    @BeforeEach
    void setup(){
        especialidade = new Especialidade("Cardiologista");
    }
    
    /*@Test Fiz esse teste apenas inicialmente, ele não é assertivo quando lidamos com mais de uma instâncias nos testes
    void testAtualizarUltimoId() {
        Especialidade especialidade2 = new Especialidade("Ortopedista");
        assertEquals(2L, especialidade2.getId());
    }*/

    @Test
    void testEquals() {
        Especialidade especialidade2 = new Especialidade("Cardiologista");
        assertEquals(true, especialidade2.equals(especialidade));
    }

    @Test
    void testGetId() {
        assertEquals(1, especialidade.getId());
    }

    @Test
    void testGetTitulo() {
        assertEquals("Cardiologista", especialidade.getTitulo());
    }
}

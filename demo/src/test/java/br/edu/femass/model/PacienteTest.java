package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PacienteTest {
    Paciente paciente;
    
    @BeforeEach
    void setup(){
        paciente = new Paciente("91738706095", "João", new PlanoSaude("Unimed"));
    }

    @Test
    void testGetAtivo() {
        assertEquals(true, paciente.getAtivo());
    }

    @Test
    void testGetCpf() {
        assertEquals("91738706095", paciente.getCpf());
    }

    @Test
    void testGetNome() {
        assertEquals("João", paciente.getNome());
    }

    @Test
    void testGetPlanoSaude() {
        assertEquals("Unimed", paciente.getPlanoSaude().getNome());
    }


    @Test
    void testSetAtivo() {
        paciente.setAtivo(false);
        assertEquals(false, paciente.getAtivo());
    }

    @Test
    void testSetPlanoSaude() {
        paciente.setPlanoSaude(new PlanoSaude("São Lucas"));
        assertEquals("São Lucas", paciente.getPlanoSaude().getNome());
    }

    @Test
    void testEquals() {
        Paciente paciente2 = new Paciente("91738706095", "Maria", new PlanoSaude("Unimed"));
        assertEquals(true, paciente2.equals(paciente));
    }

}

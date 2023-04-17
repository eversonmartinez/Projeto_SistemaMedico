package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AgendaTest {

    Agenda agenda;
    @BeforeEach
    void setup(){
        PlanoSaude pln = new PlanoSaude("Unimed");
        Especialidade esp = new Especialidade("Cardiologista");
        agenda = new Agenda(new Paciente("91738706095", "João", pln), new Medico("16234498043", "Alan", "CRM/RJ 123456", esp), esp, LocalDateTime.parse("2023-04-16T10:00:00"));
    }

    @Test
    void testEquals() {
        PlanoSaude pln = new PlanoSaude("Unimed");
        Especialidade esp = new Especialidade("Cardiologista");
        Agenda agenda2 = new Agenda(new Paciente("91738706095", "João", pln), new Medico("16234498043", "Alan", "CRM/RJ 123456", esp), esp, null);
        assertEquals(true, agenda2.equals(agenda));
    }

    @Test
    void testGetAtivo() {
        assertEquals(true, agenda.getAtivo());
    }

    @Test
    void testGetEspecialidade() {
        assertEquals("Cardiologista", agenda.getEspecialidade().getTitulo());
    }

    @Test
    void testGetHorario() {
        assertEquals(LocalDateTime.parse("2023-04-16T10:00:00"), agenda.getHorario());
    }


    @Test
    void testGetMedico() {
        assertEquals("Alan", agenda.getMedico().getNome());
    }

    @Test
    void testGetPaciente() {
        assertEquals("João", agenda.getPaciente().getNome());
    }

    @Test
    void testSetAtivo() {
        agenda.setAtivo(false);
        assertEquals(false, agenda.getAtivo());
    }


}

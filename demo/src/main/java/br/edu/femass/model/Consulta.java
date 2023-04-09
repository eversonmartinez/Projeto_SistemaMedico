package br.edu.femass.model;

import java.time.LocalDateTime;

public class Consulta {
    
    private Long id;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime agenda;
    public Long getId() {
        return id;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public Medico getMedico() {
        return medico;
    }
    
    public LocalDateTime getAgenda() {
        return agenda;
    }
    public void setAgenda(LocalDateTime agenda) {
        this.agenda = agenda;
    } 

}

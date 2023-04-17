package br.edu.femass.model;

import java.time.LocalDateTime;
import java.util.Set;

public class Agenda {
    
    private Long id;
    private Paciente paciente;
    private Medico medico;
    private Especialidade especialidade;
    private LocalDateTime horario;
    private Boolean ativo;

    private static Long ultimoId=0L;
    
    public Agenda(){

    }

    public Agenda(Paciente paciente, Medico medico, Especialidade especialidade, LocalDateTime horario){
        this.paciente=paciente;
        this.medico=medico;
        this.especialidade = especialidade;
        this.horario=horario;
        this.ativo=true;
        this.id=ultimoId+1L;
        ultimoId++;
    }
    
    public Long getId() {
        return id;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public Medico getMedico() {
        return medico;
    }
     
    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public LocalDateTime getHorario() {
        return horario;
    }
    public void sethorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    } 

    public static void atualizarUltimoId(Set<Agenda> agendas){  
        for(Agenda agenda : agendas){
            if(agenda.getId()>ultimoId)
                ultimoId=agenda.getId();
            }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((medico == null) ? 0 : medico.hashCode());
        result = prime * result + ((horario == null) ? 0 : horario.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Agenda other = (Agenda) obj;
        if (medico == null) {
            if (other.medico != null)
                return false;
        } else if (!medico.equals(other.medico))
            return false;
        if (horario == null) {
            if (other.horario != null)
                return false;
        } else if (!horario.equals(other.horario))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Consulta [medico=" + medico + ", especialidade= " + especialidade + ", paciente=" + paciente + "]";
    }

    
}

package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Paciente extends Pessoa{
    private String crm; //crm será um atributo obrigatório (um médico pode ter mais de um crm?)
    private List<PlanoSaude> planosSaude = new ArrayList<PlanoSaude>(); //lista pro caso de mais de uma PlanoSaude
    private Long id;
    private Boolean ativo;

    private static Long ultimoId=0L;

    public Paciente() {
    }

    public Paciente(String cpf, String nome, PlanoSaude planoSaude) {
        super(nome, cpf);
        planosSaude.add(planoSaude); 
        this.id=ultimoId+1L;
        this.ativo = true;
        ultimoId++;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void adicionarPlanoSaude(PlanoSaude planoSaude){
        planosSaude.add(planoSaude);
    }

    public void excluirPlanoSaude(PlanoSaude planoSaude){
        planosSaude.remove(planoSaude);
    }

    public List<PlanoSaude> getPlanoSaudes(){
        return this.planosSaude;
    }

    public static void atualizarUltimoId(Set<Paciente> pacientes){  
        for(Paciente paciente : pacientes){
            if(paciente.getId()>ultimoId)
                ultimoId=paciente.getId();
            }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((crm == null) ? 0 : crm.hashCode());
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
        Paciente other = (Paciente) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
}

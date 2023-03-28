package br.edu.femass.model;

import java.util.List;
import java.util.Set;

public class Medico {
    
    private String crm; //crm será o atributo identificador
    private String nome;
    private Set<Especialidade> especialidades; //lista pro caso de mais de uma especialidade
    private String cpf; //cpf será opcional
    private Long id;

    private Long ultimoId=0L;

    public Medico() {
    }

    public Medico(String crm, String nome, Especialidade especialidade) {
        this.crm = crm;
        this.nome = nome;
        especialidades.add(especialidade);  //acredito que eu tenha que instanciar a lista
        this.id=ultimoId+1L;
        ultimoId++;
    }

    public String getCrm() {
        return crm;
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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void adicionarEspecialidade(Especialidade especialidade){
        especialidades.add(especialidade);
    }

    public void excluirEspecialidade(Especialidade especialidade){
        especialidades.remove(especialidade);
    }

    public void atualizarUltimoId(List<Medico> medicos){  //verificar isso melhor depois
        for(Medico medico : medicos){
            if(medico.getId()>ultimoId)
                ultimoId=medico.getId();
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
        Medico other = (Medico) obj;
        if (crm == null) {
            if (other.crm != null)
                return false;
        } else if (!crm.equals(other.crm))
            return false;
        return true;
    }

    

    
}

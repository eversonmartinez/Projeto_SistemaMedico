package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public class Medico extends Pessoa{
    
    private String crm; //crm será um atributo obrigatório (um médico pode ter mais de um crm?)
    private List<Especialidade> especialidades = new ArrayList<Especialidade>(); //lista pro caso de mais de uma especialidade
    private Long id;
    private Boolean ativo;

    private static Long ultimoId=0L;

    public Medico() {
    }

    public Medico(String cpf, String nome, String crm, Especialidade especialidade) {
        super(nome, cpf);
        this.crm = crm;
        especialidades.add(especialidade);  //acredito que eu tenha que instanciar a lista
        this.id=ultimoId+1L;
        this.ativo = true;
        ultimoId++;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm){
        this.crm = crm;
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

    public void adicionarEspecialidade(Especialidade especialidade){
        especialidades.add(especialidade);
    }

    public void excluirEspecialidade(Especialidade especialidade){
        especialidades.remove(especialidade);
    }

    public List<Especialidade> getEspecialidades(){
        return this.especialidades;
    }

    public static void atualizarUltimoId(Set<Medico> medicos){  
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

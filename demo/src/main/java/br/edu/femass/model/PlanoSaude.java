package br.edu.femass.model;

import java.util.Set;

public class PlanoSaude {
    private Long id;    //pretendo colocar id para todas as classes
    private String nome;  //referente ao nome do PlanoSaude
 //   private String numero;  //Esse atributo só será utilizado ao juntar um Paciente a um plano
    private Boolean ativo;

    private static Long ultimoId=0L;
    
    public PlanoSaude(){}    //método vazio exigido pelo JavaFx

    public PlanoSaude(String nome){ //método que será utilizado ao cadastrar um plano de saúde novo
        this.nome=nome;
        this.id=ultimoId+1L;
        //this.numero="0";
        this.ativo=true;
        ultimoId++;
    }

    public String getNome(){
        return this.nome;
    }

    public Long getId(){
        return this.id;
    }

    /*public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }*/

    public void setAtivo(Boolean ativo){
        this.ativo=ativo;
    }

    public Boolean getAtivo(){
        return this.ativo;
    }

    public static void atualizarUltimoId(Set<PlanoSaude> planosSaude){  //verificar isso melhor depois
        for(PlanoSaude planoSaude : planosSaude){
            if(planoSaude.getId()>ultimoId)
                ultimoId=planoSaude.getId();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
        PlanoSaude other = (PlanoSaude) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
}

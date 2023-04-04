package br.edu.femass.model;

import java.util.List;
//Classe Especialidade, comecei o programa por aqui
public class Especialidade {
    
    private Long id;    //pretendo colocar id para todas as classes
    private String titulo;  //referente ao nome da especialidade

    private static Long ultimoId=0L;
    
    public Especialidade(){}    //método vazio exigido pelo JavaFx

    public Especialidade(String titulo){
        this.titulo=titulo;
        this.id=ultimoId+1L;
        ultimoId++;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public Long getId(){
        return this.id;
    }

    public Long getUltimoId(){
        return this.ultimoId;
    }

    public void atualizarUltimoId(List<Especialidade> especialidades){  //verificar isso melhor depois
        for(Especialidade especialidade : especialidades){
            if(especialidade.getId()>ultimoId)
                ultimoId=especialidade.getId();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
        Especialidade other = (Especialidade) obj;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        return true;
    }

    

}

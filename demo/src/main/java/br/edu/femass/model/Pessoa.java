package br.edu.femass.model;

public class Pessoa {
    protected String nome;
    protected String cpf;

    public Pessoa(){};

    public Pessoa(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

}

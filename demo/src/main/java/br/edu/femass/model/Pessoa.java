package br.edu.femass.model;

import br.edu.femass.Util.ValidadorCPF;

public class Pessoa {
    protected String nome;
    protected String cpf;

    public Pessoa(){};

    public Pessoa(String nome, String cpf){
        this.nome = nome;
        if (ValidadorCPF.validarCPF(cpf)==false) throw new IllegalArgumentException("CPF Inválido");
        this.cpf = cpf;
    }

}

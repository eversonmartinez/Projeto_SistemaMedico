package br.edu.femass.dao;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Persist {
    File arquivo;
    ObjectMapper om = new ObjectMapper();

    public Persist(String local){
        this.arquivo = new File(local);
    }
}

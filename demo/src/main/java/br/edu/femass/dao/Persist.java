package br.edu.femass.dao;

import java.io.File;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Persist {
    File arquivo;
    ObjectMapper om = new ObjectMapper();

    public Persist(String local){
        om.findAndRegisterModules();    //adicionei a biblioteca de jackson para trabalhar com datas, esse método atualiza o om
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); //utilizado para corrigir o formato das datas no json 
        om.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
        this.arquivo = new File(local);                                            
    }
}

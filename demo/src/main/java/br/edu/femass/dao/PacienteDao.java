package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Paciente;

public class PacienteDao extends Persist implements Dao<Paciente> {

    public PacienteDao() {
        super("paciente.json");
    }

    @Override
    public boolean gravar(Paciente objeto) throws StreamWriteException, DatabindException, IOException {
            Set<Paciente> pacientes = buscar();
            boolean gravou = pacientes.add(objeto);

            om.writerWithDefaultPrettyPrinter().writeValue(arquivo, pacientes);
            return gravou;
    }

    @Override
    public boolean excluir(Paciente objeto) throws StreamWriteException, DatabindException, IOException {
       Set<Paciente> pacientes = buscar();
       for(Paciente paciente:pacientes){
            if(paciente.equals(objeto)){
                paciente.setAtivo(true);
            }
       }
       
       om.writerWithDefaultPrettyPrinter().writeValue(arquivo, pacientes);
       return true;
    
    }

    @Override
    public Set<Paciente> buscar() throws DatabindException, StreamReadException {
        try{
            Set<Paciente> pacientes = om.readValue(arquivo, new TypeReference<Set<Paciente>>(){});
            Paciente.atualizarUltimoId(pacientes);
            return  pacientes;
        }catch(IOException ioe){
            return new HashSet<Paciente>();
        }
    }

    @Override
    public List<Paciente> buscarAtivos() throws DatabindException, StreamReadException {
            Set<Paciente> pacientes = buscar();
            List<Paciente> pacientesAtivos = pacientes.stream().filter(paciente -> paciente.getAtivo().equals(true)).collect(Collectors.toList());
            return pacientesAtivos;
    }
    
}

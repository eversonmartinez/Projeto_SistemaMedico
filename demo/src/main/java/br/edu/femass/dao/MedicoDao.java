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

import br.edu.femass.model.Medico;

public class MedicoDao extends Persist implements Dao<Medico> {

    public MedicoDao() {
        super("medico.json");
    }

    Medico medico;

    @Override
    public boolean gravar(Medico objeto) throws StreamWriteException, DatabindException, IOException {
        Set <Medico> medicos = buscar() ;
       boolean gravou = medicos.add(objeto);

       om.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicos);
       return gravou;
    }

    @Override
    public boolean excluir(Medico objeto) throws StreamWriteException, DatabindException, IOException {
        Set <Medico> medicos = buscar();

        for(Medico medico: medicos){
            if(medico.equals(objeto))
                medico.setAtivo(false);
        }

        om.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicos);
        return true;
    }

    public boolean editar(Medico objeto) throws StreamWriteException, DatabindException, IOException {
        Set <Medico> medicos = buscar();
        String crm = objeto.getCrm();
        System.out.println(crm);
        for(Medico medico: medicos){
            if(medico.equals(objeto))
                medico.setCrm(crm); 
        }

        om.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicos);
        return true;
    }

    @Override
    public Set<Medico> buscar() throws DatabindException, StreamReadException {
        try{
            Set<Medico> medicos = om.readValue(arquivo, new TypeReference<Set<Medico>>(){});
            Medico.atualizarUltimoId(medicos);
            return medicos;
        }
        catch(IOException ex){
            return new HashSet<Medico>();
        }    
    }

    @Override
    public List<Medico> buscarAtivos() throws DatabindException, StreamReadException {
            Set<Medico> especialidades = buscar();
            List<Medico> especialidadesAtivos = especialidades.stream().filter( especialidade -> especialidade.getAtivo().equals(true)).collect(Collectors.toList());
            return especialidadesAtivos;
    }
    
}

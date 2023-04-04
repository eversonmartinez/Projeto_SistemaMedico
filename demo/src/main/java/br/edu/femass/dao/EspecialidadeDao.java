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

import br.edu.femass.model.Especialidade;

public class EspecialidadeDao extends Persist implements Dao<Especialidade> {

    Especialidade especiaidade;

    public EspecialidadeDao(){
        super("especialidade.json");
    }

    @Override
    public boolean gravar(Especialidade objeto) throws StreamWriteException, DatabindException, IOException {
       Set <Especialidade> especialidades = buscar() ;
       boolean gravou = especialidades.add(objeto);

       om.writerWithDefaultPrettyPrinter().writeValue(arquivo, especialidades);
       return gravou;
    }

    @Override
    public boolean excluir(Especialidade objeto) throws StreamWriteException, DatabindException, IOException {
        Set <Especialidade> especialidades = buscar();

        for(Especialidade especialidade: especialidades){
            if(especialidade.equals(objeto))
                especialidade.setAtivo(false);
        }

        om.writerWithDefaultPrettyPrinter().writeValue(arquivo, especialidades);
        return true;
    }

    @Override
    public Set<Especialidade> buscar() throws DatabindException, StreamReadException {
        try{
            Set<Especialidade> especialidades = om.readValue(arquivo, new TypeReference<Set<Especialidade>>(){});
            Especialidade.atualizarUltimoId(especialidades);
            return especialidades;
        }
        catch(IOException ex){
            return new HashSet<Especialidade>();
        }    
    }

    @Override
    public List<Especialidade> buscarAtivos() throws DatabindException, StreamReadException {
       Set<Especialidade> especialidades = buscar();
       List<Especialidade> especialidadesAtivos = especialidades.stream().filter( especialidade -> especialidade.getAtivo().equals(true)).collect(Collectors.toList());
       return especialidadesAtivos;

    }

    
}

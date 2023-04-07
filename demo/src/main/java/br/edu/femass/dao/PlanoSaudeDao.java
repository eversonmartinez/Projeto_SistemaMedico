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

import br.edu.femass.model.PlanoSaude;

public class PlanoSaudeDao extends Persist implements Dao<PlanoSaude>{

    public PlanoSaudeDao() {
        super("planosaude.json");
    }

    @Override
    public boolean gravar(PlanoSaude objeto) throws StreamWriteException, DatabindException, IOException {
        Set<PlanoSaude> planosSaude = buscar();
        boolean gravou = planosSaude.add(objeto);

        om.writerWithDefaultPrettyPrinter().writeValue(arquivo, planosSaude);
        return gravou;
    }

    @Override
    public boolean excluir(PlanoSaude objeto) throws StreamWriteException, DatabindException, IOException {
        Set<PlanoSaude> planosSaudes = buscar();
        for(PlanoSaude plano : planosSaudes){
            if(plano.equals(objeto)){
                plano.setAtivo(false);
            }
        }
        om.writerWithDefaultPrettyPrinter().writeValue(arquivo, planosSaudes);
        return true;
    }

    @Override
    public Set<PlanoSaude> buscar() throws DatabindException, StreamReadException {
        try{
            Set<PlanoSaude> planosSaude= om.readValue(arquivo, new TypeReference<Set<PlanoSaude>>(){});
            PlanoSaude.atualizarUltimoId(planosSaude);
            return planosSaude;
        } catch(IOException ex){
            return new HashSet<PlanoSaude>();
        }
    }

    @Override
    public List<PlanoSaude> buscarAtivos() throws DatabindException, StreamReadException {
            Set<PlanoSaude> planosSaudes = buscar();
            List<PlanoSaude> planosSaudesAtivos = planosSaudes.stream().filter(plano -> plano.getAtivo().equals(true)).collect(Collectors.toList());
            return planosSaudesAtivos;
    }
    
}

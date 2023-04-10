package br.edu.femass.dao;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Agenda;
import br.edu.femass.model.Medico;

public class AgendaDao extends Persist implements Dao<Agenda>{

    public AgendaDao() {
        super("agenda.json");
    }

    @Override
    public boolean gravar(Agenda objeto) throws StreamWriteException, DatabindException, IOException {
        Set<Agenda> agendas = buscar();
        boolean gravou = agendas.add(objeto);

        om.writerWithDefaultPrettyPrinter().writeValue(arquivo, agendas);
        return gravou;
    }

    @Override
    public boolean excluir(Agenda objeto) throws StreamWriteException, DatabindException, IOException {
        Set<Agenda> agendas = buscar();
        for(Agenda agenda : agendas){
            if(agenda.equals(objeto)){
                agenda.setAtivo(false);
            }
        }
        om.writerWithDefaultPrettyPrinter().writeValue(arquivo, agendas);
        return true;
    }

    @Override
    public Set<Agenda> buscar() throws DatabindException, StreamReadException {
        try{
            Set<Agenda> agendas = om.readValue(arquivo, new TypeReference<Set<Agenda>>(){});
            Agenda.atualizarUltimoId(agendas);
            
            return agendas;
        }catch(IOException ex){
            return new HashSet<Agenda>();
        }

    }

    @Override
    public List<Agenda> buscarAtivos() throws DatabindException, StreamReadException {
        Set<Agenda> agendas = buscar();
        
        List<Agenda> agendasAtivas = agendas.stream().filter(agenda-> agenda.getAtivo().equals(true)).collect(Collectors.toList());
        return agendasAtivas;   
    }

    public List<Agenda> buscarData(LocalDate data) throws DatabindException, StreamReadException {
        Set<Agenda> agendas = buscar();
        
        List<Agenda> agendasData = agendas.stream().filter(agenda-> ((agenda.getHorario().getDayOfMonth()==(data.getDayOfMonth())) && (agenda.getHorario().getMonth() == data.getMonth()) && (agenda.getHorario().getYear() == data.getYear()))).collect(Collectors.toList());
        return agendasData;   
    }

    public List<Agenda> buscarMedico(List<Agenda> agendas, Medico medico) throws DatabindException, StreamReadException {
        //Set<Agenda> agendas = buscar();
        
        List<Agenda> agendasMedico = agendas.stream().filter(agenda-> agenda.getMedico().equals(medico)).collect(Collectors.toList());
        return agendasMedico;   
    }
}

package edu.br.puc.goias.clube.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.br.puc.goias.clube.Model.EventoPesca;
import edu.br.puc.goias.clube.Repository.EventoPescaRepository;

// EventoPescaService.java
@Service
public class EventoPescaService {
    @Autowired
    private EventoPescaRepository eventoPescaRepository;

    public List<EventoPesca> listarTodosEventosPesca() {
        return eventoPescaRepository.findAll();
    }

    public EventoPesca obterEventoPescaPorId(Long eventoId) {
        return eventoPescaRepository.findById(eventoId).orElse(null);
    }

    public EventoPesca criarEventoPesca(EventoPesca eventoPesca) {
        return eventoPescaRepository.save(eventoPesca);
    }

    public EventoPesca atualizarEventoPesca(Long eventoId, EventoPesca novoEventoPesca) {
        EventoPesca eventoExistente = eventoPescaRepository.findById(eventoId).orElse(null);
        
        if (eventoExistente != null) {
            // Atualize os campos relevantes com os dados do novoEventoPesca
            eventoExistente.setNomeEvento(novoEventoPesca.getNomeEvento());
            eventoExistente.setDataEvento(novoEventoPesca.getDataEvento());
            eventoExistente.setLocalizacao(novoEventoPesca.getLocalizacao());

            return eventoPescaRepository.save(eventoExistente);
        } else {
            // Lidar com o cenário em que o eventoId não existe
            return null;
        }
    }

    public void excluirEventoPesca(Long eventoId) {
        eventoPescaRepository.deleteById(eventoId);
    }

    public boolean isEventoVazio(Long eventoId) {
        EventoPesca evento = obterEventoPescaPorId(eventoId);
        return evento != null && evento.getInscricoes().isEmpty();
    }
}


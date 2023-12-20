package edu.br.puc.goias.clube.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.br.puc.goias.clube.Model.EventoPesca;
import edu.br.puc.goias.clube.Service.EventoPescaService;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/eventos")
public class EventoPescaController {
    @Autowired
    private EventoPescaService eventoPescaService;

    @GetMapping
    public ResponseEntity<List<EventoPesca>> listarTodosEventosPesca() {
        List<EventoPesca> eventosPesca = eventoPescaService.listarTodosEventosPesca();
        return ResponseEntity.ok(eventosPesca);
    }

    @GetMapping("/{eventoId}")
    public ResponseEntity<EventoPesca> obterEventoPescaPorId(@PathVariable Long eventoId,
            HttpServletResponse response) {
        EventoPesca eventoPesca = eventoPescaService.obterEventoPescaPorId(eventoId);
        return ResponseEntity.of(Optional.ofNullable(eventoPesca));
    }

    @PostMapping
    public ResponseEntity<EventoPesca> criarEventoPesca(@RequestBody EventoPesca eventoPesca) {
        EventoPesca novoEventoPesca = eventoPescaService.criarEventoPesca(eventoPesca);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEventoPesca);
    }

    @GetMapping("/{eventoId}/vazio")
    public ResponseEntity<Boolean> isEventoVazio(@PathVariable Long eventoId) {
        boolean isVazio = eventoPescaService.isEventoVazio(eventoId);
        return ResponseEntity.ok(isVazio);
    }

    @DeleteMapping("/{eventoId}")
    public ResponseEntity<Void> excluirEventoPesca(@PathVariable Long eventoId) {
        eventoPescaService.excluirEventoPesca(eventoId);
        return ResponseEntity.noContent().build();
    }
}

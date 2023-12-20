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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.br.puc.goias.clube.Model.PeixeCapturado;
import edu.br.puc.goias.clube.Service.PeixeCapturadoService;


@CrossOrigin(origins = "http://127.0.0.1:5500")@RestController
@RequestMapping("/api/peixes")
public class PeixeCapturadoController {
    @Autowired
    private PeixeCapturadoService peixeCapturadoService;

    @GetMapping
    public ResponseEntity<List<PeixeCapturado>> listarTodosPeixesCapturados() {
        List<PeixeCapturado> peixesCapturados = peixeCapturadoService.listarTodosPeixesCapturados();
        return ResponseEntity.ok(peixesCapturados);
    }

    @GetMapping("/{capturaId}")
    public ResponseEntity<PeixeCapturado> obterPeixeCapturadoPorId(@PathVariable Long capturaId) {
        PeixeCapturado peixeCapturado = peixeCapturadoService.obterPeixeCapturadoPorId(capturaId);
        return ResponseEntity.of(Optional.ofNullable(peixeCapturado));
    }

    @PostMapping
    public ResponseEntity<PeixeCapturado> criarPeixeCapturado(@RequestBody PeixeCapturado peixeCapturado) {
        PeixeCapturado novoPeixeCapturado = peixeCapturadoService.criarPeixeCapturado(peixeCapturado);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPeixeCapturado);
    }

    @PutMapping("/{capturaId}")
    public ResponseEntity<PeixeCapturado> atualizarPeixeCapturado(@PathVariable Long capturaId, @RequestBody PeixeCapturado novoPeixeCapturado) {
        PeixeCapturado peixeCapturadoAtualizado = peixeCapturadoService.atualizarPeixeCapturado(capturaId, novoPeixeCapturado);
        if (peixeCapturadoAtualizado != null) {
            return ResponseEntity.ok(peixeCapturadoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{capturaId}")
    public ResponseEntity<Void> excluirPeixeCapturado(@PathVariable Long capturaId) {
        peixeCapturadoService.excluirPeixeCapturado(capturaId);
        return ResponseEntity.noContent().build();
    }
}


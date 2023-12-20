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

import edu.br.puc.goias.clube.Model.Inscricao;
import edu.br.puc.goias.clube.Service.InscricaoService;


@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/inscricoes")
public class InscricaoController {
    @Autowired
    private InscricaoService inscricaoService;

    @GetMapping
    public ResponseEntity<List<Inscricao>> listarTodasInscricoes() {
        List<Inscricao> inscricoes = inscricaoService.listarTodasInscricoes();
        return ResponseEntity.ok(inscricoes);
    }

    @GetMapping("/{inscricaoId}")
    public ResponseEntity<Inscricao> obterInscricaoPorId(@PathVariable Long inscricaoId) {
        Inscricao inscricao = inscricaoService.obterInscricaoPorId(inscricaoId);
        return ResponseEntity.of(Optional.ofNullable(inscricao));
    }

    @PostMapping
    public ResponseEntity<Inscricao> criarInscricao(@RequestBody Inscricao inscricao) {
        Inscricao novaInscricao = inscricaoService.criarInscricao(inscricao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaInscricao);
    }

    @PutMapping("/{inscricaoId}")
    public ResponseEntity<Inscricao> atualizarInscricao(@PathVariable Long inscricaoId, @RequestBody Inscricao novaInscricao) {
        Inscricao inscricaoAtualizada = inscricaoService.atualizarInscricao(inscricaoId, novaInscricao);
        if (inscricaoAtualizada != null) {
            return ResponseEntity.ok(inscricaoAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{inscricaoId}")
    public ResponseEntity<Void> excluirInscricao(@PathVariable Long inscricaoId) {
        inscricaoService.excluirInscricao(inscricaoId);
        return ResponseEntity.noContent().build();
    }
}


package edu.br.puc.goias.clube.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

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

import edu.br.puc.goias.clube.Model.Membro;
import edu.br.puc.goias.clube.Service.MembroService;
import edu.br.puc.goias.clube.Service.VisualizarRelatorioService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/membros")
public class MembroController {

    private final MembroService membroService;
    private final VisualizarRelatorioService visualizarRelatorioService;

    @Autowired
    public MembroController(MembroService membroService, VisualizarRelatorioService visualizarRelatorioService) {
        this.membroService = membroService;
        this.visualizarRelatorioService = visualizarRelatorioService;
    }

    @GetMapping
    public ResponseEntity<List<Membro>> listarTodosMembros() {
        List<Membro> membros = membroService.listarTodosMembros();
        return ResponseEntity.ok(membros);
    }

    @GetMapping("/{membroId}")
    public ResponseEntity<Membro> obterMembroPorId(@PathVariable Long membroId) {
        Membro membro = membroService.obterMembroPorId(membroId);
        return ResponseEntity.of(Optional.ofNullable(membro));
    }

    @PostMapping
    public ResponseEntity<Membro> criarMembro(@RequestBody Membro membro) {
        Membro novoMembro = membroService.criarMembro(membro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMembro);
    }

    @PutMapping("/{membroId}")
    public ResponseEntity<Membro> atualizarMembro(@PathVariable Long membroId, @RequestBody Membro novoMembro) {
        Membro membroAtualizado = membroService.atualizarMembro(membroId, novoMembro);
        if (membroAtualizado != null) {
            return ResponseEntity.ok(membroAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{membroId}")
    public ResponseEntity<Void> excluirMembro(@PathVariable Long membroId) {
        membroService.excluirMembro(membroId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/pdf")
    public void gerarRelatório(HttpServletResponse response) throws JRException, IOException {

        JasperPrint jasperPrint = visualizarRelatorioService.exportaRelatorio();

        // Exporta o relatório para PDF
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        // Define o tipo de conteúdo da resposta
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=habitantes.pdf");
    }

    @GetMapping("/nome/{nomeMembro}")
    public ResponseEntity<List<Membro>> listarMembrosPorNome(@PathVariable String nomeMembro) {
        List<Membro> membrosPorNome = membroService.listarMembrosPorNome(nomeMembro);
        return ResponseEntity.ok(membrosPorNome);
    }
}

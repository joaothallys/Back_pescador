package edu.br.puc.goias.clube.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.br.puc.goias.clube.Model.PeixeCapturado;
import edu.br.puc.goias.clube.Repository.PeixeCapturadoRepository;

// PeixeCapturadoService.java
@Service
public class PeixeCapturadoService {
    @Autowired
    private PeixeCapturadoRepository peixeCapturadoRepository;

    public List<PeixeCapturado> listarTodosPeixesCapturados() {
        return peixeCapturadoRepository.findAll();
    }

    public PeixeCapturado obterPeixeCapturadoPorId(Long capturaId) {
        return peixeCapturadoRepository.findById(capturaId).orElse(null);
    }

    public PeixeCapturado criarPeixeCapturado(PeixeCapturado peixeCapturado) {
        return peixeCapturadoRepository.save(peixeCapturado);
    }

    public PeixeCapturado atualizarPeixeCapturado(Long capturaId, PeixeCapturado novoPeixeCapturado) {
        PeixeCapturado peixeCapturadoExistente = peixeCapturadoRepository.findById(capturaId).orElse(null);
        
        if (peixeCapturadoExistente != null) {
            // Atualize os campos relevantes com os dados do novoPeixeCapturado
            peixeCapturadoExistente.setNomePeixe(novoPeixeCapturado.getNomePeixe());
            peixeCapturadoExistente.setPeso(novoPeixeCapturado.getPeso());

            return peixeCapturadoRepository.save(peixeCapturadoExistente);
        } else {
            // Lidar com o cenário em que o capturaId não existe
            return null;
        }
    }

    public void excluirPeixeCapturado(Long capturaId) {
        peixeCapturadoRepository.deleteById(capturaId);
    }
}


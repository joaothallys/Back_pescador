package edu.br.puc.goias.clube.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.br.puc.goias.clube.Model.Inscricao;
import edu.br.puc.goias.clube.Repository.InscricaoRepository;

// InscricaoService.java
@Service
public class InscricaoService {
    @Autowired
    private InscricaoRepository inscricaoRepository;

    public List<Inscricao> listarTodasInscricoes() {
        return inscricaoRepository.findAll();
    }

    public Inscricao obterInscricaoPorId(Long inscricaoId) {
        return inscricaoRepository.findById(inscricaoId).orElse(null);
    }

    public Inscricao criarInscricao(Inscricao inscricao) {
        return inscricaoRepository.save(inscricao);
    }

    public Inscricao atualizarInscricao(Long inscricaoId, Inscricao novaInscricao) {
        Inscricao inscricaoExistente = inscricaoRepository.findById(inscricaoId).orElse(null);
        
        if (inscricaoExistente != null) {
            // Atualize os campos relevantes com os dados da novaInscricao
            inscricaoExistente.setMembro(novaInscricao.getMembro());
            inscricaoExistente.setEvento(novaInscricao.getEvento());
            inscricaoExistente.setDataInscricao(novaInscricao.getDataInscricao());

            return inscricaoRepository.save(inscricaoExistente);
        } else {
            // Lidar com o cenário em que o inscricaoId não existe
            return null;
        }
    }

    public void excluirInscricao(Long inscricaoId) {
        inscricaoRepository.deleteById(inscricaoId);
    }
}


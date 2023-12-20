package edu.br.puc.goias.clube.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.br.puc.goias.clube.Controller.DTOS.MembroDTO;
import edu.br.puc.goias.clube.Model.Membro;
import edu.br.puc.goias.clube.Repository.MembroRepository;

// MembroService.java

@Service
public class MembroService {
    @Autowired
    private MembroRepository membroRepository;

    public List<Membro> listarTodosMembros() {
        return membroRepository.findAll();
    }

    public Membro obterMembroPorId(Long membroId) {
        return membroRepository.findById(membroId).orElse(null);
    }

    public Membro criarMembro(Membro membro) {
        return membroRepository.save(membro);
    }

    public Membro atualizarMembro(Long membroId, Membro novoMembro) {
        Membro membroExistente = membroRepository.findById(membroId).orElse(null);
        
        if (membroExistente != null) {
            // Atualize os campos relevantes com os dados do novoMembro
            membroExistente.setNome(novoMembro.getNome());
            membroExistente.setEmail(novoMembro.getEmail());
            membroExistente.setDataInscricao(novoMembro.getDataInscricao());

            return membroRepository.save(membroExistente);
        } else {
            // Lidar com o cenário em que o membroId não existe
            return null;
        }
    }
    public List<Membro> listarMembrosPorNome(String nomeMembro) {
        // Lógica para buscar membros por nome no banco de dados
        // Retorna uma lista de membros que correspondem ao nome fornecido
        return membroRepository.findByNomeContainingIgnoreCase(nomeMembro);
    }

    public void excluirMembro(Long membroId) {
        membroRepository.deleteById(membroId);
    }

    public List<MembroDTO> obterTodosMembrosDTO() {
        return null;
    }
}


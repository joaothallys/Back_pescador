package edu.br.puc.goias.clube.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.br.puc.goias.clube.Model.Membro;

@Autowired
public interface MembroRepository extends JpaRepository<Membro, Long> {
    List<Membro> findByNomeContainingIgnoreCase(String nome);
}


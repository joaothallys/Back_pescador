package edu.br.puc.goias.clube.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.br.puc.goias.clube.Model.Inscricao;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
}
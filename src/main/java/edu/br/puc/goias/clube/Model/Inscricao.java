package edu.br.puc.goias.clube.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Inscricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inscricaoId;
    
    @ManyToOne
    @JoinColumn(name = "membro_id")
    private Membro membro;
    
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private EventoPesca evento;
    
    private LocalDate dataInscricao;

    public Long getInscricaoId() {
        return inscricaoId;
    }

    public void setInscricaoId(Long inscricaoId) {
        this.inscricaoId = inscricaoId;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }

    public EventoPesca getEvento() {
        return evento;
    }

    public void setEvento(EventoPesca evento) {
        this.evento = evento;
    }

    public LocalDate getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDate dataInscricao) {
        this.dataInscricao = dataInscricao;
    }
    
}

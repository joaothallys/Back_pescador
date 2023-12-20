package edu.br.puc.goias.clube.Model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PeixeCapturado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long capturaId;
    
    @ManyToOne
    @JoinColumn(name = "inscricao_id")
    private Inscricao inscricao;
    
    private String nomePeixe;
    private BigDecimal peso;
    
    public Long getCapturaId() {
        return capturaId;
    }
    public void setCapturaId(Long capturaId) {
        this.capturaId = capturaId;
    }
    public Inscricao getInscricao() {
        return inscricao;
    }
    public void setInscricao(Inscricao inscricao) {
        this.inscricao = inscricao;
    }
    public String getNomePeixe() {
        return nomePeixe;
    }
    public void setNomePeixe(String nomePeixe) {
        this.nomePeixe = nomePeixe;
    }
    public BigDecimal getPeso() {
        return peso;
    }
    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }
    
    
}

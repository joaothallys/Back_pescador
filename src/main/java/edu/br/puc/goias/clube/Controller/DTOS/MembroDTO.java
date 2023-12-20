package edu.br.puc.goias.clube.Controller.DTOS;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.CrossOrigin;

import edu.br.puc.goias.clube.Model.Membro;


@CrossOrigin(origins = "http://127.0.0.1:5500")
public class MembroDTO {
    private Long membroId;
    private String nome;
    private String email;
    private LocalDate dataInscricao;

    public MembroDTO(Membro membro) {
        this.membroId = membro.getMembroId();
        this.nome = membro.getNome();
        this.email = membro.getEmail();
        this.dataInscricao = membro.getDataInscricao();
    }

    public Long getMembroId() {
        return membroId;
    }

    public void setMembroId(Long membroId) {
        this.membroId = membroId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDate dataInscricao) {
        this.dataInscricao = dataInscricao;
    }
}

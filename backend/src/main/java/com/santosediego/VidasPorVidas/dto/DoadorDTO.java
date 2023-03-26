package com.santosediego.VidasPorVidas.dto;

import com.santosediego.VidasPorVidas.entities.Doador;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

public class DoadorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Preenchimento obrigatório!")
    @Size(min = 2, max = 120, message = "O tamanho deve ser entre 3 e 120 caracteres.")
    private String nome;

    @NotBlank(message = "Preenchimento obrigatório!")
    @Size(min = 14, max = 14, message = "O CPF deve conter números e pontos.")
    @CPF(message = "O CPF informado não é válido.")
    private String cpf;

    @NotBlank(message = "Preenchimento obrigatório!")
    @Size(max = 12)
    private String rg;

    @NotNull(message = "Preenchimento obrigatório!")
    private Instant dataNascimento;

    @NotBlank(message = "Preenchimento obrigatório!")
    private String genero;
    private String estadoCivil;

    @NotBlank(message = "Preenchimento obrigatório!")
    private String grupoSanguineo;

    @NotBlank(message = "Preenchimento obrigatório!")
    @Size(max = 16)
    private String celular;

    @Range(min = 50, message = "O peso mínimo é de 50Kg.")
    private Double peso;
    private Instant dataCadastro;
    private Instant dataAlteracao;

    @NotNull(message = "Enedereço é de preenchimento obrigatório.")
    @Valid
    private EnderecoDTO endereco;

    public DoadorDTO() {
    }

    public DoadorDTO(Long id, String nome, String cpf, String rg, Instant dataNascimento,
                     String genero, String estadoCivil, String grupoSanguineo, String celular,
                     Double peso, Instant dataCadastro, Instant dataAlteracao,
                     EnderecoDTO endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.estadoCivil = estadoCivil;
        this.grupoSanguineo = grupoSanguineo;
        this.celular = celular;
        this.peso = peso;
        this.dataCadastro = dataCadastro;
        this.dataAlteracao = dataAlteracao;
        this.endereco = endereco;
    }

    public DoadorDTO(Doador doador) {
        id = doador.getId();
        nome = doador.getNome();
        cpf = doador.getCpf();
        rg = doador.getRg();
        dataNascimento = doador.getDataNascimento();
        genero = doador.getGenero();
        estadoCivil = doador.getEstadoCivil().getId();
        grupoSanguineo = doador.getGrupoSanquineo().getId();
        celular = doador.getCelular();
        peso = doador.getPeso();
        dataCadastro = doador.getDataCadastro();
        dataAlteracao = doador.getDataAlteracao();
        endereco = new EnderecoDTO(doador.getEndereco());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Instant getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Instant dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Instant getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Instant dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Instant getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Instant dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
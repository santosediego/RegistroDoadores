package com.santosediego.VidasPorVidas.dto;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.santosediego.VidasPorVidas.entities.Doador;

public class DoadorExportDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preenchimento obrigatório!")
	@Size(min = 3, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres.")
	private String nome;
	private String cpf;
	private String rg;
	private Instant dataNascimento;
	private String genero;
	private String estadoCivil;
	private String grupoSanguineo;
	private String celular;
	private String telefone;
	private Double peso;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String localidade;
	private String estado;
	private Instant dataCadastro;
	private Instant dataAlteracao;

	public DoadorExportDTO() {
	}

	public DoadorExportDTO(Long id, String nome, String cpf, String rg, Instant dataNascimento, String genero,
			String estadoCivil, String grupoSanguineo, String celular, String telefone, Double peso, String logradouro,
			String numero, String complemento, String bairro, String cep, String localidade, String estado,
			Instant dataCadastro, Instant dataAlteracao) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.genero = genero;
		this.estadoCivil = estadoCivil;
		this.grupoSanguineo = grupoSanguineo;
		this.celular = celular;
		this.telefone = telefone;
		this.peso = peso;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.localidade = localidade;
		this.estado = estado;
		this.dataCadastro = dataCadastro;
		this.dataAlteracao = dataAlteracao;
	}

	public DoadorExportDTO(Doador doador) {
		id = doador.getId();
		nome = doador.getNome();
		cpf = doador.getCpf();
		rg = doador.getRg();
		dataNascimento = doador.getDataNascimento();
		genero = doador.getGenero();
		estadoCivil = doador.getEstadoCivil().getId();
		grupoSanguineo = doador.getGrupoSanquineo().getId();
		celular = doador.getCelular();
		telefone = doador.getTelefone();
		peso = doador.getPeso();
		logradouro = doador.getEndereco().getLogradouro();
		numero = doador.getEndereco().getNumero();
		complemento = doador.getEndereco().getComplemento();
		bairro = doador.getEndereco().getBairro();
		cep = doador.getEndereco().getCep();
		localidade = doador.getEndereco().getLocalidade();
		estado = doador.getEndereco().getEstado();
		dataCadastro = doador.getDataCadastro();
		dataAlteracao = doador.getDataAlteracao();
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
}
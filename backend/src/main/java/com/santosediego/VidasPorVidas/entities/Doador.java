package com.santosediego.VidasPorVidas.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.santosediego.VidasPorVidas.entities.enums.EstadoCivil;
import com.santosediego.VidasPorVidas.entities.enums.GrupoSanguineo;

@Entity
@Table(name = "tb_doador")
public class Doador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;

	@Column(unique = true)
	private String cpf;
	private String rg;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataNascimento;
	private String genero;
	private Integer estadoCivil;
	private Integer grupoSanguineo;
	private String celular;
	private String telefone;
	private Double peso;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataCadastro;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataAlteracao;
	
	@OneToMany(mappedBy = "doador")
	private Set<Endereco> enderecos = new HashSet<>();

	public Doador() {
	}

	public Doador(Integer id, String nome, String cpf, String rg, Instant dataNascimento, String genero,
			EstadoCivil estadoCivil, GrupoSanguineo grupoSanguineo, String celular, String telefone, Double peso,
			Instant dataCadastro, Instant dataAlteracao) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		this.genero = genero;
		this.estadoCivil = (estadoCivil == null) ? null : estadoCivil.getId();
		this.grupoSanguineo = (grupoSanguineo == null) ? null : grupoSanguineo.getId();
		this.celular = celular;
		this.telefone = telefone;
		this.peso = peso;
		this.dataCadastro = dataCadastro;
		this.dataAlteracao = dataAlteracao;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public EstadoCivil getEstadoCivil() {
		return EstadoCivil.toEnum(estadoCivil);
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil.getId();
	}

	public GrupoSanguineo getGrupoSanquineo() {
		return GrupoSanguineo.toEnum(grupoSanguineo);
	}

	public void setGrupoSanquineo(GrupoSanguineo grupoSanquineo) {
		this.grupoSanguineo = grupoSanquineo.getId();
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

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doador other = (Doador) obj;
		return Objects.equals(id, other.id);
	}
}
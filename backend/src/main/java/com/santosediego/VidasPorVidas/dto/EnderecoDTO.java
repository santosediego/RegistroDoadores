package com.santosediego.VidasPorVidas.dto;

import com.santosediego.VidasPorVidas.entities.Doador;
import com.santosediego.VidasPorVidas.entities.Endereco;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class EnderecoDTO implements Serializable {

    private Long id;

    @Size(max = 120)
    private String logradouro;

    @Size(max = 15)
    private String numero;

    @Size(max = 120)
    private String complemento;

    @Size(max = 120)
    private String bairro;

    @Size(max = 9)
    private String cep;

    @Size(max = 120)
    private String localidade;

    @Size(max = 2)
    private String estado;

    public EnderecoDTO() {
    }

    public EnderecoDTO(Long id, String logradouro, String numero, String complemento, String bairro,
                       String cep, String localidade, String estado) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.localidade = localidade;
        this.estado = estado;
    }

    public EnderecoDTO(Endereco endereco) {
        id = endereco.getId();
        logradouro = endereco.getLogradouro();
        numero = endereco.getNumero();
        complemento = endereco.getComplemento();
        bairro = endereco.getBairro();
        cep = endereco.getCep();
        localidade = endereco.getLocalidade();
        estado = endereco.getEstado();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}

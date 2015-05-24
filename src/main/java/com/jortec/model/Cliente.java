package com.jortec.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private long id;
	
    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_vendedor")
	private Vendedor vendedor;
	
	@Column(name= "nome")
	private String Nome;
	
	@Column(name= "cpf")
	private String Cpf;
	
	@Column(name= "rua")
	private String Rua;
	
	@Column(name= "complemento")
	private String Complemento;
	
	@Column(name= "referencia")
	private String Referencia;
	
	@Column(name= "numero")
	private int Numero;
	
	@Column(name= "bairro")
	private String Bairro;
	
	@Column(name= "cidade")
	private String Cidade;
	
	@Column(name= "telefone")
	private String Telefone;
	
	@Column(name= "plano")
	private String Plano;	
	
	@Column(name= "estatus")
	private String Estatus;	
	
	@Column(name= "dataCadastro")
	private String DataCadastro;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public String getRua() {
		return Rua;
	}

	public void setRua(String rua) {
		Rua = rua;
	}

	public String getComplemento() {
		return Complemento;
	}

	public void setComplemento(String complemento) {
		Complemento = complemento;
	}

	public String getReferencia() {
		return Referencia;
	}

	public void setReferencia(String referencia) {
		Referencia = referencia;
	}

	public int getNumero() {
		return Numero;
	}

	public void setNumero(int numero) {
		Numero = numero;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

	public String getPlano() {
		return Plano;
	}

	public void setPlano(String plano) {
		Plano = plano;
	}

	public String getDataCadastro() {
		return DataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		DataCadastro = dataCadastro;
	}

	public String getEstatus() {
		return Estatus;
	}

	public void setEstatus(String estatus) {
		Estatus = estatus;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
    
	
	
}

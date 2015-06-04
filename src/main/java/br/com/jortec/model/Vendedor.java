package br.com.jortec.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vendedor")
public class Vendedor implements Serializable{
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 4060306378759829630L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private long id;
	
	@Column(name="primeiroNome")
	private String PrimeiroNome;
	
	@Column(name="sobreNome")
	private String SobreNome;
	
	@Column(name="login")
	private String Login;
	
	@Column(name="senha")
	private String Senha;

	@Column(name="endereco")
	private String Endereco;
	
	@Column(name="numero")
	private Integer Numero;	
	
	@Column(name="rg")
	private String Rg;

	@Column(name="telefone")
	private String Telefone;
	
	@Column(name="email")
	private String Email;

	@Column(name="sobre")
	private String Sobre;
	
	@Column(name="dataNasc")
	private String DataNasc;
    
	@Column(name="setImg")
	public String Img;
	
	@Column(name="foto")
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	

	public String getPrimeiroNome() {
		return PrimeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		PrimeiroNome = primeiroNome;
	}

	public String getSobreNome() {
		return SobreNome;
	}

	public void setSobreNome(String sobreNome) {
		SobreNome = sobreNome;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}	

	public String getRg() {
		return Rg;
	}

	public void setRg(String rg) {
		Rg = rg;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

	public String getSobre() {
		return Sobre;
	}

	public void setSobre(String sobre) {
		Sobre = sobre;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getEndereco() {
		return Endereco;
	}

	public void setEndereco(String endereco) {
		Endereco = endereco;
	}

	public Integer getNumero() {
		return Numero;
	}

	public void setNumero(Integer numero) {
		Numero = numero;
	}

	public String getDataNasc() {
		return DataNasc;
	}

	public void setDataNasc(String dataNasc) {
		DataNasc = dataNasc;
	}

	public String getImg() {
		return Img;
	}

	public void setImg(String img) {
		Img = img;
	}

	
    
	
	
	
}

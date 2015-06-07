package br.com.jortec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="email")
public class DadosdeEmail {
    @Id
	private int id ;
   
    private String remetente;
    private String senha;
    private String destinatario;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRemetente() {
		return remetente;
	}
	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
    
    
}

package br.com.tecjor.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jortec.model.Usuario;
import br.com.jortec.model.Vendedor;

@Controller
@Scope("session")
public class UsuarioLogado implements Serializable{
 
	private Usuario usuario;
	private Vendedor vendedor;
	private String nomeLogado;
	
	public void logarUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	public void logarVendedor(Vendedor vendedor){
		this.vendedor = vendedor;
	}
	
	public String desloga() {
		this.usuario = null;
		this.vendedor = null;
		return "/index.xhtml?faces-redirect=true";
	}	
		
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Vendedor getVendedor() {
		return vendedor;
	}
	public String getNomeLogado() {
		return nomeLogado;
	}
	public void setNomeLogado(String nomeLogado) {
		this.nomeLogado = nomeLogado;
	}
}

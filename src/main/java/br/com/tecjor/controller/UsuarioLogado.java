package br.com.tecjor.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.jortec.model.Usuario;
import com.jortec.model.Vendedor;

@ManagedBean
@SessionScoped
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
	
	public void desloga() {
		this.usuario = null;
		this.vendedor = null;
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

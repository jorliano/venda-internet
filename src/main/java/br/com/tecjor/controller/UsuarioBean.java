package br.com.tecjor.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import br.com.tecjor.dao.ClienteDao;
import br.com.tecjor.dao.UsuarioDao;
import br.com.tecjor.util.Alerta;

import com.jortec.model.Cliente;
import com.jortec.model.Usuario;

@ManagedBean
@RequestScoped
public class UsuarioBean {

	Usuario usuario = new Usuario();	
	//List lista = new ArrayList();
	
	public void salvar(){
		
		Cliente cliente = new Cliente();
		cliente.setNome("teste");
		new ClienteDao().salvar(cliente);    
	    Alerta.info("Cliente salvo com sucesso");
	  }
	public String logar(){
		
		this.usuario = new UsuarioDao().autentica(usuario.getNome(), usuario.getSenha());
	    System.out.println("nome do usuario : "+usuario.getNome());
		if(this.usuario != null){
	    	return "sistema?faces-redirect=true";
	    }
		Alerta.error("Senha ou usuario incorreto");
	    return null;
	}
	public void cadastar(){
		
	}
	public void deletar(){
		
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
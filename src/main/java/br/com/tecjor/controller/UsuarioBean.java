package br.com.tecjor.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jortec.model.Usuario;
import br.com.tecjor.dao.UsuarioDao;
import br.com.tecjor.servico.ImagemValidator;
import br.com.tecjor.util.Alerta;

@Controller
@Scope("request")
public class UsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 663627621331088052L;

	Usuario usuario = new Usuario();	
	List<Usuario> lista = new ArrayList<Usuario>();
	
	@Autowired
	UsuarioDao dao;
	
	@Autowired
	Alerta alerta;
	
	@PostConstruct
	public void load() {
		lista = dao.listar();
	}
	
	public void salvar(){
		if(usuario.getId() == 0){
			 dao.salvar(usuario);
			 alerta.info("Usuario salvo com sucesso");
			 this.usuario = new Usuario();
		}else{
			 dao.atualiza(usuario);
			 alerta.info("Dados atualizados com sucesso");
		}
		
		 
	  }
	
	public String edita(){
		return "edita";
	}	
	
	public String deletar(){
		System.out.println("usuario deletado");
		dao.deletar(usuario);
		alerta.info("Usuario deletado com sucesso");
		return "usuario.xhtml?faces-redirect=true";
	}
	
	public void busca(){
		lista = dao.listarPorNome(usuario.getNome());
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}
	
}

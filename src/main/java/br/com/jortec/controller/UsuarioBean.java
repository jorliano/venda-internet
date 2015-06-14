package br.com.jortec.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jortec.model.Usuario;
import br.com.jortec.dao.UsuarioDao;
import br.com.jortec.servico.ImagemValidator;
import br.com.jortec.util.Alerta;

@Controller
@Scope("request")
public class UsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 663627621331088052L;

	Usuario usuario = new Usuario();	
	List<Usuario> lista = new ArrayList<Usuario>();
	private String confirmeSenha;
	
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
			if(confirmeSenha.equals(usuario.getSenha())){
				 dao.salvar(usuario);
				 alerta.info("Usuario salvo com sucesso");
				 this.usuario = new Usuario();
			}
			else{
				Alerta.error("Senhas não conferi");
			}
			 
		}else{
			Usuario us = new Usuario();
			us = dao.buscaPorId(usuario.getId());			
			if(us != null){
				if(us.getSenha().equals(usuario.getSenha())) {					
					 usuario.setSenha(confirmeSenha);
					 dao.atualiza(usuario);
					 alerta.info("Dados atualizados com sucesso");
				}
				else
				{
					alerta.error("Senha antiga esta errada");
				}
			}else
			{
				alerta.error("Senha antiga esta errada");
			}
			
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

	public String getConfirmeSenha() {
		return confirmeSenha;
	}

	public void setConfirmeSenha(String confirmeSenha) {
		this.confirmeSenha = confirmeSenha;
	}

	
	
}

package br.com.tecjor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
	List<Usuario> lista = new ArrayList<Usuario>();
	
	@ManagedProperty("#{usuarioDao}")
	private UsuarioDao dao;
	
	@PostConstruct
	public void load() {
		lista = dao.listar();
	}
	
	public String salvar(){
		if(usuario.getId() == 0){
			 dao.salvar(usuario);
			 Alerta.info("Usuario salvo com sucesso");
		}else{
			 dao.atualiza(usuario);
			 Alerta.info("Dados atualizados com sucesso");
		}
		
		 return "usuario?faces-redirect=true";
	  }
	
	public String edita(){
		return "configuracao";
	}
	
	public String deletar(){
		System.out.println("usuario deletado");
		dao.deletar(usuario);
		Alerta.info("Usuario deletado com sucesso");
		return "usuario.xhtml?faces-redirect=true";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public void setDao(UsuarioDao dao) {
		this.dao = dao;
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}
	
}

package br.com.tecjor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.tecjor.dao.ClienteDao;
import br.com.tecjor.util.Alerta;

import com.jortec.model.Cliente;
import com.jortec.model.Usuario;

@Named
@RequestScoped
public class ClienteBean {
  Usuario usuario = new Usuario();
 List<Cliente> lista = new ArrayList<Cliente>();
  
 @SuppressWarnings("unchecked")
public ClienteBean(){
	 lista = new ClienteDao().listar();
 }
  public void login(){
	//new ClienteDao().salvar(usuario);    
    Alerta.info("Cliente salvo com sucesso");
  }


public Usuario getUsuario() {
	return usuario;
}


public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}
public List<Cliente> getLista() {
	return lista;
}
public void setLista(List<Cliente> lista) {
	this.lista = lista;
}

  
	

	
  
  
}

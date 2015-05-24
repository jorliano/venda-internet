package br.com.tecjor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import br.com.tecjor.dao.ClienteDao;
import br.com.tecjor.util.Alerta;

import com.jortec.model.Cliente;
import com.jortec.model.Usuario;

@Named
@RequestScoped
public class ClienteBean {
 Cliente cliente = new Cliente();
 List<Cliente> lista = new ArrayList<Cliente>();
  
 @SuppressWarnings("unchecked")
public ClienteBean(){
	 lista = new ClienteDao().listar();
 }
  public String salvar(){
	new ClienteDao().salvar(cliente);    
    Alerta.info("Cliente salvo com sucesso");
    return "/sistema.xhtml";
  }
  
  public void deletar(){
	new ClienteDao().Deletar(cliente);    
    Alerta.info("Cliente deletado com sucesso");
  }
  
  public void busca(){
	lista = new ClienteDao().busca(cliente.getNome());    
    //Alerta.info("Cliente salvo com sucesso");
  }

  public String edita(){
	  return "configuracao.xhtml?faces-redirect=true";
  }

public Cliente getCliente() {
	return cliente;
}
public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}
public List<Cliente> getLista() {
	return lista;
}
public void setLista(List<Cliente> lista) {
	this.lista = lista;
}

  
	

	
  
  
}

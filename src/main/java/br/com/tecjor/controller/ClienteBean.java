package br.com.tecjor.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import br.com.tecjor.dao.ClienteDao;
import br.com.tecjor.util.Alerta;

import com.jortec.model.Cliente;
import com.jortec.model.Usuario;

@ManagedBean
@RequestScoped
public class ClienteBean {
 Cliente cliente = new Cliente();
 List<Cliente> lista = new ArrayList<Cliente>();
  
 @ManagedProperty("#{clienteDao}")
 private ClienteDao dao;
 
@PostConstruct 
public void loade(){
	 lista = dao.listar();
 }

  public String salvar(){
	dao.salvar(cliente);    
    Alerta.info("Cliente salvo com sucesso");
    return "cliente?faces-redirect=true";
  }
  
  public void deletar(){
	dao.Deletar(cliente);    
    Alerta.info("Cliente deletado com sucesso");
  }
  
  public void busca(){
	lista = dao.busca(cliente.getNome());        
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

 public void setDao(ClienteDao dao) {
	this.dao = dao;
} 
public ClienteDao getDao() {
	return dao;
}	

	
  
  
}

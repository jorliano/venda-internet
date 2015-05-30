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
import com.jortec.model.Vendedor;

@ManagedBean
@RequestScoped
public class ClienteBean implements Serializable{
 Cliente cliente = new Cliente();
 List<Cliente> lista = new ArrayList<Cliente>();
  
 @ManagedProperty("#{clienteDao}")
 private ClienteDao dao;
 
 @ManagedProperty("#{usuarioLogado}")
 private UsuarioLogado usuarioLogado;
 
@PostConstruct 
public void loade(){
	 lista = dao.listar();
 }

  public String salvar(){
	  if(cliente.getId() == 0 ){		  
		  //cliente.setVendedor(usuarioLogado.getVendedor());		 
		  dao.salvar(cliente);   		  
		  Alerta.info("Cliente salvo com sucesso");
	  }	
	  else{
		  dao.atualiza(cliente);		  
		  Alerta.info("Cliente atualizado com sucesso");
		  
	  }	
	  loade();
    return "cliente?faces-redirect=true";
  }
  
  public String deletar(){
	dao.Deletar(cliente);    
    Alerta.info("Cliente deletado com sucesso");   
    return "cliente?faces-redirect=true";
  }
  
  public void busca(){
	lista = dao.busca(cliente.getNome());        
  }

  public String edita(){
	  System.out.println("Noome do cliente"+cliente.getNome());
	  return "edita";
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
public void setUsuarioLogado(UsuarioLogado usuarioLogado) {
	this.usuarioLogado = usuarioLogado;
}
	
  
  
}

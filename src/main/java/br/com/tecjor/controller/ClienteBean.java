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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jortec.model.Cliente;
import br.com.jortec.model.Usuario;
import br.com.jortec.model.Vendedor;
import br.com.tecjor.dao.ClienteDao;
import br.com.tecjor.util.Alerta;


@Controller
@Scope("request")
public class ClienteBean implements Serializable{
 
	
Cliente cliente = new Cliente();
List<Cliente> lista = new ArrayList<Cliente>();
  
 @Autowired
 ClienteDao dao;
 
 @Autowired
 UsuarioLogado usuarioLogado;
 
 @Autowired
 Alerta alerta;
 
@PostConstruct 
public void loade(){
	 lista = dao.listar();		 
	 cliente.setVendedor(usuarioLogado.getVendedor());
 }

  public void salvar(){  			 
	  
	  if(cliente.getId() == 0 ){			 
		  dao.salvar(cliente);   		  
		  alerta.info("Cliente salvo com sucesso");
		  this.cliente = new Cliente();
	  }	
	  else{
		  System.out.println("atualizando cliente");
		  dao.atualiza(cliente);		  
		  alerta.info("Cliente atualizado com sucesso");
		  
	  }	
	  
  }
  
  public String deletar(){
	dao.Deletar(cliente);    
    alerta.info("Cliente deletado com sucesso");   
    return "cliente?faces-redirect=true";
  }
  
  public void busca(){
	lista = dao.busca(cliente.getNome());        
  }

  public String edita(){
	  System.out.println("id do cliente"+cliente.getId());
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

	
  
  
}

package br.com.jortec.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import br.com.jortec.dao.ClienteDao;
import br.com.jortec.servico.ImprimirInstalacao;
import br.com.jortec.util.Alerta;



@Controller
@Scope("request")
public class ClienteBean implements Serializable{
 
	
Cliente cliente = new Cliente();
private List<Cliente> listaDoVendedor = new ArrayList<Cliente>();
List<Cliente> listaConcluidos = new ArrayList<Cliente>();

 @Autowired
 ClienteDao dao;
 
 @Autowired
 UsuarioLogado usuarioLogado;
 
 @Autowired
 ImprimirInstalacao print;
 
 @Autowired
 Alerta alerta;
 
@PostConstruct 
public void loade(){	  
	 if(usuarioLogado.getVendedor() != null){
		 listaDoVendedor = dao.listarPorVendedor(usuarioLogado.getVendedor().getId());
		 listaConcluidos = dao.listarPorVendedorConcluido(usuarioLogado.getVendedor().getId());
		 cliente.setVendedor(usuarioLogado.getVendedor()); 	
		 
		 
	 }
 }

  public void salvar(){  			 
	  
	  if(cliente.getId() == 0 ){	
		  Cliente c = new Cliente();
		  c = dao.buscaPorNome(cliente.getNome());
		  if(!nomeExiste(c)){
			  cliente.setDataCadastro(new Date());
			  cliente.setEstatus("pendente");
			  dao.salvar(cliente);   		  
			  alerta.info("Cliente salvo com sucesso");
			  this.cliente = new Cliente();
		  }else{
			  alerta.error("Nome já exisste");
			  cliente.setNome("");
		  }
		  
	  }	
	  else{		 
		  cliente.setEstatus("cancelado");
		  dao.atualiza(cliente);		  
		  alerta.info("Cliente atualizado com sucesso");
		  
	  }	
	  
  } 
  
  public String deletar(){
	cliente.setEstatus("cancelado");
	dao.atualiza(cliente);
    alerta.info("Cliente deletado com sucesso");   
    return "cliente?faces-redirect=true";
  }
  public void busca(){
		listaDoVendedor = dao.buscaDoVendedorPorNome(cliente.getNome(), usuarioLogado.getVendedor().getId());  		
  }
  
  public String edita(){
	 return "edita";
  }    
//confirma se o nome já existe
	 public boolean nomeExiste(Cliente c){
		 if(c != null){
				if(c.getNome().equals(cliente.getNome())){
					return true;					
				}	
			}
			
		 return false;
	 }

public Cliente getCliente() {
	return cliente;
}
public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}

public List<Cliente> getListaDoVendedor() {
	return listaDoVendedor;
}

public void setListaDoVendedor(List<Cliente> listaDoVendedor) {
	this.listaDoVendedor = listaDoVendedor;
}

public List<Cliente> getListaConcluidos() {
	return listaConcluidos;
}

	
  
  
}

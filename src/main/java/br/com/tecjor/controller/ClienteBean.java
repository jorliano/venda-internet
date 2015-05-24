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
 Cliente cliente = new Cliente();
 List<Cliente> lista = new ArrayList<Cliente>();
  
 @SuppressWarnings("unchecked")
public ClienteBean(){
	 lista = new ClienteDao().listar();
 }
  public void salvar(){
	new ClienteDao().salvar(cliente);    
    Alerta.info("Cliente salvo com sucesso");
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

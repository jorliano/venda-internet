package br.com.tecjor.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jortec.model.Vendedor;
import br.com.tecjor.dao.VendedorDao;
import br.com.tecjor.util.Alerta;

@Controller
@Scope("request")
public class VendedorBean implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -3676344469132286835L;
	
	List<Vendedor> lista = new ArrayList<Vendedor>();
	Vendedor vendedor = new Vendedor();	
	
	@Autowired
	VendedorDao dao;
	
	@Autowired
	Alerta alerta;
	
	@PostConstruct
	public void load(){
		lista = dao.listar();
	}
	
	public String edita(){		
		return "edita";
	}
	public void salvar(){
		if(vendedor.getId() == 0){
		dao.salvar(vendedor);
		alerta.info("vendedor salvo com sucesso");
		this.vendedor = new Vendedor();
		}
		else{
			dao.atualiza(vendedor);
			alerta.info("dados atualizados com sucesso");
		}
		
	}
	
	
	public String deletar(){
		
		dao.deletar(vendedor);
		alerta.info("vendedor deletado com sucesso");
		
		return "vendedor?faces-redirect=true";
	}
	
	public void busca(){
		lista = dao.listarPorNome(vendedor.getPrimeiroNome());
	}
	
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}
	
	public List<Vendedor> getLista() {
		return lista;
	}


	public void setLista(List<Vendedor> lista) {
		this.lista = lista;
	}
	
	
}

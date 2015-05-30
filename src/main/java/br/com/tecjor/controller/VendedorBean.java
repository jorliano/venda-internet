package br.com.tecjor.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.tecjor.dao.VendedorDao;
import br.com.tecjor.util.Alerta;

import com.jortec.model.Vendedor;

@ManagedBean
@RequestScoped
public class VendedorBean implements Serializable{
    
	List<Vendedor> lista = new ArrayList<Vendedor>();
	Vendedor vendedor = new Vendedor();	
	
	@ManagedProperty("#{vendedorDao}")	
	private VendedorDao dao;
	
	@PostConstruct
	public void load(){
		lista = dao.listar();
	}
	
	public String edita(){		
		return "edita";
	}
	public String salvar(){
		if(vendedor.getId() == 0){
		dao.salvar(vendedor);
		Alerta.info("vendedor salvo com sucesso");
		}
		else{
			dao.atualiza(vendedor);
			Alerta.info("dados atualizados com sucesso");
		}
		return "vendedor?faces-redirect=true";
	}
	
	
	public String deletar(){
		
		dao.deletar(vendedor);
		Alerta.info("vendedor deletado com sucesso");
		
		return "vendedor?faces-redirect=true";
	}
	
	public void busca(){
		//lista = dao.listarPorNome(vendedor.getPrimeiroNome());
	}
	
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setDao(VendedorDao dao) {
		this.dao = dao;
	}


	public List<Vendedor> getLista() {
		return lista;
	}


	public void setLista(List<Vendedor> lista) {
		this.lista = lista;
	}
	
	
}

package br.com.tecjor.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.tecjor.dao.VendedorDao;

import com.jortec.model.Vendedor;


public class VendedorBean {
 
	Vendedor vendedor = new Vendedor();
	VendedorDao comando = new VendedorDao();
	List lista = new ArrayList();
	
	public VendedorBean(){
		lista = null;
	}
	
	public void cadastar(){
		
	}
	public void deletar(){
		
	}
}

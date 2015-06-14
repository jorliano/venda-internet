package br.com.jortec.controller;

import br.com.jortec.model.Cliente;
import br.com.jortec.dao.ClienteDao;



public class teste {

	public static void main(String[] args) {
		Cliente c = new Cliente();
		c.setNome("jor");
		new ClienteDao().salvar(c);
		System.out.println();
	}

}

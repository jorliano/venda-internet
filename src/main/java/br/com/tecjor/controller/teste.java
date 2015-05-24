package br.com.tecjor.controller;

import br.com.tecjor.dao.ClienteDao;

import com.jortec.model.Cliente;



public class teste {

	public static void main(String[] args) {
		Cliente c = new Cliente();
		c.setNome("jor");
		new ClienteDao().salvar(c);
		System.out.println();
	}

}

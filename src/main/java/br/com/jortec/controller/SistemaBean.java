package br.com.jortec.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jortec.model.DadosdeEmail;
import br.com.jortec.model.Sistema;
import br.com.jortec.dao.ClienteDao;
import br.com.jortec.dao.SistemaDao;
import br.com.jortec.dao.UsuarioDao;
import br.com.jortec.dao.VendedorDao;
import br.com.jortec.util.Alerta;

@Controller
@Scope("request")
public class SistemaBean implements Serializable {

	private List<Sistema> lista = new ArrayList<Sistema>();
	private List<DadosdeEmail> listEmail = new ArrayList<DadosdeEmail>();
	private List list = new ArrayList();
	private int vendedores;
	private int clientes;
	private int usuarios;

	Sistema sistema = new Sistema();
	DadosdeEmail dadosEmail = new DadosdeEmail();

	@Autowired
	VendedorDao vendedorDao;
	@Autowired
	ClienteDao clienteDao;
	@Autowired
	UsuarioDao usuariodorDao;
	@Autowired
	SistemaDao dao;

	@Autowired
	Alerta alerta;

	@PostConstruct
	public void load() {

		listEmail = dao.listarEmail();
		lista = dao.listar();

		list = vendedorDao.listar();
		vendedores = list.size();
		list = clienteDao.listar();
		clientes = list.size();
		list = usuariodorDao.listar();
		usuarios = list.size();
	}

	public void salvar() {

		if (sistema.getId() == 0) {
			dao.salvar(sistema);
			alerta.info("Plano salvo com sucesso");
			this.sistema = new Sistema();
		} else {
			dao.atualiza(sistema);
			alerta.info("Dados atualizados com sucesso");
			this.sistema = new Sistema();
		}
		lista = dao.listar();

	}

	public String deletar() {
		System.out.println("salvar chamado" + sistema.getPlano());
		dao.deletar(sistema);
		alerta.info("Plano deletado com sucesso");
		lista = dao.listar();
		return null;
	}

	// Tabela email
	public void salvarEmail() {
		dao.atualizaEmail(dadosEmail);
		alerta.info("Dados atualizados com sucesso");
		listEmail = dao.listarEmail();
		this.dadosEmail = new DadosdeEmail();
	}

	public void deletarEmail() {
		dao.deletarEmail(dadosEmail);
		alerta.info("Dados atualizados com sucesso");
		this.dadosEmail = new DadosdeEmail();
	}

	public List<Sistema> getLista() {
		return lista;

	}

	public void setLista(List<Sistema> lista) {
		this.lista = lista;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public int getVendedores() {
		return vendedores;
	}

	public void setVendedores(int vendedores) {
		this.vendedores = vendedores;
	}

	public int getClientes() {
		return clientes;
	}

	public void setClientes(int clientes) {
		this.clientes = clientes;
	}

	public int getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(int usuarios) {
		this.usuarios = usuarios;
	}

	public List<DadosdeEmail> getListEmail() {
		return listEmail;
	}

	public void setListEmail(List<DadosdeEmail> listEmail) {
		this.listEmail = listEmail;
	}

	public DadosdeEmail getDadosEmail() {
		return dadosEmail;
	}

	public void setDadosEmail(DadosdeEmail dadosEmail) {
		this.dadosEmail = dadosEmail;
	}

}

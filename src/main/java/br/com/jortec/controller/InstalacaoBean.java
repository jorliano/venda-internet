package br.com.jortec.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jortec.dao.ClienteDao;
import br.com.jortec.model.Cliente;
import br.com.jortec.servico.ImprimirInstalacao;
import br.com.jortec.util.Alerta;

@Controller
@Scope("view")
public class InstalacaoBean implements Serializable {

	Cliente cliente = new Cliente();
	List<Cliente> lista = new ArrayList<Cliente>();	
	private int paginaAtual = 0;

	@Autowired
	ClienteDao dao;

	@Autowired
	UsuarioLogado usuarioLogado;

	@Autowired
	ImprimirInstalacao print;

	@Autowired
	Alerta alerta;

	@PostConstruct
	public void loade() {
		lista = dao.paginacao(0);

	}

	// instalação com restrições do adm
	public String cancelar() {
		cliente.setEstatus("cancelado");
		cliente.setDataCadastro(new Date());
		dao.atualiza(cliente);
		alerta.info("Instalação cancelado com sucesso");
		lista = dao.listar();
		return null;
	}

	public String finalizado() {
		cliente.setEstatus("concluido");
		cliente.setDataCadastro(new Date());
		dao.atualiza(cliente);
		lista = dao.listar();
		alerta.info("Instalação concluida do sucesso");
		return null;
	}
    //pagina do vendedor
	public void busca() {
		lista = dao.buscaDoVendedorPorNome(cliente.getNome(), usuarioLogado
				.getVendedor().getId());
		paginaAtual = 0;
	}
	//pagina do adm
	public void buscaIntalacao() {
		lista = dao.buscaDoInstalacoPorNome(cliente.getNome());
	}

	// Paginação das instalações
	public void anterior() {
		if (paginaAtual >= 7) {
			paginaAtual = paginaAtual - 7;
			lista = dao.paginacao(paginaAtual);
		}
	}

	public void proximo() {

		if (paginaAtual >= 7 && lista.size() > paginaAtual) {
			paginaAtual = paginaAtual + 7;
			lista = dao.paginacao(paginaAtual);

		}
		if (paginaAtual < 7) {
			paginaAtual = 7;
			lista = dao.paginacao(paginaAtual);

		}

	}

	

	public void imprimir() {

		print.imprime(cliente,usuarioLogado.getNomeLogado());

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

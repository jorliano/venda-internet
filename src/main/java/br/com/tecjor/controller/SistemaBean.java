package br.com.tecjor.controller;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jortec.model.Sistema;
import br.com.tecjor.dao.SistemaDao;
import br.com.tecjor.util.Alerta;


@Controller
@Scope("request")
public class SistemaBean implements Serializable{

	private List<Sistema> lista = new ArrayList<Sistema>();
	Sistema sistema = new Sistema();
	
	@Autowired
	SistemaDao dao;
	
	@Autowired
	Alerta alerta;
	
	@PostConstruct
	public void load() {
		lista = dao.listar();
	}
	
    public void salvar(){	
	 
		if(sistema.getId() == 0){
			 dao.salvar(sistema);
			 alerta.info("Plano salvo com sucesso");
			 this.sistema = new Sistema();			 
		}else{
			 dao.atualiza(sistema);
			 alerta.info("Dados atualizados com sucesso");
			 this.sistema = new Sistema();		
		}
		lista = dao.listar();
		 
	  }		
	
	public String deletar(Sistema sistema){		
		System.out.println("salvar chamado" +sistema.getPlano());
		dao.deletar(sistema);
		alerta.info("Plano deletado com sucesso");
		lista = dao.listar();	
		return null;
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
	
	
}

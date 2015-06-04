package br.com.tecjor.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jortec.model.Sistema;


@Transactional
@Repository
public class SistemaDao {

	@PersistenceContext
	private EntityManager manager;
	
	
	public void salvar(Sistema sistema) {
		manager.persist(sistema);
	}	
	
	public void atualiza(Sistema sistema) {
		manager.merge(sistema);		
	}	
	
	public void deletar(Sistema sistema){
		manager.remove(manager.merge(sistema));
	}
	
	public List<Sistema> listar(){
		return manager.createQuery("select s from Sistema s order by valor", Sistema.class)
				.getResultList();
	}
	
}

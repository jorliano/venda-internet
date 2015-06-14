package br.com.jortec.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jortec.model.DadosdeEmail;
import br.com.jortec.model.Sistema;
import br.com.jortec.model.Usuario;


@Transactional
@Repository
public class SistemaDao {

	@PersistenceContext
	private EntityManager manager;
	
	
	public void salvar(Sistema sistema) {
		manager.persist(sistema);
	}	
	
	public void atualizaEmail(DadosdeEmail dadosEmail) {
		manager.merge(dadosEmail);		
	}
	
	public void atualiza(Sistema sistema) {
		manager.merge(sistema);		
	}	
	
	public void deletar(Sistema sistema){
		manager.remove(manager.merge(sistema));
	}
	
	public void deletarEmail(DadosdeEmail dadosEmail) {
		manager.remove(manager.merge(dadosEmail));
		
	}
	
	public List<Sistema> listar(){
		return manager.createQuery("select s from Sistema s order by valor ", Sistema.class)
				.getResultList();
	}
	public DadosdeEmail busca() {
		try{
        	return manager
        			.createQuery("select e from DadosdeEmail e ", DadosdeEmail.class)        			                			      
        			        .getSingleResult();
        }catch(NoResultException e){
        	return null;
        }	
	}

	public List<DadosdeEmail> listarEmail() {
		return manager.createQuery("select e from DadosdeEmail e", DadosdeEmail.class)
				.getResultList();
	}

	

	
}

package br.com.tecjor.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jortec.model.Cliente;
import br.com.jortec.model.Vendedor;
import br.com.tecjor.controller.UsuarioLogado;

@Transactional
@Repository
public class ClienteDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Cliente cliente) {		        
		
			manager.persist(manager.merge(cliente));
			System.out.println("persistiu");				
	}
	
	public void atualiza(Cliente cliente) {					
		manager.merge(cliente);
		System.out.println("persistiu");				
    }

	public void Deletar(Cliente cliente) {
		manager.remove(manager.merge(cliente));
	}

	public List<Cliente> listar(){
		return manager.createQuery("select c from Cliente c ",Cliente.class)
				.getResultList();
		
	}
	
	public List<Cliente> busca(String nome){
		
		Query query = manager.createQuery("select c from Cliente c where nome like %:name order by nome", Cliente.class);
		query.setParameter("nome", nome); 
	    
		return query.getResultList();
	}

	

}

package br.com.tecjor.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jortec.model.Vendedor;

@Transactional
@Repository
public  class VendedorDao {

	@PersistenceContext
	private EntityManager manager;
	
	public Vendedor buscaPor(String login,String senha) {
		 
        try{
        	return manager
        			.createQuery("select v from Vendedor v "
        			        + "where nome = :login and senha =:senha", Vendedor.class)
        			        .setParameter("login",login)
        			        .setParameter("senha", senha)
        			        .getSingleResult();
        }catch(NoResultException e){
        	return null;
        }				       
	}	
}

package br.com.tecjor.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jortec.model.Usuario;

@Transactional
@Repository
public class UsuarioDao  {

	@PersistenceContext
	private EntityManager manager;
	
	public Usuario buscaPor(String login,String senha) {
		 
        try{
        	return manager
        			.createQuery("select u from Usuario u "
        			        + "where login = :login and senha =:senha", Usuario.class)
        			        .setParameter("login",login)
        			        .setParameter("senha", senha)
        			        .getSingleResult();
        }catch(NoResultException e){
        	return null;
        }				       
		
	}	
}

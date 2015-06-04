package br.com.tecjor.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jortec.model.Usuario;
import br.com.jortec.model.Usuario;

@Transactional
@Repository
public class UsuarioDao  {

	@PersistenceContext
	private EntityManager manager;
	
	
	public void salvar(Usuario usuario) {
		manager.persist(usuario);
	}	
	
	public void atualiza(Usuario usuario) {
		manager.merge(usuario);		
	}	
	
	public void deletar(Usuario usuario){
		manager.remove(manager.merge(usuario));
	}
	
	public List<Usuario> listar(){
		return manager.createQuery("select u from Usuario u order by nome", Usuario.class)
				.getResultList();
	}
	
	
	
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

	public List<Usuario> listarPorNome(String nome) {
		try{
        	return manager
        			.createQuery("select v from Usuario v "
        			        + "where nome like :nome", Usuario.class)
        			        .setParameter("nome",nome+"%")        			        
        			        .getResultList();
        }catch(NoResultException e){
        	return null;
        }			
	}
}

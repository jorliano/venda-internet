package br.com.tecjor.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.tecjor.util.JPAUtil;

import com.jortec.model.Cliente;
import com.jortec.model.Usuario;

public final class UsuarioDao  {

	public Usuario autentica(String nome,String senha) {
		EntityManager em = JPAUtil.getEntityManager();		
		try {
			em.getTransaction().begin();
			 Query query = em.createQuery("select u from Usuario u where nome = :name"
			 		                    + " and senha =:senha", Usuario.class);
		        query.setParameter("name", nome);
		        query.setParameter("senha", senha);
		        
		       	return (Usuario) query.getSingleResult();
		      
		       
		}
		catch(Exception e){
			System.out.println("erro ao pesquisar");
		}
		finally{
			em.close();
		}
		return null;
	}	
}

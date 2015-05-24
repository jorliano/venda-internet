package br.com.tecjor.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jortec.model.Cliente;
import com.jortec.model.Usuario;

import br.com.tecjor.util.JPAUtil;

public final class ClienteDao {

	public void salvar(Cliente cliente) {
		EntityManager em = JPAUtil.getEntityManager();		
		try {
			em.getTransaction().begin();
			System.out.println("iniciou uma transação");
			em.persist(cliente);
			System.out.println("persistiu");
			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Erro ao salvar "+e.getMessage()+" "+e.getStackTrace());
			//em.close();
		} finally {
			em.close();
		}
	}

	public void Deletar(Object objeto) {
		EntityManager em = JPAUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(objeto);
			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Erro ao deletar");
		} finally {
			em.close();
		}
	}

	public List listar(){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select c from Cliente c order by nome", Cliente.class);
		 
	    return query.getResultList();
	}
	
	public List busca(String nome){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select c from Cliente c where nome:name order by nome", Cliente.class);
		query.setParameter("nome", nome); 
	    
		return query.getResultList();
	}

	

}

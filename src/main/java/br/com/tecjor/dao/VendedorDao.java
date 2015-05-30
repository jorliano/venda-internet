package br.com.tecjor.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jortec.model.Cliente;
import com.jortec.model.Vendedor;

@Transactional
@Repository
public  class VendedorDao {

	@PersistenceContext
	private EntityManager manager;		

	public void salvar(Vendedor vendedor) {
		manager.persist(vendedor);
	}	
	
	public void deletar(Vendedor vendedor){
		manager.remove(manager.merge(vendedor));
	}
	
	public List<Vendedor> listar(){
		return manager.createQuery("select v from Vendedor v", Vendedor.class)
				.getResultList();
	}
	
	/*public List<Vendedor> listarPorNome(String nome){
		try{
		  return manager
				.createQuery("select v from Vendedor v where  primeiroNome =:nome",Vendedor.class)
				    .setParameter("nome", nome) .getResultList();
		}catch(NoResultException e){
			return null;
		}
	}*/
	
	public Vendedor buscaPor(String login,String senha) {
		 
        try{
        	return manager
        			.createQuery("select v from Vendedor v "
        			        + "where login = :login and senha =:senha", Vendedor.class)
        			        .setParameter("login",login)
        			        .setParameter("senha", senha)
        			        .getSingleResult();
        }catch(NoResultException e){
        	return null;
        }				       
	}

	public void atualiza(Vendedor vendedor) {
		manager.merge(vendedor);
		
	}
}

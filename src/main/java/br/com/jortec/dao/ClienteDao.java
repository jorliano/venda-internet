package br.com.jortec.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jortec.model.Cliente;


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

	//Para instalação adm
	public List<Cliente> listar(){	
		
		String consulta = "select c from Cliente c  where estatus = 'pendente' order by dataCadastro ";		
		TypedQuery<Cliente> query = manager.createQuery(consulta, Cliente.class);			
		return query.getResultList();
	}
    public List<Cliente> buscaDoInstalacoPorNome(String nome){
		
		String consulta = "select c from Cliente c  where  estatus = 'pendente' and nome like :nome order by dataCadastro ";		
		TypedQuery<Cliente> query = manager.createQuery(consulta, Cliente.class);			
		query.setParameter("nome", nome+"%");	
		query.setMaxResults(7);	
		return query.getResultList();
	}
   public List<Cliente> paginacao(int paginaAtual){
		
	   String consulta = "select c from Cliente c  where  estatus = 'pendente' order by dataCadastro ";		
	   TypedQuery<Cliente> query = manager.createQuery(consulta, Cliente.class);			
	       query.setMaxResults(7);	       
	       query.setFirstResult(paginaAtual);   	   				
		
	  return query.getResultList();
	}


	//Para vendedor
	public List<Cliente> listarPorVendedor(long id){	
	 try{	
		String consulta = "select c from Cliente c  where c.vendedor.id=:id  and estatus = 'pendente' order by dataCadastro desc";		
		TypedQuery<Cliente> query = manager.createQuery(consulta, Cliente.class);	
		query.setParameter("id", id);	
		query.setMaxResults(20);	
		return query.getResultList();
	 }catch(NoResultException e){
     	return null;
     }		
	}
	
	public List<Cliente> buscaDoVendedorPorNome(String nome,long id){
		
		String consulta = "select c from Cliente c  where c.vendedor.id=:id and estatus = 'pendente' and nome like :nome order by dataCadastro desc";		
		TypedQuery<Cliente> query = manager.createQuery(consulta, Cliente.class);	
		query.setParameter("id", id);		
		query.setParameter("nome", nome+"%");
		query.setMaxResults(20);	
		return query.getResultList();
	}
    //perfil concluido
	public List<Cliente> listarPorVendedorConcluido(long id){	
		
    	String consulta = "select c from Cliente c  where c.vendedor.id=:id  and estatus = 'concluido' order by dataCadastro desc";		
		TypedQuery<Cliente> query = manager.createQuery(consulta, Cliente.class);	
		query.setParameter("id", id);	
		query.setMaxResults(3);	
		return query.getResultList();
	}
	
	//Pesquisar Nome existente
	public Cliente buscaPorNome(String nome) {
		 
        try{
        	return manager
        			.createQuery("select c from Cliente c "
        			        + "where nome=:nome", Cliente.class)
        			        .setParameter("nome",nome)        			        
        			        .getSingleResult();
        }catch(NoResultException e){
        	return null;
        }				       
	}
	
    
	

}

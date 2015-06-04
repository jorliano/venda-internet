package br.com.tecjor.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

	//Para instalação adm
	public List<Cliente> listar(){	
		
		String consulta = "select c from Cliente c join  c.vendedor where estatus = 'pendente' order by dataCadastro ";		
		TypedQuery<Cliente> query = manager.createQuery(consulta, Cliente.class);			
		return query.getResultList();
	}
    public List<Cliente> buscaDoInstalacoPorNome(String nome){
		
		String consulta = "select c from Cliente c  where  estatus = 'pendente' and nome like :nome order by dataCadastro ";		
		TypedQuery<Cliente> query = manager.createQuery(consulta, Cliente.class);			
		query.setParameter("nome", nome+"%");		
		return query.getResultList();
	}


	//Para vendedor
	public List<Cliente> listarPorVendedor(long id){	
		
		String consulta = "select c from Cliente c  where c.vendedor.id=:id  and estatus = 'pendente' order by dataCadastro desc";		
		TypedQuery<Cliente> query = manager.createQuery(consulta, Cliente.class);	
		query.setParameter("id", id);		
		return query.getResultList();
	}
	
	public List<Cliente> buscaDoVendedorPorNome(String nome,long id){
		
		String consulta = "select c from Cliente c  where c.vendedor.id=:id and estatus = 'pendente' and nome like :nome order by dataCadastro desc";		
		TypedQuery<Cliente> query = manager.createQuery(consulta, Cliente.class);	
		query.setParameter("id", id);		
		query.setParameter("nome", nome+"%");		
		return query.getResultList();
	}

	

}

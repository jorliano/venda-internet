package br.com.jortec.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jortec.model.Cliente;
import br.com.jortec.model.Vendedor;

@Transactional
@Repository
public class GraficoDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Cliente> listarPrimeiroTrimestre(int ano) {

		String consulta = "select c from Cliente c where dataCadastro BETWEEN '"+ano+"/01/01' AND '"+ano+"/03/31' ";
		TypedQuery<Cliente> query = manager
				.createQuery(consulta, Cliente.class);
		return query.getResultList();
	}	
	public List<Cliente> listarSegundoTrimestre(int ano) {

		String consulta = "select c from Cliente c where dataCadastro BETWEEN '"+ano+"/04/01' AND '"+ano+"/06/31' ";
		TypedQuery<Cliente> query = manager
				.createQuery(consulta, Cliente.class);
		return query.getResultList();
	}
	public List<Cliente> listarTerceiroTrimestre(int ano) {

		String consulta = "select c from Cliente c where dataCadastro BETWEEN '"+ano+"/07/01' AND '"+ano+"/09/31' ";
		TypedQuery<Cliente> query = manager
				.createQuery(consulta, Cliente.class);
		return query.getResultList();
	}
	public List<Cliente> listarQuartoTrimestre(int ano) {

		String consulta = "select c from Cliente c where dataCadastro BETWEEN '"+ano+"/10/01' AND '"+ano+"/12/31' ";
		TypedQuery<Cliente> query = manager
				.createQuery(consulta, Cliente.class);
		return query.getResultList();
	}
  
	//Grafico pieVendedor
	
	public List<Cliente> listaMes(int mes,int ano){
		String consulta = "select c from Cliente c where estatus='concluido' and dataCadastro BETWEEN '"+ano+"/0"+mes+"/01' AND '"+ano+"/0"+mes+"/31' ";
		TypedQuery<Cliente> query = manager
				.createQuery(consulta, Cliente.class);
		return query.getResultList();
	}
	
	public List<Vendedor> listaVendedor(){
		String consulta = "select v from Vendedor v ";
		TypedQuery<Vendedor> query = manager
				.createQuery(consulta, Vendedor.class);
		return query.getResultList();
	}
}

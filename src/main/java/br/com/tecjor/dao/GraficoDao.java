package br.com.tecjor.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jortec.model.Cliente;

@Transactional
@Repository
public class GraficoDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Cliente> listarPrimeiroTrimestre() {

		String consulta = "select c from Cliente c where dataCadastro BETWEEN '01/01/2015' AND '31/03/2015' ";
		TypedQuery<Cliente> query = manager
				.createQuery(consulta, Cliente.class);
		return query.getResultList();
	}	
	public List<Cliente> listarSegundoTrimestre() {

		String consulta = "select c from Cliente c where dataCadastro BETWEEN '01/04/2015' AND '31/06/2015' ";
		TypedQuery<Cliente> query = manager
				.createQuery(consulta, Cliente.class);
		return query.getResultList();
	}

	public List<Cliente> listarTerceiroTrimestre() {

		String consulta = "select c from Cliente c where dataCadastro BETWEEN '01/07/2015' AND '31/09/2015' ";
		TypedQuery<Cliente> query = manager
				.createQuery(consulta, Cliente.class);
		return query.getResultList();
	}
	public List<Cliente> listarQuartoTrimestre() {

		String consulta = "select c from Cliente c where dataCadastro BETWEEN '01/10/2015' AND '31/12/2015' ";
		TypedQuery<Cliente> query = manager
				.createQuery(consulta, Cliente.class);
		return query.getResultList();
	}

}

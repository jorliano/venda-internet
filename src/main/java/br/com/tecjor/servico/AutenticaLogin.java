package br.com.tecjor.servico;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.springframework.stereotype.Service;

import br.com.tecjor.dao.UsuarioDao;
import br.com.tecjor.dao.VendedorDao;

import com.jortec.model.Usuario;
import com.jortec.model.Vendedor;

@ManagedBean
public class AutenticaLogin {
  
	@ManagedProperty("#{usuarioDao}")
	private UsuarioDao usuarioDao;
	
	@ManagedProperty("#{vendedorDao}")
	private VendedorDao vendedorDao;
	
	public Usuario autenticaLogUsuario(String login,String senha){			
		Usuario usuario = usuarioDao.buscaPor(login, senha);			
		return usuario;
	}
	
	public Vendedor autenticaLogVendedor(String login,String senha){			
		Vendedor vendedor = vendedorDao.buscaPor(login, senha);		
		return vendedor;
	}
	
	public void setVendedorDao(VendedorDao vendedorDao) {
		this.vendedorDao = vendedorDao;
	}
	
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
}

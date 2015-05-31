package br.com.tecjor.servico;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.jortec.model.Usuario;
import br.com.jortec.model.Vendedor;
import br.com.tecjor.dao.UsuarioDao;
import br.com.tecjor.dao.VendedorDao;

@Service
@Scope("request")
public class AutenticaLogin {
  
	@Autowired
	UsuarioDao usuarioDao;
	
	@Autowired
	VendedorDao vendedorDao;
	
	public Usuario autenticaLogUsuario(String login,String senha){			
		Usuario usuario = usuarioDao.buscaPor(login, senha);			
		return usuario;
	}
	
	public Vendedor autenticaLogVendedor(String login,String senha){			
		Vendedor vendedor = vendedorDao.buscaPor(login, senha);		
		return vendedor;
	}
	
	
	
}

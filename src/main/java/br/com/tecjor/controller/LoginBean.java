package br.com.tecjor.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jortec.model.Usuario;
import br.com.jortec.model.Vendedor;
import br.com.tecjor.servico.AutenticaLogin;
import br.com.tecjor.util.Alerta;

@Controller
@Scope("request")
public class LoginBean {
  
	private String Login;
	private String Senha;
	private String estatus;
	
	@Autowired
	UsuarioLogado usuarioLogado;
	
	@Autowired
	AutenticaLogin autenticaLogin;
	
	@Autowired
	Alerta alerta;
	
	public String logar(){		
		
		if(estatus.equals("administrador")){			
			Usuario usuario = autenticaLogin.autenticaLogUsuario(Login, Senha);
			
			if(usuario != null){			
				usuarioLogado.logarUsuario(usuario);
				usuarioLogado.setNomeLogado(usuario.getNome());
				return "sistema?faces-redirect=true";
			}
			
		}else{			
			Vendedor vendedor = autenticaLogin.autenticaLogVendedor(Login, Senha);
			if(vendedor != null){				
				usuarioLogado.logarVendedor(vendedor);
				usuarioLogado.setNomeLogado(vendedor.getPrimeiroNome());
				return "sistema?faces-redirect=true";
			}
			
		}			
		alerta.error("Usuario ou Senha incorreto");   
		return null;
	}
	public String proximaPagina(){	
		//alerta.info("Seja Bem vindo "+usuarioLogado.getNomeLogado());
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.setKeepMessages(true);		
		return null;
		
	}
	

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
}

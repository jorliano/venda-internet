package br.com.tecjor.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.tecjor.servico.AutenticaLogin;
import br.com.tecjor.util.Alerta;

import com.jortec.model.Usuario;
import com.jortec.model.Vendedor;

@ManagedBean
@RequestScoped
public class LoginBean {
  
	private String Login;
	private String Senha;
	private String estatus;
	
	@ManagedProperty("#{usuarioLogado}")
	private UsuarioLogado usuarioLogado;
	
	@ManagedProperty("#{autenticaLogin}")
	private AutenticaLogin autenticaLogin;
	
	
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
		Alerta.error("Usuario ou Senha incorreto");   
		return null;
	}
	
	
	
	public void setAutenticaLogin(AutenticaLogin autenticaLogin) {
		this.autenticaLogin = autenticaLogin;
	}
	
	public void setUsuarioLogado(UsuarioLogado usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
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

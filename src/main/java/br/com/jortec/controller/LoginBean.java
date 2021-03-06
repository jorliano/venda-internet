package br.com.jortec.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jortec.model.Usuario;
import br.com.jortec.model.Vendedor;
import br.com.jortec.servico.AutenticaLogin;
import br.com.jortec.servico.ImagemValidator;
import br.com.jortec.util.Alerta;

@Controller
@Scope("request")
public class LoginBean {
	
	private String Login;
	private String Senha;
	private String estatus ="vendedor";
	
	@Autowired
	UsuarioLogado usuarioLogado;
	
	@Autowired
	AutenticaLogin autenticaLogin;
	
	@Autowired
	Alerta alerta;
	
	@Autowired
	ImagemValidator img;
	
	public String logar(){		
		
		if(estatus.equals("administrador")){			
			Usuario usuario = autenticaLogin.autenticaLogUsuario(Login, Senha);
			
			if(usuario != null){			
				usuarioLogado.logarUsuario(usuario);
				usuarioLogado.setNomeLogado(usuario.getNome());
				alerta.sucess("Seja bem vindo "+usuarioLogado.getNomeLogado(), true);
				return "sistema?faces-redirect=true";
			}
			
		}else{			
			Vendedor vendedor = autenticaLogin.autenticaLogVendedor(Login, Senha);
			if(vendedor != null){		
				img.caregarImagem(vendedor);
				usuarioLogado.logarVendedor(vendedor);
				usuarioLogado.setNomeLogado(vendedor.getPrimeiroNome());
				alerta.sucess("Seja bem vindo "+usuarioLogado.getNomeLogado(), true);
				return "sistema?faces-redirect=true";
			}
			
		}			
		alerta.error("Usuario ou Senha incorreto"); 		
		return null;
	}
	
	public String loginPermissao(){
		Vendedor vendedor = autenticaLogin.autenticaLogVendedor(Login, Senha);
		if(vendedor != null){		
			img.caregarImagem(vendedor);
			usuarioLogado.logarVendedor(vendedor);
			usuarioLogado.setNomeLogado(vendedor.getPrimeiroNome());
			alerta.sucess("Você logou com o vendedor "+usuarioLogado.getNomeLogado(), true);
			return "/paginas/cliente/cliente?faces-redirect=true";
		}
		alerta.error("Usuario ou Senha incorreto");   
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

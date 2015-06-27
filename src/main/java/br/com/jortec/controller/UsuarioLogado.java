package br.com.jortec.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jortec.model.Usuario;
import br.com.jortec.model.Vendedor;
import br.com.jortec.servico.ImagemValidator;

@Controller
@Scope("session")
public class UsuarioLogado implements Serializable{
    private List permissao = new ArrayList();
	private Usuario usuario;
	private Vendedor vendedor;
	private String nomeLogado;  
	private String img;
	
	public UsuarioLogado(){
		permissao.add("/paginas/cliente/cliente.xhtml");
		permissao.add("/paginas/vendedor/instalacoes.xhtml");
		permissao.add("/paginas/vendedor/cadastro.xhtml");
		permissao.add("/paginas/vendedor/edita.xhtml");
		permissao.add("/paginas/vendedor/perfil.xhtml");
	}
	
	public void logarUsuario(Usuario usuario){
		this.usuario = usuario;
		img = "/imagens/adm/adm.png";
	}
	public void logarVendedor(Vendedor vendedor){
		this.vendedor = vendedor;
		img= vendedor.getUrl();
	}
	
	public String desloga() {
		if(this.usuario != null && this.vendedor != null){
			this.vendedor = null;
			setNomeLogado(this.usuario.getNome());
			img = "/imagens/adm/adm.png";
			return "/sistema?faces-redirect=true";
		}			
		else{  
			this.usuario = null;
			this.vendedor = null;
			return "/index.xhtml?faces-redirect=true";
		}		
		
	}	
		
	public boolean isLogado(){
		if(usuario != null || vendedor != null)
			return true;
		return false;
	}
	
	public boolean permissao(String pagina){
		 if(permissao.contains(pagina))
			 return true;
		 
		return false;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Vendedor getVendedor() {
		return vendedor;
	}
	public String getNomeLogado() {
		return nomeLogado;
	}
	public void setNomeLogado(String nomeLogado) {
		this.nomeLogado = nomeLogado;
	}

	public String getImg() {
		return img;
	}
	
	
}

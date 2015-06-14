package br.com.jortec.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jortec.model.Vendedor;
import br.com.jortec.dao.VendedorDao;
import br.com.jortec.servico.Email;
import br.com.jortec.servico.ImagemValidator;
import br.com.jortec.util.Alerta;

@Controller
@Scope("request")
public class VendedorBean implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -3676344469132286835L;
	
	List<Vendedor> lista = new ArrayList<Vendedor>();
	Vendedor vendedor = new Vendedor();		
	private String confirmeSenha;
	private Part foto;
	
	@Autowired
	VendedorDao dao;
	
	@Autowired
	Alerta alerta;
	
	@Autowired
	ImagemValidator img;
	@Autowired
	Email email;
	
	@PostConstruct
	public void load(){
		lista = dao.listar();
	}
	
	public String edita(){		
		return "edita";
	}
	public void salvar() {
		try {
		if(vendedor.getId() == 0){	
			if(vendedor.getSenha().equals(confirmeSenha)){
				  img.salvaImagem(vendedor.getPrimeiroNome()+lista.size()+1+".jpg");
				  vendedor.setImg("/imagens/vendedor/"+vendedor.getPrimeiroNome()+lista.size()+1+".jpg");
				  dao.salvar(vendedor);		
				  alerta.info("vendedor salvo com sucesso");
				  this.vendedor = new Vendedor();
			}else
			{
				Alerta.error("Senhas não conferi");
			}
		
		}
		else{	
			Vendedor v = new Vendedor();
			v = dao.buscaPor(vendedor.getLogin(), vendedor.getSenha());
			  if(v!= null){
				 if(v.getSenha().equals(vendedor.getSenha())){
					 img.salvaImagem(vendedor.getPrimeiroNome()+vendedor.getId()+".jpg");			
						vendedor.setImg("/imagens/vendedor/"+vendedor.getPrimeiroNome()+vendedor.getId()+".jpg");
						vendedor.setSenha(confirmeSenha);
						dao.atualiza(vendedor);
						alerta.info("dados atualizados com sucesso");
				 }
				 else{
					 Alerta.error("Senha antiga errada");
				 }
					 
			 }else {
				Alerta.error("Senha antiga errada");
			}
					
			
		}
		} catch (IOException e) {
			System.out.println("erro : "+e.getMessage()+e.getStackTrace());
		}
		
	}
	
	
	public String deletar(){
		
		dao.deletar(vendedor);
		alerta.info("vendedor deletado com sucesso");
		
		return "vendedor?faces-redirect=true";
	}
	
	public void carregaFoto() throws IOException{
		
		if(foto != null){
			System.out.println("arquivo nulo");
		img.Upload(foto, "vendedor.jpg");
		vendedor.setImg("/imagens/vendedor.jpg");
		
		}
		
	}
	
	public void busca(){
		lista = dao.listarPorNome(vendedor.getPrimeiroNome());
	}
	
	public void enviarEmail(){
		email.enviarEmail("testando a api", "tap");
		Alerta.info("email enviado com sucesso");
	}
	
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}
	
	public List<Vendedor> getLista() {
		return lista;
	}


	public void setLista(List<Vendedor> lista) {
		this.lista = lista;
	}

	public Part getFoto() {
		return foto;
	}

	public void setFoto(Part foto) {
		this.foto = foto;
	}

	public String getConfirmeSenha() {
		return confirmeSenha;
	}

	public void setConfirmeSenha(String confirmeSenha) {
		this.confirmeSenha = confirmeSenha;
	}
	
	
}

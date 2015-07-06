package br.com.jortec.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.BehaviorEvent;
import javax.servlet.http.Part;

import org.primefaces.event.CaptureEvent;
import org.primefaces.model.CroppedImage;
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
public class VendedorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3676344469132286835L;

	List<Vendedor> lista = new ArrayList<Vendedor>();
	
	
	Vendedor vendedor = new Vendedor();
	private String confirmeSenha;
	private Part foto;
	private String conteudoEmail;
	

	@Autowired
	VendedorDao dao;

	@Autowired
	Alerta alerta;

	@Autowired
	ImagemValidator img;
	
	@Autowired
	Email email;
    
	@Autowired
	UsuarioLogado vendedorLogado;
	
	@PostConstruct
	public void load() {
		lista = dao.listar();
		
		if (!lista.isEmpty())
			img.caregarImagems(lista);
	}

	public String edita() {
		return "edita";
	}

	public void salvar() {

		if (vendedor.getId() == 0) {
			if (vendedor.getSenha().equals(confirmeSenha)) {
				if (img.salvaImagem() != null) {
					vendedor.setImg(img.salvaImagem());
					vendedor.setUrl("/imagens/" +getRandomImageName() +".jpg");
				} else {
					vendedor.setUrl("/imagens/vendedor/padrao.png");
				}

				dao.salvar(vendedor);
				alerta.info("vendedor salvo com sucesso");
				this.vendedor = new Vendedor();
			} else {
				Alerta.error("Senhas não conferi");
			}

		} else {
			Vendedor v = new Vendedor();
			v = dao.buscaPorId(vendedor.getId());
			
			// Conferir se tem imagem
			if (img.salvaImagem() != null) {
				vendedor.setImg(img.salvaImagem());
				vendedor.setUrl("/imagens/" +getRandomImageName() +".jpg");
			} else {				
				vendedor.setImg(v.getImg());
				vendedor.setUrl(v.getUrl());
			}
			// Validas se senha antiga esta certa
			if (vendedor.getSenha().length() > 0 || confirmeSenha.length() > 0) {

				if (v.getSenha().equals(vendedor.getSenha()) && confirmeSenha.length() > 0) {
					vendedor.setSenha(confirmeSenha);
					dao.atualiza(vendedor);
					alerta.info("dados atualizados com sucesso");
				} else {
					Alerta.error("Senha antiga errada");
				}

			} else {
				vendedor.setSenha(v.getSenha());
				dao.atualiza(vendedor);
				alerta.info("dados atualizados com sucesso");
			}

		}

	}

	public String deletar() {

		dao.deletar(vendedor);
		alerta.info("vendedor deletado com sucesso");

		return "vendedor?faces-redirect=true";
	}

	public void carregaFoto() throws IOException {
		System.out.println(foto.getSize());
		if (foto != null) {
			
			String nome = img.Upload(foto, "vendedor.jpg");			
			vendedor.setUrl("/imagens/vendedor/"+nome);
			System.out.println(vendedor.getUrl());
           
		}

	}
	//webcam
	 public void oncapture(CaptureEvent captureEvent) {	        
	        byte[] data = captureEvent.getData();
	      
	        vendedor.setImg(data);
	        vendedor.setUrl("/imagens/camera.jpg");
	        img.caregarImagem(vendedor);
	        
	 }      

	public void busca() {
		lista = dao.listarPorNome(vendedor.getPrimeiroNome());
	}

	public void enviarEmail() {
	    String msgEmail = "Nome do vendedor : "+vendedorLogado.getVendedor().getPrimeiroNome()
	                 +"\n Conteudo : \n"+conteudoEmail;
	                 
		
		if(email.enviarEmail(msgEmail, "Necessario para melhorar a empresa") == true){
			Alerta.info("email enviado com sucesso");
		}else
			Alerta.warn("A empresa precisa cadastrar um email valido ");
		
	}
    //nomes aleatorios
	 private String getRandomImageName() {
	        int i = (int) (Math.random() * 10000000);
	         
	        return String.valueOf(i);
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

	public String getConteudoEmail() {
		return conteudoEmail;
	}

	public void setConteudoEmail(String conteudoEmail) {
		this.conteudoEmail = conteudoEmail;
	}

	    
}

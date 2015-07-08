package br.com.jortec.servico;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.jortec.model.Vendedor;
import br.com.jortec.util.Alerta;

@Service
@Scope("view")
public class ImagemValidator implements Serializable{ 
	
@Autowired
Alerta alerta;

	/*Ler arquivo part e gera um arquivo q serar salvo */	
	 private final int limitTamanho = 2000000;
	 private final String tipoArquivo = "jpeg|jpg|gif|png";	   
	 String realSavePath;
	 String fileSavePath ;
	    
      
	    public String Upload(Part foto ,String caminho) {
	        String arquivoSalvo = "semImagens.jpg";
	        try {	        	
	        	
	            if (foto.getSize() > 0) {
	                String nomeArquivo = getFilename(foto);	                             
	                if (verificaTipoArquivo(nomeArquivo)) {
	                    if (foto.getSize() > this.limitTamanho) {
	                       System.out.println("tamanho grand");
	                    	 alerta.error("Tamanho muito grande");
	                    } else {
	                    	                      	                    	
	                    	
	                       
	                        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();	                                            
	                        System.out.println(servletContext.getRealPath(""));
	                        String radon = getRandomImageName();
	                        realSavePath = servletContext.getRealPath("");	        
	                        realSavePath = realSavePath+"/imagens/vendedor/"+radon+".jpg";
	                      
	                        System.out.println(realSavePath);
	                        
	                        try {
	                        	System.out.println(foto.getSize());
	                            
	                            java.io.InputStream input =   foto.getInputStream();
	                            byte[] conteudoArquivo = new byte[input.available()];
	                            input.read(conteudoArquivo);	                           
	                                                  	                            
	                            FileOutputStream outPut = new FileOutputStream(new File(realSavePath));
	                            outPut.write(conteudoArquivo);
	                            input.close();
	                            outPut.flush();
	                            outPut.close();
	                           
	                           // System.out.println(radon+".jpg");
	                            return radon+".jpg";                          
                             
	                        } catch (IOException e) {
	                            arquivoSalvo = "semImagens.jpg";
	                        }
	                    }

	                } else {
	                	 alerta.error("Selecione um arquivo valido");
	                	 return null;
	                }
	            }
	        } catch (Exception ex) {
	        	
	            arquivoSalvo = "semImagens.jpg";
	        }	       
	        return arquivoSalvo;
	    }
	    public void caregarImagems(List<Vendedor> lista){
	    	   
	    	   InputStream input;
			try {
				
				for (int i = 0; i < lista.size(); i++) {
					if(lista.get(i).getImg() != null){
						ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
						
						 FileOutputStream outPut = new FileOutputStream(servletContext.getRealPath("") +lista.get(i).getUrl());
		                 outPut.write(lista.get(i).getImg());                 
		                 outPut.flush();
		                 outPut.close();	                
					}
				}	 								
				
			}
			catch (FileNotFoundException e) {			
				e.printStackTrace();
			}   
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	      		          
	    	   
	       }
	   
       public byte[] salvaImagem(){ 	       	       	  
		try {
			if(realSavePath != null){

				InputStream  input = new BufferedInputStream(new FileInputStream(realSavePath));
				byte[] conteudoArquivo = new byte[input.available()];
				input.read(conteudoArquivo);
				input.close();				
				
				return conteudoArquivo;
			}else {
				 ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
				 
				 InputStream  input = new BufferedInputStream(new FileInputStream(servletContext.getRealPath("") + "/imagens/camera.jpg"));
				 byte[] conteudoArquivo = new byte[input.available()];
				 input.read(conteudoArquivo);
				 input.close();				
					
				return conteudoArquivo;
			} 			
			
		}
		catch (FileNotFoundException e) {			
			return null;
		}   
		catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}                          
    	   
       }
       public void caregarImagem(Vendedor v){
    	   
    	   InputStream input;
		try {		
			
				if(v.getUrl() != null){
					
					 ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
					     
						
					 FileOutputStream outPut = new FileOutputStream(servletContext.getRealPath("") + v.getUrl());				
	                 outPut.write(v.getImg());                 
	                 outPut.flush();
	                 outPut.close();                
				}
				 								
			
		}
		catch (FileNotFoundException e) {			
			e.printStackTrace();
		}   
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        	      		          
    	   
       }
	    private boolean verificaTipoArquivo(String nomeArquivo) {
	        if (nomeArquivo.length() > 0) {
	            String[] tipoParts = nomeArquivo.split("\\.");
	            if (tipoParts.length > 0) {
	                String ext = tipoParts[tipoParts.length - 1];
	                return this.tipoArquivo.contains(ext);

	            }

	        }
	        return false;
	    }

	    private String getFilename(Part foto) {
	        for (String cd : foto.getHeader("content-disposition").split(";")) {
	            if (cd.trim().startsWith("filename")) {
	                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
	            }
	        }
	        return null;
	    }
	    private String getRandomImageName() {
	        int i = (int) (Math.random() * 10000000);
	         
	        return String.valueOf(i);
	    }
	}



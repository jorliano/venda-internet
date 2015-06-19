package br.com.jortec.servico;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import br.com.jortec.model.Vendedor;

@Service
@Scope("session")
public class ImagemValidator { 
	
	
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
	                    	// Alerta.error("Tamanho muito grande");
	                    } else {
	                    	
	                        String nomeAtualArquivo = nomeArquivo;
	                        String ext = nomeAtualArquivo.substring(nomeAtualArquivo.lastIndexOf("."), nomeAtualArquivo.length());	                      
	                        fileSavePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("imagens");
	                        fileSavePath = fileSavePath.substring(0,fileSavePath.indexOf("/imagens"));
	                        realSavePath = fileSavePath+"/imagens/"+caminho;	                        
	                      
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
	                           
	                                                      
                             
	                        } catch (IOException e) {
	                            arquivoSalvo = "semImagens.jpg";
	                        }
	                    }

	                } else {
	                    arquivoSalvo = "semImagens.jpg";
	                    System.out.println("arquivo nao salvo");
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
					if(lista.get(i).getUrl() != null){
					 FileOutputStream outPut = new FileOutputStream("/home/jorliano/Downloads/wildfly-8.2.0.Final/standalone/deployments/venda-internet.war"+lista.get(i).getUrl());
	                 outPut.write(lista.get(i).getImg());                 
	                 outPut.flush();
	                 outPut.close();
	                 System.out.println("/home/jorliano/Downloads/wildfly-8.2.0.Final/standalone/deployments/venda-internet.war"+lista.get(i).getUrl());
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
			
			InputStream  input = new BufferedInputStream(new FileInputStream(realSavePath));
			byte[] conteudoArquivo = new byte[input.available()];
			input.read(conteudoArquivo);
			input.close();
			
			System.out.println("imagem salva");
			return conteudoArquivo;
		}
		catch (FileNotFoundException e) {			
			e.printStackTrace();
		}   
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
          return null; 	
          
    	   
       }
       public void caregarImagem(Vendedor v){
    	   
    	   InputStream input;
		try {		
			
				if(v.getUrl() != null){
				 FileOutputStream outPut = new FileOutputStream("/home/jorliano/Downloads/wildfly-8.2.0.Final/standalone/deployments/venda-internet.war"+v.getUrl());
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

	}



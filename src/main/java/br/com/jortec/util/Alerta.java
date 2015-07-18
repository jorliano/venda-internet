package br.com.jortec.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.springframework.beans.FatalBeanException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
*
* @author Jorliano
*/
@Component
@Scope("request")
public class Alerta implements Serializable{
	public static void info(String mensagem , boolean time) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
		
		//aumentar tempo de requisição da menssage
		FacesContext ctx = FacesContext.getCurrentInstance();		
		ExternalContext externalContext = ctx.getExternalContext();
		Flash flash = externalContext.getFlash();
		flash.setKeepMessages(time);
	}
	public static void sucess(String mensagem , boolean time) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, mensagem, null));
		
		//aumentar tempo de requisição da menssage
		FacesContext ctx = FacesContext.getCurrentInstance();		
		ExternalContext externalContext = ctx.getExternalContext();
		Flash flash = externalContext.getFlash();
		flash.setKeepMessages(time);
	}
	
	public static void warn(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null));
	}

	public static void error(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null));
	}
}


package br.com.tecjor.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
/**
*
* @author Jorliano
*/
public abstract class Alerta {
	public static void info(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
	}
	
	public static void warn(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null));
	}

	public static void error(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null));
	}
}


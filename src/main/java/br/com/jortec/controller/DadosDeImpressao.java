package br.com.jortec.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;


@ManagedBean("dados")
@ViewScoped
public class DadosDeImpressao {
  
	private String loginPrint;
	private String senhaPrint;
	private String ipPrint;
	private String maskPrint;
	private String gatweyPrint;
	private String dnsPrint;
	private String descricao;
	
	
	public String getLoginPrint() {
		return loginPrint;
	}
	public void setLoginPrint(String loginPrint) {
		this.loginPrint = loginPrint;
	}
	public String getSenhaPrint() {
		return senhaPrint;
	}
	public void setSenhaPrint(String senhaPrint) {
		this.senhaPrint = senhaPrint;
	}
	public String getIpPrint() {
		return ipPrint;
	}
	public void setIpPrint(String ipPrint) {
		this.ipPrint = ipPrint;
	}
	public String getMaskPrint() {
		return maskPrint;
	}
	public void setMaskPrint(String maskPrint) {
		this.maskPrint = maskPrint;
	}
	public String getGatweyPrint() {
		return gatweyPrint;
	}
	public void setGatweyPrint(String gatweyPrint) {
		this.gatweyPrint = gatweyPrint;
	}
	public String getDnsPrint() {
		return dnsPrint;
	}
	public void setDnsPrint(String dnsPrint) {
		this.dnsPrint = dnsPrint;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}

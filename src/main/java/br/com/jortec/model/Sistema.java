package br.com.jortec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sistema")
public class Sistema {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private long id;
	
	@Column(name="plano")
	private String Plano;
	
	@Column(name="valor")
	private Double Valor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlano() {
		return Plano;
	}

	public void setPlano(String plano) {
		Plano = plano;
	}

	public Double getValor() {
		return Valor;
	}

	public void setValor(Double valor) {
		Valor = valor;
	}

	
	
	
}

package br.com.jortec.servico;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.view.JasperViewer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.jortec.controller.DadosDeImpressao;
import br.com.jortec.model.Cliente;

@Service
@Scope("request")
public class ImprimirInstalacao {
  
	
	@Autowired
	DadosDeImpressao dados;
	
	Map<String, Object> parametro = new HashMap<String, Object>();
	
	public void imprime(Cliente cliente,String geradoPor) throws IOException, JRException{
		List<Cliente> lista = new ArrayList<Cliente>();		
		lista.add(cliente);
		
		  		
		
		if(dados.getIpPrint() != ""){
		//	parametro.put("geradoPor", geradoPor);
		//	parametro.put("ip", dados.getIpPrint());
		//	parametro.put("mask", dados.getMaskPrint());
		//	parametro.put("gatwey", dados.getGatweyPrint());
		//	parametro.put("dns", dados.getDnsPrint());
		//	parametro.put("descricao", dados.getDescricao());
		//	dados = new DadosDeImpressao ();
			gerarArquivo("/ireport/print.jasper",lista);
			
		}
		else{
			
			
			parametro.put("geradoPor", cliente.getVendedor().getPrimeiroNome());
			parametro.put("ip", dados.getLoginPrint());
			parametro.put("mask", dados.getSenhaPrint());
			parametro.put("descricao", dados.getDescricao());
			dados = new DadosDeImpressao ();
			gerarArquivo("/ireport/print2.jasper",lista);
			
		}
		
			
		
	}
public void gerarArquivo(String arquivo,List lista) throws IOException, JRException{
    //File jasper = new File("/home/jorliano/Downloads/wildfly-8.2.0.Final/standalone/deployments/venda-internet.war/ireport/print.jasper");
	File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(arquivo));
	JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametro,new JRBeanCollectionDataSource(lista));
	
	
	byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);   
	
	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();			
	response.addHeader("Content-disposition", "inline; filename=instalacao.pdf");
	
	
	response.getOutputStream().write(b);  
	response.getCharacterEncoding();  
	
	//Download do arquivo direto
	//response.addHeader("Content-disposition", "attachmnt; filename=instalacao.pdf");
	//ServletOutputStream stream = response.getOutputStream();
	//JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
	
	//Documento docx
	//JRDocxExporter exporter = new JRDocxExporter();
	//exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
	//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,stream);
	//exporter.exportReport();
	
	
	FacesContext.getCurrentInstance().responseComplete();
	}
}

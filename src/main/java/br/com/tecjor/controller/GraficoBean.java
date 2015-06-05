package br.com.tecjor.controller;

 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.jortec.model.Cliente;
import br.com.tecjor.dao.GraficoDao;
 
@Controller
@Scope("request")
public class GraficoBean implements Serializable {
    
	String primeiroTrimestre[] = {"janeiro","fevereiro" ,"marco"};
	String segundoTrimestre [] = {"abril" ,"maio" ,"junho"};
	String terceiroTrimestre[] = {"julho","agosto","setembro"};
	String quartoTrimestre  [] = {"outubro" ,"novembro" ,"dezembro "};
	private String nome ="primeiroTrimestre";
	private List<Cliente> lista = new ArrayList<Cliente>();
	GregorianCalendar calendar = new GregorianCalendar();  
	int[] concluido =  new int [3];
	int[] cancelado =  new int [3];
	int[] pendente =  new int [3];
	
	
	
	private PieChartModel pieModel1;
    private BarChartModel animatedModel2;
 
    @Autowired
    GraficoDao dao;
    
    @PostConstruct    
    public void barGrafico(){
    	createAnimatedModels();
    	lista = dao.listarPrimeiroTrimestre();
    }
    
    
    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
 
    private void createAnimatedModels() {                  
    	
    	createPieModel1();
    	
        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Bar Charts");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(200);
    }
     
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries concluidos = new ChartSeries();
        concluidos.setLabel("Concluidos");                    
 
        ChartSeries pendentes = new ChartSeries();
        pendentes.setLabel("Pendentes");               
        
        ChartSeries cancelados = new ChartSeries();
        cancelados.setLabel("Cancelados");        
        
       
        String array[] = trimestre(nome);                     
        
        
        for(int i =0;i< array.length;i++){
        	System.out.println("Cancelados "+cancelado[i]);
        	System.out.println("Concluidos "+concluido[i]);
        	System.out.println("Pendentes  "+pendente[i]);
        	concluidos.set(array[i], concluido[i]);
        	pendentes.set(array[i],  pendente[i]);
        	cancelados.set(array[i], cancelado[i]);
        } 
       
 
       
        model.addSeries(pendentes);
        model.addSeries(cancelados);
        model.addSeries(concluidos); 
        return model;
    }
     
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
         
        pieModel1.set("Brand 1", 540);
        pieModel1.set("Brand 2", 325);
        pieModel1.set("Brand 3", 702);
        pieModel1.set("Brand 4", 421);
         
        pieModel1.setTitle("Simple Pie");
        pieModel1.setLegendPosition("w");
    }
    
    public String[] trimestre(String nome){
    	
    	if(nome.equals("primeiroTrimestre")){
    		lista = dao.listarPrimeiroTrimestre();
    		dadosPrimeiroTrimestre();
    		return primeiroTrimestre;
    	}
    	if(nome.equals("segundoTrimestre")){
    		lista = dao.listarSegundoTrimestre();
    		dadosSegundoTrimestre();
    		return segundoTrimestre;
    	}
    	if(nome.equals("terceiroTrimestre")){
    		lista = dao.listarTerceiroTrimestre();
    		dadosTerceiroTrimestre();
    		return terceiroTrimestre;
    	}
    	if(nome.equals("quartoTrimestre")){
    	    lista = dao.listarQuartoTrimestre();
    	    dadosQuartoTrimestre();
    		return quartoTrimestre;
    	}
    	
    	return null;
    }
   
    public void dadosPrimeiroTrimestre(){
    	            
        for (int i = 0; i < lista.size(); i++) {
           calendar.setTime(lista.get(i).getDataCadastro());
       	
            int mes = calendar.get(GregorianCalendar.MONTH);  
			if(lista.get(i).getEstatus().equals("concluido") && mes == 0 ){
				concluido[0] = concluido[0] +1;
			}
			if(lista.get(i).getEstatus().equals("concluido") && mes == 1 ){
				concluido[1] = concluido[1] +1;
			}
			if(lista.get(i).getEstatus().equals("concluido") && mes == 2 ){
				concluido[2] = concluido[2] +1;
			}
			if(lista.get(i).getEstatus().equals("cancelado") && mes == 0 ){
				cancelado[0] = cancelado[0] +1;
			}
			if(lista.get(i).getEstatus().equals("cancelado") && mes == 1 ){
				cancelado[1] = cancelado[1] +1;
			}
			if(lista.get(i).getEstatus().equals("cancelado") && mes == 2 ){
				cancelado[2] = cancelado[2] +1;
			}
			if(lista.get(i).getEstatus().equals("pendente") && mes == 0 ){
				pendente[0] = pendente[0] +1;
			}
			if(lista.get(i).getEstatus().equals("pendente") && mes == 1 ){
				pendente[1] =  pendente[1] +1;
			}
			if(lista.get(i).getEstatus().equals("pendente") && mes == 2 ){
				pendente[2] = pendente[2] +1;
			}
		}
    }
    
    public void dadosSegundoTrimestre(){
        
        for (int i = 0; i < lista.size(); i++) {
           calendar.setTime(lista.get(i).getDataCadastro());
       	   
           
            int mes = calendar.get(GregorianCalendar.MONTH);  
			if(lista.get(i).getEstatus().equals("concluido") && mes == 3 ){
				concluido[0] = concluido[0] +1;
			}
			if(lista.get(i).getEstatus().equals("concluido") && mes == 4 ){
				concluido[1] = concluido[1] +1;
			}
			if(lista.get(i).getEstatus().equals("concluido") && mes == 5 ){
				concluido[2] = concluido[2] +1;
				
			}
			if(lista.get(i).getEstatus().equals("cancelado") && mes == 3 ){
				cancelado[0] = cancelado[0] +1;
			}
			if(lista.get(i).getEstatus().equals("cancelado") && mes == 4 ){
				cancelado[1] = cancelado[1] +1;
			}
			if(lista.get(i).getEstatus().equals("cancelado") && mes == 5 ){
				cancelado[2] = cancelado[2] +1;
				System.out.println("cancelado OK");
			}
			if(lista.get(i).getEstatus().equals("pendente") && mes == 3 ){
				pendente[0] = pendente[0] +1;
			}
			if(lista.get(i).getEstatus().equals("pendente") && mes == 4 ){
				pendente[1] =  pendente[1] +1;
			}
			if(lista.get(i).getEstatus().equals("pendente") && mes == 5 ){
				pendente[2] = pendente[2] +1;
			}
		}
    }
    
    public void dadosTerceiroTrimestre(){
        
        for (int i = 0; i < lista.size(); i++) {
           calendar.setTime(lista.get(i).getDataCadastro());
       	   
           
            int mes = calendar.get(GregorianCalendar.MONTH);  
			if(lista.get(i).getEstatus().equals("concluido") && mes == 6 ){
				concluido[0] = concluido[0] +1;
			}
			if(lista.get(i).getEstatus().equals("concluido") && mes == 7 ){
				concluido[1] = concluido[1] +1;
			}
			if(lista.get(i).getEstatus().equals("concluido") && mes == 8 ){
				concluido[2] = concluido[2] +1;
				
			}
			if(lista.get(i).getEstatus().equals("cancelado") && mes == 6 ){
				cancelado[0] = cancelado[0] +1;
			}
			if(lista.get(i).getEstatus().equals("cancelado") && mes == 7 ){
				cancelado[1] = cancelado[1] +1;
			}
			if(lista.get(i).getEstatus().equals("cancelado") && mes == 8 ){
				cancelado[2] = cancelado[2] +1;
				System.out.println("cancelado OK");
			}
			if(lista.get(i).getEstatus().equals("pendente") && mes == 6 ){
				pendente[0] = pendente[0] +1;
			}
			if(lista.get(i).getEstatus().equals("pendente") && mes == 7 ){
				pendente[1] =  pendente[1] +1;
			}
			if(lista.get(i).getEstatus().equals("pendente") && mes == 8 ){
				pendente[2] = pendente[2] +1;
			}
		}
    }
   
    public void dadosQuartoTrimestre(){
      
      for (int i = 0; i < lista.size(); i++) {
            calendar.setTime(lista.get(i).getDataCadastro());
     	   
         
            int mes = calendar.get(GregorianCalendar.MONTH);  
			if(lista.get(i).getEstatus().equals("concluido") && mes == 9 ){
				concluido[0] = concluido[0] +1;
			}
			if(lista.get(i).getEstatus().equals("concluido") && mes == 10 ){
				concluido[1] = concluido[1] +1;
			}
			if(lista.get(i).getEstatus().equals("concluido") && mes == 11 ){
				concluido[2] = concluido[2] +1;				
			}
			if(lista.get(i).getEstatus().equals("cancelado") && mes == 9 ){
				cancelado[0] = cancelado[0] +1;
			}
			if(lista.get(i).getEstatus().equals("cancelado") && mes == 10 ){
				cancelado[1] = cancelado[1] +1;
			}
			if(lista.get(i).getEstatus().equals("cancelado") && mes == 11 ){
				cancelado[2] = cancelado[2] +1;
				System.out.println("cancelado OK");
			}
			if(lista.get(i).getEstatus().equals("pendente") && mes == 9 ){
				pendente[0] = pendente[0] +1;
			}
			if(lista.get(i).getEstatus().equals("pendente") && mes == 10 ){
				pendente[1] =  pendente[1] +1;
			}
			if(lista.get(i).getEstatus().equals("pendente") && mes == 11 ){
				pendente[2] = pendente[2] +1;
			}
		}
  }
    
    
    
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
    
}
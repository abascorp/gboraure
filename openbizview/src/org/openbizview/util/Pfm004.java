/*
 *  Copyright (C) 2011  ANDRES DOMINGUEZ

    Este programa es software libre: usted puede redistribuirlo y/o modificarlo 
    bajo los terminos de la Licencia Pública General GNU publicada 
    por la Fundacion para el Software Libre, ya sea la version 3 
    de la Licencia, o (a su eleccion) cualquier version posterior.

    Este programa se distribuye con la esperanza de que sea útil, pero 
    SIN GARANTiA ALGUNA; ni siquiera la garantia implicita 
    MERCANTIL o de APTITUD PARA UN PROPoSITO DETERMINADO. 
    Consulte los detalles de la Licencia Pública General GNU para obtener 
    una informacion mas detallada. 

    Deberia haber recibido una copia de la Licencia Pública General GNU 
    junto a este programa. 
    En caso contrario, consulte <http://www.gnu.org/licenses/>.
 */

package org.openbizview.util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.openbizview.getset.Inicial;
import org.openbizview.util.PntGenerica;

/**
 *
 * @author Andres
 */
@ManagedBean
@ViewScoped
public class Pfm004 extends Bd {

	public Pfm004() throws IOException {	
        try {
			select();
			selectInputs();    //Inputs para modificar planificación
			selectPfm004ConsumoC(); //Inputs para consumo Centro
			selectPfm004ConsumoO(); //Inputs para consumo Oriente
			selectPfm004ConsumoOC(); //Inputs para consumo Occidente
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        table10 = new String[inputnumber10];//Asigna tamaño al arreglo
        table20 = new String[inputnumber20];//Asigna tamaño al arreglo
        table30 = new String[inputnumber30];//Asigna tamaño al arreglo
        table40 = new String[inputnumber40];//Asigna tamaño al arreglo
        
        //Consumo de alimento
        ////System.out.println("rows: " + inputnumberconsumoc);
        tableconsumoc = new String[inputnumberconsumoc];//Consumo zona centro
        tableconsumoo = new String[inputnumberconsumoo];//Consumo zona centro
        tableconsumooc = new String[inputnumberconsumooc];//Consumo zona centro
	}
	
	@PostConstruct
	//Load the table before the html table is rended on the page
    public void initialize() 
    {
		
      list.clear();
      try {
		select();
	} catch (ClassNotFoundException | SQLException | NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
	
	
	private String bnumper = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("periodo");
	private String bcodesc = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("esc");
	private List<Inicial> list = new ArrayList<Inicial>();
	private List<Inicial> filtro;
	private String zona = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("zona");
	private String pollos = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tippollo");
	private String total = "0";
	private String sem47 = "0";
	private String kilosventas = "0";
    private String exito = "exito";
    
    //Premisas
    private String ppromedio = "0";
    private String conversion = "0";
    private String mortalidad = "0";
    private String dya = "0";
    private String rb = "0";
    private String mcdyb = "0";
    private String asegundas = "0";
    private String despresados = "0";
    
    //Consumo de alimento centro
    private int inputnumberconsumoc = 0;
    private String[]   tableconsumoc;
    private String[][] consumoc;
    
    //Consumo de alimento oriente
    private int inputnumberconsumoo = 0;
    private String[]   tableconsumoo;
    private String[][] consumoo;
    
    
    //Consumo de alimento occidente
    private int inputnumberconsumooc = 0;
    private String[]   tableconsumooc;
    private String[][] consumooc;
  


	public String[][] getConsumoc() {
		return consumoc;
	}

	public void setConsumoc(String[][] consumoc) {
		this.consumoc = consumoc;
	}



	public String[][] getConsumoo() {
		return consumoo;
	}

	public void setConsumoo(String[][] consumoo) {
		this.consumoo = consumoo;
	}


	public String[][] getConsumooc() {
		return consumooc;
	}

	public void setConsumooc(String[][] consumooc) {
		this.consumooc = consumooc;
	}

			/**
		 * @return the list
		 */
		public List<Inicial> getList() {
			return list;
		}

		/**
		 * @param list the list to set
		 */
		public void setList(List<Inicial> list) {
			this.list = list;
		}
		
		/**
		 * @return the filtro
		 */
		public List<Inicial> getFiltro() {
			return filtro;
		}
		
		/**
		 * @param filtro the filtro to set
		 */
		public void setFiltro(List<Inicial> filtro) {
			this.filtro = filtro;
		}

	
	
	
	/**
	 * @return the bnumper
	 */
	public String getBnumper() {
		return bnumper;
	}
	
	/**
	 * @param bnumper the bnumper to set
	 */
	public void setBnumper(String bnumper) {
		this.bnumper = bnumper;
	}
	
	/**
	 * @return the bcodesc
	 */
	public String getBcodesc() {
		return bcodesc;
	}
	
	/**
	 * @param bcodesc the bcodesc to set
	 */
	public void setBcodesc(String bcodesc) {
		this.bcodesc = bcodesc;
	}
	
   	
	
	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}

	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}

	/**
	 * @return the pollos
	 */
	public String getPollos() {
		return pollos;
	}

	/**
	 * @param pollos the pollos to set
	 */
	public void setPollos(String pollos) {
		this.pollos = pollos;
	}



/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	
	
	public String getPpromedio() {
		return ppromedio;
	}

	public void setPpromedio(String ppromedio) {
		this.ppromedio = ppromedio;
	}

	public String getConversion() {
		return conversion;
	}

	public void setConversion(String conversion) {
		this.conversion = conversion;
	}

	public String getMortalidad() {
		return mortalidad;
	}

	public void setMortalidad(String mortalidad) {
		this.mortalidad = mortalidad;
	}

	public String getDya() {
		return dya;
	}

	public void setDya(String dya) {
		this.dya = dya;
	}

	public String getRb() {
		return rb;
	}

	public void setRb(String rb) {
		this.rb = rb;
	}

	public String getMcdyb() {
		return mcdyb;
	}

	public void setMcdyb(String mcdyb) {
		this.mcdyb = mcdyb;
	}

	public String getAsegundas() {
		return asegundas;
	}

	public void setAsegundas(String asegundas) {
		this.asegundas = asegundas;
	}

	public String getDespresados() {
		return despresados;
	}

	public void setDespresados(String despresados) {
		this.despresados = despresados;
	}

	


	public String[] getTableconsumoc() {
		return tableconsumoc;
	}

	public void setTableconsumoc(String[] tableconsumoc) {
		this.tableconsumoc = tableconsumoc;
	}

	public String[] getTableconsumoo() {
		return tableconsumoo;
	}

	public void setTableconsumoo(String[] tableconsumoo) {
		this.tableconsumoo = tableconsumoo;
	}

	public String[] getTableconsumooc() {
		return tableconsumooc;
	}

	public void setTableconsumooc(String[] tableconsumooc) {
		this.tableconsumooc = tableconsumooc;
	}
	
	


	public int getInputnumberconsumoc() {
		return inputnumberconsumoc;
	}

	public void setInputnumberconsumoc(int inputnumberconsumoc) {
		this.inputnumberconsumoc = inputnumberconsumoc;
	}

	public int getInputnumberconsumoo() {
		return inputnumberconsumoo;
	}

	public void setInputnumberconsumoo(int inputnumberconsumoo) {
		this.inputnumberconsumoo = inputnumberconsumoo;
	}

	public int getInputnumberconsumooc() {
		return inputnumberconsumooc;
	}

	public void setInputnumberconsumooc(int inputnumberconsumooc) {
		this.inputnumberconsumooc = inputnumberconsumooc;
	}
	
	

    public String getSem47() {
		return sem47;
	}

	public void setSem47(String sem47) {
		this.sem47 = sem47;
	}

	

	public String getKilosventas() {
		return kilosventas;
	}

	public void setKilosventas(String kilosventas) {
		this.kilosventas = kilosventas;
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Variables seran utilizadas para capturar mensajes de errores de Oracle y parametros de metodos
	FacesMessage msj =  null;
	PntGenerica consulta = new PntGenerica();
	private int rows;
	boolean vGacc; //Validador de opciones del menú
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//Coneccion a base de datos
//Pool de conecciones JNDI
	Connection con;
	PreparedStatement pstmt = null;
	ResultSet r;

	 /**
     * Actualiza la planificación de pollos
     * <p>
	 * @throws SQLException 
	 * @throws NamingException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
     **/	
	public void ModPlanificacion() throws SQLException, ClassNotFoundException, NamingException{
    	//Actualiza los imputs 10
    	for(int i = 0 ; i < inputnumber10; i++){
    		update(vlArraytb10[i][0], vlArraytb10[i][1], vlArraytb10[i][2], vlArraytb10[i][3]);
    	}
    	//Actualiza los imputs 20
    	for(int i = 0 ; i < inputnumber20; i++){
    		update(vlArraytb20[i][0], vlArraytb20[i][1], vlArraytb20[i][2], vlArraytb20[i][3]);
    	}
    	//Actualiza los imputs 30
    	for(int i = 0 ; i < inputnumber30; i++){
    		update(vlArraytb30[i][0], vlArraytb30[i][1], vlArraytb30[i][2], vlArraytb30[i][3]);
    	}
    	//Actualiza los imputs 40
    	for(int i = 0 ; i < inputnumber40; i++){
    		update(vlArraytb40[i][0], vlArraytb40[i][1], vlArraytb40[i][2], vlArraytb40[i][3]);
    	}
    	pfm005_alimento();
    	if(exito.equals("exito")){
    		msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("msnUpdate"), "");
    		FacesContext.getCurrentInstance().addMessage(null, msj);
    	}    	    	
        list.clear();
        select();
    }
	
	 /**
     * Actualiza el consumo de alimento de pollos
     * <p>
	 * @throws SQLException 
	 * @throws NamingException 
	 * @throws ClassNotFoundException 
     **/	
	public void ModConsumo() throws SQLException, ClassNotFoundException, NamingException{	
		////System.out.println("modificando: ");
    	//Actualiza Centro
    	for(int i = 0 ; i < inputnumberconsumoc; i++){
    		////System.out.println("modificando: " + i);
    		updatePfm004Consumo(i, consumoc[i][0], consumoc[i][2], consumoc[i][1], "CENTRO");
    	}
    	//Actualiza Oriente
    	for(int i = 0 ; i < inputnumberconsumoo;  i++){
    		updatePfm004Consumo(i, consumoo[i][0], consumoo[i][2], consumoo[i][1], "ORIENTE");
    	}
    	//Actualiza Occidente
    	for(int i = 0 ; i < inputnumberconsumooc; i++){
    		updatePfm004Consumo(i, consumooc[i][0], consumooc[i][2], consumooc[i][1], "OCCIDENTE");
    	}
    	////System.out.println(exito);
    	if(exito.equals("exito")){
    		msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("msnUpdate"), "");
    		FacesContext.getCurrentInstance().addMessage(null, msj);
    	}  

    }
    
    /**
     * Actualiza la planificación de pollos
     * <p>
     * Parametros del metodo: semana, total, aniocon, mescon
	 * @throws SQLException 
     **/	
    private void update(String semana, String total, String aniocon, String mescon) throws SQLException{
    	try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();
     		
            String query = "UPDATE pfm004";
             query += " SET total = '" + total + "'";
             query += " WHERE p_anocal||p_numper = '" + bnumper.split(" - ")[0] + "'";
             query += " and p_codesc = '" + bcodesc.split(" - ")[0] + "'";
             query += " and pollos = '" + pollos + "'";
             query += " and zona = '" + zona + "'";
             query += " and aniocon = '" + aniocon + "'";
             query += " and mescon = '" + mescon + "'";
             query += " and semcon = '" + semana + "'";
            ////System.out.println(query);
            pstmt = con.prepareStatement(query);            
            try {
                //Avisando
                pstmt.executeUpdate();
                
            } catch (SQLException e) {
            	exito = "error";
            	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), "");
            	FacesContext.getCurrentInstance().addMessage(null, msj);
            }
        } catch (Exception e) {
        	exito = "error";
        	e.printStackTrace();
        }
    	pstmt.close();
        con.close();
    }
    
    /**
     * Actualiza la planificación de consumo de alimento
     * <p>
     * Parametros del metodo: semana, total, aniocon, mescon
	 * @throws SQLException 
     **/	
    private void updatePfm004Consumo(int psemana, String pconsumo, String ptipali, String pmort, String pzona) throws SQLException{
    	try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();
     		
     		psemana = psemana +1;
     		
            String query = "UPDATE pfm004_consumo";
             query += " SET consumo = " + pconsumo + ", mortalidad = " + pmort;
             query += " WHERE p_anocal||p_numper = '" + bnumper.split(" - ")[0] + "'";
             query += " and p_codesc = '" + bcodesc.split(" - ")[0] + "'";
             query += " and semana = '" + psemana + "'" ;
             query += " and tipalimento = '" + ptipali + "'";
             query += " and zona = '" + pzona + "'";
            ////System.out.println(query);
            pstmt = con.prepareStatement(query);            
            try {
                //Avisando
                pstmt.executeUpdate();
                
            } catch (SQLException e) {
            	exito = "error";
            	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), "");
            	FacesContext.getCurrentInstance().addMessage(null, msj);
            }
        } catch (Exception e) {
        	exito = "error";
        	e.printStackTrace();
        }
    	pstmt.close();
        con.close();
    }
    
    public void delete() throws NamingException{
    	deletePfm004();
    	if(pollos.equals("INICIADOS")){
    	deletePfm004_alimento();
    	deletePfm004_distribucion();
    	}
    }
    
    
    /**
     * Borra Paises
     * <p>
     * Parametros del metodo: String codpai. Pool de conecciones
     **/
	private void deletePfm004() throws NamingException  {  
	        try {
	        	

	     		String[] vlnumper = bnumper.split("\\ - ", -1); 
	     		String[] vlcodesc = bcodesc.split("\\ - ", -1); 
	       	
	     		String anio = vlnumper[0].substring(0, 4);
	    		String per;
	            if(vlnumper[0].length()==6){
	            	per = vlnumper[0].substring(4, 6);
	            } else {
	            	per = vlnumper[0].substring(4, 5);
	            }
	            
	        	Context initContext = new InitialContext();     
	     		DataSource ds = (DataSource) initContext.lookup(JNDI);

	     		con = ds.getConnection();		
	        	
	
	        	String query = "DELETE PFM004 WHERE P_ANOCAL= ? AND P_NUMPER= ? AND P_CODESC= ? AND ZONA = ? AND POLLOS = ?";
	        	        		        	
	            pstmt = con.prepareStatement(query);
	            ////System.out.println(query);
	        	pstmt.setString(1, anio);
	        	pstmt.setString(2, per);
	        	pstmt.setString(3, vlcodesc[0]);
	        	pstmt.setString(4, zona);
	        	pstmt.setString(5, pollos);
	
	            try {
	                //Avisando
	                pstmt.executeUpdate();
	                
	                if(pstmt.getUpdateCount()==0){
	                	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
	                } else {
	                	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnDelete"), "");
	                }
	                limpiarValores();
	                list.clear();
	                select();	                
	            } catch (SQLException e) {
	                e.printStackTrace();
	                msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
	            }
	
	            pstmt.close();
	            con.close();
	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    	
    	FacesContext.getCurrentInstance().addMessage(null, msj); 
    }
	
	private void deletePfm004_alimento() throws NamingException  {  
        try {
        	

     		String[] vlnumper = bnumper.split("\\ - ", -1); 
     		String[] vlcodesc = bcodesc.split("\\ - ", -1); 
       	
     		String anio = vlnumper[0].substring(0, 4);
    		String per;
            if(vlnumper[0].length()==6){
            	per = vlnumper[0].substring(4, 6);
            } else {
            	per = vlnumper[0].substring(4, 5);
            }
            
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();		
        	

        	String query = "DELETE PFM004_ALIMENTO WHERE P_ANOCAL= ? AND P_NUMPER= ? AND P_CODESC= ? AND ZONA = ?";
        	        		        	
            pstmt = con.prepareStatement(query);
            ////System.out.println(query);
        	pstmt.setString(1, anio);
        	pstmt.setString(2, per);
        	pstmt.setString(3, vlcodesc[0]);
        	pstmt.setString(4, zona);

            try {
                //Avisando
                pstmt.executeUpdate();
                
                if(pstmt.getUpdateCount()==0){
                	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
                } else {
                	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnDelete"), "");
                }
                limpiarValores();
                list.clear();
                select();	                
            } catch (SQLException e) {
                e.printStackTrace();
                msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
            }

            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
	
	FacesContext.getCurrentInstance().addMessage(null, msj); 
}
	
	private void deletePfm004_distribucion() throws NamingException  {  
        try {
        	

     		String[] vlnumper = bnumper.split("\\ - ", -1); 
     		String[] vlcodesc = bcodesc.split("\\ - ", -1); 
       	
     		String anio = vlnumper[0].substring(0, 4);
    		String per;
            if(vlnumper[0].length()==6){
            	per = vlnumper[0].substring(4, 6);
            } else {
            	per = vlnumper[0].substring(4, 5);
            }
            
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();		
        	

        	String query = "DELETE PFM004_DISTRIBUCION WHERE P_ANOCAL= ? AND P_NUMPER= ? AND P_CODESC= ? AND ZONA = ?";
        	        		        	
            pstmt = con.prepareStatement(query);
            ////System.out.println(query);
        	pstmt.setString(1, anio);
        	pstmt.setString(2, per);
        	pstmt.setString(3, vlcodesc[0]);
        	pstmt.setString(4, zona);

            try {
                //Avisando
                pstmt.executeUpdate();
                
                if(pstmt.getUpdateCount()==0){
                	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
                } else {
                	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnDelete"), "");
                }
                limpiarValores();
                list.clear();
                select();	                
            } catch (SQLException e) {
                e.printStackTrace();
                msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
            }

            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
	
	FacesContext.getCurrentInstance().addMessage(null, msj); 
}
 
   
   /**
    * Leer Datos de paises
    * @throws NamingException 
 * @throws IOException 
    **/ 	
 	public void select() throws SQLException, ClassNotFoundException, NamingException {

 		Context initContext = new InitialContext();     
 		DataSource ds = (DataSource) initContext.lookup(JNDI);

 		con = ds.getConnection();	
 		
 		if(bnumper==null){
 			bnumper = " - ";
 		}
 		if(bnumper==""){
 			bnumper = " - ";
 		}
 		
 		if(bcodesc==null){
 			bcodesc = " - ";
 		}
 		if(bcodesc==""){
 			bcodesc = " - ";
 		}
 		
 		if(zona==null){
 			zona = " - ";
 		}
 		if(zona==""){
 			zona = " - ";
 		}

 		if(pollos==null){
 			pollos = " - ";
 		}
 		if(pollos==""){
 			pollos = " - ";
 		}
 		
 		String[] vlnumper = bnumper.split("\\ - ", -1); 
 		String[] vlcodesc = bcodesc.split("\\ - ", -1); 
 		String[] vlpollos = pollos.split("\\ - ", -1); 
 		String[] vlzona = zona.split("\\ - ", -1); 
 		
 		
 		
 		
 		String query;
 		//Consulta paginada
 			   query = " select P_ANOCAL, P_NUMPER, P_CODESC, ANIOCON, MESCON, SEMCON, ZONA, POLLOS, round(TOTAL), PPROMEDIO, CONVERSION, MORTALIDAD, DYA, RB, MCDYB, ASEGUNDAS, DESPRESADOS, SEMORD" ;
               query += " FROM pfm004";
  		       query += " WHERE   p_anocal||p_numper  LIKE trim('" + vlnumper[0].toUpperCase() +  "%')";
  		       query += " AND  p_codesc  LIKE trim('" + vlcodesc[0].toUpperCase() +  "%')";
  		       query += " AND  ZONA  LIKE trim('" + vlzona[0].toUpperCase() +  "%')";
  		       query += " AND  POLLOS  LIKE trim('" + vlpollos[0].toUpperCase() +  "%')";
               query += " ORDER BY P_ANOCAL, P_NUMPER, P_CODESC, SEMORD, POLLOS" ;

       pstmt = con.prepareStatement(query);
       ////System.out.println(query);
 		
       r =  pstmt.executeQuery();
       
 		
       
       
       while (r.next()){
    	Inicial select = new Inicial();
        select.setVbanocal(r.getString(1));
        select.setBvnumper(r.getString(2));
        select.setVbcodesc(r.getString(3));
        select.setVanocon(r.getString(4));
        select.setVmescon(r.getString(5));
        select.setVsemcon(r.getString(6));
        select.setVzona(r.getString(7));
        select.setVpollos(r.getString(8));
        select.setVtotal(r.getString(9));
       	//Agrega la lista
       	list.add(select);
       	rows = list.size();
       }
       //Cierra las conecciones
       //bnumper = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("periodo");
   	   //bcodesc = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("esc");
       
       r.close();
       pstmt.close();
       con.close();
       
 			bnumper = "";
 			bcodesc = "";
 			zona = "";
 			pollos = "";


 	}
   
    /**
     * Rutina que ejecuta la carga de saldo inicial
     * @throws NamingException 
     **/

    public void bi_saldo_inicial() throws NamingException {

    	    String[] vlnumper = bnumper.split("\\ - ", -1); 
     		String[] vlesc = bcodesc.split("\\ - ", -1);
     		String[] vlpollos = pollos.split("\\ - ", -1); 
     		String[] vlzona = zona.split("\\ - ", -1); 
     		
     		String anio = vlnumper[0].substring(0, 4);
    		String per;
            if(vlnumper[0].length()==6){
            	per = vlnumper[0].substring(4, 6);
            } else {
            	per = vlnumper[0].substring(4, 5);
            }
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();	
     		
            CallableStatement proc = null;


            proc = con.prepareCall("{CALL SPCALPREPF05(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            proc.setString(1, anio);
            proc.setString(2, per);
            proc.setString(3, vlesc[0]);
            proc.setString(4, vlpollos[0]);
            proc.setString(5, vlzona[0]);
            proc.setBigDecimal(6, new BigDecimal(total));
            proc.setBigDecimal(7, new BigDecimal(ppromedio));
            proc.setBigDecimal(8, new BigDecimal(conversion));
            proc.setBigDecimal(9, new BigDecimal(mortalidad));
            proc.setBigDecimal(10, new BigDecimal(dya));
            proc.setBigDecimal(11, new BigDecimal(rb));
            proc.setBigDecimal(12, new BigDecimal(mcdyb));
            proc.setBigDecimal(13, new BigDecimal(asegundas));
            proc.setBigDecimal(14, new BigDecimal(despresados));
            proc.setBigDecimal(15, new BigDecimal(sem47));
            proc.setBigDecimal(16, new BigDecimal(kilosventas));
            ////System.out.println("Añio: " + anio);
            ////System.out.println("Período: " + per);
            ////System.out.println("Escenario: " + vlesc[0]);
            try {
            proc.execute();
            //Insertando consumo
            String qconsulta = "select * from pfm004_consumo where p_anocal = '" + anio + "' and p_numper = '" + per + "' and p_codesc = '" + vlesc[0] + "'";
            consulta.selectPntGenerica(qconsulta, JNDI);
            ////System.out.println(qconsulta);
            int rows = consulta.getRows();
            if(rows<=0){//Inserta si no hay registros
            for(int i = 1 ; i <= 6; i++){
	    		////System.out.println("Insertando registros en centro fila :" + i);
	    		insertPfm004Consumo(i, "0", "0", "CENTRO", "0");
	    		insertPfm004Consumo(i, "0", "0", "ORIENTE", "0");
	    		insertPfm004Consumo(i, "0", "0", "OCCIDENTE", "0");
	    	}
            }
            //
            list.clear();
            select();
            limpiarValores();
            
            proc.close();
            con.close();
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("bvm002Saldo"), "");
            } catch (SQLException e)  {
            	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
            }

        } catch (Exception e) {
            e.printStackTrace();
            
        }   	
        FacesContext.getCurrentInstance().addMessage(null, msj);      
    }
    
    
   
    
   /**
  	 * @return the rows
  	 */
  	public int getRows() {
  		return rows;
  	}

  	
    public void limpiarValores() {
 		// TODO Auto-generated method stub
    	total = "0";
 	}
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////CREACION Y CONSULTAS DE INPUTS DINAMICOS////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    private String[] table10; //Tabla para imprimir inputs en el formularios palnificación de avez
    private String[] table20; //Tabla para imprimir inputs en el formularios palnificación de avez
    private String[] table30; //Tabla para imprimir inputs en el formularios palnificación de avez
    private String[] table40; //Tabla para imprimir inputs en el formularios palnificación de avez

    private String[][] vlArraytb10; //El arreglo que imprime los nombres
    private String[][] vlArraytb20; //El arreglo que imprime los nombres
    private String[][] vlArraytb30; //El arreglo que imprime los nombres
    private String[][] vlArraytb40; //El arreglo que imprime los nombres
    
    private int inputnumber10 = 0; //El número de inputs que tendrá el forulario dependiendo de la cantidad de registros que tenga la tabla pfm004
    private int inputnumber20 = 0; //El número de inputs que tendrá el forulario dependiendo de la cantidad de registros que tenga la tabla pfm004
    private int inputnumber30 = 0; //El número de inputs que tendrá el forulario dependiendo de la cantidad de registros que tenga la tabla pfm004
    private int inputnumber40 = 0; //El número de inputs que tendrá el forulario dependiendo de la cantidad de registros que tenga la tabla pfm004
	
	
	
	public String[] getTable10() {
		return table10;
	}

	public void setTable10(String[] table10) {
		this.table10 = table10;
	}

	public String[] getTable20() {
		return table20;
	}

	public void setTable20(String[] table20) {
		this.table20 = table20;
	}

	public String[] getTable30() {
		return table30;
	}

	public void setTable30(String[] table30) {
		this.table30 = table30;
	}

	public String[] getTable40() {
		return table40;
	}

	public void setTable40(String[] table40) {
		this.table40 = table40;
	}

	public String[][] getVlArraytb10() {
		return vlArraytb10;
	}

	public void setVlArraytb10(String[][] vlArraytb10) {
		this.vlArraytb10 = vlArraytb10;
	}

	public String[][] getVlArraytb20() {
		return vlArraytb20;
	}

	public void setVlArraytb20(String[][] vlArraytb20) {
		this.vlArraytb20 = vlArraytb20;
	}

	public String[][] getVlArraytb30() {
		return vlArraytb30;
	}

	public void setVlArraytb30(String[][] vlArraytb30) {
		this.vlArraytb30 = vlArraytb30;
	}

	public String[][] getVlArraytb40() {
		return vlArraytb40;
	}

	public void setVlArraytb40(String[][] vlArraytb40) {
		this.vlArraytb40 = vlArraytb40;
	}

	private void selectInputs10() throws NamingException, SQLException {
		
		if(bnumper==null){
 			bnumper = " - ";
 		}
 		if(bnumper==""){
 			bnumper = " - ";
 		}
 		
 		if(bcodesc==null){
 			bcodesc = " - ";
 		}
 		if(bcodesc==""){
 			bcodesc = " - ";
 		}
 		
 		if(zona==null){
 			zona = " - ";
 		}
 		if(zona==""){
 			zona = " - ";
 		}

 		if(pollos==null){
 			pollos = " - ";
 		}
 		if(pollos==""){
 			pollos = " - ";
 		}
 		
		
		String[] vlnumper = bnumper.split("\\ - ", -1); 
 		String[] vlcodesc = bcodesc.split("\\ - ", -1); 
 		String[] vlpollos = pollos.split("\\ - ", -1); 
 		String[] vlzona = zona.split("\\ - ", -1); 
 		
        String query = "select * from ( select query.*, rownum filaDesde from";
           query += " (SELECT SEMCON, TOTAL, ANIOCON, MESCON";
           query += " FROM PFM004";
           query += " WHERE p_anocal||p_numper  LIKE trim('" + vlnumper[0].toUpperCase() +  "%')";
           query += " AND P_CODESC = '" + vlcodesc[0] + "'";
           query += " AND POLLOS = '" + vlpollos[0] + "'";
           query += " AND ZONA = '" + vlzona[0] + "'";
           query += " ORDER BY semord) query";
           query += " where rownum <= 15)";
           query += " where filaDesde >= 1";
           
           consulta.selectPntGenerica(query, JNDI);
           inputnumber10 = consulta.getRows();
   		   if(inputnumber10>0){
   			vlArraytb10 = consulta.getArray();
   		}

    }
	
   private void selectInputs20() throws NamingException, SQLException {
		
		if(bnumper==null){
 			bnumper = " - ";
 		}
 		if(bnumper==""){
 			bnumper = " - ";
 		}
 		
 		if(bcodesc==null){
 			bcodesc = " - ";
 		}
 		if(bcodesc==""){
 			bcodesc = " - ";
 		}
 		
 		if(zona==null){
 			zona = " - ";
 		}
 		if(zona==""){
 			zona = " - ";
 		}

 		if(pollos==null){
 			pollos = " - ";
 		}
 		if(pollos==""){
 			pollos = " - ";
 		}
 		
		
		String[] vlnumper = bnumper.split("\\ - ", -1); 
 		String[] vlcodesc = bcodesc.split("\\ - ", -1); 
 		String[] vlpollos = pollos.split("\\ - ", -1); 
 		String[] vlzona = zona.split("\\ - ", -1); 
 		
        String query = "select * from ( select query.*, rownum filaDesde from";
           query += " (SELECT SEMCON, TOTAL, ANIOCON, MESCON";
           query += " FROM PFM004";
           query += " WHERE p_anocal||p_numper  LIKE trim('" + vlnumper[0].toUpperCase() +  "%')";
           query += " AND P_CODESC = '" + vlcodesc[0] + "'";
           query += " AND POLLOS = '" + vlpollos[0] + "'";
           query += " AND ZONA = '" + vlzona[0] + "'";
           query += " ORDER BY semord) query";
           query += " where rownum <= 30)";
           query += " where filaDesde >= 16";
	       ////System.out.println(query);
	 		
           consulta.selectPntGenerica(query, JNDI);
           inputnumber20 = consulta.getRows();
   		   if(inputnumber20>0){
   			vlArraytb20 = consulta.getArray();
   		}
    }
	
	private void selectInputs30() throws NamingException, SQLException {
			
		if(bnumper==null){
				bnumper = " - ";
			}
			if(bnumper==""){
				bnumper = " - ";
			}
			
			if(bcodesc==null){
				bcodesc = " - ";
			}
			if(bcodesc==""){
				bcodesc = " - ";
			}
			
			if(zona==null){
				zona = " - ";
			}
			if(zona==""){
				zona = " - ";
			}
	
			if(pollos==null){
				pollos = " - ";
			}
			if(pollos==""){
				pollos = " - ";
			}
			
		
		String[] vlnumper = bnumper.split("\\ - ", -1); 
			String[] vlcodesc = bcodesc.split("\\ - ", -1); 
			String[] vlpollos = pollos.split("\\ - ", -1); 
			String[] vlzona = zona.split("\\ - ", -1); 
			
	    String query = "select * from ( select query.*, rownum filaDesde from";
	       query += " (SELECT SEMCON, TOTAL, ANIOCON, MESCON";
	       query += " FROM PFM004";
	       query += " WHERE p_anocal||p_numper  LIKE trim('" + vlnumper[0].toUpperCase() +  "%')";
	       query += " AND P_CODESC = '" + vlcodesc[0] + "'";
	       query += " AND POLLOS = '" + vlpollos[0] + "'";
	       query += " AND ZONA = '" + vlzona[0] + "'";
           query += " ORDER BY semord) query";
	       query += " where rownum <= 45)";
	       query += " where filaDesde >= 31";

	       ////System.out.println(query);
	 		
	       consulta.selectPntGenerica(query, JNDI);
           inputnumber30 = consulta.getRows();
   		   if(inputnumber30>0){
   			vlArraytb30 = consulta.getArray();
   		}
	}
	
    private void selectInputs40() throws NamingException, SQLException {
				
		if(bnumper==null){
 			bnumper = " - ";
 		}
 		if(bnumper==""){
 			bnumper = " - ";
 		}
 		
 		if(bcodesc==null){
 			bcodesc = " - ";
 		}
 		if(bcodesc==""){
 			bcodesc = " - ";
 		}
 		
 		if(zona==null){
 			zona = " - ";
 		}
 		if(zona==""){
 			zona = " - ";
 		}

 		if(pollos==null){
 			pollos = " - ";
 		}
 		if(pollos==""){
 			pollos = " - ";
 		}
 		
		
		String[] vlnumper = bnumper.split("\\ - ", -1); 
 		String[] vlcodesc = bcodesc.split("\\ - ", -1); 
 		String[] vlpollos = pollos.split("\\ - ", -1); 
 		String[] vlzona = zona.split("\\ - ", -1); 
 		
        String query = "select * from ( select query.*, rownum filaDesde from";
           query += " (SELECT SEMCON, TOTAL, ANIOCON, MESCON";
           query += " FROM PFM004";
           query += " WHERE p_anocal||p_numper  LIKE trim('" + vlnumper[0].toUpperCase() +  "%')";
           query += " AND P_CODESC = '" + vlcodesc[0] + "'";
           query += " AND POLLOS = '" + vlpollos[0] + "'";
           query += " AND ZONA = '" + vlzona[0] + "'";
           query += " ORDER BY semord) query";
           query += " where rownum <= 56)";
           query += " where filaDesde >= 46";
           
           consulta.selectPntGenerica(query, JNDI);
           inputnumber40 = consulta.getRows();
   		   if(inputnumber40>0){
   			vlArraytb40 = consulta.getArray();
   		}
    }
    
    
      /*
	  * Seleccione consumo pfm004_consumo
	  * */
    private void selectPfm004ConsumoC() throws NamingException, SQLException {
		
		if(bnumper==null){
 			bnumper = " - ";
 		}
 		if(bnumper==""){
 			bnumper = " - ";
 		}
 		
 		if(bcodesc==null){
 			bcodesc = " - ";
 		}
 		if(bcodesc==""){
 			bcodesc = " - ";
 		}

 		
		
		String[] vlnumper = bnumper.split("\\ - ", -1); 
 		String[] vlcodesc = bcodesc.split("\\ - ", -1); 
 		
        String query = "select consumo, mortalidad, tipalimento";
           query += " FROM PFM004_consumo";
           query += " WHERE p_anocal||p_numper  LIKE trim('" + vlnumper[0].toUpperCase() +  "%')";
           query += " AND P_CODESC = '" + vlcodesc[0] + "'";
           query += " AND ZONA = 'CENTRO'";
        ////System.out.println(query);  
           consulta.selectPntGenerica(query, JNDI);
           inputnumberconsumoc = consulta.getRows();
   		   if(inputnumberconsumoc>0){
   			consumoc = consulta.getArray();
   		   } 
    }
    
    /*
	  * Seleccione consumo pfm004_consumo
	  * */
   private void selectPfm004ConsumoO() throws NamingException, SQLException {
		
		if(bnumper==null){
			bnumper = " - ";
		}
		if(bnumper==""){
			bnumper = " - ";
		}
		
		if(bcodesc==null){
			bcodesc = " - ";
		}
		if(bcodesc==""){
			bcodesc = " - ";
		}

		
		
		String[] vlnumper = bnumper.split("\\ - ", -1); 
		String[] vlcodesc = bcodesc.split("\\ - ", -1); 
		
       String query = "select consumo, mortalidad, tipalimento";
          query += " FROM PFM004_consumo";
          query += " WHERE p_anocal||p_numper  LIKE trim('" + vlnumper[0].toUpperCase() +  "%')";
          query += " AND P_CODESC = '" + vlcodesc[0] + "'";
          query += " AND ZONA = 'ORIENTE'";
          
          consulta.selectPntGenerica(query, JNDI);
          inputnumberconsumoo = consulta.getRows();
  		   if(inputnumberconsumoo>0){
  			consumoo = consulta.getArray();
  		   } 
   }
   
	   /*
		  * Seleccione consumo pfm004_consumo
		  * */
	 private void selectPfm004ConsumoOC() throws NamingException, SQLException {
			
			if(bnumper==null){
				bnumper = " - ";
			}
			if(bnumper==""){
				bnumper = " - ";
			}
			
			if(bcodesc==null){
				bcodesc = " - ";
			}
			if(bcodesc==""){
				bcodesc = " - ";
			}
	
			
			
			String[] vlnumper = bnumper.split("\\ - ", -1); 
			String[] vlcodesc = bcodesc.split("\\ - ", -1); 
			
	     String query = "select consumo, mortalidad, tipalimento";
	        query += " FROM PFM004_consumo";
	        query += " WHERE p_anocal||p_numper  LIKE trim('" + vlnumper[0].toUpperCase() +  "%')";
	        query += " AND P_CODESC = '" + vlcodesc[0] + "'";
	        query += " AND ZONA = 'ORIENTE'";
	        
	        consulta.selectPntGenerica(query, JNDI);
	        inputnumberconsumooc = consulta.getRows();
			   if(inputnumberconsumooc>0){
				consumooc = consulta.getArray();
			   } 
	 }
   	
     
    	
      
	 public void selectInputs() throws NamingException, SQLException{
	        //Render the inputs
	        selectInputs10();
	        selectInputs20();
	        selectInputs30();
	        selectInputs40();	        
	 }
	 
	 
	 public void selectInputsConsumo() throws NamingException, SQLException{
	        //Render the inputs
		 selectPfm004ConsumoC(); //Inputs para consumo Centro
		 selectPfm004ConsumoO(); //Inputs para consumo Oriente
		 selectPfm004ConsumoOC(); //Inputs para consumo Occidente    
	 }
	 
	 
	 /*
	  * Inserta la definición de los consumos de alimentos para cada semana
	  * Donde se define el tipo de alimento por semana
	  * S1 : 1P, S2: 2P, S3: 2P, S4: 3P; S5: 3P; S6: 3F
	  * Adicionalmente se carga la fórmula correspondiente al consumo semanal  
	  * */
	 private void insertPfm004Consumo(int psemana, String pconsumo, String ptipalimento, String pzona, String pmort) throws SQLException{
		 
		//Valida que los campos no sean nulos
	        try {
	        	//Context initContext = new InitialContext();     
	     		//DataSource ds = (DataSource) initContext.lookup(JNDI);

	     		//con = ds.getConnection();
	     		
	     		String[] vecanoper = bnumper.split("\\ - ", -1);

	    		
	        		String anio = vecanoper[0].substring(0, 4);
	        		String per;
	                if(vecanoper[0].length()==6){
	                	per = vecanoper[0].substring(4, 6);
	                } else {
	                	per = vecanoper[0].substring(4, 5);
	                }
	            
	            if(psemana==1){
	            	ptipalimento = "1P";
	            }
	            if(psemana==2){
	            	ptipalimento = "2P";
	            }
	            if(psemana==3){
	            	ptipalimento = "2P";
	            }
	            if(psemana==4){
	            	ptipalimento = "3P";
	            }
	            if(psemana==5){
	            	ptipalimento = "3P";
	            }
	            if(psemana==6){
	            	ptipalimento = "3F";
	            }
	                
	            String query = "INSERT INTO pfm004_consumo VALUES (?,?,?,?,?,?,?,?)";
	            pstmt = con.prepareStatement(query);
	            pstmt.setBigDecimal(1, new BigDecimal(anio));
	            pstmt.setBigDecimal(2, new BigDecimal(per));
	            pstmt.setString(3, bcodesc.split(" - ")[0]);
	            pstmt.setInt(4, psemana);
	            pstmt.setBigDecimal(5, new BigDecimal(pconsumo));
	            pstmt.setString(6, ptipalimento);
	            pstmt.setString(7, pzona);
	            pstmt.setString(8, pmort);
	            ////System.out.println(query);
	            try {
	                //Avisando
	                pstmt.executeUpdate();
	             } catch (SQLException e)  {
	            	e.printStackTrace();
	            }	            
	        } catch (Exception e) {
	        }
	       // pstmt.close();
	       // con.close();
	        
	 }
	 
	 
	 /**
	  * Cálculo de consumo de alimento
	  */
      private void pfm005_alimento() throws SQLException{
    	
    	String[] vlnumper = bnumper.split("\\ - ", -1); 
   		String[] vlesc = bcodesc.split("\\ - ", -1);
   		String[] vlzona = zona.split("\\ - ", -1); 
   		
   		String anio = vlnumper[0].substring(0, 4);
  		String per;
          if(vlnumper[0].length()==6){
          	per = vlnumper[0].substring(4, 6);
          } else {
          	per = vlnumper[0].substring(4, 5);
          }
      
      try {
      	Context initContext = new InitialContext();     
   		DataSource ds = (DataSource) initContext.lookup(JNDI);

   		con = ds.getConnection();	
   		
          CallableStatement proc = null;


          proc = con.prepareCall("{CALL SPCALPREPF05_ALIMENTO(?,?,?,?)}");
          proc.setString(1, anio);
          proc.setString(2, per);
          proc.setString(3, vlesc[0]);
          proc.setString(4, vlzona[0]);
          ////System.out.println("Año: " + anio);
          ////System.out.println("Período: " + per);
          ////System.out.println("Escenario: " + vlesc[0]);
          ////System.out.println("Zona: " + vlzona[0]);

          proc.execute();
          
          proc.close();
          con.close();

      } catch (Exception e) {
    	  exito = "error";
    	  msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
    	  //e.printStackTrace();
    	  FacesContext.getCurrentInstance().addMessage(null, msj);
      }   	
      //FacesContext.getCurrentInstance().addMessage(null, msj);
      }
	
}

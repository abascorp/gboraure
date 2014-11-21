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
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.openbizview.util.PntGenerica;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Andres
 */
@ManagedBean
@ViewScoped
public class Bvm002 extends Bd implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LazyDataModel<Bvm002> lazyModel;  
	
	/**
	 * @return the lazyModel
	 */
	public LazyDataModel<Bvm002> getLazyModel() {
		return lazyModel;
	}	

	@PostConstruct
	public void init(){
		lazyModel  = new LazyDataModel<Bvm002>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 7217573531435419432L;
			
            @Override
			public List<Bvm002> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) { 
            	//Filtro
            	if (filters != null) {
               	 for(Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
               		 String filterProperty = it.next(); // table column name = field name
                     filterValue = filters.get(filterProperty);
               	 }
                }
            	try { 
            		//Consulta
					select(first, pageSize,sortField, filterValue);
					//Counter
					counter(filterValue);
					//Contador lazy
					lazyModel.setRowCount(rows);  //Necesario para crear la paginación
				} catch (SQLException | NamingException | ClassNotFoundException e) {	
					e.printStackTrace();
				}             
				return list;  
            } 
            
            
            //Arregla bug de primefaces
            @Override
            /**
			 * Arregla el Issue 1544 de primefaces lazy loading porge generaba un error
			 * de divisor equal a cero, es solamente un override
			 */
            public void setRowIndex(int rowIndex) {
                /*
                 * The following is in ancestor (LazyDataModel):
                 * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
                 */
                if (rowIndex == -1 || getPageSize() == 0) {
                    super.setRowIndex(-1);
                }
                else
                    super.setRowIndex(rowIndex % getPageSize());
            }
            
		};
	}
	

	private String bcodcia = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cia");
	private String bnumper = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("periodo");
	private String bcodesc = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("esc");
	private Date fechade = null;
	private String codneg = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tipneg");
	private List<Bvm002> list = new ArrayList<Bvm002>();
	private Object filterValue = "";
	
	
	//Campos para el select 
	private String vbocdia;
	private String vbanocal;
	private String bvnumper;
	private String vbcodesc;
	private String vfecini;
	private String vfecfin;
    private String vcodneg;
    private String vfeccon;
    private String vprecio;
    private String vvolumen;
    private String vcodart;
    private String vdesart;


	/**
	 * @return the bvnumper
	 */
	public String getBvnumper() {
		return bvnumper;
	}

	/**
	 * @param bvnumper the bvnumper to set
	 */
	public void setBvnumper(String bvnumper) {
		this.bvnumper = bvnumper;
	}

	/**
	 * @return the vprecio
	 */
	public String getVprecio() {
		return vprecio;
	}

	/**
	 * @param vprecio the vprecio to set
	 */
	public void setVprecio(String vprecio) {
		this.vprecio = vprecio;
	}

	/**
	 * @return the vbocdia
	 */
	public String getVbocdia() {
		return vbocdia;
	}

	/**
	 * @param vbocdia the vbocdia to set
	 */
	public void setVbocdia(String vbocdia) {
		this.vbocdia = vbocdia;
	}

	/**
	 * @return the vbanocal
	 */
	public String getVbanocal() {
		return vbanocal;
	}

	/**
	 * @param vbanocal the vbanocal to set
	 */
	public void setVbanocal(String vbanocal) {
		this.vbanocal = vbanocal;
	}

	/**
	 * @return the vbcodesc
	 */
	public String getVbcodesc() {
		return vbcodesc;
	}

	/**
	 * @param vbcodesc the vbcodesc to set
	 */
	public void setVbcodesc(String vbcodesc) {
		this.vbcodesc = vbcodesc;
	}

	/**
	 * @return the vfecini
	 */
	public String getVfecini() {
		return vfecini;
	}

	/**
	 * @param vfecini the vfecini to set
	 */
	public void setVfecini(String vfecini) {
		this.vfecini = vfecini;
	}

	/**
	 * @return the vfecfin
	 */
	public String getVfecfin() {
		return vfecfin;
	}

	/**
	 * @param vfecfin the vfecfin to set
	 */
	public void setVfecfin(String vfecfin) {
		this.vfecfin = vfecfin;
	}

	/**
	 * @return the vcodneg
	 */
	public String getVcodneg() {
		return vcodneg;
	}

	/**
	 * @param vcodneg the vcodneg to set
	 */
	public void setVcodneg(String vcodneg) {
		this.vcodneg = vcodneg;
	}

	/**
	 * @return the vfeccon
	 */
	public String getVfeccon() {
		return vfeccon;
	}

	/**
	 * @param vfeccon the vfeccon to set
	 */
	public void setVfeccon(String vfeccon) {
		this.vfeccon = vfeccon;
	}

	/**
	 * @return the vvolumen
	 */
	public String getVvolumen() {
		return vvolumen;
	}

	/**
	 * @param vvolumen the vvolumen to set
	 */
	public void setVvolumen(String vvolumen) {
		this.vvolumen = vvolumen;
	}

	/**
	 * @return the vcodart
	 */
	public String getVcodart() {
		return vcodart;
	}

	/**
	 * @param vcodart the vcodart to set
	 */
	public void setVcodart(String vcodart) {
		this.vcodart = vcodart;
	}

	/**
	 * @return the vdesart
	 */
	public String getVdesart() {
		return vdesart;
	}

	/**
	 * @param vdesart the vdesart to set
	 */
	public void setVdesart(String vdesart) {
		this.vdesart = vdesart;
	}

	/**
	 * @return the list
	 */
	public List<Bvm002> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Bvm002> list) {
		this.list = list;
	}

	/**
	 * @return the filterValue
	 */
	public Object getFilterValue() {
		return filterValue;
	}

	/**
	 * @param filterValue the filterValue to set
	 */
	public void setFilterValue(Object filterValue) {
		this.filterValue = filterValue;
	}

	/**
	 * @return the bcodcia
	 */
	public String getBcodcia() {
		return bcodcia;
	}
	
	/**
	 * @param bcodcia the bcodcia to set
	 */
	public void setBcodcia(String bcodcia) {
		this.bcodcia = bcodcia;
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
	 * @return the fechade
	 */
	public Date getFechade() {
		return fechade;
	}
	
	/**
	 * @param fechade the fechade to set
	 */
	public void setFechade(Date fechade) {
		this.fechade = fechade;
	}
	
	
	
	/**
	 * @return the codneg
	 */
	public String getCodneg() {
		return codneg;
	}
	
	/**
	 * @param codneg the codneg to set
	 */
	public void setCodneg(String codneg) {
		this.codneg = codneg;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
     * Borra Paises
     * <p>
     * Parametros del metodo: String codpai. Pool de conecciones
     **/
	private void deletes(String tabla) throws NamingException  {  
	        try {
	        	
	        	String[] vlcia = bcodcia.split("\\ - ", -1); 
	     		String[] vlnumper = bnumper.split("\\ - ", -1); 
	     		String[] vlcodesc = bcodesc.split("\\ - ", -1); 
	     		String[] vlcodneg = codneg.split("\\ - ", -1); 
	       	
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
	        	
	
	        	String query = "DELETE " + tabla + " WHERE P_CODCIA = ? AND P_ANOCAL= ? AND P_NUMPER= ? AND P_CODESC= ? AND P_CODNEG= ?";
	        	        		        	
	            pstmt = con.prepareStatement(query);
	            ////System.out.println(query);
	            pstmt.setString(1, vlcia[0]);
	        	pstmt.setString(2, anio);
	        	pstmt.setString(3, per);
	        	pstmt.setString(4, vlcodesc[0]);
	        	pstmt.setString(5, vlcodneg[0]);
	
	            try {
	                //Avisando
	                pstmt.executeUpdate();
	                
	                if(pstmt.getUpdateCount()==0){
	                	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
	                } else {
	                	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnDelete"), "");
	                }
	                
	                
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
	
	/*
	 * Borra tabla pfm003*/
	public void delete() throws NamingException, ClassNotFoundException, SQLException {  
		deletes("PFM003");
		deletes("PFM002");
        limpiarValores();	
     }
 
   
   /**
    * Leer Datos de paises
    * @throws NamingException 
 * @throws IOException 
    **/ 	
 	public void select(int first, int pageSize, String sortField, Object filterValue) throws SQLException, ClassNotFoundException, NamingException {

 		Context initContext = new InitialContext();     
 		DataSource ds = (DataSource) initContext.lookup(JNDI);

 		con = ds.getConnection();	
 		
 		if(bcodcia==null){
			bcodcia = " - ";
 		}
 		if(bcodcia==""){
 			bcodcia = " - ";
 		}
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
 		if(codneg==null){
 			codneg = " - ";
 		}
 		if(codneg==""){
 			codneg = " - ";
 		}
 		
 		
 		String[] vlcia = bcodcia.split("\\ - ", -1); 
 		String[] vlnumper = bnumper.split("\\ - ", -1); 
 		String[] vlcodesc = bcodesc.split("\\ - ", -1); 
 		String[] vlcodneg = codneg.split("\\ - ", -1); 
 		
 		//Consulta paginada
 		String query = "  select * from ";
 	           query += " ( select query.*, rownum as rn from";
 			   query = " select p_codcia, p_anocal, p_numper, p_codesc, trim(p_desc_negocio), to_char(feccon,'dd/mm/yyyy'), precio, volumen, p_codigo_articulo, p_desc_articulo " ;
               query += " FROM pfm002";
               query += " WHERE p_codcia LIKE trim('" + vlcia[0].toUpperCase() +  "%') ";
  		       query += " AND   p_anocal||p_numper  LIKE trim('" + vlnumper[0].toUpperCase() +  "%')";
  		       query += " AND  p_codesc  LIKE trim('" + vlcodesc[0].toUpperCase() +  "%')";
  		       query += " AND  p_codneg  LIKE trim('" + vlcodneg[0].toUpperCase() +  "%')";
               query += " ORDER BY 1,2,3,4,5" ;
               query += " order by " + sortField.replace("v", "") + ") query";
	           query += " ) where rownum <= " + pageSize ;
	           query += " and rn > (" + first + ")";

       pstmt = con.prepareStatement(query);
       System.out.println(query);
 		
       ResultSet r =  pstmt.executeQuery();
       
 		
       
       
       while (r.next()){
    	Bvm002 select = new Bvm002();
        select.setVbocdia(r.getString(1));
        select.setVbanocal(r.getString(2));
        select.setBvnumper(r.getString(3));
        select.setVbcodesc(r.getString(4));
        select.setVcodneg(r.getString(5));
        select.setVfeccon(r.getString(6));
        select.setVprecio(r.getString(7));
        select.setVvolumen(r.getString(8));
        select.setVcodart(r.getString(9));
        select.setVdesart(r.getString(10));
       	//Agrega la lista
       	list.add(select);
       	rows = list.size();
       }
       //Cierra las conecciones
       pstmt.close();
       con.close();
       bcodcia = "";
       bnumper = "";
       bcodesc = "";
       codneg = "";
 	}
 	
 	/**
     * Leer registros en la tabla
     * @throws NamingException 
     * @throws IOException 
     **/ 	
  	public void counter(Object filterValue ) throws SQLException, NamingException {
     try {	
    	Context initContext = new InitialContext();     
   		DataSource ds = (DataSource) initContext.lookup(JNDI);
   		con = ds.getConnection();
		String query = "";

        	 query = "SELECT count_bvm002('" + ((String) filterValue).toUpperCase()  + "') from dual";
   
        pstmt = con.prepareStatement(query);
        //System.out.println(query);

         r =  pstmt.executeQuery();
        
        
        while (r.next()){
        	rows = r.getInt(1);
        }
     } catch (SQLException e){
         e.printStackTrace();    
     }
        //Cierra las conecciones
        pstmt.close();
        con.close();
        r.close();

  	}
   
    /**
     * Rutina que ejecuta la carga de saldo inicial
     * @throws NamingException 
     **/

    public void bi_saldo_inicial() throws NamingException {

    		String[] vlcia = bcodcia.split("\\ - ", -1); 
     		String[] vlnumper = bnumper.split("\\ - ", -1); 
     		String[] vlesc = bcodesc.split("\\ - ", -1);
     		String[] vlcodneg = codneg.split("\\ - ", -1);
     		
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


            proc = con.prepareCall("{CALL PF_VALOR_INICIAL(?,?,?,?,?,to_char(sysdate,'dd/mm/yyyy'))}");
            proc.setString(1, vlcia[0]);
            proc.setString(2, anio);
            proc.setString(3, per);
            proc.setString(4, vlesc[0]);
            proc.setString(5, vlcodneg[0]);
            
           
            try {
            proc.execute();
            //
            
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

  	
    private void limpiarValores() {
 		// TODO Auto-generated method stub
    	bcodcia = "";
    	bnumper = "";
    	bcodesc = "";
    	fechade = null;
 	}


}

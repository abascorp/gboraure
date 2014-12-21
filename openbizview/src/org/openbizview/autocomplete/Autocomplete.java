/*
 *  Copyright (C) 2011  ANDRES DOMINGUEZ, CHRISTIAN DiAZ

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

package org.openbizview.autocomplete;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import org.openbizview.util.Bd;
import org.openbizview.util.PntGenerica;

/*
 * Esta clase permite mostrar las listas de valores en todas las páginas
 * utilizando Primefaces y sustituyendo a JQuery
 */

@ManagedBean
@RequestScoped
public class Autocomplete extends Bd {
	PntGenerica consulta = new PntGenerica();
	int rows;
	String[][] tabla;
	private String coduser = "";
	private String session = "";
	private String instancia = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("instancia"); //Usuario logeado
	

	/**
	 * Lista grupo de reportes.
	 * @throws NamingException 
	 * @return List String
	 * @throws IOException 
	 **/
	 public List<String> completeGrupo(String query) throws NamingException, IOException {  
	 
	 String queryora = "Select codgrup||' - '||desgrup " +
			   " from bvt001a " +
				" where codgrup like '%" + query + "%' or desgrup like '%" + query.toUpperCase() + "%' order by codgrup";
	 String querypg = "Select codgrup||' - '||desgrup " +
			   " from bvt001a " +
				" where codgrup like '%" + query + "%' or desgrup like '%" + query.toUpperCase() + "%' order by codgrup";	 
	 String querysql = "Select codgrup + ' - ' + desgrup " +
			   " from bvt001a " +
				" where codgrup like '%" + query + "%' or desgrup like '%" + query.toUpperCase() + "%' order by codgrup";
	 
	 List<String> results = new ArrayList<String>();  
	 
	 consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI);
	 
	 rows = consulta.getRows();
	 tabla = consulta.getArray();
	   for (int i = 0; i < rows; i++) {  
		   results.add(tabla[i][0]);  
	    }  
	    return results;  
	}  
	 
	 
	 /**
		 * Lista grupo de reportes.
		 * @throws NamingException 
		 * @return List String
		 * @throws IOException 
		 **/
		 public List<String> completeInstancias(String query) throws NamingException, IOException {  
		 
		 String queryora = "Select instancia||' - '||descripcion " +
				   " from instancias " +
					" where instancia like '%" + query + "%' or descripcion like '%" + query.toUpperCase() + "%' order by instancia";
		 String querypg = "Select instancia||' - '||descripcion " +
				   " from instancias " +
					" where cast(instancia as text) like '%" + query + "%' or descripcion like '%" + query.toUpperCase() + "%' order by instancia";	 
		 String querysql = "Select instancia||' - '||descripcion " +
				   " from instancias " +
					" where instancia like '%" + query + "%' or descripcion like '%" + query.toUpperCase() + "%' order by instancia";
		 
		 List<String> results = new ArrayList<String>();  
		// System.out.println(queryora);
		 consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI);
		 
		 rows = consulta.getRows();
		 tabla = consulta.getArray();
		   for (int i = 0; i < rows; i++) {  
			   results.add(tabla[i][0]);  
		    }  
		    return results;  
		}  
	
	/**
	 * Lista Categoria1.
	 * @throws NamingException 
	 * @return List String
	 * @throws IOException 
	 **/
	 public List<String> completeCat1(String query) throws NamingException, IOException {  

	 String vlqueryOra = "Select codcat1||' - '||descat1 from bvtcat1 where codcat1||descat1 like '%" + query.toUpperCase() + "%'  and instancia = '" + instancia + "' order by codcat1";
	  
	 String vlqueryPg = "Select codcat1||' - '||descat1 from bvtcat1 where codcat1||descat1 like '%" + query.toUpperCase() + "%' and instancia = '" + instancia + "' order by codcat1";
	  
	 String vlquerySql = "Select codcat1 + ' - ' + descat1 from bvtcat1 where codcat1 + descat1 like '%" + query.toUpperCase() + "%' and instancia = '" + instancia + "' order by codcat1";
	  
	 List<String> results = new ArrayList<String>();  
	 
	 consulta.selectPntGenericaMDB(vlqueryOra,vlqueryPg, vlquerySql,  JNDI);
	 
	 rows = consulta.getRows();
	 
	 tabla = consulta.getArray();
	   for (int i = 0; i < rows; i++) {  
		   results.add(tabla[i][0]);  
	    }  
	    return results;  
	}  
	 
	 /**
	 * Lista Categoria1 en seguridad.
	 * @throws NamingException 
	 * @return List String
	 * @throws IOException 
	 **/
	 public List<String> completeCat1AccCat2(String query) throws NamingException, IOException {  
		  String segrol = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("segrol"); //Usuario logeado
		  
		  if(segrol==null){
	        	segrol = " - ";
	        }
	        if(segrol==""){
	        	segrol = " - ";
	        }
	      String[] vecrol = segrol.split("\\ - ", -1);
	      
		String queryora = "Select codcat1||' - '||descat1 " +
			" from bvtcat1 " +
			" where codcat1||descat1 like '%" + query + "%' and codcat1 in (select B_CODCAT1 from acccat1 where B_CODROL ='" + vecrol[0].toUpperCase() + "') and instancia = '" + instancia + "' order by codcat1";
		String querypg = "Select codcat1||' - '||descat1 " +
				" from bvtcat1 " +
				" where codcat1||descat1 like '%" + query + "%' and codcat1 in (select B_CODCAT1 from acccat1 where B_CODROL ='" + vecrol[0].toUpperCase() + "') and instancia = '" + instancia + "' order by codcat1";		
		String querysql = "Select codcat1 + ' - ' + descat1 " +
				" from bvtcat1 " +
				" where codcat1 + descat1 like '%" + query + "%' and codcat1 in (select B_CODCAT1 from acccat1 where B_CODROL ='" + vecrol[0].toUpperCase() + "') and instancia = '" + instancia + "' order by codcat1";
		
		List<String> results = new ArrayList<String>();  
		  

		 
	      consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI);
		 
	      rows = consulta.getRows();
		 
	      tabla = consulta.getArray();

	      for (int i = 0; i < rows; i++) {  
			   results.add(tabla[i][0]);  
		    }  
		    return results;  
		}  
	 
	 
	 /**
		 * Lista Categoria2.
		 * @throws NamingException 
		 * @return List String
	 * @throws IOException 
		 **/
	public List<String> completeCat2(String query) throws NamingException, IOException {  
		String cat1 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cat1"); //Usuario logeado
		 if(cat1==null){
	        	cat1 = " - ";
	        }
	        if(cat1==""){
	        	cat1 = " - ";
	        }
	        String[] veccat1 = cat1.split("\\ - ", -1);
	    String vlqueryOra = "Select codcat2||' - '||descat2 from bvtcat2 where codcat2||descat2 like '%" + query.toUpperCase() +  "%' and b_codcat1 = '" + veccat1[0] + "' and instancia = '" + instancia + "' order by codcat2";
	    String vlqueryPg = "Select codcat2||' - '||descat2 from bvtcat2 where codcat2||descat2 like '%" + query.toUpperCase() +  "%' and b_codcat1 = '" + veccat1[0] + "' and instancia = '" + instancia + "' order by codcat2";
	    String vlquerySql = "Select codcat2 + ' - ' + descat2 from bvtcat2 where codcat2 + descat2 like '%" + query.toUpperCase() +  "%' and b_codcat1 = '" + veccat1[0] + "' and instancia = '" + instancia + "' order by codcat2";
	    
	    //System.out.println(vlqueryOra);    
	  List<String> results = new ArrayList<String>();  
	 consulta.selectPntGenericaMDB(vlqueryOra, vlqueryPg, vlquerySql, JNDI);
	 rows = consulta.getRows();
	 tabla = consulta.getArray();
	   for (int i = 0; i < rows; i++) {  
		   results.add(tabla[i][0]);  
	    }  
	   return results;  
	}  
	
	/**
	 * Lista Categoria2.
	 * @throws NamingException 
	 * @return List String
	 * @throws IOException 
	 **/
	public List<String> completeCat2AccCat3(String query) throws NamingException, IOException {  
		String segrol = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("segrol"); //Usuario logeado

		String cat1 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cat1"); //Usuario logeado

		    if(segrol==null){
	        	segrol = " - ";
	        }
	        if(segrol==""){
	        	segrol = " - ";
	        }
	        if(cat1==null){
	        	cat1 = " - ";
	        }
	        if(cat1==""){
	        	cat1 = " - ";
	        }
	        String[] vecrol = segrol.split("\\ - ", -1);
	        String[] veccat1 = cat1.split("\\ - ", -1);
	
	  
	  String queryora = "Select codcat2||' - '||descat2 " +
			   " from bvtcat2 " +
				" where codcat2||descat2 like '%" + query + "%' and codcat2 in (select B_CODCAT2 from acccat2 where B_CODROL ='" + vecrol[0].toUpperCase() + "') and b_codcat1 = '" + veccat1[0] + "' and instancia = '" + instancia + "' order by codcat2";      
	  String querypg  = "Select codcat2||' - '||descat2 " +
			   " from bvtcat2 " +
				" where codcat2||descat2 like '%" + query + "%' and codcat2 in (select B_CODCAT2 from acccat2 where B_CODROL ='" + vecrol[0].toUpperCase() + "') and b_codcat1 = '" + veccat1[0] + "' and instancia = '" + instancia + "' order by codcat2"; 	        
	  String querysql = "Select codcat2 + ' - ' + descat2 " +
			   " from bvtcat2 " +
				" where codcat2 + descat2 like '%" + query + "%' and codcat2 in (select B_CODCAT2 from acccat2 where B_CODROL ='" + vecrol[0].toUpperCase() + "') and b_codcat1 = '" + veccat1[0] + "' and instancia = '" + instancia + "' order by codcat2"; 
	  
	  List<String> results = new ArrayList<String>();  
	  

	  consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI);

	   rows = consulta.getRows();
	   tabla = consulta.getArray();
	   for (int i = 0; i < rows; i++) {  
		   results.add(tabla[i][0]);  
	    }  
	   return results;  
	}  
	
	
	/**
	 * Lista Categoria2.
	 * @throws NamingException 
	 * @return List String
	 * @throws IOException 
	 **/
	public List<String> completeCat3AccCat4(String query) throws NamingException, IOException {  
		String segrol = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("segrol"); //Usuario logeado
		String cat1 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cat1"); //Usuario logeado
		String cat2 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cat2"); //Usuario logeado
		  if(segrol==null){
	        	segrol = " - ";
	        }
	        if(segrol==""){
	        	segrol = " - ";
	        }
	        if(cat1==null){
	        	cat1 = " - ";
	        }
	        if(cat1==""){
	        	cat1 = " - ";
	        }
	        if(cat2==null){
	        	cat2 = " - ";
	        }
	        if(cat2==""){
	        	cat2 = " - ";
	        }
	        String[] vecrol = segrol.split("\\ - ", -1);
	        String[] veccat1 = cat1.split("\\ - ", -1);
	        String[] veccat2 = cat2.split("\\ - ", -1);
	
	  List<String> results = new ArrayList<String>();  
	  String queryora = "Select codcat3||' - '||descat3 " +
			   " from bvtcat3 " +
				" where codcat3||descat3 like '%" + query + "%' and codcat3 in (select B_CODCAT3 from acccat3 where B_CODROL ='" + vecrol[0].toUpperCase() + "') and b_codcat1 = '" + veccat1[0] + "' and b_codcat2 = '" + veccat2[0] + "' and instancia = '" + instancia + "' order by codcat3";
	  String querypg  = "Select codcat3||' - '||descat3 " +
			   " from bvtcat3 " +
				" where codcat3||descat3 like '%" + query + "%' and codcat3 in (select B_CODCAT3 from acccat3 where B_CODROL ='" + vecrol[0].toUpperCase() + "') and b_codcat1 = '" + veccat1[0] + "' and b_codcat2 = '" + veccat2[0] + "' and instancia = '" + instancia + "' order by codcat3";	 
	  String querysql = "Select codcat3 + ' - ' + descat3 " +
			   " from bvtcat3 " +
				" where codcat3 + descat3 like '%" + query + "%' and codcat3 in (select B_CODCAT3 from acccat3 where B_CODROL ='" + vecrol[0].toUpperCase() + "') and b_codcat1 = '" + veccat1[0] + "' and b_codcat2 = '" + veccat2[0] + "' and instancia = '" + instancia + "' order by codcat3";
	 consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI); 
	 rows = consulta.getRows();
	 tabla = consulta.getArray();
	   for (int i = 0; i < rows; i++) {  
		   results.add(tabla[i][0]);  
	    }  
	   return results;  
	}  
		 
		 
		 /**
			 * Lista Categoria3.
			 * @throws NamingException 
			 * @return List String
		 * @throws IOException 
			 **/
	 public List<String> completeCat3(String query) throws NamingException, IOException {  
		    String cat1 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cat1"); //Usuario logeado
			String cat2 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cat2"); //Usuario logeado
			
		        if(cat1==null){
		        	cat1 = " - ";
		        }
		        if(cat1==""){
		        	cat1 = " - ";
		        }
		        if(cat2==null){
		        	cat2 = " - ";
		        }
		        if(cat2==""){
		        	cat2 = " - ";
		        }
		        String[] veccat1 = cat1.split("\\ - ", -1);
		        String[] veccat2 = cat2.split("\\ - ", -1);
		        String vlqueryOra = "Select codcat3||' - '||descat3 from bvtcat3 where codcat3||descat3 like '%" + query.toUpperCase() +  "%' and b_codcat1 = '" + veccat1[0] + "' and b_codcat2 = '" + veccat2[0] + "' and instancia = '" + instancia + "' order by  codcat3";
			    String vlqueryPg = "Select codcat3||' - '||descat3 from bvtcat3 where codcat3||descat3 like '%" + query.toUpperCase() +  "%' and b_codcat1 = '" + veccat1[0] + "' and b_codcat2 = '" + veccat2[0] + "' and instancia = '" + instancia + "' order by  codcat3";
			    String vlquerySql = "Select codcat3+' - '+descat3 from bvtcat3 where codcat3+descat3 like '%" + query.toUpperCase() +  "%' and b_codcat1 = '" + veccat1[0] + "' and b_codcat2 = '" + veccat2[0] + "' and instancia = '" + instancia + "' order by  codcat3";
			   // System.out.println(vlqueryOra);
			   // System.out.println(vlqueryPg);
	  List<String> results = new ArrayList<String>();  
	 consulta.selectPntGenericaMDB(vlqueryOra,vlqueryPg, vlquerySql , JNDI);
		 rows = consulta.getRows();
		 tabla = consulta.getArray();
		   for (int i = 0; i < rows; i++) {  
			   results.add(tabla[i][0]);  
		    }  
		    return results;  
		}   

			 /**
				 * Lista Categoria1.
				 * @throws NamingException 
				 * @return List String
			 * @throws IOException 
				 **/
	public List<String> completeUorg(String query) throws NamingException, IOException {  
	  List<String> results = new ArrayList<String>();  

	  
	  String queryora = "select distinct 'UNIDAD ORIGEN'||' - '|| trim(UORG)"
	       + " from BI_HISTORIALINV "
	       + " where UORG like'" + query.toUpperCase() + "%'"
	       + " order by 1";
	  String querypg = "select distinct 'UNIDAD ORIGEN'||' - '|| trim(UORG)"
		       + " from BI_HISTORIALINV "
		       + " where UORG like'" + query.toUpperCase() + "%'"
		       + " order by 1";	 
	  String querysql = "select distinct 'UNIDAD ORIGEN'+' - '+ trim(UORG)"
		       + " from BI_HISTORIALINV "
		       + " where UORG like'" + query.toUpperCase() + "%'"
		       + " order by 1";
	  consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI);
	 rows = consulta.getRows();
	 tabla = consulta.getArray();
	   for (int i = 0; i < rows; i++) {  
	     results.add(tabla[i][0]);  
		   }  
		   return results;  
		}  
	
	/**
	 * Lista Categoria1.
	 * @throws NamingException 
	 * @return List String
	 * @throws IOException 
	 **/
	 public List<String> completeCat4(String query) throws NamingException, IOException {  
	  List<String> results = new ArrayList<String>();  

	  String queryora = "Select codcat4||' - '||descat4 " +
			   " from bvtcat4 " +
				" where codcat4 like '%" + query + "%' or descat4 like '%" + query.toUpperCase() + "%' and instancia = '" + instancia + "' order by codcat4";
	  String querypg = "Select codcat4||' - '||descat4 " +
			   " from bvtcat4 " +
				" where codcat4 like '%" + query + "%' or descat4 like '%" + query.toUpperCase() + "%' and instancia = '" + instancia + "' order by codcat4";	  
	  String querysql = "Select codcat4+' - '+descat4 " +
			   " from bvtcat4 " +
				" where codcat4 like '%" + query + "%' or descat4 like '%" + query.toUpperCase() + "%' and instancia = '" + instancia + "' order by codcat4";
	  
	  consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI);
	 rows = consulta.getRows();
	 tabla = consulta.getArray();
	   for (int i = 0; i < rows; i++) {  
		   results.add(tabla[i][0]);  
	    }  
	    return results;  
	}  
	

	 public List<String> completeAsocat(String query) throws NamingException, IOException {  
		 String uorg = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("uorg"); //Usuario logeado
		 if(uorg==null){
			 uorg = " - ";
	        }
	        if(uorg==""){
	        	uorg = " - ";
	        }
	        String[] vecuorg = uorg.split("\\ - ", -1);
		  List<String> results = new ArrayList<String>();  

	  	String queryora = "select distinct trim(CPRO)||' - '||NPRO "
			        + " from BI_HISTORIALINV "
			        + " where trim(CPRO)||NPRO like'" + query.toUpperCase() + "%'"
			        + " and UORG = '" + vecuorg[1] + "'"
			        + " order by 1";
	  	String querypg = "select distinct trim(CPRO)||' - '||NPRO "
		        + " from BI_HISTORIALINV "
		        + " where trim(CPRO)||NPRO like'" + query.toUpperCase() + "%'"
		        + " and UORG = '" + vecuorg[1] + "'"
		        + " order by 1";
	  	String querysql = "select distinct trim(CPRO)+' - '+NPRO "
		        + " from BI_HISTORIALINV "
		        + " where trim(CPRO)+NPRO like'" + query.toUpperCase() + "%'"
		        + " and UORG = '" + vecuorg[1] + "'"
		        + " order by 1";
	  	
	  	consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI);
		rows = consulta.getRows();
		 tabla = consulta.getArray();
		  for (int i = 0; i < rows; i++) {  
		  results.add(tabla[i][0]);  
		   }  
		   return results;  
		}
	 
	 /**
		 * Lista Usuario.
		 * @throws NamingException 
		 * @return List String
	 * @throws IOException 
		 **/
	 
		 public List<String> completeUser(String query) throws NamingException, IOException {  

		 String queryora = "Select coduser||' - '|| desuser "  +
				 " from bvt002 " +
				 " where coduser like '%" + query + "%' or desuser like '%" + query.toUpperCase() + "%' and instancia = '" + instancia + "' order by coduser";
		 String querypg  = "Select coduser||' - '|| desuser "  +
				 " from bvt002 " +
				 " where coduser like '%" + query + "%' or desuser like '%" + query.toUpperCase() + "%' and instancia = '" + instancia + "' order by coduser";
		 String querysql = "Select coduser + ' - ' + desuser " +
				 " from bvt002 " +
				 " where coduser like '%" + query + "%' or desuser like '%" + query.toUpperCase() + "%' and instancia = '" + instancia + "' order by coduser";
	     
		 List<String> results = new ArrayList<String>();
		  
		 consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI);

		 rows = consulta.getRows();
		 tabla = consulta.getArray();
		   for (int i = 0; i < rows; i++) {  
			   results.add(tabla[i][0]);  
		    }  
		    return results;  
		}  
		 
		 /**
			 * Lista rol.
			 * @throws NamingException 
			 * @return List String
		 * @throws IOException 
			 **/
		 public List<String> completeRol(String query) throws NamingException, IOException {  

		 String queryora = "Select codrol||' - '||desrol " +
				   " from bvt003 " +
					" where codrol||desrol like '%" + query + "%'  and instancia = '" + instancia + "' order by codrol ";
		 String querypg = "Select codrol||' - '||desrol " +
				   " from bvt003 " +
					" where codrol||desrol like '%" + query + "%'  and instancia = '" + instancia + "' order by codrol ";
		 String querysql = "Select codrol + ' - ' + desrol " +
				   " from bvt003 " +
					" where codrol + desrol like '%" + query + "%'  and instancia = '" + instancia + "' order by codrol ";
		 	//System.out.println(querypg);	
		 List<String> results = new ArrayList<String>();  
		 consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI);
		 rows = consulta.getRows();
		 tabla = consulta.getArray();
		   for (int i = 0; i < rows; i++) {  
			   results.add(tabla[i][0]);  
		    }  
		    return results;  
		 } 
		 
		 /**
			 * Lista Categoria4.
			 * @throws NamingException 
			 * @return List String
		 * @throws IOException 
			 **/
	 public List<String> completeCat4a(String query) throws NamingException, IOException {  
		 coduser = (String) UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get("coduser");
		 session = (String) UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get("session");
	  List<String> results = new ArrayList<String>();  

	  String queryora = "Select codcat4||' - '||descat4 " +
	   " from bvtcat4 " +
		" where descat4 like '%" + query.toUpperCase() + "%' and b_codcat1 =( select cat1 from temp01 where coduser ='"
		+ coduser + "' and SESSIONID = '" + session + "') and b_codcat2 =( select cat2 from temp01 where coduser ='"
	   + coduser + "' and SESSIONID = '" + session + "') and b_codcat3 =( select cat3 from temp01 where coduser ='"
	   + coduser + "' and SESSIONID = '" + session + "')"
	   + " order by codcat4";

	  String querypg = "Select codcat4||' - '||descat4 " +
			   " from bvtcat4 " +
				" where descat4 like '%" + query.toUpperCase() + "%' and b_codcat1 =( select cat1 from temp01 where coduser ='"
				+ coduser + "' and SESSIONID = '" + session + "') and b_codcat2 =( select cat2 from temp01 where coduser ='"
			   + coduser + "' and SESSIONID = '" + session + "') and b_codcat3 =( select cat3 from temp01 where coduser ='"
			   + coduser + "' and SESSIONID = '" + session + "')"
			   + " order by codcat4";

	  String querysql = "Select codcat4+' - '+descat4 " +
			   " from bvtcat4 " +
				" where descat4 like '%" + query.toUpperCase() + "%' and b_codcat1 =( select cat1 from temp01 where coduser ='"
				+ coduser + "' and SESSIONID = '" + session + "') and b_codcat2 =( select cat2 from temp01 where coduser ='"
			   + coduser + "' and SESSIONID = '" + session + "') and b_codcat3 =( select cat3 from temp01 where coduser ='"
			   + coduser + "' and SESSIONID = '" + session + "')"
			   + " order by codcat4";

	  consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI);

	  rows = consulta.getRows();

	  tabla = consulta.getArray();
		   for (int i = 0; i < rows; i++) {  
			   results.add(tabla[i][0]);  
		    }  
		    return results;  
		}  
	 
	 /**
		 * Lista Reportes.
		 * @throws NamingException 
		 * @return List String
	 * @throws IOException 
		 **/
		 public List<String> completeRep(String query) throws NamingException, IOException {  
		  
		  List<String> results = new ArrayList<String>();  

		  String queryora = "Select codrep||' - '||desrep " +
		   " from bvt001 " +
			" where codrep like '%" + query + "%' or desrep like '%" + query.toUpperCase() + "%' and instancia = '" + instancia + "' order by codrep";

		  String querypg = "Select codrep||' - '||desrep " +
		   " from bvt001 " +
			" where codrep like '%" + query + "%' or desrep like '%" + query.toUpperCase() + "%' and instancia = '" + instancia + "' order by codrep";

		  String querysql = "Select codrep+' - '+desrep " +
		   " from bvt001 " +
			" where codrep like '%" + query + "%' or desrep like '%" + query.toUpperCase() + "%' and instancia = '" + instancia + "' order by codrep";
		 
		  consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI);
		 
		  rows = consulta.getRows();
		 
		  tabla = consulta.getArray();
		  
		  for (int i = 0; i < rows; i++) {  
			   results.add(tabla[i][0]);  
		    }  
		    return results;  
		}
		 
		 /**
			 * Lista Categoria1.
			 * @throws NamingException 
			 * @return List String
		 * @throws IOException 
			 **/
		public List<String> completeCias(String query) throws NamingException, IOException {  
		  List<String> results = new ArrayList<String>();  

		  String queryora = "Select codcia||' - '||descia " +
		   " from bvt010" +
			" where codcia||descia like '%" + query + "%' or descia like '%" + query.toUpperCase() + "%' order by codcia";
		  String querypg = "Select codcia||' - '||descia " +
				   " from bvt010" +
					" where codcia||descia like '%" + query + "%' or descia like '%" + query.toUpperCase() + "%' order by codcia";
		  String querysql = "Select codcia + ' - ' + descia " +
				   " from bvt010" +
					" where codcia + descia like '%" + query + "%' or descia like '%" + query.toUpperCase() + "%' order by codcia";
		 consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI);
		 rows = consulta.getRows();
		 tabla = consulta.getArray();
		   for (int i = 0; i < rows; i++) {  
			   results.add(tabla[i][0]);  
		    }  
		    return results;  
			}  
		
		 /**
		 * Lista Periodo.
		 * @throws NamingException 
		 * @return List String
		 * @throws IOException 
		 **/
		public List<String> completePeriodos(String query) throws NamingException, IOException {  
			String ciacopia = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ciacopia");
       	    String[] vecciacopia = ciacopia.split("\\ - ", -1);
			 
			  List<String> results = new ArrayList<String>();  
			 consulta.selectPntGenerica("select anocal||numper||' - '||to_char(fecini,'dd/mm/yyyy')|| ' / '||to_char(fecfin,'dd/mm/yyyy')"
				        + " from bvt011 "
				        + " where anocal||numper like '%" + query.toUpperCase() + "%'"
				        + " and b_codcia = '" + vecciacopia[0] + "'"
				        + " order by 1",JNDI );
			rows = consulta.getRows();
			 tabla = consulta.getArray();
			  for (int i = 0; i < rows; i++) {  
			  results.add(tabla[i][0]);  
			   }  
			   return results;  
			}
		
		public List<String> completePeriodosSelect(String query) throws NamingException, IOException {  
			//String ciacopia = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cia");
				 
			  List<String> results = new ArrayList<String>();  
			 consulta.selectPntGenerica("select anocal||numper||' - '||to_char(fecini,'dd/mm/yyyy')|| ' / '||to_char(fecfin,'dd/mm/yyyy')"
				        + " from bvt011 "
				        + " where anocal||numper like '%" + query.toUpperCase() + "%'"
				        + " order by 1 desc",JNDI );
			rows = consulta.getRows();
			 tabla = consulta.getArray();
			  for (int i = 0; i < rows; i++) {  
			  results.add(tabla[i][0]);  
			   }  
			   return results;  
			}
		
		/**
		 * Lista Dimensiones.
		 * @throws NamingException 
		 * @return List String
		 * @throws IOException 
		 **/
		 public List<String> completeDim(String query) throws NamingException, IOException {  
		  List<String> results = new ArrayList<String>();  
		 consulta.selectPntGenerica("Select coddim||' - '||desdim " +
		   " from bvt009 " +
			" where coddim||desdim like '%" + query + "%' order by coddim", JNDI);
		 rows = consulta.getRows();
		 tabla = consulta.getArray();
		   for (int i = 0; i < rows; i++) {  
			   results.add(tabla[i][0]);  
		    }  
		    return results;  
		}
		 
		 /**
			 * Lista Gasto.
			 * @throws NamingException 
			 * @return List String
		 * @throws IOException 
			 **/
			 public List<String> completeGto(String query) throws NamingException, IOException {  
			  List<String> results = new ArrayList<String>();  
			 consulta.selectPntGenerica("Select codgto||' - '||desgto " +
			   " from bvt013 " +
				" where codgto like '%" + query + "%' or desgto like '%" + query.toUpperCase() + "%' order by codgto", JNDI);
			 rows = consulta.getRows();
			 tabla = consulta.getArray();
			   for (int i = 0; i < rows; i++) {  
				   results.add(tabla[i][0]);  
			    }  
			    return results;  
			}  
			 
			 /**
				 * Lista Cuentas.
				 * @throws NamingException 
				 * @return List String
			 * @throws IOException 
				 **/
				 public List<String> completeCta(String query) throws NamingException, IOException {  
				  List<String> results = new ArrayList<String>();  
				 consulta.selectPntGenerica("Select codplan||' - '||desplan " +
				   " from bvt008 " +
					" where codplan like '%" + query + "%' or desplan like '%" + query.toUpperCase() + "%' order by codplan", JNDI);
				 rows = consulta.getRows();
				 tabla = consulta.getArray();
				   for (int i = 0; i < rows; i++) {  
					   results.add(tabla[i][0]);  
				    }  
				    return results;  
				}
				 
				 /**
					 * Lista escenario.
					 * @throws NamingException 
					 * @return List String
				 * @throws IOException 
					 **/
					public List<String> completeEscenario(String query) throws NamingException, IOException {  
						 //String cia = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cia");
						 String periodo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("periodo");

							if(periodo==null){
								periodo = " - ";
							}

					  String[] vecperiodo = periodo.split("\\ - ", -1);
						 
					     
						  List<String> results = new ArrayList<String>();  
						 consulta.selectPntGenerica("select codesc||' - '||desesc"
							        + " from bvt012"
						            + " where b_anocal||b_numper like '%" + query.toUpperCase() + "%'"
						            + " and b_anocal||b_numper like '" + vecperiodo[0] + "%'"
							        + " order by 1",JNDI );
						rows = consulta.getRows();
						 tabla = consulta.getArray();
						  for (int i = 0; i < rows; i++) {  
						  results.add(tabla[i][0]);  
						   }  
						   return results;  
						}
		
					 /**
					 * Lista escenario.
					 * @throws NamingException 
					 * @return List String
					 * @throws IOException 
					 **/
					public List<String> completeNegocio(String query) throws NamingException, IOException {  
				     
						  List<String> results = new ArrayList<String>();  
						 consulta.selectPntGenerica("select tipneg||' - '||descripcion"
							        + " from VTAS_TIPNEG"
						            + " where upper(tipneg||descripcion) like '%" + query.toUpperCase() + "%'"
							        + " order by tipneg",JNDI );
						rows = consulta.getRows();
						 tabla = consulta.getArray();
						  for (int i = 0; i < rows; i++) {  
						  results.add(tabla[i][0]);  
						   }  
						   return results;  
						}	
					
	/**
	* Lista escenario.
	* @throws NamingException 
	* @return List String
	 * @throws IOException 
	**/
	public List<String> completeTerritorio(String query) throws NamingException, IOException {  
				     
	  List<String> results = new ArrayList<String>();  
	    consulta.selectPntGenerica("select territorio"
		   + " from VTAS_DETALLE"
		   + " where territorio is not null",JNDI );
			rows = consulta.getRows();
		    tabla = consulta.getArray();
		   for (int i = 0; i < rows; i++) {  
			  results.add(tabla[i][0]);  
			   }  
		   return results;  
	}						
					
		/**
		* Lista Asociar dimensiones.
		* @throws NamingException 
		* @return List String
		 * @throws IOException 
		**/
		public List<String> completeAsodim(String query) throws NamingException, IOException {  
		 coduser = (String) UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get("coduser");
		 session = (String) UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get("session");
		  List<String> results = new ArrayList<String>();  
		 consulta.selectPntGenerica("Select ASODIM||' - '||DESASODIM" +
		   " from bvt014" +
			" where DESASODIM like '%" + query.toUpperCase() + "%' and B_CODDIM =( select CODDIM from temp05 where coduser ='"
				+ coduser + "' and SESSIONID = '" + session + "')"
				+ " order by asodim", JNDI);
			 rows = consulta.getRows();
			 tabla = consulta.getArray();
			   for (int i = 0; i < rows; i++) {  
				   results.add(tabla[i][0]);  
			    }  
			   return results;  
		}  
			
		/**
		 * Lista grupos para cuenta contable.
		 * @throws NamingException 
		 * @return List String
		 * @throws IOException 
		 **/
		 public List<String> completeGrupos(String query) throws NamingException, IOException {  
		  List<String> results = new ArrayList<String>();  
		 consulta.selectPntGenerica("Select codgrup||' - '||desgrup " +
		   " from bvt017 " +
			" where codgrup||desgrup like '%" + query.toUpperCase() + "%' order by codgrup", JNDI);
		 rows = consulta.getRows();
		 tabla = consulta.getArray();
		   for (int i = 0; i < rows; i++) {  
			   results.add(tabla[i][0]);  
		    }  
		    return results;  
		}  	
		 
		 /**
			 * Lista grupos de mail.
			 * @throws NamingException 
			 * @return List String
		 * @throws IOException 
			 **/
			 public List<String> completeMailGrupos(String query) throws NamingException, IOException {  
			  List<String> results = new ArrayList<String>();  
			 
			  String queryora = "Select IDGRUPO||' - '||DESGRUPO " +
			   " from mailgrupos " +
				" where IDGRUPO||DESGRUPO like '%" + query.toUpperCase() + "%' and instancia = '" + instancia + "' order by IDGRUPO";
			  String querypg = "Select IDGRUPO||' - '||DESGRUPO " +
					   " from mailgrupos " +
						" where IDGRUPO||DESGRUPO like '%" + query.toUpperCase() + "%' and instancia = '" + instancia + "' order by IDGRUPO";
			  String querysql = "Select LTRIM(RTRIM(CAST(IDGRUPO AS CHAR)))+' - '+DESGRUPO " +
					   " from mailgrupos " +
						" where LTRIM(RTRIM(cast(IDGRUPO as char)))+DESGRUPO like '%" + query.toUpperCase() + "%' oand instancia = '" + instancia + "' order by IDGRUPO";
			 consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI); 
			 rows = consulta.getRows();
			 tabla = consulta.getArray();
			   for (int i = 0; i < rows; i++) {  
				   results.add(tabla[i][0]);  
			    }  
			    return results;  
			}  	
			 
			 
	 public List<String> completeFamilias(String query) throws NamingException, IOException {  
		 String cia = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cia");
		 String periodo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("periodo");
	
		if(cia==null){
			cia = " - ";
		}
		if(periodo==null){
			periodo = " - ";
		}
		  String[] veccia = cia.split("\\ - ", -1);
		  String[] vecperiodo = periodo.split("\\ - ", -1);
				 
		 List<String> results = new ArrayList<String>();  
		 consulta.selectPntGenerica("select p_codigo_familia||' - '||p_desc_familia"
		     + " from pfm002 "
		     + " where p_codigo_familia||p_desc_familia like '%" + query.toUpperCase() + "%'"
		     + " and p_codcia = '" + veccia[0] + "'"
		     + " and  p_anocal||p_numper = '" + vecperiodo[0] + "' group by p_codigo_familia, p_desc_familia"
		     + " order by 1",JNDI );
			rows = consulta.getRows();
		   tabla = consulta.getArray();
		  for (int i = 0; i < rows; i++) {  
			  results.add(tabla[i][0]);  
			   }  
		   return results;  
		}	
	 
	 public List<String> completeProducto(String query) throws NamingException, IOException {  
		 String cia = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cia");
		 String periodo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("periodo");
	
		if(cia==null){
			cia = " - ";
		}
		if(periodo==null){
			periodo = " - ";
		}
		  String[] veccia = cia.split("\\ - ", -1);
		  String[] vecperiodo = periodo.split("\\ - ", -1);
				 
		 List<String> results = new ArrayList<String>();  
		 consulta.selectPntGenerica("select p_codigo_articulo||' - '||p_desc_articulo"
		     + " from pfm002 "
		     + " where p_codigo_articulo||p_desc_articulo like '%" + query.toUpperCase() + "%'"
		     + " and p_codcia = '" + veccia[0] + "'"
		     + " and  p_anocal||p_numper = '" + vecperiodo[0] + "' group by p_codigo_articulo, p_desc_articulo"
		     + " order by 1",JNDI );
			rows = consulta.getRows();
		   tabla = consulta.getArray();
		  for (int i = 0; i < rows; i++) {  
			  results.add(tabla[i][0]);  
			   }  
		   return results;  
		}	
	 
	 
	 public List<String> completeUbicacion(String query) throws NamingException, IOException {  
		 String cia = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cia");
		 String periodo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("periodo");
	
		if(cia==null){
			cia = " - ";
		}
		if(periodo==null){
			periodo = " - ";
		}
		  String[] veccia = cia.split("\\ - ", -1);
		  String[] vecperiodo = periodo.split("\\ - ", -1);
				 
		 List<String> results = new ArrayList<String>();  
		 consulta.selectPntGenerica("select distinct ubicacion"
		     + " from pfm002 "
		     + " where ubicacion like '%" + query.toUpperCase() + "%'"
		     + " and p_codcia = '" + veccia[0] + "'"
		     + " and  p_anocal||p_numper = '" + vecperiodo[0] + "'"
		     + " order by ubicacion",JNDI );
			rows = consulta.getRows();
		   tabla = consulta.getArray();
		  for (int i = 0; i < rows; i++) {  
			  results.add(tabla[i][0]);  
			   }  
		   return results;  
		}
	 
	 
	 public List<String> completeEstado(String query) throws NamingException, IOException {  
		 String cia = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cia");
		 String periodo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("periodo");
	
		if(cia==null){
			cia = " - ";
		}
		if(periodo==null){
			periodo = " - ";
		}
		  String[] veccia = cia.split("\\ - ", -1);
		  String[] vecperiodo = periodo.split("\\ - ", -1);
				 
		 List<String> results = new ArrayList<String>();  
		 consulta.selectPntGenerica("select distinct estado"
		     + " from pfm002 "
		     + " where ubicacion like '%" + query.toUpperCase() + "%'"
		     + " and p_codcia = '" + veccia[0] + "'"
		     + " and  p_anocal||p_numper = '" + vecperiodo[0] + "'"
		     + " order by estado",JNDI );
			rows = consulta.getRows();
		   tabla = consulta.getArray();
		  for (int i = 0; i < rows; i++) {  
			  results.add(tabla[i][0]);  
			   }  
		   return results;  
		}
	 
	 
	 public List<String> completeZonas(String query) throws NamingException, IOException {  
		 String cia = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cia");
		 String periodo = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("periodo");
	
		if(cia==null){
			cia = " - ";
		}
		if(periodo==null){
			periodo = " - ";
		}
		  String[] veccia = cia.split("\\ - ", -1);
		  String[] vecperiodo = periodo.split("\\ - ", -1);
				 
		 List<String> results = new ArrayList<String>();  
		 consulta.selectPntGenerica("select distinct zonas"
		     + " from pfm002 "
		     + " where ubicacion like '%" + query.toUpperCase() + "%'"
		     + " and p_codcia = '" + veccia[0] + "'"
		     + " and  p_anocal||p_numper = '" + vecperiodo[0] + "'"
		     + " order by zonas",JNDI );
			rows = consulta.getRows();
		   tabla = consulta.getArray();
		  for (int i = 0; i < rows; i++) {  
			  results.add(tabla[i][0]);  
			   }  
		   return results;  
		}
			
	 
	 /**
		 * Lista Usuario.
		 * @throws NamingException 
		 * @return List String
	 * @throws IOException 
		 **/
		 public List<String> completeNivel(String query) throws NamingException, IOException {  
		  List<String> results = new ArrayList<String>();  
		  consulta.selectPntGenerica("Select codniv||' - '||desniv" +
		  " from req001 " +
		  " where codniv||desniv like '%" + query + "%' order by codniv", JNDI);
		 rows = consulta.getRows();
		 tabla = consulta.getArray();
		   for (int i = 0; i < rows; i++) {  
			   results.add(tabla[i][0]);  
		    }  
		    return results;  
		}  
			 
}			 

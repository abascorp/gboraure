/*
 * Copyright 2011 DvConsultores
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	    http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
 */

package org.openbizview.autocomplete;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
	private String instancia = (String) FacesContext.getCurrentInstance()
			.getExternalContext().getSessionMap().get("instancia"); // Usuario
																	// logeado
	List<String> results = new ArrayList<String>();
	
	
	//Coneccion a base de datos
	//Pool de conecciones JNDI
	Connection con;
	PreparedStatement pstmt = null;
	ResultSet r;
	
		
	/**
	 * Lista grupo de reportes.
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/
	public List<String> prueba(String query)  {

		String queryora = "Select codgrup||' - '||desgrup " + " from bvt001a "
				+ " where codgrup||desgrup like '%" + query.toUpperCase() + "%' and instancia = '"
				+ instancia + "' order by codgrup";

		consulta.selectGenerica(queryora, JNDI);
		//System.out.println(consulta.getData());
		
		IntStream.range(0, consulta.getData().get(0).size())
		     .forEach(i -> 
		       results.add(consulta.getData().get(0).get(i))
		       );			
		return results;
	}

	/**
	 * Lista grupo de reportes.
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/
	public List<String> completeGrupo(String query) throws NamingException,
			IOException {

		String queryora = "Select codgrup||' - '||desgrup " + " from bvt001a "
				+ " where codgrup||desgrup like '%" + query.toUpperCase() + "%' and instancia = '"
				+ instancia + "' order by codgrup";
		String querypg = "Select codgrup||' - '||desgrup " + " from bvt001a "
				+ " where codgrup||desgrup like '%" + query.toUpperCase() + "%'  and instancia = '"
				+ instancia	+ "' order by codgrup";
		String querysql = "Select codgrup + ' - ' + desgrup "
				+ " from bvt001a " + " where codgrup+desgrup like '%" + query.toUpperCase() + "%' and instancia = '"
				+ instancia
				+ "' order by codgrup";

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
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/
	public List<String> completeInstancias(String query)
			throws NamingException, IOException {

		String queryora = "Select instancia||' - '||descripcion "
				+ " from instancias  where instancia||descripcion like '%" + query.toUpperCase()
				+ "%' order by instancia";
		String querypg = "Select instancia||' - '||descripcion "
				+ " from instancias  where cast(instancia as text)||descripcion like '%" + query.toUpperCase()
				+ "%' order by instancia";
		String querysql = "Select instancia+' - '+descripcion "
				+ " from instancias  where cast(instancia as varchar)+descripcion like '%" + query.toUpperCase()
				+ "%' order by instancia";

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
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws SQLException 
	 * @throws IOException
	 **/
	public List<String> completeCat1(String query)  {

       	String strOra    = " Select codcat1||' - '||descat1 from bvtcat1 where codcat1||descat1 like '%" + query.toUpperCase() + "%'  and instancia = '" + instancia + "' order by codcat1";
       	String strPg     = " Select codcat1||' - '||descat1 from bvtcat1 where codcat1||descat1 like '%" + query.toUpperCase() + "%'  and instancia = '" + instancia + "' order by codcat1";
       	String strSqlSrv = " Select codcat1+' - '+descat1 from bvtcat1 where codcat1+descat1 like '%" + query.toUpperCase() + "%'  and instancia = '" + instancia + "' order by codcat1";        		

 		consulta.selectGenericaMDB(strOra, strPg, strSqlSrv, JNDI);
		//System.out.println(consulta.getData());
		
		IntStream.range(0, consulta.getData().get(0).size())
		     .forEach(i -> 
		       results.add(consulta.getData().get(0).get(i))
		       );			
		return results;
	}

	/**
	 * Lista Categoria1 en seguridad.
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/
	public List<String> completeCat1AccCat2(String query) {
		String segrol = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("segrol"); // Usuario
																		// logeado

		if (segrol == null) {
			segrol = " - ";
		}
		if (segrol == "") {
			segrol = " - ";
		}
		String[] vecrol = segrol.split("\\ - ", -1);

		String queryora = "Select codcat1||' - '||descat1 "
				+ " from bvtcat1 "
				+ " where codcat1||descat1 like '%"
				+ query.toUpperCase()
				+ "%' and codcat1 in (select B_CODCAT1 from acccat1 where B_CODROL ='"
				+ vecrol[0].toUpperCase() + "') and instancia = '" + instancia
				+ "' order by codcat1";
		String querypg = "Select codcat1||' - '||descat1 "
				+ " from bvtcat1 "
				+ " where codcat1||descat1 like '%"
				+ query.toUpperCase()
				+ "%' and codcat1 in (select B_CODCAT1 from acccat1 where B_CODROL ='"
				+ vecrol[0].toUpperCase() + "') and instancia = '" + instancia
				+ "' order by codcat1";
		String querysql = "Select codcat1 + ' - ' + descat1 "
				+ " from bvtcat1 "
				+ " where codcat1 + descat1 like '%"
				+ query.toUpperCase()
				+ "%' and codcat1 in (select B_CODCAT1 from acccat1 where B_CODROL ='"
				+ vecrol[0].toUpperCase() + "') and instancia = '" + instancia
				+ "' order by codcat1";

		List<String> results = new ArrayList<String>();

		consulta.selectGenericaMDB(queryora, querypg, querysql, JNDI);

		IntStream.range(0, consulta.getData().get(0).size())
	     .forEach(i -> 
	       results.add(consulta.getData().get(0).get(i))
	       );	
		return results;
	}

	/**
	 * Lista Categoria2.
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/
	public List<String> completeCat2(String query) throws NamingException,
			IOException {
		String cat1 = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("cat1"); // Usuario
																	// logeado
		if (cat1 == null) {
			cat1 = " - ";
		}
		if (cat1 == "") {
			cat1 = " - ";
		}
		String[] veccat1 = cat1.split("\\ - ", -1);
		String vlqueryOra = "Select codcat2||' - '||descat2 from bvtcat2 where codcat2||descat2 like '%"
				+ query.toUpperCase()
				+ "%' and b_codcat1 = '"
				+ veccat1[0]
				+ "' and instancia = '" + instancia + "' order by codcat2";
		String vlqueryPg = "Select codcat2||' - '||descat2 from bvtcat2 where codcat2||descat2 like '%"
				+ query.toUpperCase()
				+ "%' and b_codcat1 = '"
				+ veccat1[0]
				+ "' and instancia = '" + instancia + "' order by codcat2";
		String vlquerySql = "Select codcat2+' - '+descat2 from bvtcat2 where codcat2+descat2 like '%"
				+ query.toUpperCase()
				+ "%' and b_codcat1 = '"
				+ veccat1[0]
				+ "' and instancia = '" + instancia + "' order by codcat2";

		// System.out.println(vlqueryOra);
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
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/
	public List<String> completeCat2AccCat3(String query)
			throws NamingException, IOException {
		String segrol = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("segrol"); // Usuario
																		// logeado

		String cat1 = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("cat1"); // Usuario
																	// logeado

		if (segrol == null) {
			segrol = " - ";
		}
		if (segrol == "") {
			segrol = " - ";
		}
		if (cat1 == null) {
			cat1 = " - ";
		}
		if (cat1 == "") {
			cat1 = " - ";
		}
		String[] vecrol = segrol.split("\\ - ", -1);
		String[] veccat1 = cat1.split("\\ - ", -1);

		String queryora = "Select codcat2||' - '||descat2 "
				+ " from bvtcat2 "
				+ " where codcat2||descat2 like '%"
				+ query.toUpperCase()
				+ "%' and codcat2 in (select B_CODCAT2 from acccat2 where B_CODROL ='"
				+ vecrol[0].toUpperCase() + "') and b_codcat1 = '" + veccat1[0]
				+ "' and instancia = '" + instancia + "' order by codcat2";
		String querypg = "Select codcat2||' - '||descat2 "
				+ " from bvtcat2 "
				+ " where codcat2||descat2 like '%"
				+ query.toUpperCase()
				+ "%' and codcat2 in (select B_CODCAT2 from acccat2 where B_CODROL ='"
				+ vecrol[0].toUpperCase() + "') and b_codcat1 = '" + veccat1[0]
				+ "' and instancia = '" + instancia + "' order by codcat2";
		String querysql = "Select codcat2 + ' - ' + descat2 "
				+ " from bvtcat2 "
				+ " where codcat2 + descat2 like '%"
				+ query.toUpperCase()
				+ "%' and codcat2 in (select B_CODCAT2 from acccat2 where B_CODROL ='"
				+ vecrol[0].toUpperCase() + "') and b_codcat1 = '" + veccat1[0]
				+ "' and instancia = '" + instancia + "' order by codcat2";

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
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/
	public List<String> completeCat3AccCat4(String query)
			throws NamingException, IOException {
		String segrol = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("segrol"); // Usuario
																		// logeado
		String cat1 = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("cat1"); // Usuario
																	// logeado
		String cat2 = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("cat2"); // Usuario
																	// logeado
		if (segrol == null) {
			segrol = " - ";
		}
		if (segrol == "") {
			segrol = " - ";
		}
		if (cat1 == null) {
			cat1 = " - ";
		}
		if (cat1 == "") {
			cat1 = " - ";
		}
		if (cat2 == null) {
			cat2 = " - ";
		}
		if (cat2 == "") {
			cat2 = " - ";
		}
		String[] vecrol = segrol.split("\\ - ", -1);
		String[] veccat1 = cat1.split("\\ - ", -1);
		String[] veccat2 = cat2.split("\\ - ", -1);

		List<String> results = new ArrayList<String>();
		String queryora = "Select codcat3||' - '||descat3 "
				+ " from bvtcat3 "
				+ " where codcat3||descat3 like '%"
				+ query.toUpperCase()
				+ "%' and codcat3 in (select B_CODCAT3 from acccat3 where B_CODROL ='"
				+ vecrol[0].toUpperCase() + "') and b_codcat1 = '" + veccat1[0]
				+ "' and b_codcat2 = '" + veccat2[0] + "' and instancia = '"
				+ instancia + "' order by codcat3";
		String querypg = "Select codcat3||' - '||descat3 "
				+ " from bvtcat3 "
				+ " where codcat3||descat3 like '%"
				+ query.toUpperCase()
				+ "%' and codcat3 in (select B_CODCAT3 from acccat3 where B_CODROL ='"
				+ vecrol[0].toUpperCase() + "') and b_codcat1 = '" + veccat1[0]
				+ "' and b_codcat2 = '" + veccat2[0] + "' and instancia = '"
				+ instancia + "' order by codcat3";
		String querysql = "Select codcat3 + ' - ' + descat3 "
				+ " from bvtcat3 "
				+ " where codcat3 + descat3 like '%"
				+ query.toUpperCase()
				+ "%' and codcat3 in (select B_CODCAT3 from acccat3 where B_CODROL ='"
				+ vecrol[0].toUpperCase() + "') and b_codcat1 = '" + veccat1[0]
				+ "' and b_codcat2 = '" + veccat2[0] + "' and instancia = '"
				+ instancia + "' order by codcat3";
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
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/
	public List<String> completeCat3(String query) throws NamingException,
			IOException {
		String cat1 = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("cat1"); // Usuario
																	// logeado
		String cat2 = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("cat2"); // Usuario
																	// logeado

		if (cat1 == null) {
			cat1 = " - ";
		}
		if (cat1 == "") {
			cat1 = " - ";
		}
		if (cat2 == null) {
			cat2 = " - ";
		}
		if (cat2 == "") {
			cat2 = " - ";
		}
		String[] veccat1 = cat1.split("\\ - ", -1);
		String[] veccat2 = cat2.split("\\ - ", -1);
		String vlqueryOra = "Select codcat3||' - '||descat3 from bvtcat3 where codcat3||descat3 like '%"
				+ query.toUpperCase()
				+ "%' and b_codcat1 = '"
				+ veccat1[0]
				+ "' and b_codcat2 = '"
				+ veccat2[0]
				+ "' and instancia = '"
				+ instancia + "' order by  codcat3";
		String vlqueryPg = "Select codcat3||' - '||descat3 from bvtcat3 where codcat3||descat3 like '%"
				+ query.toUpperCase()
				+ "%' and b_codcat1 = '"
				+ veccat1[0]
				+ "' and b_codcat2 = '"
				+ veccat2[0]
				+ "' and instancia = '"
				+ instancia + "' order by  codcat3";
		String vlquerySql = "Select codcat3+' - '+descat3 from bvtcat3 where codcat3+descat3 like '%"
				+ query.toUpperCase()
				+ "%' and b_codcat1 = '"
				+ veccat1[0]
				+ "' and b_codcat2 = '"
				+ veccat2[0]
				+ "' and instancia = '"
				+ instancia + "' order by  codcat3";
		// System.out.println(vlqueryOra);
		// System.out.println(vlqueryPg);
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
	 * Lista Categoria1.
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/
	public List<String> completeCat4(String query) throws NamingException,
			IOException {
		List<String> results = new ArrayList<String>();

		String queryora = "Select codcat4||' - '||descat4 " + " from bvtcat4 "
				+ " where codcat4 like '%" + query + "%' or descat4 like '%"
				+ query.toUpperCase() + "%' and instancia = '" + instancia
				+ "' order by codcat4";
		String querypg = "Select codcat4||' - '||descat4 " + " from bvtcat4 "
				+ " where codcat4 like '%" + query + "%' or descat4 like '%"
				+ query.toUpperCase() + "%' and instancia = '" + instancia
				+ "' order by codcat4";
		String querysql = "Select codcat4+' - '+descat4 " + " from bvtcat4 "
				+ " where codcat4 like '%" + query + "%' or descat4 like '%"
				+ query.toUpperCase() + "%' and instancia = '" + instancia
				+ "' order by codcat4";

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
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/

	public List<String> completeUser(String query) throws NamingException,
			IOException {

		String queryora = "Select coduser||' - '|| desuser " + " from bvt002 "
				+ " where coduser||desuser like '%"
				+ query.toUpperCase() + "%' and instancia = '" + instancia
				+ "' order by coduser";
		String querypg = "Select coduser||' - '|| desuser " + " from bvt002 "
				+ " where coduser||desuser  like '%"
				+ query.toUpperCase() + "%' and instancia = '" + instancia
				+ "' order by coduser";
		String querysql = "Select coduser + ' - ' + desuser " + " from bvt002 "
				+ " where coduser+desuser  like '%"
				+ query.toUpperCase() + "%' and instancia = '" + instancia
				+ "' order by coduser";

		List<String> results = new ArrayList<String>();
		//System.out.println(querypg);

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
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/

	public List<String> completeUserCod(String query) throws NamingException,
			IOException {

		String queryora = "Select coduser " + " from bvt002 "
				+ " where coduser like '%"
				+ query.toUpperCase() + "%' and instancia = '" + instancia
				+ "' order by coduser";
		String querypg = "Select coduser " + " from bvt002 "
				+ " where coduser  like '%"
				+ query.toUpperCase() + "%' and instancia = '" + instancia
				+ "' order by coduser";
		String querysql = "Select coduser" + " from bvt002 "
				+ " where coduser  like '%"
				+ query.toUpperCase() + "%' and instancia = '" + instancia
				+ "' order by coduser";

		List<String> results = new ArrayList<String>();
		//System.out.println(querypg);

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
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/
	public List<String> completeRol(String query) throws NamingException,
			IOException {

		String queryora = "Select codrol||' - '||desrol " + " from bvt003 "
				+ " where codrol||desrol like '%" + query.toUpperCase()
				+ "%'  and instancia = '" + instancia + "' order by codrol ";
		String querypg = "Select codrol||' - '||desrol " + " from bvt003 "
				+ " where codrol||desrol like '%" + query.toUpperCase()
				+ "%'  and instancia = '" + instancia + "' order by codrol ";
		String querysql = "Select codrol + ' - ' + desrol " + " from bvt003 "
				+ " where codrol + desrol like '%" + query.toUpperCase()
				+ "%'  and instancia = '" + instancia + "' order by codrol ";
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
	 * Lista Reportes.
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/
	public List<String> completeRep(String query) throws NamingException,
			IOException {

		List<String> results = new ArrayList<String>();

		String queryora = "Select codrep||' - '||desrep " + " from bvt001 "
				+ " where codrep||desrep like '%" + query.toUpperCase()
				+ "%'  and instancia = '" + instancia + "' order by codrep";

		String querypg = "Select codrep||' - '||desrep " + " from bvt001 "
				+ " where codrep||desrep like '%" + query.toUpperCase()
				+ "%' and instancia = '" + instancia + "' order by codrep";

		String querysql = "Select codrep+' - '+desrep " + " from bvt001 "
				+ " where codrep+desrep like '%" + query.toUpperCase()
				+ "%' and instancia = '" + instancia + "' order by codrep";
		
		//System.out.println(querypg);

		consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI);

		rows = consulta.getRows();

		tabla = consulta.getArray();

		for (int i = 0; i < rows; i++) {
			results.add(tabla[i][0]);
		}
		return results;
	}

	/**
	 * Lista grupos de mail.
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/
	public List<String> completeMailGrupos(String query)
			throws NamingException, IOException {
		List<String> results = new ArrayList<String>();

		String queryora = "Select IDGRUPO||' - '||DESGRUPO "
				+ " from mailgrupos " + " where IDGRUPO||DESGRUPO like '%"
				+ query.toUpperCase() + "%' and instancia = '" + instancia
				+ "' order by IDGRUPO";
		String querypg = "Select IDGRUPO||' - '||DESGRUPO "
				+ " from mailgrupos " + " where IDGRUPO||DESGRUPO like '%"
				+ query.toUpperCase() + "%' and instancia = '" + instancia
				+ "' order by IDGRUPO";
		String querysql = "Select LTRIM(RTRIM(CAST(IDGRUPO AS CHAR)))+' - '+DESGRUPO "
				+ " from mailgrupos "
				+ " where LTRIM(RTRIM(cast(IDGRUPO as char)))+DESGRUPO like '%"
				+ query.toUpperCase()
				+ "%' and instancia = '"
				+ instancia
				+ "' order by IDGRUPO";
		consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI);
		rows = consulta.getRows();
		tabla = consulta.getArray();
		for (int i = 0; i < rows; i++) {
			results.add(tabla[i][0]);
		}
		return results;
	}
	
	
	/**
	 * Lista frecuencia de repetición.
	 * 
	 * @throws NamingException
	 * @return List String
	 * @throws IOException
	 **/
	public List<String> completeFrecuenciaRepeticion(String query)
			throws NamingException, IOException {
		List<String> results = new ArrayList<String>();

		String queryora = "Select distinct frecuencia||' - '||case frecuencia when '0' then '" + getMessage("mailtareaDiario").toUpperCase() + "' when  '1' then '" + getMessage("mailtareaSemanal").toUpperCase() + "' when '2' then'" + getMessage("mailtareaPersonalizada").toUpperCase() + "' when '3' then '" + getMessage("mailtareaHoraRep").toUpperCase() + "' when '4' then '" + getMessage("mailimes1").toUpperCase() + "' when '5' then '" + getMessage("maillidiasSelect").toUpperCase() + "'  else '" + getMessage("maillidiasSelect1").toUpperCase() + "' end "
				+ " from t_programacion " + " where frecuencia like '%"
				+ query.toUpperCase() + "%' and instancia = '" + instancia
				+ "'";
		String querypg = "Select distinct frecuencia||' - '||case frecuencia when '0' then '" + getMessage("mailtareaDiario").toUpperCase() + "' when  '1' then '" + getMessage("mailtareaSemanal").toUpperCase() + "' when '2' then'" + getMessage("mailtareaPersonalizada").toUpperCase() + "' when '3' then '" + getMessage("mailtareaHoraRep").toUpperCase() + "' when '4' then '" + getMessage("mailimes1").toUpperCase() + "' when '5' then '" + getMessage("maillidiasSelect").toUpperCase() + "'  else '" + getMessage("maillidiasSelect1").toUpperCase() + "' end "
				+ " from t_programacion " + " where frecuencia like '%"
				+ query.toUpperCase() + "%' and instancia = '" + instancia
				+ "'";
		String querysql = "Select LTRIM(RTRIM(CAST(IDGRUPO AS CHAR)))+' - '+DESGRUPO "
				+ " from mailgrupos "
				+ " where LTRIM(RTRIM(cast(IDGRUPO as char)))+DESGRUPO like '%"
				+ query.toUpperCase()
				+ "%' and instancia = '"
				+ instancia
				+ "' order by IDGRUPO";
		consulta.selectPntGenericaMDB(queryora, querypg, querysql, JNDI);
		//System.out.println(querypg);
		rows = consulta.getRows();
		tabla = consulta.getArray();
		for (int i = 0; i < rows; i++) {
			results.add(tabla[i][0]);
		}
		return results;
	}
	
	
}

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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;  

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.openbizview.getset.Transacciones;
import org.openbizview.util.PntGenerica;

/**
 *
 * @author Andres
 */
@ManagedBean
@SessionScoped
public class Bvm001 extends Bd {

	public Bvm001(){

	}
	
private Date fechade = null;
private Date fechaha = null;
private String cia = " - ";
private String cta = " - ";
private String anocon = "";
private String mescon = "";
private String mesconh = "";

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
 * @return the fechaha
 */
public Date getFechaha() {
	return fechaha;
}

/**
 * @param fechaha the fechaha to set
 */
public void setFechaha(Date fechaha) {
	this.fechaha = fechaha;
}




/**
 * @return the cta
 */
public String getCta() {
	return cta;
}

/**
 * @param cta the cta to set
 */
public void setCta(String cta) {
	this.cta = cta;
}

/**
 * @return the cia
 */
public String getCia() {
	return cia;
}

/**
 * @param cia the cia to set
 */
public void setCia(String cia) {
	this.cia = cia;
}

/**
 * @return the paginaActual
 */
public int getPaginaActual() {
	return paginaActual;
}

/**
 * @param paginaActual the paginaActual to set
 */
public void setPaginaActual(int paginaActual) {
	this.paginaActual = paginaActual;
}

/**
 * @return the orden
 */
public String getOrden() {
	return orden;
}

/**
 * @param orden the orden to set
 */
public void setOrden(String orden) {
	this.orden = orden;
}




/**
 * @return the anocon
 */
public String getAnocon() {
	return anocon;
}

/**
 * @param anocon the anocon to set
 */
public void setAnocon(String anocon) {
	this.anocon = anocon;
}

/**
 * @return the mescon
 */
public String getMescon() {
	return mescon;
}

/**
 * @param mescon the mescon to set
 */
public void setMescon(String mescon) {
	this.mescon = mescon;
}



/**
 * @return the mesconh
 */
public String getMesconh() {
	return mesconh;
}

/**
 * @param mesconh the mesconh to set
 */
public void setMesconh(String mesconh) {
	this.mesconh = mesconh;
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Variables seran utilizadas para capturar mensajes de errores de Oracle y parametros de metodos
PntGenerica consulta = new PntGenerica();
boolean vGacc; //Validador de opciones del menú
//O genera un error
private String orden = "1"; //Orden de la consulta
private int rows; //Registros de tabla
private int paginaActual = 1; //Páginas para paginar
private int rPp; //Registros por página

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//Coneccion a base de datos
//Pool de conecciones JNDI
@Resource(name="jdbc/orabiz")
private DataSource ds;
Connection con;
PreparedStatement pstmt = null;



   
    
    
    /**
     * Borra Paises
     * <p>
     * Parametros del metodo: String codpai. Pool de conecciones
     **/
    public void delete() throws  NamingException { 
    	if(anocon!="" && mescon!=""){
    	try {
        	con = ds.getConnection();
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //        getUrl(), getUser(), getPass());
        	String query = "DELETE Bvm001 WHERE ANOCON = '" + anocon + "' and MESCON = '" + mescon + "'";
        	integracion_del(anocon,mescon);
            pstmt = con.prepareStatement(query);
            ////System.out.println(query);
            //Antes de insertar verifica si el rol del usuario tiene permisos para insertar
            
            try {
                //Avisando
                pstmt.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();

            }

            pstmt.close();
            con.close();      
        } catch (Exception e) {
            e.printStackTrace();
        }
    	}
    }
    
 
   
   /**
    * Leer Datos de paises
    * @throws NamingException 
 * @throws IOException 
    **/ 	
 	public List<Transacciones> select(String regP) throws SQLException, ClassNotFoundException, NamingException {
       rPp = Integer.parseInt(regP);
 		con = ds.getConnection();
 	    //Class.forName(getDriver());
       //con = DriverManager.getConnection(
       // getUrl(), getUser(), getPass());
 		
 		
 		String[] vlcia = cia.split("\\ - ", -1); 
 		String[] vlcta = cta.split("\\ - ", -1); 
 		String querypag;
 		if(fechade!=null && fechaha!=null){
 			querypag = "select count(*) from bvm001" +
 		 		 	 " where FECASIENTO BETWEEN '" + sdfecha.format(fechade) 
 		 		 	 + "' and '" +  sdfecha.format(fechaha) 
 		 		 	 + "' and B_CODCIA LIKE trim('" + vlcia[0] +  "%') " +
 		 		 	 		" and cta like '" + vlcta[0].toUpperCase() + "%' and anocon like '" + anocon + "%'";
 		}else {
 			querypag = "select count(*) from bvm001" +
 		 	 		" where B_CODCIA LIKE trim('" + vlcia[0] +  "%') " +
 		 	 				"and cta like '" + vlcta[0].toUpperCase() + "%' and anocon like '" + anocon + "%'";
 		}
 		
 	    //Pagina resultados
 		consulta.selectPntGenerica(querypag, JNDI); 
 		String[][] vlArraytb = consulta.getArray();
 		String vlTabla = vlArraytb[0][0].toString();
 		rows = Integer.parseInt(vlTabla);
 		
 		String query;
 		if(fechade!=null && fechaha!=null){
 		//Consulta paginada
 		       query = "select * from ";
 			   query += "( select query.*, rownum filaDesde from " ;
               query += " (SELECT a.b_codcia, b.descia,  to_char(a.fecasiento,'dd/mm/yyyy'), a.cta, c.desplan, TO_CHAR(A.MONTO,'999,9999,999.99'), anocon, mescon";
               query += " FROM BVM001 A, BVT010 B, BVT008 C";
               query += " WHERE A.B_CODCIA=B.CODCIA";
               query += " AND  A.CTA=C.CODPLAN(+)";
               query += " AND A.B_CODCIA LIKE trim('" + vlcia[0] +  "%') ";
               query += " AND A.CTA LIKE trim('" + vlcta[0] +  "%') ";
               query += " AND   A.FECASIENTO BETWEEN '" + sdfecha.format(fechade) + "' and '" + sdfecha.format(fechaha) + "'";
               query += " AND   A.ANOCON LIKE'" + anocon + "%'";
               query += " ORDER BY " + orden + ") query" ;

 		} else {
 			query = "select * from ";
			query += "( select query.*, rownum filaDesde from " ;
            query += " (SELECT a.b_codcia, b.descia,  to_char(a.fecasiento,'dd/mm/yyyy'), a.cta, c.desplan, TO_CHAR(A.MONTO,'999,9999,999.99'), anocon, mescon";
            query += " FROM BVM001 A, BVT010 B, BVT008 C";
            query += " WHERE A.B_CODCIA=B.CODCIA";
            query += " AND  A.CTA=C.CODPLAN(+)";
            query += " AND A.B_CODCIA LIKE trim('" + vlcia[0] +  "%') ";
            query += " AND A.CTA LIKE trim('" + vlcta[0] +  "%') ";
            query += " AND   A.ANOCON LIKE'" + anocon + "%'";
            //query += " AND   A.FECASIENTO BETWEEN '" + vlfechade + "' and '" + vlfechaha + "'";
            query += " ORDER BY " + orden + ") query" ;

 			
 		}
       pstmt = con.prepareStatement(query);
       ////System.out.println(query);
 		
       ResultSet r =  pstmt.executeQuery();
       
 		
       List<Transacciones> list = new ArrayList<Transacciones>();
       
       while (r.next()){
    	Transacciones select = new Transacciones();
    	select.setVcodcia(r.getString(1));
    	select.setVdescia(r.getString(2));
       	select.setVfecasiento(r.getString(3));
       	select.setVcta(r.getString(4));
        select.setVdescta(r.getString(5));
        select.setVmonto(r.getString(6));
        select.setVanocon(r.getString(7));
        select.setVmescon(r.getString(8));
       	//Agrega la lista
       	list.add(select);
       	//rows = list.size();
       }
       //Cierra las conecciones
       pstmt.close();
       con.close();
       
 		return list;
 	}
   
   /**
  	 * @return the rows
  	 */
  	public int getRows() {
  		return rows;
  	}



  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////PAGINACION///////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /**
   * Indica si muestra en la paginación
   * @return String
   **/
  public String isPag(){
  //Si registros página es mayor a paginación
  String vLretorno = "display: none;";
  if (rows>rPp){
  vLretorno = "display: '';";
   }
  return vLretorno;
  }

  
	public void integracion() throws NamingException {
		if(valida()){ //No hace nada si son nulos
		try {
			con = ds.getConnection();
			CallableStatement proc = null;
			// Class.forName(getDriver());
			// con = DriverManager.getConnection(
			// getUrl(), getUsuario(), getClave());
			try {
				// Avisando
				proc = con.prepareCall("{CALL BI_COST_BAAN_EXE(?,?,?)}");
				proc.setString(1, anocon);
		        proc.setString(2, mescon);
		        proc.setString(3, mesconh);
				proc.execute();

			} catch (SQLException e) {
				e.printStackTrace();

			}
			//
			proc.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		}    	
	}

	
	public void integracion_del(String anocon, String mescon) throws NamingException {
		try {
			con = ds.getConnection();
			CallableStatement proc = null;
			// Class.forName(getDriver());
			// con = DriverManager.getConnection(
			// getUrl(), getUsuario(), getClave());
			try {
				// Avisando
				proc = con.prepareCall("{CALL BI_COST_BAAN_DEL(?,?)}");
				 proc.setString(1, anocon);
		         proc.setString(2, mescon);
				proc.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//
			proc.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
    	    	
	}

	
	  /**
	   * Valida que no existan campos nulos en el formulario
	   * @return Boolean
	   **/
	  private Boolean valida(){
	  	Boolean retorno = true;
	  	//Chequea que las variables no sean nulas 
	  	if (anocon.equals("") || mescon.equals("") || mesconh.equals("")){
	  		retorno = false;
	  	}
	  	return retorno;
	  }


}

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
import java.sql.*;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;
import javax.sql.DataSource;



/**
 *
 * @author Andres
 */
@ManagedBean
@SessionScoped
public class Bvtreljer extends Bd {

public Bvtreljer(){

}




public String getMsj() {
	return msj;
}



public void setMsj(String msj) {
	this.msj = msj;
}



public String getClaseResult() {
	return claseResult;
}



public void setClaseResult(String claseResult) {
	this.claseResult = claseResult;
}




///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Variables seran utilizadas para capturar mensajes de errores de Oracle y parametros de metodos
private String msj = null;
PntGenerica consulta = new PntGenerica();
private String claseResult = "";
boolean vGacc; //Validador de opciones del menú
//O genera un error
private int rows = 0;
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


public int getRows() {
	return rows;
}




public void setRows(int rows) {
	this.rows = rows;
}




//Coneccion a base de datos
//Pool de conecciones JNDI
@Resource(name="jdbc/orabiz")
private DataSource ds;
Connection con;
PreparedStatement pstmt = null;


	   

    public void delete() throws  NamingException {  
    	try {
        	con = ds.getConnection();
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //        getUrl(), getUser(), getPass());
        	String query = "DELETE bvtreljer";
            pstmt = con.prepareStatement(query);
            ////System.out.println(query);
            //Antes de insertar verifica si el rol del usuario tiene permisos para insertar
            
            try {
                //Avisando
                pstmt.executeUpdate();
 
                    msj =  getMessage("msnDeletes");
                    claseResult = "exito";
                
                claseResult = "exito";
            } catch (SQLException e) {
                e.printStackTrace();
                msj =   e.getMessage();
                claseResult = "error";
            }

            pstmt.close();
            con.close();      
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    }
    

  
   public void count() throws NamingException {
	   consulta.selectPntGenerica("Select count(*) from bvtreljer", JNDI);
	   String [][] tabla = consulta.getArray();
	   int filas = consulta.getRows();
	   if(filas>0){
	   rows = Integer.parseInt(tabla[0][0]);
	   }
   }


}

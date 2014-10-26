/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openbizview.util;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author andres
 */
public class Menu extends Bd {
//Variables para select
private int columns;
private String[][] arr;
private int rows;

/**
     * Leer opciones de bvtmenu
     *<p>
     *Parámetros del método: String b_codrol
     *<p>
     *Los parámetros se pueden omitir, al dejar en blancos el query asume
     *que buscará todos los registros en la consulta.
     * Fila desde y hasta para paginación, orden de la consulta.
     **/

    public void  selectBvtmenu(String rol,String pool) throws  NamingException {

        //String codpai, String despai
        //Pool de conecciones JNDI
        Context initContext = new InitialContext();
        DataSource ds = (DataSource) initContext.lookup(pool);
        try {
            Statement stmt;
            ResultSet rs;
            Connection con = ds.getConnection();
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //        getUrl(), getUsuario(), getClave());
            stmt = con.createStatement(
               		ResultSet.TYPE_SCROLL_INSENSITIVE,
                     	ResultSet.CONCUR_READ_ONLY);
            //Si los valores son nulos, devuelve todos los resultados
            String query = "";
             query = "SELECT DECODE(CODVIS,'0','block', 'none'), CODVIS"
                    + " FROM BVTMENU"
                    + " WHERE B_CODROL = trim('" + rol.toUpperCase() +  "') ";
            
            //System.out.println(query);
            try{
            rs = stmt.executeQuery(query);
            rows = 1;
		    rs.last();
		    rows = rs.getRow();
            //System.out.println(rows);

            ResultSetMetaData rsmd = rs.getMetaData();
        	columns = rsmd.getColumnCount();
		    //System.out.println(columns);
        	arr = new String[rows][columns];

            int i = 0;
		    rs.beforeFirst();
            while (rs.next()){
                for (int j = 0; j < columns; j++)
				arr [i][j] = rs.getString(j+1);
				i++;
               }
                    } catch (SQLException e) {
                }
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 /**
 * @return Retorna el arreglo
 **/
public String[][] getArray(){
	return arr;
}

/**
 * @return Retorna número de filas
 **/
public int getRows(){
	return rows;
}
/**
 * @return Retorna número de columnas
 **/
public int getColumns(){
	return columns;
}

//public static void main (String [] args) throws NamingException {
//Menu a = new Menu();
//a.selectBvtmenu("ROL2", "");
//}

}

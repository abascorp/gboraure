package org.openbizview.util;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;


@ManagedBean
@SessionScoped
public class Descargas extends Bd {
	
	//Coneccion a base de datos
	//Pool de conecciones JNDI
	Connection con;
	PreparedStatement pstmt = null;

	
	public void link1() throws IOException{
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("http://www.dvconsultores.com/app/OpenBizView_V6.0.zip");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//System.out.println("insertando");
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
		String ip = httpServletRequest.getRemoteAddr();
		System.out.println(ip);
		insert(ip);
	}
	
	public void link2(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("https://drive.google.com/open?id=0B6OjSSfHmS_iVXJOUHV4ZDM3SHc");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//System.out.println("insertando");
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
		String ip = httpServletRequest.getRemoteAddr();
		insert(ip);
	}
	
	
    private void insert(String ip) {   	      		
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);
            con = ds.getConnection();
            
            String query = "INSERT INTO descargas VALUES (now(),1,'"+ip+"')";
            pstmt = con.prepareStatement(query);
            System.out.println(query);
            try {
                //Avisando
            	pstmt.executeUpdate();
             } catch (SQLException e)  {
            }
            
            pstmt.close();
            con.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }	
    }

}

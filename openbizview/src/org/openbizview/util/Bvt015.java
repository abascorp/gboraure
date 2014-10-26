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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.openbizview.getset.Param;
import org.openbizview.util.PntGenerica;

/**
 *
 * @author Andres
 */
	@ManagedBean
	@ViewScoped
	public class Bvt015 extends Bd {
	
		public Bvt015() throws ClassNotFoundException, SQLException, NamingException{
	 		
	        select();
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
		
	private String bcodcia = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cia");
	private String anoper = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("periodo");
	private String bcodesc = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("esc");
	private String opc = "0";
	private String bcodcta = "";
	private String bcodddim = "";
	private String basodim = "";
	private String factor = "";
	private String monto = "";
	private Date mesini = null;
	private String coment = "";
	private String facant = "0";
	private String tipfac = "2";
	private String substrcta = "";
	private String ctaini = "";
	
	private String bcodciaor = "";
	private String anoperor = "";
	private String bcodescor = "";
	private String bcodescodest = "";
	private String bcodciadest = "";
	private String anoperdest = "";
	
	
	private String isDisabledDim = "false";
	private String isDisabledAsoDim = "false";
	private String isDisabledCta = "false";
	private String isDisabledSubstr = "false";
	
	
	//Presupuesto financiero
	private String p_codigo_familia = "";
	private String p_grupo = "";
	private String p_codigo_articulo = "";
	private String p_codneg = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tipneg");
	
	
	//Zonas
	private String ubicacion = "";
	private String estado = "";
	private String zonas = "";
	
	private String factor_precio = "";
	private String monto_precio = "";
	private String factor_volumen = "";
	private String monto_volumen = "";
	private Date fechafin = null;
	
	private List<Param> list = new ArrayList<Param>();
	private List<Param> filtro;
	private int validarOperacion= 0;
	private String exito = "exito";
	
	

	/**
	 * @return the validarOperacion
	 */
	public int getValidarOperacion() {
		return validarOperacion;
	}

	/**
	 * @param validarOperacion the validarOperacion to set
	 */
	public void setValidarOperacion(int validarOperacion) {
		this.validarOperacion = validarOperacion;
	}

	/**
	 * @return the list
	 */
	public List<Param> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<Param> list) {
		this.list = list;
	}

	/**
	 * @return the filtro
	 */
	public List<Param> getFiltro() {
		return filtro;
	}

	/**
	 * @param filtro the filtro to set
	 */
	public void setFiltro(List<Param> filtro) {
		this.filtro = filtro;
	}

	/**
	 * @return the fechafin
	 */
	public Date getFechafin() {
		return fechafin;
	}

	/**
	 * @param fechafin the fechafin to set
	 */
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	/**
	 * @return the factor_precio
	 */
	public String getFactor_precio() {
		return factor_precio;
	}

	/**
	 * @param factor_precio the factor_precio to set
	 */
	public void setFactor_precio(String factor_precio) {
		this.factor_precio = factor_precio;
	}

	/**
	 * @return the monto_precio
	 */
	public String getMonto_precio() {
		return monto_precio;
	}

	/**
	 * @param monto_precio the monto_precio to set
	 */
	public void setMonto_precio(String monto_precio) {
		this.monto_precio = monto_precio;
	}

	/**
	 * @return the factor_volumen
	 */
	public String getFactor_volumen() {
		return factor_volumen;
	}

	/**
	 * @param factor_volumen the factor_volumen to set
	 */
	public void setFactor_volumen(String factor_volumen) {
		this.factor_volumen = factor_volumen;
	}

	/**
	 * @return the monto_volumen
	 */
	public String getMonto_volumen() {
		return monto_volumen;
	}

	/**
	 * @param monto_volumen the monto_volumen to set
	 */
	public void setMonto_volumen(String monto_volumen) {
		this.monto_volumen = monto_volumen;
	}

	/**
	 * @return the p_codneg
	 */
	public String getP_codneg() {
		return p_codneg;
	}

	/**
	 * @param p_codneg the p_codneg to set
	 */
	public void setP_codneg(String p_codneg) {
		this.p_codneg = p_codneg;
	}

	/**
	 * @return the p_codigo_familia
	 */
	public String getP_codigo_familia() {
		return p_codigo_familia;
	}

	/**
	 * @param p_codigo_familia the p_codigo_familia to set
	 */
	public void setP_codigo_familia(String p_codigo_familia) {
		this.p_codigo_familia = p_codigo_familia;
	}

	/**
	 * @return the p_grupo
	 */
	public String getP_grupo() {
		return p_grupo;
	}

	/**
	 * @param p_grupo the p_grupo to set
	 */
	public void setP_grupo(String p_grupo) {
		this.p_grupo = p_grupo;
	}

	/**
	 * @return the p_codigo_articulo
	 */
	public String getP_codigo_articulo() {
		return p_codigo_articulo;
	}

	/**
	 * @param p_codigo_articulo the p_codigo_articulo to set
	 */
	public void setP_codigo_articulo(String p_codigo_articulo) {
		this.p_codigo_articulo = p_codigo_articulo;
	}

	/**
	 * @return the facant
	 */
	public String getFacant() {
		return facant;
	}
	
	/**
	 * @param facant the facant to set
	 */
	public void setFacant(String facant) {
		this.facant = facant;
	}
	
	/**
	 * @return the isDisabledDim
	 */
	public String getIsDisabledDim() {
		return isDisabledDim;
	}
	
	/**
	 * @param isDisabledDim the isDisabledDim to set
	 */
	public void setIsDisabledDim(String isDisabledDim) {
		this.isDisabledDim = isDisabledDim;
	}
	
	/**
	 * @return the isDisabledAsoDim
	 */
	public String getIsDisabledAsoDim() {
		return isDisabledAsoDim;
	}
	
	/**
	 * @param isDisabledAsoDim the isDisabledAsoDim to set
	 */
	public void setIsDisabledAsoDim(String isDisabledAsoDim) {
		this.isDisabledAsoDim = isDisabledAsoDim;
	}
	
	/**
	 * @return the isDisabledCta
	 */
	public String getIsDisabledCta() {
		return isDisabledCta;
	}
	
	/**
	 * @param isDisabledCta the isDisabledCta to set
	 */
	public void setIsDisabledCta(String isDisabledCta) {
		this.isDisabledCta = isDisabledCta;
	}
	
	
	
	/**
	 * @return the isDisabledSubstr
	 */
	public String getIsDisabledSubstr() {
		return isDisabledSubstr;
	}
	
	/**
	 * @param isDisabledSubstr the isDisabledSubstr to set
	 */
	public void setIsDisabledSubstr(String isDisabledSubstr) {
		this.isDisabledSubstr = isDisabledSubstr;
	}
	
	
	
	
	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getZonas() {
		return zonas;
	}

	public void setZonas(String zonas) {
		this.zonas = zonas;
	}

	public void desabilita(){
		if(opc.equals("10")){
			isDisabledDim = "false";
			isDisabledAsoDim = "true";
			isDisabledCta = "true";
		} else if(opc.equals("11")){
			isDisabledDim = "true";
			isDisabledAsoDim = "false";
			isDisabledCta = "true";
		} else if(opc.equals("12")){
			isDisabledDim = "true";
			isDisabledAsoDim = "true";
			isDisabledCta = "false";
		} else {
			isDisabledDim = "true";
			isDisabledAsoDim = "true";
			isDisabledCta = "true";
		}
		
		////System.out.println("isDisabledDim :" + isDisabledDim);
		////System.out.println("isDisabledAsoDim :" + isDisabledAsoDim);
		////System.out.println("isDisabledCta :" + isDisabledCta);
	}
	
	
	/**
	 * @return the anoper
	 */
	public String getAnoper() {
		return anoper;
	}
	
	/**
	 * @param anoper the anoper to set
	 */
	public void setAnoper(String anoper) {
		this.anoper = anoper;
	}
	
	/**
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}
	
	/**
	 * @param monto the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}
	
	/**
	 * @return the opc
	 */
	public String getOpc() {
		return opc;
	}
	
	/**
	 * @param opc the opc to set
	 */
	public void setOpc(String opc) {
		this.opc = opc;
	}
	
	/**
	 * @return the bcodcta
	 */
	public String getBcodcta() {
		return bcodcta;
	}
	
	/**
	 * @param bcodcta the bcodcta to set
	 */
	public void setBcodcta(String bcodcta) {
		this.bcodcta = bcodcta;
	}
	
	/**
	 * @return the bcodddim
	 */
	public String getBcodddim() {
		return bcodddim;
	}
	
	/**
	 * @param bcodddim the bcodddim to set
	 */
	public void setBcodddim(String bcodddim) {
		this.bcodddim = bcodddim;
	}
	
	/**
	 * @return the factor
	 */
	public String getFactor() {
		return factor;
	}
	
	/**
	 * @param factor the factor to set
	 */
	public void setFactor(String factor) {
		this.factor = factor;
	}
	
	
	/**
	 * @return the mesini
	 */
	public Date getMesini() {
		return mesini;
	}
	
	/**
	 * @param mesini the mesini to set
	 */
	public void setMesini(Date mesini) {
		this.mesini = mesini;
	}
	
	/**
	 * @return the coment
	 */
	public String getComent() {
		return coment;
	}
	
	/**
	 * @param coment the coment to set
	 */
	public void setComent(String coment) {
		this.coment = coment;
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
	 * @return the bcodciaor
	 */
	public String getBcodciaor() {
		return bcodciaor;
	}
	
	/**
	 * @param bcodciaor the bcodciaor to set
	 */
	public void setBcodciaor(String bcodciaor) {
		this.bcodciaor = bcodciaor;
	}
	
	/**
	 * @return the anoperor
	 */
	public String getAnoperor() {
		return anoperor;
	}
	
	/**
	 * @param anoperor the anoperor to set
	 */
	public void setAnoperor(String anoperor) {
		this.anoperor = anoperor;
	}
	
	/**
	 * @return the bcodescor
	 */
	public String getBcodescor() {
		return bcodescor;
	}
	
	/**
	 * @param bcodescor the bcodescor to set
	 */
	public void setBcodescor(String bcodescor) {
		this.bcodescor = bcodescor;
	}
	
	/**
	 * @return the bcodciadest
	 */
	public String getBcodciadest() {
		return bcodciadest;
	}
	
	/**
	 * @param bcodciadest the bcodciadest to set
	 */
	public void setBcodciadest(String bcodciadest) {
		this.bcodciadest = bcodciadest;
	}
	
	/**
	 * @return the anoperdest
	 */
	public String getAnoperdest() {
		return anoperdest;
	}
	
	/**
	 * @param anoperdest the anoperdest to set
	 */
	public void setAnoperdest(String anoperdest) {
		this.anoperdest = anoperdest;
	}
	
	
	
	/**
	 * @return the basodim
	 */
	public String getBasodim() {
		return basodim;
	}
	
	/**
	 * @param basodim the basodim to set
	 */
	public void setBasodim(String basodim) {
		this.basodim = basodim;
	}
	
	
	
	
	/**
	 * @return the bcodescodest
	 */
	public String getBcodescodest() {
		return bcodescodest;
	}
	
	/**
	 * @param bcodescodest the bcodescodest to set
	 */
	public void setBcodescodest(String bcodescodest) {
		this.bcodescodest = bcodescodest;
	}
	
	
	
	/**
	 * @return the tipfac
	 */
	public String getTipfac() {
		return tipfac;
	}
	
	/**
	 * @param tipfac the tipfac to set
	 */
	public void setTipfac(String tipfac) {
		this.tipfac = tipfac;
	}
	
	
	
	/**
	 * @return the substrcta
	 */
	public String getSubstrcta() {
		return substrcta;
	}
	
	/**
	 * @param substrcta the substrcta to set
	 */
	public void setSubstrcta(String substrcta) {
		this.substrcta = substrcta;
	}
	
	
	
	/**
	 * @return the ctaini
	 */
	public String getCtaini() {
		return ctaini;
	}
	
	/**
	 * @param ctaini the ctaini to set
	 */
	public void setCtaini(String ctaini) {
		this.ctaini = ctaini;
	}
	
	

/**
	 * @return the exito
	 */
	public String getExito() {
		return exito;
	}

	/**
	 * @param exito the exito to set
	 */
	public void setExito(String exito) {
		this.exito = exito;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Variables seran utilizadas para capturar mensajes de errores de Oracle y parametros de metodos
	FacesMessage msj = null;
	PntGenerica consulta = new PntGenerica();
	boolean vGacc; //Validador de opciones del menú
	private int rows; //Registros de tabla
	private String login = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"); //Usuario logeado
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//Coneccion a base de datos
//Pool de conecciones JNDI
	Connection con;
	PreparedStatement pstmt = null;
	ResultSet r;


	
	private void insertBvt015(String pcodcia) throws  NamingException, SQLException, ParseException {   
		
		if( FN_VAL_FECHAS_PRESU_FECINI(sdfecha.format(mesini))){
			exito = "error";
			msj = new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage("bvt015outRage"), "");
			FacesContext.getCurrentInstance().addMessage(null, msj);
		} else if (FN_VAL_FECHAS_PRESU_FECFIN(sdfecha.format(mesini))){
			exito = "error";
			msj = new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage("bvt015outRage"), "");
			FacesContext.getCurrentInstance().addMessage(null, msj);
		} else {
			
		if(p_codigo_familia==""){
			p_codigo_familia = " - ";
		}
		if(p_codigo_familia==null){
			p_codigo_familia = " - ";
		}
		if(p_codigo_articulo==null){
			p_codigo_articulo = " - ";
		}
		if(p_codigo_articulo==""){
			p_codigo_articulo = " - ";
		}
		
		if(fechafin==null){
			SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
			String dateInString = "31/12/2999";	
			
			fechafin = formatter.parse(dateInString);
		}
		
				
    	//Valida que los campos no sean nulos
		//String[] vecbcodcia = bcodcia.split("\\ - ", -1);		
		String[] vecanoper = anoper.split("\\ - ", -1);
		String[] vecbcodesc = bcodesc.split("\\ - ", -1);
		String[] vecp_codneg = p_codneg.split("\\ - ", -1);
		String[] vecp_codigo_familia = p_codigo_familia.split("\\ - ", -1);
		String[] vecp_codigo_articulo = p_codigo_articulo.split("\\ - ", -1);
		
    		String anio = vecanoper[0].substring(0, 4);
    		String per;
            if(vecanoper[0].length()==6){
            	per = vecanoper[0].substring(4, 6);
            } else {
            	per = vecanoper[0].substring(4, 5);
            }
            
    		
    		BigDecimal[] conver = new BigDecimal[6];
            conver[0] = new BigDecimal(anio);
            conver[1] = new BigDecimal(per);
            conver[2] = new BigDecimal(factor_precio);
            conver[3] = new BigDecimal(monto_precio);
            conver[4] = new BigDecimal(factor_volumen);
            conver[5] = new BigDecimal(monto_volumen);
            
            
    		
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);
     		
     		////System.out.println("COMPAÑIA: " + vecbcodcia[0].toUpperCase());
            ////System.out.println("ESCENARIO: " + vecbcodesc[0].toUpperCase());
            ////System.out.println("AÑO: " + conver[0]);
     		////System.out.println("PERIODO: " + conver[1]);
     		////System.out.println("CODIGO DE NEGOCIO: " + vecp_codneg[0].toUpperCase());
     		////System.out.println("OPC: " + opc);
     		////System.out.println("CODIGO DE FAMILIA: " + vecp_codigo_familia[0].toUpperCase());
     		////System.out.println("GRUPO: " + "");
     		////System.out.println("CODIGO DE ARTICULO: " + vecp_codigo_articulo[0].toUpperCase());
     		////System.out.println("FACTOR PRECIO: " + conver[2]);
     		////System.out.println("MONTO PRECIO: " + conver[3]);
     		////System.out.println("FACTOR VOLUMEN: " + conver[4]);
     		////System.out.println("MONTO VOLUMEN: " + conver[5]);
     		////System.out.println("FECHA INI: " + sdfecha.format(mesini));
     		////System.out.println("FECHA FIN: " + sdfecha.format(fechafin));
     		////System.out.println("COMENTARIO: " + coment);
     		////System.out.println("FACTOR ANTERIOR: " + facant);
     		
     		
     		////System.out.println("UBICACION: " + ubicacion);
            ////System.out.println("ESTADO: " + estado);
            ////System.out.println("ZONAS: " + zonas);

     		con = ds.getConnection();
            //Class.forName(getDriver());
            //con = DriverManager.getConnection(
            //      getUrl(), getUsuario(), getClave());
            String query = "INSERT INTO PFM001 VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, pcodcia.toUpperCase());
            pstmt.setString(2, vecp_codneg[0].toUpperCase());
            pstmt.setBigDecimal(3, conver[0]);
            pstmt.setBigDecimal(4, conver[1]);
            pstmt.setString(5, vecbcodesc[0].toUpperCase());
            pstmt.setString(6, opc);
            pstmt.setString(7, vecp_codigo_familia[0].toUpperCase());
            pstmt.setString(8, "");
            pstmt.setString(9, vecp_codigo_articulo[0].toUpperCase());
            pstmt.setBigDecimal(10, conver[2]);
            pstmt.setBigDecimal(11, conver[3]);
            pstmt.setBigDecimal(12, conver[4]);
            pstmt.setBigDecimal(13, conver[5]);
            pstmt.setString(14, sdfecha.format(mesini));
            pstmt.setString(15, sdfecha.format(fechafin));
            pstmt.setString(16, coment);
            pstmt.setString(17, login);
            pstmt.setString(18, getFecha());
            pstmt.setString(19, login);
            pstmt.setString(20, getFecha());
            pstmt.setString(21, facant);
            pstmt.setString(22, ubicacion);
            pstmt.setString(23, estado);
            pstmt.setString(24, zonas);
            
            
            try {
                //Avisando
            	pstmt.executeUpdate();
                
                //limpiarValores();
                //list.clear();
                //select();
                
             } catch (SQLException e)  {
            	 exito = "error";
            	msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
            	FacesContext.getCurrentInstance().addMessage(null, msj);
            }
            
            pstmt.close();
            con.close();
        } catch (Exception e) {
        }
		}
		
    }
	
	public void insert() throws NamingException, SQLException, ClassNotFoundException, ParseException{
		     
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	String[] chkbox = request.getParameterValues("copiar");
    	////System.out.println("Checkbox:" + chkbox);
    	
    	if(p_codigo_familia==""){
			p_codigo_familia = " - ";
		}
		if(p_codigo_familia==null){
			p_codigo_familia = " - ";
		}
		if(p_codigo_articulo==null){
			p_codigo_articulo = " - ";
		}
		if(p_codigo_articulo==""){
			p_codigo_articulo = " - ";
		}
		String[] vecp_codigo_familia = p_codigo_familia.split("\\ - ", -1);
		String[] vecp_codigo_articulo = p_codigo_articulo.split("\\ - ", -1);
    	
    	if (chkbox==null && bcodcia!=null){
    		if (bcodcia==null){
       		 msj = new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage("bvt011IngCia"), "");      
       		FacesContext.getCurrentInstance().addMessage(null, msj);
       	  } else if(opc.equals("1") && vecp_codigo_familia[0].equals("")){
          	msj = new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage("bvt015FamiliaG"), "");
  			FacesContext.getCurrentInstance().addMessage(null, msj);
          } else if (opc.equals("2") && vecp_codigo_articulo[0].equals("")){
          	msj = new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage("bvt015Producto"), "");
  			FacesContext.getCurrentInstance().addMessage(null, msj);
          } else {
       		
    		String[] vecvalores = bcodcia.split("\\ - ", -1);
    		insertBvt015(vecvalores[0]);
    		if(exito.equals("exito")){
    		msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnInsert"), "");  
            FacesContext.getCurrentInstance().addMessage(null, msj);
    		}
    		limpiarValores();
            list.clear();
            select();      		
       	  }
    	} else {
    		if(opc.equals("1") && vecp_codigo_familia[0].equals("")){
              	msj = new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage("bvt015FamiliaG"), "");
      			FacesContext.getCurrentInstance().addMessage(null, msj);
              } else if (opc.equals("2") && vecp_codigo_articulo[0].equals("")){
              	msj = new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage("bvt015Producto"), "");
      			FacesContext.getCurrentInstance().addMessage(null, msj);
              } else {
    		consulta.selectPntGenerica("select codcia from bvt010", JNDI);
    		int vrows = consulta.getRows();
    		String[][] vtabla = consulta.getArray();
    		////System.out.println("Rows :" + vrows);
    		if(vrows>0){
    			for(int i = 0; i < vrows; i++){
    				////System.out.println("Compañias :" + vtabla[i][0]);
    				insertBvt015(vtabla[i][0]);
    			}
    			if(exito.equals("exito")){
    			msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnInsert"), "");  
                FacesContext.getCurrentInstance().addMessage(null, msj);
    			}
    			limpiarValores();
                list.clear();
                select();
    			
    		}
              }
    	}
    }
    
    
	   /**
	   * Borra Parametros
	   * <p>
	   * Parametros del metodo: String codpai. Pool de conecciones
	   **/
	  public void delete() throws  NamingException {  
		  HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	  	String[] chkbox = request.getParameterValues("toDelete");
	  	
	  	if (chkbox==null){
	  		msj = new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage("del"), "");
	  	} else {
		        try {
	  		Context initContext = new InitialContext();     
	   		DataSource ds = (DataSource) initContext.lookup(JNDI);
	
	   		con = ds.getConnection();
	   		
	   		String param = "'" + StringUtils.join(chkbox, "','") + "'";
	   		
	   		String query = "DELETE pfm001 WHERE P_CODCIA||P_CODNEG||P_ANOCAL||P_NUMPER||P_CODESC||to_char(fecini,'dd/mm/yyyy') in (" + param + ")";
	         pstmt = con.prepareStatement(query);
	          ////System.out.println(query);
	          //Antes de insertar verifica si el rol del usuario tiene permisos para insertar
	          
	          try {
	          	pstmt.executeUpdate();
	              msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("msnDelete"), "");
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
				}
				FacesContext.getCurrentInstance().addMessage(null, msj); 
	 }


  
   private void limpiarValores() {
		 opc = "0";
		 bcodcta = "";
		 bcodddim = "";
		 basodim = "";
		 factor = "";
		 monto = "";
		 mesini = null;
		 coment = "";
		 facant = "";
		 tipfac = "2";
		 substrcta = "";
		 ctaini = "";
		
		 bcodciaor = "";
		 anoperor = "";
		 bcodescor = "";
		 bcodescodest = "";
		 bcodciadest = "";
		 anoperdest = "";
		 
		 ubicacion = "";
		 estado = "";
		 zonas = "";
		
		
		 //isDisabledDim = "false";
		 //isDisabledAsoDim = "false";
		 //isDisabledCta = "false";
		 //isDisabledSubstr = "false";
		
		
		//Presupuesto financiero
		 p_codigo_familia = "";
		 p_grupo = "";
		 p_codigo_articulo = "";
		
		 factor_precio = "";
		 monto_precio = "";
		 factor_volumen = "";
		 monto_volumen = "";
		 fechafin = null;
		
	}

	/**
	   * Actualiza bvt015
	   **/
	   public void update() throws  NamingException {
	
	       try {
	    	   
	    	if(p_codigo_familia==""){
	   			p_codigo_familia = " - ";
	   		}
	   		if(p_codigo_familia==null){
	   			p_codigo_familia = " - ";
	   		}
	   		if(p_codigo_articulo==null){
	   			p_codigo_articulo = " - ";
	   		}
	   		if(p_codigo_articulo==""){
	   			p_codigo_articulo = " - ";
	   		}
	   		
	    	String[] vecbcodcia = bcodcia.split("\\ - ", -1);		
	   		String[] vecanoper = anoper.split("\\ - ", -1);
	   		String[] vecbcodesc = bcodesc.split("\\ - ", -1);
	   		String[] vecp_codneg = p_codneg.split("\\ - ", -1);
	   		String[] vecp_codigo_familia = p_codigo_familia.split("\\ - ", -1);
	   		String[] vecp_codigo_articulo = p_codigo_articulo.split("\\ - ", -1);
	   		
	       		String anio = vecanoper[0].substring(0, 4);
	       		String per;
	               if(vecanoper[0].length()==6){
	               	per = vecanoper[0].substring(4, 6);
	               } else {
	               	per = vecanoper[0].substring(4, 5);
	               }
	               
	       		
	       		BigDecimal[] conver = new BigDecimal[6];
	               conver[0] = new BigDecimal(anio);
	               conver[1] = new BigDecimal(per);
	               conver[2] = new BigDecimal(factor_precio);
	               conver[3] = new BigDecimal(monto_precio);
	               conver[4] = new BigDecimal(factor_volumen);
	               conver[5] = new BigDecimal(monto_volumen);
	               
	      if(opc.equals("1") && vecp_codigo_familia[0].equals("")){
	       	msj = new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage("bvt015FamiliaG"), "");
	      } else if (opc.equals("2") && vecp_codigo_articulo[0].equals("")){
	       	msj = new FacesMessage(FacesMessage.SEVERITY_WARN, getMessage("bvt015Producto"), "");
	      } 
	        
	        Context initContext = new InitialContext();     
	        DataSource ds = (DataSource) initContext.lookup(JNDI);
	
	        con = ds.getConnection();
	        		
	        String query = "UPDATE pfm001";
	         query += " SET   FACTOR_PRECIO = ?";
	         query += " , MONTO_PRECIO = ?";
	         query += " , FACTOR_VOLUMEN = ?";
	         query += " , MONTO_VOLUMEN = ?";
	         query += " , FECFIN = ?";
	         query += " , COMENT = ?";
	         query += " , FACANT = ?";
	         query += " ,  usract = ?";
	         query += " , fecact='" + getFecha() + "'";
	         query += " WHERE P_CODCIA = ?";
	         query += " and P_ANOCAL = ?";
	         query += " and P_NUMPER = ?";
	         query += " and P_CODESC = ?";
	         query += " and P_CODNEG = ?";
	         query += " and FECINI = ?";
	         query += " and OPC = ?";
	         query += " and UBICACION = ?";	 
	         query += " and ESTADO = ?";	 
	         query += " and ZONAS = ?";
	         if(opc.equals("1") && !vecp_codigo_familia[0].equals("") && vecp_codigo_articulo[0].equals("")){
	         query += " and P_CODIGO_FAMILIA = ?";	 
	         }
	         if(opc.equals("3") && vecp_codigo_familia[0].equals("") && !vecp_codigo_articulo[0].equals("")){
		     query += " and P_CODIGO_ARTICULO = ?";	 
		     }
	 
	        ////System.out.println(query);
	        pstmt = con.prepareStatement(query);
	        pstmt.setBigDecimal(1, conver[2]);
	        pstmt.setBigDecimal(2, conver[3]);
	        pstmt.setBigDecimal(3, conver[4]);
	        pstmt.setBigDecimal(4, conver[5]);
	        pstmt.setString(5, sdfecha.format(fechafin));
	        pstmt.setString(6, coment);
	        pstmt.setString(7, facant);
	        pstmt.setString(8, login);
	        pstmt.setString(9, vecbcodcia[0].toUpperCase());
	        pstmt.setBigDecimal(10, conver[0]);
	        pstmt.setBigDecimal(11, conver[1]);
	        pstmt.setString(12, vecbcodesc[0].toUpperCase()); 
	        pstmt.setString(13, vecp_codneg[0].toUpperCase());          
	        pstmt.setString(14, sdfecha.format(mesini));
	        pstmt.setString(15, opc);
	        pstmt.setString(16, ubicacion);
	        pstmt.setString(17, estado);
	        pstmt.setString(18, zonas);
	        if(opc.equals("1") && !vecp_codigo_familia[0].equals("") && vecp_codigo_articulo[0].equals("")){
	        pstmt.setString(19, vecp_codigo_familia[0].toUpperCase());	
	        }
	        if(opc.equals("3") && vecp_codigo_familia[0].equals("") && !vecp_codigo_articulo[0].equals("")){
	        pstmt.setString(19, vecp_codigo_articulo[0].toUpperCase());	 
			}
	
	        try {
	            //Avisando
	            pstmt.executeUpdate();
	            if(pstmt.getUpdateCount()==0){
	            	msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, getMessage("msnNoUpdate"), "");
	            } else {
	            	msj = new FacesMessage(FacesMessage.SEVERITY_INFO,  getMessage("msnUpdate"), "");
	            }
	            limpiarValores();
	            list.clear();
	            select();
	            
	        } catch (SQLException e) {
	        	msj = new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), "");
	        }
	      
	        pstmt.close();
	        con.close();
	    } catch (Exception e) {
	    }
	        
	    FacesContext.getCurrentInstance().addMessage(null, msj);
	}
	 
   
   public void guardar() throws NamingException, SQLException, ClassNotFoundException, ParseException{   	
   	if(validarOperacion==0){
   		insert();
   	} else {
   		update();
   	}
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
 		
		
		if(bcodcia==null){
			bcodcia = " - ";
 		}
 		if(bcodcia==""){
 			bcodcia = " - ";
 		}
 		if(anoper==null){
 			anoper = " - ";
 		}
 		if(anoper==""){
 			anoper = " - ";
 		}
 		
 		if(bcodesc==null){
 			bcodesc = " - ";
 		}
 		if(bcodesc==""){
 			bcodesc = " - ";
 		}
 		if(p_codneg==null){
 			p_codneg = " - ";
 		}
 		if(p_codneg==""){
 			p_codneg = " - ";
 		}
 		if(p_codigo_familia==""){
 			p_codigo_familia = " - ";
 		}
 		if(p_grupo==null){
 			p_grupo = " - ";
 		}
 		if(p_codigo_articulo==""){
 			p_codigo_articulo = " - ";
 		}
 		
 		
 		String[] vecbcodcia = bcodcia.split("\\ - ", -1);		
		String[] vecanoper = anoper.split("\\ - ", -1);
		String[] vecbcodesc = bcodesc.split("\\ - ", -1);
		String[] vecp_codneg = p_codneg.split("\\ - ", -1);
 		
 		String query;
 		//Consulta paginada
 		       query = " SELECT trim(A.P_CODCIA), trim(B.DESCIA), A.P_CODNEG, A.P_ANOCAL, A.P_NUMPER, A.P_CODESC, C.DESESC";
               query += " , A.OPC,  DECODE(A.OPC,'0','TODOS',DECODE(A.OPC,'1','FAMILIA',DECODE(A.OPC,'2','GRUPO DE PRODUCTOS','CODIGO DE PRODUCTO')))";
               query += " , A.P_CODIGO_FAMILIA, A.P_GRUPO, A.P_CODIGO_ARTICULO, A.FACTOR_PRECIO, A.MONTO_PRECIO, A.FACTOR_VOLUMEN, A.MONTO_VOLUMEN";
               query += " , to_char(A.FECINI, 'dd/mm/yyyy'), to_char(A.FECFIN, 'dd/mm/yyyy'), trim(A.COMENT), A.FACANT, TRIM(A.P_CODCIA)";
               query += " , TRIM(A.UBICACION), TRIM(A.ESTADO), TRIM(A.ZONA)";
               query += " FROM PFM001 A, BVT010 B, BVT012 C";
               query += " WHERE  A.P_CODCIA=B.CODCIA";
               query += " AND  A.P_ANOCAL=C.B_ANOCAL";
               query += " AND  A.P_NUMPER=C.B_NUMPER";
               query += " AND  A.P_CODESC=C.CODESC";
               query += " AND  A.P_CODCIA LIKE '" + vecbcodcia[0].toUpperCase() + "%'";
               query += " AND  A.P_ANOCAL||A.P_NUMPER LIKE '" + vecanoper[0].toUpperCase() + "%'";
               query += " AND  A.P_CODNEG LIKE '" + vecp_codneg[0].toUpperCase() + "%'";
               query += " AND  A.P_CODESC LIKE '" + vecbcodesc[0].toUpperCase() + "%'";
               query += " ORDER BY A.P_CODCIA, A.FECINI" ;

       pstmt = con.prepareStatement(query);
       ////System.out.println(query);
 		
       r =  pstmt.executeQuery();
       
       
       while (r.next()){
    	Param select = new Param();
        select.setVcia(r.getString(1));
        select.setVcodciadescia(r.getString(2));
        select.setVcodeng(r.getString(3));
        select.setVbanocal(r.getString(4));
        select.setVbnumper(r.getString(5));
        select.setVbcodesc(r.getString(6));
        select.setVbcodescdesesc(r.getString(7));
        select.setVopc(r.getString(8));
        select.setVopc1(r.getString(9));
        select.setVcodfamilia(r.getString(10));
        select.setVgrupo(r.getString(11));
        select.setVcodigoarticulo(r.getString(12));
        select.setVfactorprecio(r.getString(13));
        select.setVmontoprecio(r.getString(14));
        select.setVfactorcolumen(r.getString(15));
        select.setVmontovolumen(r.getString(16));
        select.setVfecini(r.getString(17));
        select.setVfecfin(r.getString(18));
        select.setVcoment(r.getString(19));
        select.setVfacant(r.getString(20));
        select.setVubicacion(r.getString(22));
        select.setVestado(r.getString(23));
        select.setVzona(r.getString(24));
       	//Agrega la lista
       	list.add(select);
       }
       //Cierra las conecciones
       pstmt.close();
       con.close();
       
			bcodcia = "";
			anoper = "";
			bcodesc = "";
			p_codneg = "";
			p_codigo_familia = "";
			p_grupo = "";
			p_codigo_articulo = "";


 	}
 	
 	private boolean FN_VAL_FECHAS_PRESU_FECINI(String pfecha) throws NamingException, SQLException{
 		boolean bfecha = false;
 		try {	
 			Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();
     		
    		String[] vecanoper = anoper.split("\\ - ", -1);
    		String anio = vecanoper[0].substring(0, 4);
    		String per;
            if(vecanoper[0].length()==6){
            	per = vecanoper[0].substring(4, 6);
            } else {
            	per = vecanoper[0].substring(4, 5);
            }
    		
 	  		//Consulta paginada
 	  		String query = "SELECT PKGPF01.FN_VAL_FECHAS_PRESU_FECINI('" + anio + "','" + per + "','" + pfecha + "') from dual";

 	        
 	        pstmt = con.prepareStatement(query);
 	        ////System.out.println(query);

 	         r =  pstmt.executeQuery();
 	        
 	        
 	        while (r.next()){
 	        	bfecha = Boolean.parseBoolean(r.getString(1));
 	        }
 	     } catch (SQLException e){
 	         e.printStackTrace();    
 	     }
 	        //Cierra las conecciones
 	        pstmt.close();
 	        con.close();
 	        r.close();
 		
 		return bfecha;
 	}
 	
 	private boolean FN_VAL_FECHAS_PRESU_FECFIN(String pfecha) throws NamingException, SQLException{
 		boolean bfecha = false;
 		try {	
 			Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();
     			
    		String[] vecanoper = anoper.split("\\ - ", -1);
    		String anio = vecanoper[0].substring(0, 4);
    		String per;
            if(vecanoper[0].length()==6){
            	per = vecanoper[0].substring(4, 6);
            } else {
            	per = vecanoper[0].substring(4, 5);
            }
    		
 	  		//Consulta paginada
 	  		String query = "SELECT PKGPF01.FN_VAL_FECHAS_PRESU_FECFIN('" + anio + "','" + per + "','" + pfecha + "') from dual";

 	        
 	        pstmt = con.prepareStatement(query);
 	        ////System.out.println(query);

 	         r =  pstmt.executeQuery();
 	        
 	        
 	        while (r.next()){
 	        	bfecha = Boolean.parseBoolean(r.getString(1));
 	        }
 	     } catch (SQLException e){
 	         e.printStackTrace();    
 	     }
 	        //Cierra las conecciones
 	        pstmt.close();
 	        con.close();
 	        r.close();
 		
 		return bfecha;
 	}
   
       
   /**
  	 * @return the rows
  	 */
  	public int getRows() {
  		return rows;
  	}
  	

    /**
     * Ejecuta procedimiento almacenado en la base de datos
     **/
    private void execProcedure(String pcodcia) throws  NamingException {
        try {
        	Context initContext = new InitialContext();     
     		DataSource ds = (DataSource) initContext.lookup(JNDI);

     		con = ds.getConnection();
     		
     		String[] vecanoper = anoper.split("\\ - ", -1);
    		String[] vecbcodesc = bcodesc.split("\\ - ", -1);
    		String[] vecp_codneg = p_codneg.split("\\ - ", -1);
    		
        		String anio = vecanoper[0].substring(0, 4);
        		String per;
                if(vecanoper[0].length()==6){
                	per = vecanoper[0].substring(4, 6);
                } else {
                	per = vecanoper[0].substring(4, 5);
                }

     	   String query = "'" + pcodcia + "','" + vecp_codneg[0] + "'," + anio + "," + per + ",'" + vecbcodesc[0] + "','0',''";
           CallableStatement proc = con.prepareCall("{CALL SPCALPREPF01(" + query + ")}");
           ////System.out.println(query);
            try {
            	////System.out.println("Calculando");
                proc.execute(); //ejecuta 
               // //System.out.println("Culminado");
               } catch (SQLException e) {
                //e.printStackTrace();
            	   exito = "error";
                msj = new FacesMessage(FacesMessage.SEVERITY_FATAL, e.getMessage(), "");
                FacesContext.getCurrentInstance().addMessage(null, msj);
            }
            //
            //proc.close();
            proc.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    
    
    public void exec() throws NamingException, SQLException, ClassNotFoundException, ParseException{
	     
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	String[] chkbox = request.getParameterValues("copiar");
    	////System.out.println("Checkbox:" + chkbox);
    	
    	if (chkbox==null){
       		String[] vecvalores = bcodcia.split("\\ - ", -1);
       		////System.out.println("Compañia :" +vecvalores[0]);
    		execProcedure(vecvalores[0]);
    		if(exito.equals("exito")){
    		msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("bvt016Ok"), "");  
            FacesContext.getCurrentInstance().addMessage(null, msj);
    		}
    		limpiarValores();
            list.clear();
            select();
    	} else {
    		String[] vecanoper = anoper.split("\\ - ", -1);
    		String[] vecbcodesc = bcodesc.split("\\ - ", -1);
    		consulta.selectPntGenerica("select b_codcia from bvt012 where b_anocal||b_numper = '" + vecanoper[0] + "' and codesc = '" + vecbcodesc[0] + "'", JNDI);
    		int vrows = consulta.getRows();
    		String[][] vtabla = consulta.getArray();
    		////System.out.println("Rows :" + vrows);
    		if(vrows>0){
    			for(int i = 0; i < vrows; i++){
    				////System.out.println("Compañias :" + vtabla[i][0]);
    				execProcedure(vtabla[i][0]);
    			}
    			if(exito.equals("exito")){
    			msj = new FacesMessage(FacesMessage.SEVERITY_INFO, getMessage("bvt016Ok"), "");  
                FacesContext.getCurrentInstance().addMessage(null, msj);
    			}
    			limpiarValores();
                list.clear();
                select();
    			
    		}
    	}
    }

}

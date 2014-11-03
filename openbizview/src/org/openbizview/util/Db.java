package org.openbizview.util;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;


@ManagedBean
@ViewScoped
public class Db extends Bd implements Serializable  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() {
	       // ...
	 //Lee cuantos reportes impresos
	   try {
		q1 = "select * from bvt001";
		consulta.selectPntGenerica(q1, JNDI);	
		r1 = consulta.getRows();
		//Lee cuantas tareas
		q2 = "select * from t_programacion";
		consulta.selectPntGenerica(q2, JNDI);
		r2 = consulta.getRows();
		//Lee cuantos usuarios
		q3 = "select * from bvt002";
		consulta.selectPntGenerica(q3, JNDI);
		r3 = consulta.getRows();
		//Lee cuantos reportes impresos
		q4 = "select * from bvt006";
		consulta.selectPntGenerica(q4, JNDI);
		r4 = consulta.getRows();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   toprep();
    }

	//Querys
	private String q1;
	private String q2;
	private String q3;
	private String q4;
	//Chart
	private String chart1;//Query oracle
	private String chart2;//Query postgres
	private String chart3;//Query sqlserver
    protected int rowschart;
    protected String[][] vlTabla;
	//Registros
	private int r1 = 0;
	private int r2 = 0;
	private int r3 = 0;
	private int r4 = 0;
	PntGenerica consulta = new PntGenerica();
	/**
	 * @return the r1
	 */
	public int getR1() {
		return r1;
	}
	/**
	 * @param r1 the r1 to set
	 */
	public void setR1(int r1) {
		this.r1 = r1;
	}
	/**
	 * @return the r2
	 */
	public int getR2() {
		return r2;
	}
	/**
	 * @param r2 the r2 to set
	 */
	public void setR2(int r2) {
		this.r2 = r2;
	}
	/**
	 * @return the r3
	 */
	public int getR3() {
		return r3;
	}
	/**
	 * @param r3 the r3 to set
	 */
	public void setR3(int r3) {
		this.r3 = r3;
	}
	/**
	 * @return the r4
	 */
	public int getR4() {
		return r4;
	}
	/**
	 * @param r4 the r4 to set
	 */
	public void setR4(int r4) {
		this.r4 = r4;
	}
	
	/**
	 * Tope de los reportes impresos para graficar
	 */
	public void toprep(){

		chart1 = "select * from ( select query.*, rownum as rn from ( select count(b_codrep), b_codrep from bvt006 group by b_codrep order by 1 desc) query ) where rownum <= 5 and rn > (0)";
		chart2 = "select count(b_codrep), b_codrep from bvt006 group by b_codrep order by 1 desc LIMIT 5 OFFSET 0";
		chart3 = "SELECT ROW_NUMBER() OVER (ORDER BY B_CODREP ASC) AS ROW_NUM, B_CODREP FROM BVT006 HAVING ROW_NUMBER() OVER (ORDER BY B_CODREP ASC) <= 5 AND ROW_NUMBER() OVER (ORDER BY B_CODREP ASC) > 0 ORDER BY 1 DESC";
		try {
			consulta.selectPntGenericaMDB(chart1, chart2, chart3, JNDI);		
			rowschart = consulta.getRows();
			vlTabla = consulta.getArray();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Retorna el primer reporte impreso
	 */
	public String get1Rep(){
		String rep = "";
		if(rowschart<1){
			rep = "0";
		} else if(rowschart>0){
			rep = vlTabla[0][0];
		}
		return rep;
	}
	
	/**
	 * Retorna el segundo reporte impreso
	 */
	public String get2Rep(){
		String rep = "";
		if(rowschart<2){
			rep = "0";
		} else if(rowschart>0){
			rep = vlTabla[1][0];
		}
		return rep;
	}
	
	/**
	 * Retorna el tercer reporte impreso
	 */
	public String get3Rep(){
		String rep = "";
		if(rowschart<3){
			rep = "0";
		} else if(rowschart>0){
			rep = vlTabla[2][0];
		}
		return rep;
	}
	
	/**
	 * Retorna el cuarto reporte impreso
	 */
	public String get4Rep(){
		String rep = "";
		if(rowschart<4){
			rep = "0";
		} else if(rowschart>0){
			rep = vlTabla[3][0];
		}
		return rep;
	}
	
	/**
	 * Retorna el quinto reporte impreso
	 */
	public String get5Rep(){
		String rep = "";
		if(rowschart<5){
			rep = "0";
		} else if(rowschart>0){
			rep = vlTabla[4][0];
		}
		return rep;
	}
	
	//Descripciones de los reportes
	
	
	/**
	 * Retorna el primer reporte impreso
	 */
	public String get1RepDesc(){
		String rep = "";
		if(rowschart<1){
			rep = "N/A";
		} else if(rowschart>0){
			rep = vlTabla[0][1];
		}
		return rep;
	}
	
	/**
	 * Retorna el segundo reporte impreso
	 */
	public String get2RepDesc(){
		String rep = "";
		if(rowschart<2){
			rep = "N/A";
		} else if(rowschart>0){
			rep = vlTabla[1][1];
		}
		return rep;
	}
	
	/**
	 * Retorna el tercer reporte impreso
	 */
	public String get3RepDesc(){
		String rep = "";
		if(rowschart<3){
			rep = "N/A";
		} else if(rowschart>0){
			rep = vlTabla[2][1];
		}
		return rep;
	}
	
	/**
	 * Retorna el cuarto reporte impreso
	 */
	public String get4RepDesc(){
		String rep = "";
		if(rowschart<4){
			rep = "N/A";
		} else if(rowschart>0){
			rep = vlTabla[3][1];
		}
		return rep;
	}
	
	/**
	 * Retorna el quinto reporte impreso
	 */
	public String get5RepDesc(){
		String rep = "";
		if(rowschart<5){
			rep = "N/A";
		} else if(rowschart>0){
			rep = vlTabla[4][1];
		}
		return rep;
	}

}

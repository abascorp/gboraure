package org.openbizview.util;

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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="countBean")
@SessionScoped
public class Counter {
	 int myCount;
	 
	    /**
	     * Construct a counter whose value is zero.
	     */
	    public Counter()
	    {
		myCount = 0;
	    }

	    /**
	     * Construct a counter with given initial value.
	     * @param init is the initial value of the counter
	     */

	    public Counter(int init)
	    {
		myCount = init;
	    }

	    /**
	     * Returns the value of the counter.
	     * @return the value of the counter
	     */
	    public int getValue()
	    {
		return myCount;
	    }
	    
	    
		/**
		 * @param myCount the myCount to set
		 */
		public void setMyCount(int myCount) {
			this.myCount = myCount;
		}

		/**
	     * Zeros the counter so getValue() == 0.
	     */
	    public void clear()
	    {
		myCount = 1;
	    }

	    /**
	     * Increase the value of the counter by one.
	     */
	    public void increment(int totalPag) {
	  	//Valida que el contador no sobrepase el total de las páginas
	  	  if(myCount >= totalPag){
	  		myCount = totalPag;
	  	  } else {
		    myCount++;
	  	  }	
	    }
	    
	    /**
	     * Decrease the value of the counter by one.
	     */
	    public void decrement() {
	    //Valida que el contador no sea menor a cero(0)	
	    	if(myCount <= 1){
	  			myCount = 1;
	    	} else {	
		        myCount--;
	    	}
	    }

	    /**
	     * Return a string representing the value of this counter.
	     * @return a string representation of the value
	     */
	    
	    public String toString()
	    {
		return ""+myCount;
	    }
}

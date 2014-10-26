/*
 *  Copyright (C) 2011  ANDRES DOMINGUEZ, CHRISTIAN DÍAZ

    Este programa es software libre: usted puede redistribuirlo y/o modificarlo 
    bajo los términos de la Licencia Pública General GNU publicada 
    por la Fundación para el Software Libre, ya sea la versión 3 
    de la Licencia, o (a su elección) cualquier versión posterior.

    Este programa se distribuye con la esperanza de que sea útil, pero 
    SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita 
    MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO. 
    Consulte los detalles de la Licencia Pública General GNU para obtener 
    una información más detallada. 

    Debería haber recibido una copia de la Licencia Pública General GNU 
    junto a este programa. 
    En caso contrario, consulte <http://www.gnu.org/licenses/>.
 */


	function limpiar()
	{  //Llamado por el boton limpiar
	    limpiarInput('formbvt016:cia');
	    limpiarInput('formbvt016:anio');
	    limpiarInput('formbvt016:numper');
	    limpiarInput('formbvt016:codesc');
	    document.getElementById("formbvt016:opc").value= "0";
	
	}
	
	function enviar(vT0,vT1,vT2,vT3){
		  document.getElementById("formbvt016:cia").value= rTrim(vT0);
		  document.getElementById("formbvt016:anio").value= rTrim(vT1);
		  document.getElementById("formbvt016:numper").value= rTrim(vT2);
		  document.getElementById("formbvt016:codesc").value= rTrim(vT3);
		}


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

	clearUpdateInput('formBvt015:tabView:cia_input', 'white');
	clearUpdateInput('formBvt015:tabView:per_input', 'white');
	clearUpdateInput('formBvt015:tabView:esc_input', 'white');
	clearUpdateInput('formBvt015:tabView:codneg_input', 'white');
}


function enviar(vT0,vT1,vT2,vT3,vT4,vT5,vT6,vT7,vT8,vT9,vT10,vT11,vT12,vT13,vT14,vT15,vT16,vT17,vT18){
	  document.getElementById("formBvt015:tabView:cia_input").value= rTrim(vT0);
	  document.getElementById("formBvt015:tabView:per_input").value= rTrim(vT1);
	  document.getElementById("formBvt015:tabView:esc_input").value= rTrim(vT2);
	  document.getElementById("formBvt015:tabView:codneg_input").value= rTrim(vT3);
	  //
	  document.getElementById("formBvt015:tabView:opc").value= rTrim(vT4);
	  document.getElementById("formBvt015:tabView:familia_input").value= rTrim(vT5);
	  //document.getElementById("formBvt015:tabView:grupo_input").value= rTrim(vT6);
	  document.getElementById("formBvt015:tabView:articulo_input").value= rTrim(vT6);
	  //
	  document.getElementById("formBvt015:tabView:factorPrecio").value= rTrim(vT7);
	  document.getElementById("formBvt015:tabView:montoPrecio").value= rTrim(vT8);
	  document.getElementById("formBvt015:tabView:factorVolumen").value= rTrim(vT9);
	  document.getElementById("formBvt015:tabView:montoVolumen").value= rTrim(vT10);
	  document.getElementById("formBvt015:tabView:ini_input").value= rTrim(vT11);
	  document.getElementById("formBvt015:tabView:fin_input").value= rTrim(vT12);
	  document.getElementById("formBvt015:tabView:facant").value= rTrim(vT13);
	  //
	  document.getElementById("formBvt015:tabView:coment").value= rTrim(vT14);
	  
	  document.getElementById("formBvt015:vop").value= rTrim(vT15);
	  //
	  document.getElementById("formBvt015:tabView:ubicacion_input").value= rTrim(vT16);
	  document.getElementById("formBvt015:tabView:estado_input").value= rTrim(vT17);
	  document.getElementById("formBvt015:tabView:zona_input").value= rTrim(vT18);

	  
	  updateInput('formBvt015:tabView:cia_input', '#F2F2F2');
	  updateInput('formBvt015:tabView:per_input', '#F2F2F2');
	  updateInput('formBvt015:tabView:esc_input', '#F2F2F2');
	  updateInput('formBvt015:tabView:codneg_input', '#F2F2F2');
	}


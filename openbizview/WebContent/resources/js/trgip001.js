/*
 *  Copyright (C) 2011  ANDRES DOMINGUEZ, CHRISTIAN DÍAZ

    Este programa es software libre: usted puede redistribuirlo y/o modificarlo 
    bajo los términos de la Licencia Pública General GNU publicada 
    por la Fundación para el Software Libre, ya sea la versión 3 
    de la Licencia, o (a su elección) cualquier versión posterior.

    Este programa se distribuye con la esmesproanza de que sea útil, mesproo 
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
	limpiarInput('formTrgip001:anopro');
    limpiarInput('formTrgip001:mespro');
    limpiarInput('formTrgip001:anocon');
    limpiarInput('formTrgip001:mescon');
    limpiarInput('formTrgip001:semcon');
    limpiarInput('formTrgip001:ini_input');
    limpiarInput('formTrgip001:fin_input');
}


function enviar(vT0,vT1,vT2,vT3,vT4,vT5,vT6,vT7){
	  document.getElementById("formTrgip001:anopro").value= rTrim(vT0);
	  document.getElementById("formTrgip001:mespro").value= rTrim(vT1);
	  document.getElementById("formTrgip001:anocon").value= rTrim(vT2);
	  document.getElementById("formTrgip001:mescon").value= rTrim(vT3);
	  document.getElementById("formTrgip001:semcon").value= rTrim(vT4);
	  document.getElementById("formTrgip001:ini_input").value= rTrim(vT5);
	  document.getElementById("formTrgip001:fin_input").value= rTrim(vT6);
	  document.getElementById("formTrgip001:vop").value= rTrim(vT7);
	}





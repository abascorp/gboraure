/*
 *  Copyright (C) 2011  DVCONSULTORES,

    Este programa es software libre: usted puede redistribuirlo y/o modificarlo 
    bajo los términos de la Licencia Pública General GNU publicada 
    por la Fundación para el Software Libre, ya sea la versión 3 
    de la Licencia, o (a su elección) cualquier versión posterior.

    Este programa se distribuye con la esperanza de que sea útil, pero 
    SIN GARANTÍA ALGUNA; ni siquiera la garantía implícita 
    MERCANTIL o de APTITUD PARA UN PROPÓSITO DETERMINADO. 
    Consulte los detaloles de la Licencia Pública General GNU para obtener 
    una información más detallada. 

    Debería haber recibido una copia de la Licencia Pública General GNU 
    junto a este programa. 
    En caso contrario, consulte <http://www.gnu.org/licenses/>.
 */

function enviar(vT0,vT1,vT2,vT3,vT4,vT5,vT6,vT7,vT8,vT9,vT10,vT11){
	  if(vT5=='0'){
		  vT5='2';
	  }
	  if(vT10=='0'){
		  vT10='1';
	  }
	  document.getElementById("formmailconfig:tabView:tarea").value= rTrim(vT0);
	  document.getElementById("formmailconfig:tabView:prg").value= rTrim(vT1);
	  document.getElementById("formmailconfig:tabView:hora").value= rTrim(vT2);
	  document.getElementById("formmailconfig:tabView:minutos").value= rTrim(vT3);
	  document.getElementById("formmailconfig:tabView:frecuencia").value= rTrim(vT4);
	  document.getElementById("formmailconfig:tabView:dias").value= rTrim(vT5);
	  document.getElementById("formmailconfig:tabView:reporte_input").value= rTrim(vT6);
	  document.getElementById("formmailconfig:tabView:idgrupo_input").value= rTrim(vT7);
	  document.getElementById("formmailconfig:tabView:asunto").value= vT8;
	  document.getElementById("formmailconfig:tabView:contenido_input").value= vT9;
	  document.getElementById("formmailconfig:tabView:diames").value= rTrim(vT10);
	  document.getElementById("formmailconfig:tabView:ini_input").value= rTrim(vT11);	
	  document.getElementById("formmailconfig:ruta").value = "NA";
	}

    function detalle(vT0,vT1,vT2,vT3,vT4,vT5,vT6,vT7){
    	$("#txt_det_1").text(vT0);
    	$("#txt_det_2").text(vT1);
    	$("#txt_det_3").text(vT2);
    	$("#txt_det_4").text(vT3);
    	$("#txt_det_5").text(vT4);
    	$("#txt_det_6").text(vT5);
    	$("#txt_det_7").text(vT6); 
    	$("#txt_det_8").text(vT7); 
    }

	function controlSwitch(){
		$('.chkActiva').prop('checked', true);
		$('.chkActiva').prop('chkInactiva', true);
		$("[name='activa']").bootstrapSwitch('size', 'mini');
		$("[name='activa']").bootstrapSwitch('onColor', 'success');
		$("[name='activa']").bootstrapSwitch('offColor', 'danger');
		$("[name='activa']").bootstrapSwitch('onText', '<i class="fa fa-thumbs-o-up fa-2x"></i>');
		$("[name='activa']").bootstrapSwitch('offText', '<i class="fa fa-thumbs-down fa-2x"></i>');		
	}
	
	function inputs(){
		var opt = document.getElementById("formmailconfig:tabView:opciones").value;
		if(opt=='1'){
			document.getElementById("formmailconfig:ruta").value = "NA";
			document.getElementById("formmailconfig:tabView:asunto").value = "";
			document.getElementById("formmailconfig:tabView:contenido_input").value= "";
		} 
		else if(opt=='2'){
			document.getElementById("formmailconfig:tabView:asunto").value = "NA";
			document.getElementById("formmailconfig:tabView:contenido_input").value= "NA";
			document.getElementById("formmailconfig:ruta").value = "";
		} else if(opt=='3'){
			document.getElementById("formmailconfig:ruta").value = "NA";
			document.getElementById("formmailconfig:tabView:asunto").value = "";
			document.getElementById("formmailconfig:tabView:contenido_input").value= "";
		}
	}
	
	function loadT(){
		document.getElementById("formmailconfig:ruta").value = "NA";
	}

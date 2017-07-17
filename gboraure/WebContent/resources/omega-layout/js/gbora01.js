	/*
	 *  Copyright (C) 2017  MAURICIO ALBANESE
	
	    Este programa es software libre: usted puede redistribuirlo y/o modificarlo 
	    bajo los tÃ©rminos de la Licencia PÃºblica General GNU publicada 
	    por la FundaciÃ³n para el Software Libre, ya sea la versiÃ³n 3 
	    de la Licencia, o (a su elecciÃ³n) cualquier versiÃ³n posterior.
	
	    Este programa se distribuye con la esperanza de que sea Ãºtil, pero 
	    SIN GARANTÃ�A ALGUNA; ni siquiera la garantÃ­a implÃ­cita 
	    MERCANTIL o de APTITUD PARA UN PROPÃ“SITO DETERMINADO. 
	    Consulte los detalles de la Licencia PÃºblica General GNU para obtener 
	    una informaciÃ³n mÃ¡s detallada. 
	
	    DeberÃ­a haber recibido una copia de la Licencia PÃºblica General GNU 
	    junto a este programa. 
	    En caso contrario, consulte <http://www.gnu.org/licenses/>.
	 */
	
	function limpiar()
	{  //Llamado por el boton limpiar
	    document.getElementById("formgbora01:vop").value="0";
	    clearUpdateInput('formgbora01:codgra', 'white');
	}

	function enviar(vT0,vT1,vT2,vT3,vT4,vT5,vT6,vT7,vT8){
		  document.getElementById("formgbora01:codgra").value= rTrim(vT0);
		  document.getElementById("formgbora01:desgra").value= rTrim(vT1);
		  document.getElementById("formgbora01:numrif").value= rTrim(vT2);
		  document.getElementById("formgbora01:numtel").value= rTrim(vT3);
		  document.getElementById("formgbora01:direc1").value= rTrim(vT4);
		  document.getElementById("formgbora01:direc2").value= rTrim(vT5);
		  document.getElementById("formgbora01:paigra").value= rTrim(vT6);
		  document.getElementById("formgbora01:edogra").value= rTrim(vT7);
		  document.getElementById("formgbora01:vop").value= rTrim(vT8);
		  updateInput('formgbora01:codgra', '#F2F2F2');
		}
	
	function detalle(vT0,vT1,vT2,vT3,vT4,vT5){
		$("#txt_det_1").text(vT0);
		$("#txt_det_2").text(vT1);
		$("#txt_det_3").text(vT2);
		$("#txt_det_4").text(vT3);
		$("#txt_det_5").text(vT4);
		$("#txt_det_6").text(vT5);
	}


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
	    document.getElementById("formgbora04:vop").value="0";
	    clearUpdateInput('formgbora04:codgra_input', 'white');
	    clearUpdateInput('formgbora04:codsec_input', 'white');
	    clearUpdateInput('formgbora04:codlot_input', 'white');
	    clearUpdateInput('formgbora04:codtab', 'white');
	}

	function enviar(vT0,vT1,vT2,vT3,vT4,vT5){
		  document.getElementById("formgbora04:codgra_input").value= rTrim(vT0);
		  document.getElementById("formgbora04:codsec_input").value= rTrim(vT1);
		  document.getElementById("formgbora04:codlot_input").value= rTrim(vT2);
		  document.getElementById("formgbora04:codtab").value= rTrim(vT3);
		  document.getElementById("formgbora04:destab").value= rTrim(vT4);
		  document.getElementById("formgbora04:vop").value= rTrim(vT5);
		  updateInput('formgbora04:codgra_input', '#F2F2F2');
		  updateInput('formgbora04:codsec_input', '#F2F2F2');
		  updateInput('formgbora04:codlot_input', '#F2F2F2');
		  updateInput('formgbora04:codtab', '#F2F2F2');
		}

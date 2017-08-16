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
	    document.getElementById("formgbora05:vop").value="0";
	    clearUpdateInput('formgbora05:codsec_input', 'white');
	}

	function enviarl(vT0,vT1,vT2,vT3){
		  document.getElementById("formgbora06l:fecarr_input").value= rTrim(vT0);
		  document.getElementById("formgbora06l:codsecl_input").value= rTrim(vT1);
		  document.getElementById("formgbora06l:kilos").value= rTrim(vT2);
		  document.getElementById("formgbora06l:vopl").value= rTrim(vT3);
		  updateInput('formgbora06l:fecarr_input', '#F2F2F2');
		  updateInput('formgbora06l:codsecl_input', '#F2F2F2');
		}
	
	function enviarr(vT0,vT1,vT2,vT3,vT4){
		  document.getElementById("formgbora06r:fecmue_input").value= rTrim(vT0);
		  document.getElementById("formgbora06r:codsecr_input").value= rTrim(vT1);
		  document.getElementById("formgbora06r:codtab_input").value= rTrim(vT2);
		  document.getElementById("formgbora06r:rendimiento").value= rTrim(vT3);
		  document.getElementById("formgbora06r:vopr").value= rTrim(vT4);
		  updateInput('formgbora06r:fecmue_input', '#F2F2F2');
		  updateInput('formgbora06r:codsecr_input', '#F2F2F2');
		  updateInput('formgbora06r:codtab_input', '#F2F2F2');
		}
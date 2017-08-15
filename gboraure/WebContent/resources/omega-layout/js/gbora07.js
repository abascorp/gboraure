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
	    document.getElementById("formgbora07:vop").value="0";
	    clearUpdateInput('formgbora07:codsec_input', 'white');
	    clearUpdateInput('formgbora07:codtab_input', 'white');
	}

	function enviar(vT0,vT1,vT2,vT3,vT4,vT5,vT6,vT7,vT8,vT9,vT10,vT11,vT12,vT13){
		  document.getElementById("formgbora07:fecnot_input").value= rTrim(vT0);
		  document.getElementById("formgbora07:codsec_input").value= rTrim(vT1);
		  document.getElementById("formgbora07:codtab_input").value= rTrim(vT2);
		  document.getElementById("formgbora07:boleto").value= rTrim(vT3);
		  document.getElementById("formgbora07:remesa").value= rTrim(vT4);
		  document.getElementById("formgbora07:cosechadora").value= rTrim(vT5);
		  document.getElementById("formgbora07:acarreo").value= rTrim(vT6);
		  document.getElementById("formgbora07:cana").value= rTrim(vT7);
		  document.getElementById("formgbora07:rendimiento").value= rTrim(vT8);
		  document.getElementById("formgbora07:ttp").value= rTrim(vT9);
		  document.getElementById("formgbora07:fecrea_input").value= rTrim(vT10);
		  document.getElementById("formgbora07:placa").value= rTrim(vT11);
		  document.getElementById("formgbora07:chofer").value= rTrim(vT12);
		  document.getElementById("formgbora07:vop").value= rTrim(vT13);
		  updateInput('formgbora07:codsec_input', '#F2F2F2');
		  updateInput('formgbora07:codtab_input', '#F2F2F2');
		}
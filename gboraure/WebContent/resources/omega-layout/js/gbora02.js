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
	    document.getElementById("formgbora02:vop").value="0";
	    //clearUpdateInput('formgbora02:codgra_input', 'white');
	    clearUpdateInput('formgbora02:codsec', 'white');
	}

	function enviar(vT0,vT1,vT2){
		//alert("Valor: " + vT0);
		  //document.getElementById("formgbora02:codgra_input").value= rTrim(vT0);
		  document.getElementById("formgbora02:codsec").value= rTrim(vT0);
		  document.getElementById("formgbora02:dessec").value= rTrim(vT1);
		  document.getElementById("formgbora02:vop").value= rTrim(vT2);
		  //updateInput('formgbora02:codgra_input', '#F2F2F2');
		  updateInput('formgbora02:codsec', '#F2F2F2');
		}
	
	//SE USAN PARA PODER AGREGAR EL VALOR POR DEFECTO A LOS AUTOCOMPLETES DE LAS GRANJAS
	//Y SU EJECUCION AUTOMATICA AL INICIAR CADA CLASE QUE REQUIERA
/*	
	function autogranja(){
		valor = document.getElementById("formgbora02:test").value
		//alert("Valor: " + valor.substring(1, 23));
		  document.getElementById("formgbora02:codgra_input").value = valor.substring(1, 50);
		}
	
	$(window).on("load", autogranja);
	*/
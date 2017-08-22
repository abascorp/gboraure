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
	    document.getElementById("formgbora10:vop").value="0";
	    //clearUpdateInput('formgbora10:codgra_input', 'white');
	    clearUpdateInput('formgbora10:codsec_input', 'white');
	    clearUpdateInput('formgbora10:codpoz_input', 'white');
	    clearUpdateInput('formgbora10:fecafo_input', 'white');
	}

	function enviar(vT0,vT1,vT2,vT3,vT4,vT5,vT6){
		  //document.getElementById("formgbora10:codgra_input").value= rTrim(vT0);
		  document.getElementById("formgbora10:codsec_input").value= rTrim(vT0);
		  document.getElementById("formgbora10:codpoz_input").value= rTrim(vT1);
		  document.getElementById("formgbora10:estatus").value= rTrim(vT2);
		  document.getElementById("formgbora10:qls").value= rTrim(vT3);
		  document.getElementById("formgbora10:fecafo_input").value= rTrim(vT4);
		  document.getElementById("formgbora10:observ").value= rTrim(vT5);
		  document.getElementById("formgbora10:vop").value= rTrim(vT6);
		  //updateInput('formgbora10:codgra_input', '#F2F2F2');
		  updateInput('formgbora10:codsec_input', '#F2F2F2');
		  updateInput('formgbora10:codpoz_input', '#F2F2F2');
		  updateInput('formgbora10:fecafo_input', '#F2F2F2');
		}
	
	function imprimir(){
		  // Si el mensaje que retorna es acceso
		//alert("llame al metodo");
		    window.open('../ct/reportes.jsp?reporte=GBORA10.rptdesign');
		}

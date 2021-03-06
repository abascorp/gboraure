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
	    //clearUpdateInput('formgbora05:codgra_input', 'white');
	    clearUpdateInput('formgbora05:codsec_input', 'white');
	}

	function enviar(vT0,vT1,vT2,vT3){
		  //document.getElementById("formgbora05:codgra_input").value= rTrim(vT0);
		  document.getElementById("formgbora05:codsec_input").value= rTrim(vT0);
		  document.getElementById("formgbora05:fecreg_input").value= rTrim(vT1);
		  document.getElementById("formgbora05:canmil").value= rTrim(vT2);
		  document.getElementById("formgbora05:vop").value= rTrim(vT3);
		  //updateInput('formgbora05:codgra_input', '#F2F2F2');
		  updateInput('formgbora05:codsec_input', '#F2F2F2');
		  updateInput('formgbora05:fecreg_input', '#F2F2F2');
		}

	function imprimir(){
		  // Si el mensaje que retorna es acceso
		//alert("llame al metodo");
		    window.open('../ct/reportes.jsp?reporte=GBORA05.rptdesign');
		}
	
	//Muestra la hora del lado del cliente
	function displayTime()
	   {
	       var localTime = new Date();
	       var month= localTime.getMonth() +1; 
	       var cadena = month.toString();
	       var hora = localTime.getHours();
	       var minutos = localTime.getMinutes();
	      
	       if(cadena.length==1){
	       	month = '0'+month;
	       }
	       if(hora.length==1){
	    	   hora = '0'+hora;
	          }
	       if(minutos.length==1){
	    	   minutos = '0'+minutos;
	          } 
	       
	       var fechaHora = localTime.getDate()+"/"+month+"/"+localTime.getFullYear()+" "+formatAMPM(localTime);
	     return fechaHora;
	   } 
	
	function formatAMPM(date) {
		  var hours = date.getHours();
		  var minutes = date.getMinutes();
		  var segundos = date.getSeconds();
		  var ampm = hours >= 12 ? 'PM' : 'AM';
		  hours = hours % 12;
		  hours = hours ? hours : 12; // the hour '0' should be '12'
		  minutes = minutes < 10 ? '0'+minutes : minutes;
		  var strTime = hours + ':' + minutes + ':' + segundos + ' ' + ampm;
		  return strTime;
		}
	
	function log(){	
		
		document.getElementById("formsgc004:comp").value= "1";
		document.getElementById("formsgc004:area").value= "1";
		document.getElementById("formsgc004:desc").value= "1";
	
	    setTimeout("document.getElementById('formsgc004:comp').value=''",300);
	   	setTimeout("document.getElementById('formsgc004:area').value=''",300);  
	   	setTimeout("document.getElementById('formsgc004:desc').value=''",300);  
	   	
	}
	
	
	function detalle(vT0,vT1,vT2,vT3){
		$("#txt_det_1").text(vT0);
		$("#txt_det_2").text(vT1);
		$("#txt_det_3").text(vT2);
		$("#txt_det_4").text(vT3);
	}
	
	function modal(vT0){
		if(vT0=="1"){
		$( document ).ready( function() {
		    $( '#myModal' ).modal( 'toggle' );
		});
	}
	}
	
	
	function dismissModal(){
		$( document ).ready( function() {
		    $( '#myModal' ).modal( 'hide' );
		});
	}
	
	
	
		//Morris charts snippet - js
		function chart(vT0,vT1,vT2,vT3,vT4,vT5,vT6,vT7,vT8,vT9){	
			
		$.getScript('../resources/omega-layout/js/raphael.min.js',function(){
		$.getScript('../resources/omega-layout/js/morris.min.js',function(){     
			Morris.Bar({
		        element: 'morris-bar-chart',
		        data: [{
		            report: vT5,
		            geekbench: vT0
		        }, {
		        	report: vT6,
		            geekbench: vT1
		        }, {
		        	report: vT7,
		            geekbench: vT2
		        }, {
		        	report: vT8,
		            geekbench: vT3
		        }, {
		        	report: vT9,
		            geekbench: vT4
		        }],
		        xkey: 'report',
		        ykeys: ['geekbench'],
		        labels: ['Total'],
		        barRatio: 0.4,
		        xLabelAngle: 35,
		        hideHover: 'auto',
		        resize: true
		    });
		
		});
		});}

	/*
	 * Funciones estandart que pueden ser utilizadas en todas las páginas
	 * 
	 */
	/*
	 *  Copyright (C) 2011 - 2016  DVCONSULTORES
	
	  Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	    http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
	 */
	
	//Crea el foco en el input usuario, al cargar la página
	function inicio(){
	    	document.getElementById("loginform:usuario").focus();
	}
	
	
	/******************************************************************************
	 ******************************************************************************/
	
	function lTrim(sStr){
	    while (sStr.charAt(0) == " ")
	        sStr = sStr.substr(1, sStr.length - 1);
	    return sStr;
	}
	
	/******************************************************************************
	  *********************Click para acceso directo en lista de valores************
	 ******************************************************************************/
	function fm_mlista(vtId)
	{
	    document.getElementById(vtId).value='%';
	    document.getElementById(vtId).focus();   
	        $('#'+vtId).keydown();
	
	}
	
	
	function info(){
	    // Si el mensaje que retorna es acceso
	    window.open('../ct/acercade.jsp','target_blank','height=190,width=550','top=100,resizable=false,scrollbars=no,toolbar=no,status=no');
	
	}
	/******************************************************************************
	  *********************Funciones genéricas************
	 ******************************************************************************/
	
	function rTrim(sStr){
	    while (sStr.charAt(sStr.length - 1) == " ")
	        sStr = sStr.substr(0, sStr.length - 1);
	    return sStr;
	}
	
	function fm_check(vTcheck){
	
		var chkBoxes = $('input[name='+vTcheck+']');
	    chkBoxes.prop("checked", !chkBoxes.prop("checked"));
	}
	
	function cursor(){ 
	    document.body.style.cursor = "pointer"; 
	} 
	
	function uncursor(){ 
	    document.body.style.cursor = ""; 
	} 
	
	
	function updateInput(vinputid, vcolor){
		document.getElementById(vinputid).style.backgroundColor = vcolor;
		document.getElementById(vinputid).readOnly = true;
	}
	
	function clearUpdateInput(vinputid, vcolor){
		document.getElementById(vinputid).style.backgroundColor = vcolor;
		document.getElementById(vinputid).readOnly = false;
	}
	
	
	function HideUpload(){
		jQuery('#upload').hide("linear"); //oculto mediante id
	}
	
	
	///Menus
	
	function mnuBas(){
		jQuery('.mnuBas').show();
		jQuery('.mnuAdmin').hide();
		jQuery('.mnuTask').hide();
		jQuery('.mnuSeg').hide();
		jQuery('.mnuRep').hide();
		//Protinal
		jQuery('.mnuGto').hide();
		jQuery('.mnuBudget').hide();
	}
	
	function mnuAdmin(){
		jQuery('.mnuBas').hide();
		jQuery('.mnuAdmin').show();
		jQuery('.mnuTask').hide();
		jQuery('.mnuSeg').hide();
		jQuery('.mnuRep').hide();
		//Protinal
		jQuery('.mnuGto').hide();
		jQuery('.mnuBudget').hide();
	}
	
	function mnuTask(){
		jQuery('.mnuBas').hide();
		jQuery('.mnuAdmin').hide();
		jQuery('.mnuTask').show();
		jQuery('.mnuSeg').hide();
		jQuery('.mnuRep').hide();
		//Protinal
		jQuery('.mnuGto').hide();
		jQuery('.mnuBudget').hide();
	}
	
	function mnuRep(){
		jQuery('.mnuBas').hide();
		jQuery('.mnuAdmin').hide();
		jQuery('.mnuTask').hide();
		jQuery('.mnuSeg').hide();
		jQuery('.mnuRep').show();
		//Protinal
		jQuery('.mnuGto').hide();
		jQuery('.mnuBudget').hide();
	}
	
	function mnuSeg(){
		jQuery('.mnuBas').hide();
		jQuery('.mnuAdmin').hide();
		jQuery('.mnuTask').hide();
		jQuery('.mnuSeg').show();
		jQuery('.mnuRep').hide();
		//Protinal
		jQuery('.mnuGto').hide();
		jQuery('.mnuBudget').hide();
	}
	
	//Protinal
	function mnuGto(){
		jQuery('.mnuBas').hide();
		jQuery('.mnuAdmin').hide();
		jQuery('.mnuTask').hide();
		jQuery('.mnuSeg').hide();
		jQuery('.mnuRep').hide();
		//Protinal
		jQuery('.mnuGto').show();
		jQuery('.mnuBudget').hide();
	}
	
	//Protinal
	function mnuBudget(){
		jQuery('.mnuBas').hide();
		jQuery('.mnuAdmin').hide();
		jQuery('.mnuTask').hide();
		jQuery('.mnuSeg').hide();
		jQuery('.mnuRep').hide();
		//Protinal
		jQuery('.mnuGto').hide();
		jQuery('.mnuBudget').show();
	}

	//Modal delete
	function modalDelete(){
		$("#myModalDelete").modal();
	}
	

	//Modal delete
	function modalHide(){
		$("#myModalDelete").modal('hide');
	}
	
	function chpassSuccess(vT0){
		alert(vT0);
		if(vT0=='1'){
			PF('idleDialog').show();
		}
	}

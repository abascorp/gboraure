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

var milisec = 120000;


function limpiarInputs(){
	if(document.getElementById("formAutoext:mensaje").className == "exito"){
    document.getElementById("formAutoext:lista").value="1";
	}
}

function procesar(){
	if(document.getElementById("formAutoext:lista").value=="1"){
		document.getElementById("jsmsj").innerHTML= document.getElementById("msnProc").value;
        document.getElementById("jsmsj").className = 'alerta';
        document.getElementById("formAutoext:lista").focus();
        setTimeout("limpiarMensaje('jsmsj');", milisec);
        document.getElementById("formAutoext:mensaje").innerHTML= "";
        document.getElementById("formAutoext:mensaje").className = "";
    }else{
    	// Limpiamos valores   
    	document.getElementById("jsmsj").innerHTML= "";
        document.getElementById("jsmsj").className = "";
        
      //Creamos mensaje
        setTimeout("limpiarInputs();",500);
    } 
}




function limpiar()
{  //Llamado por el boton limpiar
	document.getElementById("formAutoext:lista").value="1";
    document.getElementById("jsmsj").innerHTML = "";
    document.getElementById("jsmsj").className = "";

}


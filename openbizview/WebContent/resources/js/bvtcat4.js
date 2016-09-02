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

function enviar(vT0,vT1,vT2,vT3,vT4,vT5,vT6,vT7){
	  document.getElementById("formbvtcat4:bcodcat1_input").value= rTrim(vT0);
	  document.getElementById("formbvtcat4:bcodcat2_input").value= rTrim(vT1);
	  document.getElementById("formbvtcat4:bcodcat3_input").value= rTrim(vT2);
	  document.getElementById("formbvtcat4:codcat4").value= rTrim(vT3);
	  document.getElementById("formbvtcat4:descat4").value= rTrim(vT4);
	  document.getElementById("formbvtcat4:equicat4").value= rTrim(vT5);
	  document.getElementById("formbvtcat4:tippro").value= rTrim(vT6);	  
	  document.getElementById("formbvtcat4:vop").value= rTrim(vT7);	 
	  updateInput('formbvtcat4:bcodcat1_input', '#F5F6CE');
	  updateInput('formbvtcat4:bcodcat2_input', '#F5F6CE');
	  updateInput('formbvtcat4:bcodcat3_input', '#F5F6CE');
	  updateInput('formbvtcat4:codcat4', '#F5F6CE');
	}


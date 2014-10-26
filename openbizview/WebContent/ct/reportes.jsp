<%-- 
    Document   : 2
    Created on : 08/05/2009, 08:21:43 AM
    Author     : Andres
--%>

<%@ page session="true" language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/birt.tld" prefix="birt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-----------------------------------------------------------------------------
    Expected java beans
-----------------------------------------------------------------------------%>

<%-------------------------------------------------------------------------------------------
  Define variables de session.
--------------------------------------------------------------------------------------------%>
<%
boolean sesion = false;
HttpSession sesionOk = request.getSession();
if (sesionOk.getAttribute("usuario") == null) {
	sesion = false;
response.sendRedirect("/openbizview/login.xhtml");

}//Fin valida que usuario no sea nulo
%>
<html>
    <head>
        <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
        <jsp:useBean id="log" class="org.openbizview.util.LogReportesImpresos" scope="session"/>
        <title></title>
    </head>
    <body>

       <%   
       //El viejo jsp me ayudó a insertar el log, ya que por la página xhtml, cuando el datatable es lazy nada que ver
       //Ni idea porqué no deja. 01/09/2014      
   
        // Recoge parámetros para pasar a reportes BIRT
       String rep= null;
       String captura = request.getParameter("reporte");
       String replog = request.getParameter("replog");
       String usuario = request.getParameter("usuario");
       String descripcion = request.getParameter("descripcion");
       String hora = request.getParameter("hora");
       String rol = request.getParameter("rol");
        if (captura!=null){
         rep =  captura;
        }
      
       log.insertBvt006(replog, descripcion,  usuario, hora);
        %>
        <birt:viewer id='birtViewer' reportDesign= "<%=rep%>"  isHostPage="true">
            <birt:param  name="ROL" value="<%=rol%>"  ></birt:param>
            <birt:param  name="USUARIO" value="<%=usuario%>"  ></birt:param>
        </birt:viewer>
    </body>
</html>

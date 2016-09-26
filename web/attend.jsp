<%-- 
    Document   : attend
    Created on : 26/09/2016, 06:36:17 AM
    Author     : brand
--%>

<%@page import="com.google.gson.JsonParser"%>
<%@page import="com.google.gson.JsonArray"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Asistencias</title>
    <link rel="stylesheet" href="css/bootstrap.css" />
    <link rel="stylesheet" href="css/styleSign.css" />
    <script src="js/bootrstrap.js"></script>
  </head>

  <body>
      <%
          HttpSession misession= (HttpSession) request.getSession();
          String token = (String) misession.getAttribute("token");
          if(token.equalsIgnoreCase("error") || token.equalsIgnoreCase("") || token==null){
                response.sendRedirect("index.jsp");
          }
          
      %>
          <%-- start web service invocation --%><hr/>
    <%
    JsonArray asistencias = null;
    try {
	services.GestorWS_Service service = new services.GestorWS_Service();
	services.GestorWS port = service.getGestorWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String docCOD = "11483614";
	int asiCOD = 846;
	//java.lang.String token = "";
	// TODO process result here
	java.lang.String result = port.getAllAsistanceByAsignature(docCOD, asiCOD, token);
	asistencias = new JsonParser().parse(result).getAsJsonArray();
        //out.print(result);
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>

  <div class="container">
  <h1>Asistencias</h3>
    <table class="table table-hover">
    <thead>
      <tr>
        <th>Fecha</th>
        <th>Codigo del estudiante</th>
        <th>Asistió</th>
      </tr>
    </thead>
    <tbody>
        <%  
            String fecha="", cod="", asis="", row="";
            if(asistencias!=null && !asistencias.isJsonNull()){
                for(int i=0; i<asistencias.size();i++){
                      fecha = asistencias.get(i).getAsJsonObject().get("Fecha").getAsString();
                      cod = asistencias.get(i).getAsJsonObject().get("Est_cod").getAsString();
                      asis = asistencias.get(i).getAsJsonObject().get("asistencia").getAsString();
                      if(asis.equalsIgnoreCase("1")) asis="Si";
                      else asis="No";
                      row="<tr><td>"+fecha+"</td><td>"+cod+"</td><td>"+asis+"</td></tr>";
                      out.print(row);
                };
            };
        %>
      
    </tbody>
  </table>
  </div>
  </body>

</html>

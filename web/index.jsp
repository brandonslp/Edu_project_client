<%-- 
    Document   : index
    Created on : 26/09/2016, 06:35:57 AM
    Author     : brand
--%>

<%@page import="com.google.gson.JsonParser"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Inicio</title>
	<link rel="stylesheet" href="css/bootstrap.css" />
    <link rel="stylesheet" href="css/styleSign.css" />
    <script src="js/bootrstrap.js"></script>
</head>
<body>
<div class="container">
    <form class="form-signin" action="index.jsp">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="text" name="id" id="inputEmail" class="form-control" placeholder="User" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="pass"id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
</div> <!-- /container -->

    <%-- start web service invocation --%><hr/>
    <%
    try {
	services.GestorWS_Service service = new services.GestorWS_Service();
	services.GestorWS port = service.getGestorWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String user = request.getParameter("id");
	java.lang.String pass = request.getParameter("pass");;
	// TODO process result here
	java.lang.String result = port.loginUser(user, pass);
	//out.println("Result = "+result);
        JsonObject jo = new JsonParser().parse(result).getAsJsonObject();
        //out.print(new Gson().toJson(jo));
        //out.print(jo.get("token"));
        String token = jo.get("token").getAsString();
        if(!token.equalsIgnoreCase("error")){
            HttpSession sessionme= request.getSession(true);
            sessionme.setAttribute("token", jo.get("token").getAsString());
            response.sendRedirect("attend.jsp");
        }
        
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>

</body>
</html>
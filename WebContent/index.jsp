<%@page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
<%
	// Redireciona o fluxo para o NoticiasServlet 
	request.getRequestDispatcher("noticias").forward(request, response);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title></title>
	</head>
	<body></body>
</html>
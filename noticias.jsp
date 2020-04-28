<%@page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.util.*,model.Noticia" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>RealNews</title>
		<link href="style.css" rel="stylesheet" type="text/css">
	</head>
	
	<body>
		<h1>RealNews</h1>
		<hr>	
		
		<ol><%
			@SuppressWarnings("unchecked")
			List<Noticia> noticias = (List<Noticia>)request.getAttribute("noticias");
			if (noticias != null) {
				for (Noticia noticia : noticias) { %>
					<li><a href=""> <%= noticia.getTitulo() %> </a></li> <%
				}
			} %>
		</ol>
		<hr>
	</body>
</html>
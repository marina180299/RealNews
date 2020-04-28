<%@page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.util.*,model.Noticia,model.Comentario" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>RealNews</title>
		<link href="style.css" rel="stylesheet" type="text/css">
	</head>
	
	<body>
		<% 
			@SuppressWarnings("unchecked")
			Noticia noticia = (Noticia)request.getAttribute("noticia");
		%>
		
		<div>
			<h1>RealNews</h1>
			<hr>
		</div>
		
		<div class="content">
			<h2><%= noticia.getTitulo() %></h2>
			<br>
			<p><%= noticia.getTexto() %></p>
			<br>
			<h2>Comentários</h2>
			
			<div class="comment-container">
				<%
				for (Comentario comentario : noticia.getComentarios()) { %>
					<h3> <%= comentario.getNome() %> </h3>
					<p class="comment-text"> <%= comentario.getTexto() %> </p>
					<hr class="comment-separator"> <%
				}
				%>
				
				<h3 class="add-comment-lbl">Adicionar comentário:</h3>
			
				<form method="post" action="comentario">
					<div class="row">
						<div class="col-16">
							<label class="comment-field-lbl" for="nome">Nome:</label>
						</div>
						<div class="col-84">
							<input id="nome" name="nome" type="text" placeholder="Digite seu nome.." />
						</div>
					</div>
					<div class="row">
						<div class="col-16">
							<label class="comment-field-lbl" for="comentario">Comentário:</label>
						</div>
						<div class="col-84">
							<textarea id="comentario" name="comentario" placeholder="Diga algo sobre a notícia.." style="height: 100px"></textarea>
						</div>
					</div>
					<div class="row">
						<input class="comment-field-btn" type="submit" value="Enviar" />
					</div>
				</form>
			</div>
		</div>
		
		<div class="footer">
			<p>Copyright © 2020 - RealNews</p>
		</div>
	</body>
</html>
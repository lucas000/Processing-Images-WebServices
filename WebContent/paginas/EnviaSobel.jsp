<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div>
	  	<h2>Imagem Sobel</h2>
	
	    <form method="POST" action="../ServicosProjeto/FileUploadServlet" enctype="multipart/form-data" >
	        Imagem sobel:
	        <input type="file" name="file" id="file" /> <br/>
	        Salvar em:
	        <input type="text" value="/tmp" name="destination"/>
	        </br>
	        <input type="submit" value="Enviar" name="upload" id="upload" />
	    </form>
	</div>	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Imagem em RGB</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css"/>
</head>
<body>
    <div align="center">
	  	<h2>Imagem RGB</h2>
	
	    <form method="POST" action="../ImagemRGB" enctype="multipart/form-data" >
	        Imagem:
	        <input type="file" name="file" id="file" /> <br/>
	        Salvar em:
	        <input type="text" value="/tmp" name="destination"/>
	        </br>
	        <input type="submit" value="Enviar" name="upload" id="upload" />
	    </form>
	</div>	
</body>
</html>
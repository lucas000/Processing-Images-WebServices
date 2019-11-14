<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Escala de cinza</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css"/>    
</head>
<body>
    <body>
    	<div align="center">
	    	<h1>Imagem Grayscale</h1>
	    	
	        <form method="POST" action="../ServicosProjeto/ImagemGrayScale" enctype="multipart/form-data" >
	            Imagem original:
	            <input type="file" name="file" id="file" /> <br/>
	            Destination:
	            <input type="text" value="/tmp" name="destination"/>
	            </br>
	            <input type="submit" value="Upload" name="upload" id="upload" />
	        </form>
        </div>
    </body>
</body>
</html>
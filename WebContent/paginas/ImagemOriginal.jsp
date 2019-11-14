<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Envie imagem original</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.css"/>
</head>
<body>
<div align="center">

	<h1 class='display-5 mt-3'>Selecione uma imagem para ser processada</h1>
	 
		<form action="../../ServicosProjeto/rest/file/uploadImagemOriginal" method="post" enctype="multipart/form-data">
	 
		   <p>
			Selecionar um arquivo : <input type="file" name="file" size="45" />
		   </p>
	 
		   <input type="submit" value="Enviar" />
		</form>
</div>
	<script>
    // nosso script fica aqui!
    </script>
</body>
</html>
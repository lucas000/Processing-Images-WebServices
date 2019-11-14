<%@page contentType="text/html" import="java.util.*, java.text.*" 
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.css"/>
</head>
<body>
	<div align="center">
		<h3>Entrar</h3>
		
		<form method = "POST" action = "../Login">
		  <div class="form-group">
		    <label for="usuario">Usuário</label>
		    <input type="text" class="form-control" name="usuario" id="usuario" aria-describedby="emailHelp" placeholder="Nome de usuário">
		    </div>
		  <div class="form-group">
		    <label for="senha">Senha</label>
		    <input type="password" class="form-control" name="senha" id="senha" placeholder="Sua senha">
		  </div>
		  <button type="submit" class="btn btn-primary">Entrar</button>
		</form>
		<!-- 
		
		
		<form method = "POST"  action = "../LoginServlet">
                Usuario: <input type = "text" name = "usuario" value="lucas"/>
                <br>
                Senha: <input type = "text" name = "senha" value="123"/>
                <br>
                
                <input type = "submit" class="btn btn-info" value = "Login"/>
        </form>
         -->
	</div>        
</body>
</html>
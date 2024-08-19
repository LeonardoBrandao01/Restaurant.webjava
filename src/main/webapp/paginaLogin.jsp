<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página de Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
    <style>
       /* Estilos básicos para o formulário */
body {
    font-family: 'Roboto', sans-serif;
    margin: 0;
    padding: 0;
    background-color: #333;
    color: #fff;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.container-login {
    background: #444;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
    width: 100%;
    max-width: 400px;
}

h1 {
    text-align: center;
    color: #fff;
    margin-bottom: 20px;
    font-size: 24px;
}

.grupo-formulario {
    margin-bottom: 15px;
}

.grupo-formulario label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
    color: #fff;
}

.grupo-formulario input {
    width: 100%;
    padding: 10px;
    border: 1px solid #666;
    border-radius: 4px;
    background-color: #555;
    color: #fff;
    box-sizing: border-box;
}

.grupo-formulario input[type="submit"] {
    background-color: #000;
    color: #fff;
    border: 1px solid #666;
    padding: 10px 15px;
    font-size: 16px;
    cursor: pointer;
    border-radius: 4px;
    margin-top: 10px;
}

.grupo-formulario input[type="submit"]:hover {
    background-color: #222;
}

.link-restrito {
    display: block;
    margin-top: 10px;
    text-align: center;
    color: #4CAF50;
    text-decoration: none;
}

.link-restrito:hover {
    text-decoration: underline;
}

    </style>
</head>
<body>
    <div class="container-login">
        <h1>Página de Login</h1>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="grupo-formulario">
                <label for="usuario">Nome de Usuário</label>
                <input type="text" id="usuario" name="usuario" required placeholder="Digite seu nome de usuário">
            </div>
            <div class="grupo-formulario">
                <label for="senha">Senha</label>
                <input type="password" id="senha" name="senha" required placeholder="Digite sua senha">
            </div>
            <div class="grupo-formulario">
                <input type="submit" value="Entrar">
            </div>
            <a href="${pageContext.request.contextPath}/areaRestrita.jsp" class="link-restrito">Área Restrita</a>
        </form>
    </div>
</body>
</html>

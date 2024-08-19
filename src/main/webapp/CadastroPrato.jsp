<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="areaRestrita.jsp" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Pratos</title>
    <style>
        /* Estilos básicos para o corpo da página */
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #333;
            color: #fff;
        }

        h1 {
            text-align: center;
            color: #fff;
            margin-top: 20px;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background: #444;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.5);
        }

        form {
            margin-bottom: 20px;
        }

        p {
            margin: 10px 0;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #fff;
        }

        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #666;
            border-radius: 4px;
            box-sizing: border-box;
            background-color: #555;
            color: #fff;
        }

        textarea {
            height: 100px;
        }

        button {
            background-color: #000;
            color: #fff;
            border: 1px solid #666;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
        }

        button:hover {
            background-color: #222;
        }

        .message {
            color: #ff4d4d;
            text-align: center;
            margin-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            color: #fff;
        }

        table, th, td {
            border: 1px solid #666;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #555;
        }

        a {
            color: #f44336;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
    <script>
        function submitForm(opcaoValue) {
            document.getElementById("opcao").value = opcaoValue;
            document.getElementById("cadastroForm").submit();
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>Cadastro de Pratos</h1>
        <!-- Formulário para cadastro, edição e exclusão de pratos -->
        <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PratoControlador" method="get">
            <input type="hidden" name="opcao" value="${opcao}" id="opcao" />
            <input type="hidden" name="id_prato" value="${id_prato}" />

            <p>
                <label for="nome"><strong>Nome:</strong></label>
                <input type="text" id="nome" name="nome" value="${nome}" required />
            </p>

            <p>
                <label for="preco"><strong>Preço:</strong></label>
                <input type="number" id="preco" name="preco" value="${preco}" required />
            </p>

            <p>
                <label for="descricao"><strong>Descrição:</strong></label>
                <textarea id="descricao" name="descricao" required>${descricao}</textarea>
            </p>

            <button type="submit">${opcao == 'confirmarExcluir' ? 'Excluir' : 'Salvar'}</button>
            <button type="submit" name="opcao" value="cancelar">Cancelar</button>
        </form>

        <c:if test="${not empty mensagem}">
            <p class="message">${mensagem}</p>
        </c:if>

        <!-- Lista de pratos -->
        <h2>Lista de Pratos</h2>
        <table>
            <c:if test="${not empty pratos}">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Preço</th>
                    <th>Descrição</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </c:if>

            <c:forEach var="prato" items="${pratos}">
                <tr>
                    <td>${prato.id_prato}</td>
                    <td>${prato.nome}</td>
                    <td>${prato.preco}</td>
                    <td>${prato.descricao}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}${URL_BASE}/PratoControlador?opcao=editar&id_prato=${prato.id_prato}&nome=${prato.nome}&preco=${prato.preco}&descricao=${prato.descricao}">Editar</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}${URL_BASE}/PratoControlador?opcao=excluir&id_prato=${prato.id_prato}&nome=${prato.nome}&preco=${prato.preco}&descricao=${prato.descricao}">Excluir</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>

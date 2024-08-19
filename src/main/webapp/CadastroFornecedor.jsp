<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="areaRestrita.jsp" %>

<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Fornecedor</title>
    <style>
        /* Estilos básicos para o corpo da página */
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #333;
            color: #fff;
        }

        h1, h2 {
            text-align: center;
            color: #fff;
            margin: 20px 0;
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

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"], input[type="email"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #666;
            border-radius: 4px;
            box-sizing: border-box;
            background-color: #555;
            color: #fff;
        }

        input[type="submit"], button {
            background-color: #000;
            color: #fff;
            border: 1px solid #666;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
        }

        input[type="submit"]:hover, button:hover {
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

        button {
            background-color: #f44336;
            border: none;
            padding: 8px 12px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            margin: 2px;
        }

        button:hover {
            background-color: #c62828;
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
    <h1>Cadastro de Fornecedor</h1>
    <div class="container">
        <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador" method="get">
            <input type="hidden" name="opcao" id="opcao" value="${opcao}" />
            <input type="hidden" name="idFornecedor" value="${idFornecedor}" />

            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" value="${nome}" required />

            <label for="cnpj">CNPJ:</label>
            <input type="text" id="cnpj" name="cnpj" value="${cnpj}" required />

            <label for="telefone">Telefone:</label>
            <input type="text" id="telefone" name="telefone" value="${telefone}" required />

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${email}" required />

            <input type="submit" value="Salvar" />
        </form>

        <form name="cancelarForm" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador" method="get">
            <input type="hidden" name="opcao" value="cancelar" />
            <input type="submit" value="Cancelar" />
        </form>

        <c:if test="${not empty mensagem}">
            <p class="message">${mensagem}</p>
        </c:if>

        <h2>Lista de Fornecedores</h2>
        <table>
            <c:if test="${not empty fornecedores}">
                <tr>
                    <th>Nome</th>
                    <th>CNPJ</th>
                    <th>Telefone</th>
                    <th>Email</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </c:if>

            <c:forEach var="fornecedor" items="${fornecedores}">
                <tr>
                    <td>${fornecedor.nome}</td>
                    <td>${fornecedor.cnpj}</td>
                    <td>${fornecedor.telefone}</td>
                    <td>${fornecedor.email}</td>
                    <td>
                        <form name="editarForm" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador" method="get">
                            <input type="hidden" name="idFornecedor" value="${fornecedor.idFornecedor}" />
                            <input type="hidden" name="nome" value="${fornecedor.nome}" />
                            <input type="hidden" name="cnpj" value="${fornecedor.cnpj}" />
                            <input type="hidden" name="telefone" value="${fornecedor.telefone}" />
                            <input type="hidden" name="email" value="${fornecedor.email}" />
                            <input type="hidden" name="opcao" value="editar" />
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                    <td>
                        <form name="excluirForm" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador" method="get">
                            <input type="hidden" name="idFornecedor" value="${fornecedor.idFornecedor}" />
                            <input type="hidden" name="nome" value="${fornecedor.nome}" />
                            <input type="hidden" name="cnpj" value="${fornecedor.cnpj}" />
                            <input type="hidden" name="telefone" value="${fornecedor.telefone}" />
                            <input type="hidden" name="email" value="${fornecedor.email}" />
                            <input type="hidden" name="opcao" value="excluir" />
                            <button type="submit">Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>

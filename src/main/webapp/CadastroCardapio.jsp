<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="areaRestrita.jsp" %>

<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Cardápios</title>
    <style>
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

        input[type="text"], select {
            width: 100%;
            padding: 8px;
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
</head>
<body>
    <div class="container">
        <h1>Cadastro de Cardápios</h1>
        <div>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/CardapioControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" id="opcao" />
                <input type="hidden" name="id_cardapio" value="${id_cardapio}" />

                <p>
                    <label for="descricao"><strong>Descrição:</strong></label>
                    <input type="text" id="descricao" name="descricao" value="${descricao}" required />
                </p>

                <p>
                    <label for="id_bebida"><strong>ID da Bebida:</strong></label>
                    <input type="text" id="id_bebida" name="id_bebida" value="${id_bebida}" required />
                </p>

                <p>
                    <label for="id_prato"><strong>ID do Prato:</strong></label>
                    <input type="text" id="id_prato" name="id_prato" value="${id_prato}" required />
                </p>

                <input type="submit" name="Salvar" value="Salvar" />
            </form>

            <form name="cancelarForm" action="${pageContext.request.contextPath}${URL_BASE}/CardapioControlador" method="get">
                <input type="hidden" name="opcao" value="cancelar" />
                <input type="submit" name="Cancelar" value="Cancelar" />
            </form>
        </div>
        <div class="message">${mensagem}</div>

        <h2>Lista de Cardápios</h2>

        <table>
            <c:if test="${not empty cardapios}">
                <tr>
                    <th>Descrição</th>
                    <th>ID da Bebida</th>
                    <th>ID do Prato</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </c:if>

            <c:forEach var="cardapio" items="${cardapios}">
                <tr>
                    <td>${cardapio.descricao}</td>
                    <td>${cardapio.id_bebida}</td>
                    <td>${cardapio.id_prato}</td>
                    <td>
                        <form name="editarForm" action="${pageContext.request.contextPath}${URL_BASE}/CardapioControlador" method="get">
                            <input type="hidden" name="id_cardapio" value="${cardapio.id_cardapio}" />
                            <input type="hidden" name="descricao" value="${cardapio.descricao}" />
                            <input type="hidden" name="id_bebida" value="${cardapio.id_bebida}" />
                            <input type="hidden" name="id_prato" value="${cardapio.id_prato}" />
                            <input type="hidden" name="opcao" value="editar" />
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                    <td>
                        <form name="excluirForm" action="${pageContext.request.contextPath}${URL_BASE}/CardapioControlador" method="get">
                            <input type="hidden" name="id_cardapio" value="${cardapio.id_cardapio}" />
                            <input type="hidden" name="descricao" value="${cardapio.descricao}" />
                            <input type="hidden" name="id_bebida" value="${cardapio.id_bebida}" />
                            <input type="hidden" name="id_prato" value="${cardapio.id_prato}" />
                            <input type="hidden" name="opcao" value="excluir" />
                            <button type="submit">Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <h2>Lista de Bebidas</h2>

        <table>
            <c:if test="${not empty bebidas}">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Preço</th>
                    <th>Tipo</th>
                </tr>
            </c:if>

            <c:forEach var="bebida" items="${bebidas}">
                <tr>
                    <td>${bebida.id_bebida}</td>
                    <td>${bebida.nome}</td>
                    <td>${bebida.preco}</td>
                    <td>${bebida.tipo}</td>
                    
                </tr>
            </c:forEach>
        </table>
        <h2>Lista de Pratos</h2>
        <table>
            <c:if test="${not empty pratos}">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Preço</th>
                    <th>Descrição</th>
                </tr>
            </c:if>

            <c:forEach var="prato" items="${pratos}">
                <tr>
                    <td>${prato.id_prato}</td>
                    <td>${prato.nome}</td>
                    <td>${prato.preco}</td>
                    <td>${prato.descricao}</td>
                   
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>

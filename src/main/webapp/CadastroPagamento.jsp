<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="areaRestrita.jsp" %>

<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Pagamento</title>
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

        input[type="text"] {
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
    <script>
        function submitForm(opcaoValue) {
            document.getElementById("opcao").value = opcaoValue;
            document.getElementById("cadastroForm").submit();
        }
    </script>
</head>
<body>
    <h1>Cadastro de Pagamento</h1>
    <div class="container">
        <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PagamentoControlador" method="get">
            <input type="hidden" name="opcao" value="${opcao}" id="opcao" />
            <input type="hidden" name="id_pagamento" value="${id_pagamento}" />

            <p>
                <label for="valor"><strong>Valor:</strong></label>
                <input type="text" id="valor" name="valor" value="${valor}" required />
            </p>

            <p>
                <label for="metodo_pagamento"><strong>Método de Pagamento:</strong></label>
                <input type="text" id="metodo_pagamento" name="metodo_pagamento" value="${metodo_pagamento}" required />
            </p>

            <input type="submit" value="Salvar" />
        </form>

        <form name="cancelarForm" action="${pageContext.request.contextPath}${URL_BASE}/PagamentoControlador" method="get">
            <input type="hidden" name="opcao" value="cancelar" />
            <input type="submit" value="Cancelar" />
        </form>

        <c:if test="${not empty mensagem}">
            <p class="message">${mensagem}</p>
        </c:if>

        <h2>Lista de Pagamentos</h2>
        <table>
            <c:if test="${not empty pagamentos}">
                <tr>
                    <th>Valor</th>
                    <th>Método de Pagamento</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </c:if>

            <c:forEach var="pagamento" items="${pagamentos}">
                <tr>
                    <td>${pagamento.valor}</td>
                    <td>${pagamento.metodo_pagamento}</td>
                    <td>
                        <form name="editarForm" action="${pageContext.request.contextPath}${URL_BASE}/PagamentoControlador" method="get">
                            <input type="hidden" name="id_pagamento" value="${pagamento.id_pagamento}" />
                            <input type="hidden" name="valor" value="${pagamento.valor}" />
                            <input type="hidden" name="metodo_pagamento" value="${pagamento.metodo_pagamento}" />
                            <input type="hidden" name="opcao" value="editar" />
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                    <td>
                        <form name="excluirForm" action="${pageContext.request.contextPath}${URL_BASE}/PagamentoControlador" method="get">
                            <input type="hidden" name="id_pagamento" value="${pagamento.id_pagamento}" />
                            <input type="hidden" name="valor" value="${pagamento.valor}" />
                            <input type="hidden" name="metodo_pagamento" value="${pagamento.metodo_pagamento}" />
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

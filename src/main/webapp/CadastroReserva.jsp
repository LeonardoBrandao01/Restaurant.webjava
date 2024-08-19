
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="areaRestrita.jsp" %>

<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Reservas</title>
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
        <h1>Cadastro de Reservas</h1>
        <div>
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ReservaControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" id="opcao" />
                <input type="hidden" name="id_reserva" value="${id_reserva}" />

                <p>
                    <label for="data_reserva"><strong>Data da Reserva:</strong></label>
                    <input type="date" id="data_reserva" name="data_reserva" value="${data_reserva}" required />
                </p>

                <p>
                    <label for="id_cliente"><strong>ID do Cliente:</strong></label>
                    <input type="text" id="id_cliente" name="id_cliente" value="${id_cliente}" required />
                </p>

                <p>
                    <label for="id_mesa"><strong>ID da Mesa:</strong></label>
                    <input type="text" id="id_mesa" name="id_mesa" value="${id_mesa}" required />
                </p>
                <input type="submit" name="Salvar" value="Salvar" />
            </form>

            <form name="cancelarForm" action="${pageContext.request.contextPath}${URL_BASE}/ReservaControlador" method="get">
                <input type="hidden" name="opcao" value="cancelar" />
                <input type="submit" name="Cancelar" value="Cancelar" />
            </form>
        </div>
        <div class="message">${mensagem}</div>

        <h2>Lista de Reservas</h2>

        <table>
            <c:if test="${not empty reservas}">
                <tr>
                    <th>Data da Reserva</th>
                    <th>ID do Cliente</th>
                    <th>ID da Mesa</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </c:if>

            <c:forEach var="reserva" items="${reservas}">
                <tr>
                    <td>${reserva.data_reserva}</td>
                    <td>${reserva.id_cliente}</td>
                    <td>${reserva.id_mesa}</td>
                    <td>
                        <form name="editarForm" action="${pageContext.request.contextPath}${URL_BASE}/ReservaControlador" method="get">
                            <input type="hidden" name="id_reserva" value="${reserva.id_reserva}" />
                            <input type="hidden" name="data_reserva" value="${reserva.data_reserva}" />
                            <input type="hidden" name="id_cliente" value="${reserva.id_cliente}" />
                            <input type="hidden" name="id_mesa" value="${reserva.id_mesa}" />
                            <input type="hidden" name="opcao" value="editar" />
                            <button type="submit">Editar</button>
                        </form>
                    </td>
                    <td>
                        <form name="excluirForm" action="${pageContext.request.contextPath}${URL_BASE}/ReservaControlador" method="get">
                            <input type="hidden" name="id_reserva" value="${reserva.id_reserva}" />
                            <input type="hidden" name="data_reserva" value="${reserva.data_reserva}" />
                            <input type="hidden" name="id_cliente" value="${reserva.id_cliente}" />
                            <input type="hidden" name="id_mesa" value="${reserva.id_mesa}" />
                            <input type="hidden" name="opcao" value="excluir" />
                            <button type="submit">Excluir</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <h2>Lista de Clientes</h2>
        <table>
            <c:if test="${not empty clientes}">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Email</th>
                </tr>
            </c:if>

            <c:forEach var="cliente" items="${clientes}">
                <tr>
                    <td>${cliente.id_cliente}</td>
                    <td>${cliente.nome}</td>
                    <td>${cliente.email}</td>
                </tr>
            </c:forEach>
        </table>

        <h2>Lista de Mesas</h2>
        <table>
            <c:if test="${not empty mesas}">
                <tr>
                    <th>ID</th>
                    <th>Capacidade</th>
                   
                </tr>
            </c:if>

            <c:forEach var="mesa" items="${mesas}">
                <tr>
                    <td>${mesa.id_mesa}</td>
                    <td>${mesa.capacidade}</td>
                    
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>

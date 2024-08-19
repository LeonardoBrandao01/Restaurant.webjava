<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Principal</title>
    
    <style>
        /* Estilos básicos para o corpo da página */
        * {
            font-family: 'Roboto', sans-serif;
            box-sizing: border-box;
        }
        body {
            margin: 0;
            background-color: #f2f2f2;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        /* Estilos para a área do menu */
        .menuCentral {
            background-color: rgba(51, 51, 51, 0.85);
            color: #ffffff;
            padding: 30px;
            border-radius: 15px;
            text-align: center;
            box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
            width: 300px;
        }

        .menuCentral h2 {
            margin-bottom: 20px;
            font-size: 24px;
        }

        .menuCentral .botaoMenu {
            background-color: #ff6347;
            color: white;
            border: none;
            border-radius: 50px;
            padding: 12px;
            font-size: 16px;
            margin: 10px 0;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.3s, box-shadow 0.3s;
            width: 100%;
            text-decoration: none;
            display: inline-block;
        }

        .menuCentral .botaoMenu:hover {
            background-color: #e5533d;
            transform: translateY(-3px);
            box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
        }

        /* Estilos para a logo */
        .logo {
            position: fixed;
            bottom: 20px;
            right: 20px;
            z-index: 1000;
        }

        .logo img {
            width: 120px;
            height: auto;
        }
    </style>
</head>
<body>

    <!-- Menu Central -->
    <div class="menuCentral">
        <h2>Menu Principal</h2>
        <a href="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador?opcao=cancelar" class="botaoMenu">Cliente</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/PratoControlador?opcao=cancelar" class="botaoMenu">Prato</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador?opcao=cancelar" class="botaoMenu">Fornecedor</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador?opcao=cancelar" class="botaoMenu">Funcionário</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/BebidasControlador?opcao=cancelar" class="botaoMenu">Bebidas</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/MesaControlador?opcao=cancelar" class="botaoMenu">Mesas</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/PagamentoControlador?opcao=cancelar" class="botaoMenu">Pagamento</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/CardapioControlador?opcao=cancelar" class="botaoMenu">Cardápio</a>
        <a href="${pageContext.request.contextPath}${URL_BASE}/ReservaControlador?opcao=encaminharParaPagina" class="botaoMenu">Reserva</a>
    </div>

    <!-- Logo -->
    <div class="logo">
        <img src="logo.png" alt="Logo">
    </div>
</body>
</html>

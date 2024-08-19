/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barto.controlador;


import com.mycompany.barto.modelo.dao.PagamentoDao;
import com.mycompany.barto.modelo.entidade.Pagamento;
import com.mycompany.barto.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/PagamentoControlador")
public class PagamentoControlador extends HttpServlet {
    private PagamentoDao pagamentoDao;
    private Pagamento pagamento;
    private String id_pagamento;
    private String valor = "";
    private String metodo_pagamento = "";
    private String opcao = "";
    
    @Override
    public void init() throws ServletException {
        pagamentoDao = new PagamentoDao();
        pagamento = new Pagamento();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            id_pagamento = request.getParameter("id_pagamento");
            valor = request.getParameter("valor");
            metodo_pagamento = request.getParameter("metodo_pagamento");
            
            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }

            switch (opcao) {
                case "cadastrar":
                    cadastrar(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "confirmarEditar":
                    confirmarEditar(request, response);
                    break;
                case "excluir":
                    excluir(request, response);
                    break;
                case "confirmarExcluir":
                    confirmarExcluir(request, response);
                    break;
                case "cancelar":
                    cancelar(request, response);
                    break;
                case "encaminharParaPagina":
                    encaminharParaPagina(request, response);
                    break;
                default:
                    throw new IllegalArgumentException("Opção inválida " + opcao);
            }

        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são números válidos");
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        pagamento.setValor(parseInt(valor));
        pagamento.setMetodo_pagamento(metodo_pagamento);
        
        pagamentoDao.salvar(pagamento);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id_pagamento", id_pagamento);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("valor", valor);
        request.setAttribute("metodo_pagamento", metodo_pagamento);
        
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");

        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id_pagamento", id_pagamento);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("valor", valor);
        request.setAttribute("metodo_pagamento", metodo_pagamento);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            validaCampos(); // Valida os campos recebidos

            Pagamento pagamentoEditado = new Pagamento();
            pagamentoEditado.setId_pagamento(parseInt(request.getParameter("id_pagamento")));
            pagamentoEditado.setValor(parseInt(request.getParameter("valor")));
            pagamentoEditado.setMetodo_pagamento(request.getParameter("metodo_pagamento"));

            pagamentoDao.alterar(pagamentoEditado);
            cancelar(request, response);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro na validação dos campos: " + e.getMessage());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro inesperado: " + e.getMessage());
        }
    }

    private int parseInt(String value) {
        return (value != null && !value.isEmpty()) ? Integer.parseInt(value) : 0;
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pagamento.setId_pagamento(Integer.parseInt(request.getParameter("id_pagamento")));
        pagamentoDao.excluir(pagamento);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id_pagamento", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("valor", "");
        request.setAttribute("metodo_pagamento", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pagamento> pagamentos = pagamentoDao.buscarTodos();
        request.setAttribute("pagamentos", pagamentos);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroPagamento.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (valor == null || valor.isEmpty() || metodo_pagamento == null || metodo_pagamento.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}

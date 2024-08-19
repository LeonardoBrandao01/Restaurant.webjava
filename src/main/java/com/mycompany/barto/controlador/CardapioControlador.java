/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barto.controlador;

import com.mycompany.barto.modelo.dao.BebidasDAO;
import com.mycompany.barto.modelo.dao.CardapioDao;
import com.mycompany.barto.modelo.dao.PratoDao;
import com.mycompany.barto.modelo.entidade.Bebidas;
import com.mycompany.barto.modelo.entidade.Cardapio;
import com.mycompany.barto.modelo.entidade.Prato;
import com.mycompany.barto.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controlador para gerenciar as operações CRUD dos cardápios.
 */
@WebServlet(WebConstantes.BASE_PATH + "/CardapioControlador")
public class CardapioControlador extends HttpServlet {
    private CardapioDao cardapioDao;
    private Cardapio cardapio;
    private String id_cardapio;
    private String descricao = "";
    private String id_bebida = "";
    private String id_prato = "";
    private String opcao = "";
    private BebidasDAO bebidasDao;
    private Bebidas bebidas;
    private String nome = "";
    private String preco = "";
    private String tipo = "";
    private PratoDao pratoDao;
    private Prato prato;
    
    
    @Override
    public void init() throws ServletException {
        cardapioDao = new CardapioDao();
        cardapio = new Cardapio();
        bebidasDao = new BebidasDAO();
        bebidas = new Bebidas();
        pratoDao = new PratoDao();
        prato = new Prato();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            id_cardapio = request.getParameter("id_cardapio");
            descricao = request.getParameter("descricao");
            id_bebida = request.getParameter("id_bebida");
            id_prato = request.getParameter("id_prato");
            opcao = request.getParameter("opcao");
            id_bebida = request.getParameter("id_bebida");
            nome = request.getParameter("nome");
            preco = request.getParameter("preco");
            tipo = request.getParameter("tipo");
            opcao = request.getParameter("opcao");
            id_prato = request.getParameter("id_prato");
            nome = request.getParameter("nome");
            preco = request.getParameter("preco");
            descricao = request.getParameter("descricao");
            
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
        cardapio.setDescricao(descricao);
        cardapio.setId_bebida(parseInt(id_bebida));
        cardapio.setId_prato(parseInt(id_prato));
        
        bebidas.setNome(nome);
        bebidas.setPreco(parseInt(preco));
        bebidas.setTipo(tipo);
       
        prato.setNome(nome);
        prato.setPreco(parseInt(preco));
        prato.setDescricao(descricao);
        
        pratoDao.salvar(prato);
        bebidasDao.salvar(bebidas);
        cardapioDao.salvar(cardapio);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id_cardapio", id_cardapio);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("descricao", descricao);
        request.setAttribute("id_bebida", id_bebida);
        request.setAttribute("id_prato", id_prato);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        
         
        
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id_cardapio", id_cardapio);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("descricao", descricao);
        request.setAttribute("id_bebida", id_bebida);
        request.setAttribute("id_prato", id_prato);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        
         
        
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            validaCampos(); // Valida os campos recebidos

            // Criação e configuração do objeto Cardapio
            Cardapio cardapioEditado = new Cardapio();
            cardapioEditado.setId_cardapio(parseInt(request.getParameter("id_cardapio")));
            cardapioEditado.setDescricao(request.getParameter("descricao"));
            cardapioEditado.setId_bebida(parseInt(request.getParameter("id_bebida")));
            cardapioEditado.setId_prato(parseInt(request.getParameter("id_prato")));

            // Atualização do cardápio no banco de dados
            cardapioDao.alterar(cardapioEditado);

            // Redireciona para o método cancelar após a atualização
            cancelar(request, response);
        } catch (IllegalArgumentException e) {
            // Trata erros relacionados à validação de campos
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro na validação dos campos: " + e.getMessage());
        } catch (Exception e) {
            // Trata outros erros inesperados
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro inesperado: " + e.getMessage());
        }
    }

    private int parseInt(String value) {
        return (value != null && !value.isEmpty()) ? Integer.parseInt(value) : 0;
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cardapio.setId_cardapio(Integer.parseInt(request.getParameter("id_cardapio")));
        cardapioDao.excluir(cardapio);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id_cardapio", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("descricao", "");
        request.setAttribute("id_bebida", "");
        request.setAttribute("id_prato", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cardapio> cardapios = cardapioDao.buscarTodos();
        request.setAttribute("cardapios", cardapios);
        request.setAttribute(opcao, opcao);
        List<Bebidas> bebidas = bebidasDao.buscarTodos();
        request.setAttribute("bebidas", bebidas);
        request.setAttribute(opcao, opcao);
        List<Prato> pratos = pratoDao.buscarTodos();
        request.setAttribute("pratos", pratos);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroCardapio.jsp");
        dispatcher.forward(request, response);
        
       
        
    }

    public void validaCampos() {
        if (descricao == null || descricao.isEmpty() || id_bebida == null || id_bebida.isEmpty() || id_prato == null || id_prato.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}

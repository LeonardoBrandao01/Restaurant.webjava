/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barto.controlador;

import com.mycompany.barto.modelo.dao.BebidasDAO;
import com.mycompany.barto.modelo.entidade.Bebidas;
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
 *
 * @author guidi
 */
@WebServlet(WebConstantes.BASE_PATH + "/BebidasControlador")
public class BebidasControlador extends HttpServlet {
    private BebidasDAO bebidasDao;
    private Bebidas bebidas;
    private String id_bebida;
    private String nome = "";
    private String preco = "";
    private String tipo = "";
    private String opcao = "";
    
    @Override
    public void init() throws ServletException {
        bebidasDao = new BebidasDAO();
        bebidas = new Bebidas();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            id_bebida = request.getParameter("id_bebida");
            nome = request.getParameter("nome");
            preco = request.getParameter("preco");
            tipo = request.getParameter("tipo");
            

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
        bebidas.setNome(nome);
        bebidas.setPreco(parseInt(preco));
        bebidas.setTipo(tipo);
       
        
        
        bebidasDao.salvar(bebidas);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id_bebida", id_bebida);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nome", nome);
        request.setAttribute("preco", preco);
        request.setAttribute("tipo", tipo);
        
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");

        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id_bebida", id_bebida);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nome", nome);
        request.setAttribute("preco", preco);
        request.setAttribute("tipo", tipo);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            validaCampos(); // Valida os campos recebidos

            // Criação e configuração do objeto Caixa
            Bebidas bebidasEditado = new Bebidas();
            bebidasEditado.setId_bebida(parseInt(request.getParameter("id_bebida")));
            bebidasEditado.setNome(request.getParameter("nome"));
            bebidasEditado.setPreco(parseInt(request.getParameter("preco")));
            bebidasEditado.setTipo(request.getParameter("tipo"));
            

            // Atualização da caixa no banco de dados
            bebidasDao.alterar(bebidasEditado);

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

    // Método auxiliar para conversão de String para int
    private int parseInt(String value) {
        return (value != null && !value.isEmpty()) ? Integer.parseInt(value) : 0;
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        bebidas.setId_bebida(Integer.parseInt(request.getParameter("id_bebida")));
        bebidasDao.excluir(bebidas);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id_bebida", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nome", "");
        request.setAttribute("preco", "");
        request.setAttribute("tipo", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bebidas> bebidas = bebidasDao.buscarTodos();
        request.setAttribute("bebidas", bebidas);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroBebidas.jsp");
        dispatcher.forward(request, response);
        
    }

    public void validaCampos() {
        if (nome == null || nome.isEmpty() || preco == null || preco.isEmpty() || tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}

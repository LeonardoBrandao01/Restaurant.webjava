/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barto.controlador;

import com.mycompany.barto.modelo.dao.MesaDao;
import com.mycompany.barto.modelo.entidade.Mesa;
import com.mycompany.barto.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/MesaControlador")
public class MesaControlador extends HttpServlet {
    private MesaDao mesaDao;
    private Mesa mesa;
    private String id_mesa;
    private String numero = "";
    private String capacidade = "";
    private String opcao = "";
    
    @Override
    public void init() throws ServletException {
        mesaDao = new MesaDao();
        mesa = new Mesa();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            id_mesa = request.getParameter("id_mesa");
            numero = request.getParameter("numero");
            capacidade = request.getParameter("capacidade");
            
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
        mesa.setNumero(parseInt(numero));
        mesa.setCapacidade(parseInt(capacidade));
        
        mesaDao.salvar(mesa);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id_mesa", id_mesa);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("numero", numero);
        request.setAttribute("capacidade", capacidade);
        
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");

        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id_mesa", id_mesa);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("numero", numero);
        request.setAttribute("capacidade", capacidade);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            validaCampos(); // Valida os campos recebidos

            // Criação e configuração do objeto Mesa
            Mesa mesaEditada = new Mesa();
            mesaEditada.setId_mesa(parseInt(request.getParameter("id_mesa")));
            mesaEditada.setNumero(parseInt(request.getParameter("numero")));
            mesaEditada.setCapacidade(parseInt(request.getParameter("capacidade")));

            // Atualização da mesa no banco de dados
            mesaDao.alterar(mesaEditada);

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
        mesa.setId_mesa(Integer.parseInt(request.getParameter("id_mesa")));
        mesaDao.excluir(mesa);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id_mesa", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("numero", "");
        request.setAttribute("capacidade", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Mesa> mesas = mesaDao.buscarTodos();
        request.setAttribute("mesas", mesas);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroMesa.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (numero == null || numero.isEmpty() || capacidade == null || capacidade.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}

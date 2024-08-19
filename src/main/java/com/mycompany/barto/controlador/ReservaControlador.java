/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barto.controlador;

import com.mycompany.barto.modelo.dao.ClienteDao;
import com.mycompany.barto.modelo.dao.MesaDao;
import com.mycompany.barto.modelo.dao.ReservaDao;
import com.mycompany.barto.modelo.entidade.Cliente;
import com.mycompany.barto.modelo.entidade.Mesa;
import com.mycompany.barto.modelo.entidade.Reserva;
import com.mycompany.barto.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * Controlador para gerenciar as operações CRUD das reservas.
 */
@WebServlet(WebConstantes.BASE_PATH + "/ReservaControlador")
public class ReservaControlador extends HttpServlet {
    private ReservaDao reservaDao;
    private ClienteDao clienteDao;
    private MesaDao mesaDao;
    private Reserva reserva;
    private String id_reserva;
    private String data_reserva = "";
    private String id_cliente = "";
    private String id_mesa = "";
    private String opcao = "";

    @Override
    public void init() throws ServletException {
        reservaDao = new ReservaDao();
        clienteDao = new ClienteDao();
        mesaDao = new MesaDao();
        reserva = new Reserva();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            id_reserva = request.getParameter("id_reserva");
            data_reserva = request.getParameter("data_reserva");
            id_cliente = request.getParameter("id_cliente");
            id_mesa = request.getParameter("id_mesa");

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
        reserva.setData_reserva(Date.valueOf(data_reserva));
        reserva.setId_cliente(parseInt(id_cliente));
        reserva.setId_mesa(parseInt(id_mesa));
        
        reservaDao.salvar(reserva);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id_reserva", id_reserva);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("data_reserva", data_reserva);
        request.setAttribute("id_cliente", id_cliente);
        request.setAttribute("id_mesa", id_mesa);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");

        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id_reserva", id_reserva);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("data_reserva", data_reserva);
        request.setAttribute("id_cliente", id_cliente);
        request.setAttribute("id_mesa", id_mesa);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");

        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            validaCampos(); // Valida os campos recebidos

            // Criação e configuração do objeto Reserva
            Reserva reservaEditada = new Reserva();
            reservaEditada.setId_reserva(parseInt(request.getParameter("id_reserva")));
            reservaEditada.setData_reserva(Date.valueOf(request.getParameter("data_reserva")));
            reservaEditada.setId_cliente(parseInt(request.getParameter("id_cliente")));
            reservaEditada.setId_mesa(parseInt(request.getParameter("id_mesa")));

            // Atualização da reserva no banco de dados
            reservaDao.alterar(reservaEditada);

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

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        reserva.setId_reserva(Integer.parseInt(request.getParameter("id_reserva")));
        reservaDao.excluir(reserva);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("id_reserva", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("data_reserva", "");
        request.setAttribute("id_cliente", "");
        request.setAttribute("id_mesa", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Reserva> reservas = reservaDao.buscarTodos1();
        request.setAttribute("reservas", reservas);
        List<Cliente> clientes = clienteDao.buscarTodos();
        request.setAttribute("clientes", clientes);
        List<Mesa> mesas = mesaDao.buscarTodos();
        request.setAttribute("mesas", mesas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroReserva.jsp");
        dispatcher.forward(request, response);
    }

    private int parseInt(String value) {
        return (value != null && !value.isEmpty()) ? Integer.parseInt(value) : 0;
    }

    public void validaCampos() {
        if (data_reserva == null || data_reserva.isEmpty() || id_cliente == null || id_cliente.isEmpty() || id_mesa == null || id_mesa.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}

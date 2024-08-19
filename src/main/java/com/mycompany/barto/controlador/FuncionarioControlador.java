package com.mycompany.barto.controlador;

import com.mycompany.barto.modelo.dao.FuncionarioDao;
import com.mycompany.barto.modelo.entidade.Funcionario;
import com.mycompany.barto.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/FuncionarioControlador")
public class FuncionarioControlador extends HttpServlet {

    private FuncionarioDao funcionarioDao;
    private Funcionario funcionario;
    private String idFuncionario = "";
    private String nome = "";
    private String telefone = "";
    private String cargo = "";
    private String salario = "";
    private String opcao = "";

    @Override
    public void init() throws ServletException {
        funcionarioDao = new FuncionarioDao();
        funcionario = new Funcionario();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idFuncionario = request.getParameter("idFuncionario");
            nome = request.getParameter("nome");
            telefone = request.getParameter("telefone");
            cargo = request.getParameter("cargo");
            salario = request.getParameter("salario");

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
        funcionario.setNome(nome);
        funcionario.setTelefone(telefone);
        funcionario.setCargo(cargo);
        funcionario.setSalario(Float.parseFloat(salario));
        
        funcionarioDao.salvar(funcionario);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idFuncionario", idFuncionario);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nome", nome);
        request.setAttribute("telefone", telefone);
        request.setAttribute("cargo", cargo);
        request.setAttribute("salario", salario);

        request.setAttribute("mensagem", "Edite os dados e clique em salvar");

        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idFuncionario", idFuncionario);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nome", nome);
        request.setAttribute("telefone", telefone);
        request.setAttribute("cargo", cargo);
        request.setAttribute("salario", salario);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            validaCampos(); // Valida os campos recebidos

            // Criação e configuração do objeto Caixa
            Funcionario funcionarioEditado = new Funcionario();
            funcionarioEditado.setIdFuncionario(parseInt(request.getParameter("idFuncionario")));
            funcionarioEditado.setNome(request.getParameter("nome"));
            funcionarioEditado.setTelefone(request.getParameter("telefone"));
            funcionarioEditado.setCargo(request.getParameter("cargo"));
            funcionarioEditado.setSalario(Float.parseFloat(request.getParameter("salario")));

            // Atualização da caixa no banco de dados
            funcionarioDao.alterar(funcionarioEditado);

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
        funcionario.setIdFuncionario(Integer.parseInt(request.getParameter("idFuncionario")));
        funcionarioDao.excluir(funcionario);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idFuncionario", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nome", "");
        request.setAttribute("telefone", "");
        request.setAttribute("descricao", "");
        request.setAttribute("data", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Funcionario> funcionarios = funcionarioDao.buscarTodos();
        request.setAttribute("funcionarios", funcionarios);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroFuncionario.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (nome == null || nome.isEmpty() || telefone == null || telefone.isEmpty() || cargo == null || cargo.isEmpty() || salario == null || salario.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}

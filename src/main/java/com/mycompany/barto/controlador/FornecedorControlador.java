package com.mycompany.barto.controlador;

import com.mycompany.barto.modelo.dao.FornecedorDao;
import com.mycompany.barto.modelo.entidade.Fornecedor;
import com.mycompany.barto.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/FornecedorControlador")
public class FornecedorControlador extends HttpServlet {

    private FornecedorDao fornecedorDao;
    private Fornecedor fornecedor;
    private String idFornecedor = "";
    private String nome = "";
    private String cnpj = "";
    private String telefone = "";
    private String email = "";
    private String opcao = "";

    @Override
    public void init() throws ServletException {
        fornecedorDao = new FornecedorDao();
        fornecedor = new Fornecedor();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idFornecedor = request.getParameter("idFornecedor");
            nome = request.getParameter("nome");
            cnpj = request.getParameter("cnpj");
            telefone = request.getParameter("telefone");
            email = request.getParameter("email");

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
        fornecedor.setNome(nome);
        fornecedor.setCnpj(cnpj);
        fornecedor.setTelefone(telefone);
        fornecedor.setEmail(email);
        
        fornecedorDao.salvar(fornecedor);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idFornecedor", idFornecedor);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nome", nome);
        request.setAttribute("cnpj", cnpj);
        request.setAttribute("email", email);
        request.setAttribute("telefone", telefone);

        request.setAttribute("mensagem", "Edite os dados e clique em salvar");

        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idFornecedor", idFornecedor);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nome", nome);
        request.setAttribute("cnpj", cnpj);
        request.setAttribute("email", email);
        request.setAttribute("telefone", telefone);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            validaCampos(); // Valida os campos recebidos

            // Criação e configuração do objeto Caixa
            Fornecedor fornecedorEditado = new Fornecedor();
            fornecedorEditado.setIdFornecedor(parseInt(request.getParameter("idFornecedor")));
            fornecedorEditado.setNome(request.getParameter("nome"));
            fornecedorEditado.setCnpj(request.getParameter("cnpj"));
            fornecedorEditado.setTelefone(request.getParameter("telefone"));
            fornecedorEditado.setEmail(request.getParameter("email"));

            // Atualização da caixa no banco de dados
            fornecedorDao.alterar(fornecedorEditado);

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
        fornecedor.setIdFornecedor(Integer.parseInt(request.getParameter("idFornecedor")));
        fornecedorDao.excluir(fornecedor);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idFornecedor", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nome", "");
        request.setAttribute("cnpj", "");
        request.setAttribute("telefone", "");
        request.setAttribute("email", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Fornecedor> fornecedores = fornecedorDao.buscarTodos();
        request.setAttribute("fornecedores", fornecedores);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroFornecedor.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (nome == null || nome.isEmpty() || cnpj == null || cnpj.isEmpty() || telefone == null || telefone.isEmpty() || email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}

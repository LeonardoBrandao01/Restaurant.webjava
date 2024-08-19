/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.barto.controlador;

import com.mycompany.barto.modelo.dao.UsuarioDao;
import com.mycompany.barto.modelo.entidade.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginControlador extends HttpServlet {

    private UsuarioDao usuarioDao;

    @Override
    public void init() {
        usuarioDao = new UsuarioDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
        String nomeUsuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        
        Usuario usuario = usuarioDao.buscarPorUsuario(nomeUsuario);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuario);

           
            response.sendRedirect("areaRestrita.jsp");
        } else {
         
            request.setAttribute("mensagemErro", "Usuário ou senha inválidos");
            RequestDispatcher dispatcher = request.getRequestDispatcher("paginaLogin.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        RequestDispatcher dispatcher = request.getRequestDispatcher("paginaLogin.jsp");
        dispatcher.forward(request, response);
    }
}

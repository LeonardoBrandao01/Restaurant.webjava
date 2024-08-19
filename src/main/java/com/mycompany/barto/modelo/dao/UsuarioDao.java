package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDao extends GenericoDao<Usuario> {

    public void salvar(Usuario u) {
        String insert = "INSERT INTO USUARIO (USUARIO, SENHA) VALUES (?, ?)";
        save(insert, u.getUsuario(), u.getSenha());
    }

    public void alterar(Usuario u) {
        String update = "UPDATE USUARIO SET USUARIO=?, SENHA=? WHERE ID_USUARIO=?";
        save(update, u.getUsuario(), u.getSenha(), u.getIdUsuario());
    }

    public void excluir(Usuario u) {
        String delete = "DELETE FROM USUARIO WHERE ID_USUARIO=?";
        save(delete, u.getIdUsuario());
    }

    public Usuario buscarPorId(int id) {
        String select = "SELECT * FROM USUARIO WHERE ID_USUARIO=?";
        return buscarPorId(select, new UsuarioRowMapper(), id);
    }

    public List<Usuario> buscarTodos() {
        String select = "SELECT * FROM USUARIO";
        return buscarTodos(select, new UsuarioRowMapper());
    }

    public Usuario buscarPorUsuario(String usuario) {
        String select = "SELECT * FROM USUARIO WHERE USUARIO=?";
        return buscarPorId(select, new UsuarioRowMapper(), usuario);
    }

    public static class UsuarioRowMapper implements RowMapper<Usuario> {

        @Override
        public Usuario mapRow(ResultSet rs) throws SQLException {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("ID_USUARIO"));
            usuario.setUsuario(rs.getString("USUARIO"));
            usuario.setSenha(rs.getString("SENHA"));
            return usuario;
        }
    }
}

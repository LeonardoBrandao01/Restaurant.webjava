package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDao extends GenericoDao<Cliente> {

    public void salvar(Cliente cliente) {
        String insert = "INSERT INTO CLIENTE(NOME, TELEFONE, EMAIL) VALUES (?, ?, ?)";
        save(insert, cliente.getNome(), cliente.getTelefone(), cliente.getEmail());
    }

    public void alterar(Cliente cliente) {
        String update = "UPDATE CLIENTE SET NOME=?, TELEFONE=?, EMAIL=? WHERE ID_CLIENTE=?";
        save(update, cliente.getNome(), cliente.getTelefone(), cliente.getEmail(), cliente.getid_cliente());
    }

    public void excluir(Cliente cliente) {
        String delete = "DELETE FROM CLIENTE WHERE ID_CLIENTE=?";
        save(delete, cliente.getid_cliente());
    }

    public Cliente buscarPorId(int id) {
        String select = "SELECT * FROM CLIENTE WHERE ID_CLIENTE=?";
        return buscarPorId(select, new ClienteRowMapper(), id);
    }

    public List<Cliente> buscarTodos() {
        String select = "SELECT * FROM CLIENTE";
        return buscarTodos(select, new ClienteRowMapper());
    }

    public static class ClienteRowMapper implements RowMapper<Cliente> {

        @Override
        public Cliente mapRow(ResultSet rs) throws SQLException {
            Cliente cliente = new Cliente();
            cliente.setid_cliente(rs.getInt("ID_CLIENTE"));
            cliente.setNome(rs.getString("NOME"));
            cliente.setTelefone(rs.getString("TELEFONE"));
            cliente.setEmail(rs.getString("EMAIL"));
            return cliente;
        }
    }
}

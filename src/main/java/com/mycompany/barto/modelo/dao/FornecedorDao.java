package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.Fornecedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FornecedorDao extends GenericoDao<Fornecedor> {

    public void salvar(Fornecedor fornecedor) {
        String insert = "INSERT INTO FORNECEDOR(nome, cnpj, telefone, email) VALUES (?, ?, ?, ?)";
        save(insert, fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getTelefone(), fornecedor.getEmail());
    }

    public void alterar(Fornecedor fornecedor) {
        String update = "UPDATE FORNECEDOR SET nome=?, cnpj=?, telefone=?, email=? WHERE idFornecedor=?";
        save(update, fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getTelefone(), fornecedor.getEmail(), fornecedor.getIdFornecedor());
    }

    public void excluir(Fornecedor fornecedor) {
        String delete = "DELETE FROM FORNECEDOR WHERE idFornecedor=?";
        save(delete, fornecedor.getIdFornecedor());
    }

    public Fornecedor buscarPorId(int id) {
        String select = "SELECT * FROM FORNECEDOR WHERE idFornecedor=?";
        return buscarPorId(select, new FornecedorRowMapper(), id);
    }

    public List<Fornecedor> buscarTodos() {
        String select = "SELECT * FROM FORNECEDOR";
        return buscarTodos(select, new FornecedorRowMapper());
    }

    public static class FornecedorRowMapper implements RowMapper<Fornecedor> {

        @Override
        public Fornecedor mapRow(ResultSet rs) throws SQLException {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
            fornecedor.setNome(rs.getString("nome"));
            fornecedor.setCnpj(rs.getString("cnpj"));
            fornecedor.setTelefone(rs.getString("telefone"));
            fornecedor.setEmail(rs.getString("email"));
            return fornecedor;
        }
    }
}

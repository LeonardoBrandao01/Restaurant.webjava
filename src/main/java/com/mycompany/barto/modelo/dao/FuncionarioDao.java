package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioDao extends GenericoDao<Funcionario> {
    
    public void salvar(Funcionario funcionario) {
        String insert = "INSERT INTO FUNCIONARIO (NOME, TELEFONE, CARGO, SALARIO) VALUES (?, ?, ?, ?)";
        save(insert, funcionario.getNome(), funcionario.getTelefone(), funcionario.getCargo(), funcionario.getSalario());
    }
    
    public void alterar(Funcionario funcionario) {
        String update = "UPDATE FUNCIONARIO SET NOME=?, TELEFONE=?, CARGO=?, SALARIO=? WHERE ID_FUNCIONARIO=?";
        save(update, funcionario.getNome(), funcionario.getTelefone(), funcionario.getCargo(), funcionario.getSalario(), funcionario.getIdFuncionario());
    }
    
    public void excluir(Funcionario funcionario) {
        String delete = "DELETE FROM FUNCIONARIO WHERE ID_FUNCIONARIO=?";
        save(delete, funcionario.getIdFuncionario());
    }
    
    public Funcionario buscarPorId(int id) {
        String select = "SELECT * FROM FUNCIONARIO WHERE ID_FUNCIONARIO=?";
        return buscarPorId(select, new FuncionarioRowMapper(), id);
    }
    
    public List<Funcionario> buscarTodos() {
        String select = "SELECT * FROM FUNCIONARIO";
        return buscarTodos(select, new FuncionarioRowMapper());
    }
    
    public static class FuncionarioRowMapper implements RowMapper<Funcionario> {
        @Override
        public Funcionario mapRow(ResultSet rs) throws SQLException {
            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
            funcionario.setNome(rs.getString("NOME"));
            funcionario.setTelefone(rs.getString("TELEFONE"));
            funcionario.setCargo(rs.getString("CARGO"));
            funcionario.setSalario(rs.getFloat("SALARIO"));
            return funcionario;
        }
    }
}

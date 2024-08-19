package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.Empresa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmpresaDao extends GenericoDao<Empresa> {

    public void salvar(Empresa empresa) {
        String insert = "INSERT INTO EMPRESA(NOME, CNPJ, TELEFONE, EMAIL) VALUES (?, ?, ?, ?)";
        save(insert, empresa.getNome(), empresa.getCnpj(), empresa.getTelefone(), empresa.getEmail());
    }

    public void alterar(Empresa empresa) {
        String update = "UPDATE EMPRESA SET NOME=?, CNPJ=?, TELEFONE=?, EMAIL=? WHERE idEmpresa=?";
        save(update, empresa.getNome(), empresa.getCnpj(), empresa.getTelefone(), empresa.getEmail(), empresa.getIdEmpresa());
    }

    public void excluir(Empresa empresa) {
        String delete = "DELETE FROM EMPRESA WHERE idEmpresa=?";
        save(delete, empresa.getIdEmpresa());
    }

    public Empresa buscarPorId(int id) {
        String select = "SELECT * FROM EMPRESA WHERE idEmpresa=?";
        return buscarPorId(select, new EmpresaRowMapper(), id);
    }

    public List<Empresa> buscarTodas() {
        String select = "SELECT * FROM EMPRESA";
        return buscarTodos(select, new EmpresaRowMapper());
    }

    public static class EmpresaRowMapper implements RowMapper<Empresa> {

        @Override
        public Empresa mapRow(ResultSet rs) throws SQLException {
            Empresa empresa = new Empresa();
            empresa.setIdEmpresa(rs.getInt("idEmpresa"));
            empresa.setNome(rs.getString("NOME"));
            empresa.setCnpj(rs.getString("CNPJ"));
            empresa.setTelefone(rs.getString("TELEFONE"));
            empresa.setEmail(rs.getString("EMAIL"));

            return empresa;
        }
    }
}

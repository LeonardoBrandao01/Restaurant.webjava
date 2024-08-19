/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.Prato;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PratoDao extends GenericoDao<Prato> {

    public void salvar(Prato prato) {
        String insert = "INSERT INTO PRATO(nome, preco, descricao) VALUES (?, ?, ?)";
        save(insert, prato.getNome(), prato.getPreco(), prato.getDescricao());
    }

    public void alterar(Prato prato) {
        String update = "UPDATE PRATO SET nome=?, preco=?, descricao=? WHERE id_prato=?";
        save(update, prato.getNome(), prato.getPreco(), prato.getDescricao(), prato.getId_prato());
    }

    public void excluir(Prato prato) {
        String delete = "DELETE FROM PRATO WHERE id_prato=?";
        save(delete, prato.getId_prato());
    }

    public Prato buscarPorId(int id) {
        String select = "SELECT * FROM PRATO WHERE id_prato=?";
        return buscarPorId(select, new PratoRowMapper(), id);
    }

    public List<Prato> buscarTodos() {
        String select = "SELECT * FROM PRATO";
        return buscarTodos(select, new PratoRowMapper());
    }

    public static class PratoRowMapper implements RowMapper<Prato> {

        @Override
        public Prato mapRow(ResultSet rs) throws SQLException {
            Prato prato = new Prato();
            prato.setId_prato(rs.getInt("id_prato"));
            prato.setNome(rs.getString("nome"));
            prato.setPreco(rs.getInt("preco"));
            prato.setDescricao(rs.getString("descricao"));
            return prato;
        }
    }
}

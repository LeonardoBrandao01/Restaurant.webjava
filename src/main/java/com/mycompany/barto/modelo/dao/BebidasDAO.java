/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.Bebidas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author guidi
 */
public class BebidasDAO extends GenericoDao<Bebidas> {
    public void salvar(Bebidas bebidas) {
        String insert = "INSERT INTO BEBIDAS(nome, preco, tipo) VALUES (?, ?, ?)";
        save(insert, bebidas.getNome(), bebidas.getPreco(), bebidas.getTipo());
    }

    public void alterar(Bebidas bebidas) {
        String update = "UPDATE BEBIDAS SET nome=?, preco=?, tipo=? WHERE id_bebida=?";
        save(update, bebidas.getNome(), bebidas.getPreco(), bebidas.getTipo(), bebidas.getId_bebida());
    }

    public void excluir(Bebidas bebidas) {
        String delete = "DELETE FROM BEBIDAS WHERE id_bebida=?";
        save(delete, bebidas.getId_bebida());
    }

    public Bebidas buscarPorId(int id) {
        String select = "SELECT * FROM BEBIDAS WHERE id_bebida=?";
        return buscarPorId(select, new BebidasRowMapper(), id);
    }

    public List<Bebidas> buscarTodos() {
        String select = "SELECT * FROM BEBIDAS";
        return buscarTodos(select, new BebidasRowMapper());
    }

    public static class BebidasRowMapper implements RowMapper<Bebidas> {

        @Override
        public Bebidas mapRow(ResultSet rs) throws SQLException {
            Bebidas bebidas = new Bebidas();
            bebidas.setId_bebida(rs.getInt("id_bebida"));
            bebidas.setNome(rs.getString("nome"));
            bebidas.setPreco(rs.getInt("preco"));
            bebidas.setTipo(rs.getString("tipo"));
            return bebidas;
        }
    }
}

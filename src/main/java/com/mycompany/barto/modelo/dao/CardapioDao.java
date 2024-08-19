/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.Cardapio;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardapioDao extends GenericoDao<Cardapio> {

    public List<Cardapio> buscarTodos() {
        List<Cardapio> cardapio = new ArrayList<>();
        String procedureCall = "{CALL GetCardapioDetails()}";
        return buscarTodos(select, new CardapioRowMapper());
    }

    public static class CardapioRowMapper implements RowMapper<Cardapio> {

        @Override
        public Cardapio mapRow(ResultSet rs) throws SQLException {
            Cardapio cardapio = new Cardapio();
            cardapio.setId_cardapio(rs.getInt("ID_CARDAPIO"));
            cardapio.setDescricao(rs.getString("descricao"));
            cardapio.setId_bebida(rs.getInt("ID_BEBIDA"));
            cardapio.setId_prato(rs.getInt("ID_PRATO"));
            return cardapio;
        }
    }
}
*/
package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.Cardapio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardapioDao extends GenericoDao<Cardapio> {

    public void salvar(Cardapio cardapio) {
        String insert = "INSERT INTO CARDAPIO(DESCRICAO, ID_BEBIDA, ID_PRATO) VALUES (?, ?, ?)";
        save(insert, cardapio.getDescricao(), cardapio.getId_bebida(), cardapio.getId_prato());
    }

    public void alterar(Cardapio cardapio) {
        String update = "UPDATE CARDAPIO SET DESCRICAO=?, ID_BEBIDA=?, ID_PRATO=? WHERE ID_CARDAPIO=?";
        save(update, cardapio.getDescricao(), cardapio.getId_bebida(), cardapio.getId_prato(), cardapio.getId_cardapio());
    }

    public void excluir(Cardapio cardapio) {
        String delete = "DELETE FROM CARDAPIO WHERE ID_CARDAPIO=?";
        save(delete, cardapio.getId_cardapio());
    }

    public Cardapio buscarPorId(int id) {
        String select = "SELECT * FROM CARDAPIO WHERE ID_CARDAPIO=?";
        return buscarPorId(select, new CardapioRowMapper(), id);
    }

    public List<Cardapio> buscarTodos() {
        String select = "SELECT * FROM CARDAPIO";
        return buscarTodos(select, new CardapioRowMapper());
    }

    public static class CardapioRowMapper implements RowMapper<Cardapio> {

        @Override
        public Cardapio mapRow(ResultSet rs) throws SQLException {
            Cardapio cardapio = new Cardapio();
            cardapio.setId_cardapio(rs.getInt("ID_CARDAPIO"));
            cardapio.setDescricao(rs.getString("DESCRICAO"));
            cardapio.setId_bebida(rs.getInt("ID_BEBIDA"));
            cardapio.setId_prato(rs.getInt("ID_PRATO"));
            return cardapio;
        }
    }
}

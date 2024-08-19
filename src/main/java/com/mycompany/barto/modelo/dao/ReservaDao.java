/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.Reserva;

/**
 *
 * @author guidi
 */
import com.mycompany.barto.modelo.entidade.Reserva;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDao extends GenericoDao<Reserva> {

    public void salvar(Reserva reserva) {
        String insert = "INSERT INTO RESERVA( DATA_RESERVA, ID_CLIENTE, ID_MESA) VALUES (?, ?, ?)";
        save(insert, reserva.getData_reserva(), reserva.getId_cliente(), reserva.getId_mesa());
    }

    public void alterar(Reserva reserva) {
        String update = "UPDATE RESERVA SET DATA_RESERVA=?, ID_CLIENTE=?, ID_MESA=? WHERE ID_RESERVA=?";
        save(update, reserva.getData_reserva(), reserva.getId_cliente(), reserva.getId_mesa(), reserva.getId_reserva());
    }

    public void excluir(Reserva reserva) {
        String delete = "DELETE FROM RESERVA WHERE ID_RESERVA=?";
        save(delete, reserva.getId_reserva());
    }

    public Reserva buscarPorId(int id) {
        String select = "SELECT * FROM RESERVA WHERE ID_RESERVA=?";
        return buscarPorId(select, new ReservaRowMapper(), id);
    }

    public List<Reserva> buscarTodos1() {
        String select = "SELECT * FROM RESERVA";
        return buscarTodos(select, new ReservaRowMapper());
    }

    public static class ReservaRowMapper implements RowMapper<Reserva> {

        @Override
        public Reserva mapRow(ResultSet rs) throws SQLException {
            Reserva reserva = new Reserva();
            reserva.setId_reserva(rs.getInt("ID_RESERVA"));
            reserva.setData_reserva(rs.getDate("DATA_RESERVA"));
            reserva.setId_cliente(rs.getInt("ID_CLIENTE"));
            reserva.setId_mesa(rs.getInt("ID_MESA"));
            return reserva;
        }
    }
}

    


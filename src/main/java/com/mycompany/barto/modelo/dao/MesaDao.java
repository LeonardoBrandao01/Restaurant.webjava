/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barto.modelo.dao;
import com.mycompany.barto.modelo.entidade.Mesa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author guidi
 */
public class MesaDao extends GenericoDao<Mesa> {

    public void salvar(Mesa mesa) {
        String insert = "INSERT INTO MESA(numero, capacidade) VALUES (?, ?)";
        save(insert, mesa.getNumero(), mesa.getCapacidade());
    }

    public void alterar(Mesa mesa) {
        String update = "UPDATE MESA SET numero=?, capacidade=? WHERE id_mesa=?";
        save(update, mesa.getNumero(), mesa.getCapacidade(), mesa.getId_mesa());
    }

    public void excluir(Mesa mesa) {
        String delete = "DELETE FROM MESA WHERE id_mesa=?";
        save(delete, mesa.getId_mesa());
    }

    public Mesa buscarPorId(int id) {
        String select = "SELECT * FROM MESA WHERE id_mesa=?";
        return buscarPorId(select, new MesaRowMapper(), id);
    }

    public List<Mesa> buscarTodos() {
        String select = "SELECT * FROM MESA";
        return buscarTodos(select, new MesaRowMapper());
    }

    public static class MesaRowMapper implements RowMapper<Mesa> {

        @Override
        public Mesa mapRow(ResultSet rs) throws SQLException {
            Mesa mesa = new Mesa();
            mesa.setId_mesa(rs.getInt("id_mesa"));
            mesa.setNumero(rs.getInt("numero"));
            mesa.setCapacidade(rs.getInt("capacidade"));
            return mesa;
        }
    }
}


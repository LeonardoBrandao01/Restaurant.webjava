/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barto.modelo.dao;

import com.mycompany.barto.modelo.entidade.Pagamento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PagamentoDao extends GenericoDao<Pagamento> {

    public void salvar(Pagamento pagamento) {
        String insert = "INSERT INTO PAGAMENTO(valor, metodo_pagamento) VALUES (?, ?)";
        save(insert, pagamento.getValor(), pagamento.getMetodo_pagamento());
    }

    public void alterar(Pagamento pagamento) {
        String update = "UPDATE PAGAMENTO SET valor=?, metodo_pagamento=? WHERE id_pagamento=?";
        save(update, pagamento.getValor(), pagamento.getMetodo_pagamento(), pagamento.getId_pagamento());
    }

    public void excluir(Pagamento pagamento) {
        String delete = "DELETE FROM PAGAMENTO WHERE id_pagamento=?";
        save(delete, pagamento.getId_pagamento());
    }

    public Pagamento buscarPorId(int id) {
        String select = "SELECT * FROM PAGAMENTO WHERE id_pagamento=?";
        return buscarPorId(select, new PagamentoRowMapper(), id);
    }

    public List<Pagamento> buscarTodos() {
        String select = "SELECT * FROM PAGAMENTO";
        return buscarTodos(select, new PagamentoRowMapper());
    }

    public static class PagamentoRowMapper implements RowMapper<Pagamento> {

        @Override
        public Pagamento mapRow(ResultSet rs) throws SQLException {
            Pagamento pagamento = new Pagamento();
            pagamento.setId_pagamento(rs.getInt("id_pagamento"));
            pagamento.setValor(rs.getInt("valor"));
            pagamento.setMetodo_pagamento(rs.getString("metodo_pagamento"));
            return pagamento;
        }
    }
}


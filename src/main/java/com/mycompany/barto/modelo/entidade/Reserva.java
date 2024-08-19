/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barto.modelo.entidade;

import java.sql.Date;
/**
 *
 * @author guidi
 */
public class Reserva {
    private int id_reserva;
    private Date data_reserva;
    private int id_cliente;
    private int id_mesa;

    public Reserva(){
        
    }
    
    public Reserva(int id_reserva, Date data_reserva, int id_cliente, int id_mesa){
        this.id_reserva = id_reserva;
        this.data_reserva = data_reserva;
        this.id_cliente = id_cliente;
        this.id_mesa = id_mesa;
    }
    
    public Reserva(Date data_reserva, int id_cliente, int id_mesa){
        this.data_reserva = data_reserva;
        this.id_cliente = id_cliente;
        this.id_mesa = id_mesa;
    }
    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Date getData_reserva() {
        return data_reserva;
    }

    public void setData_reserva(Date data_reserva) {
        this.data_reserva = data_reserva;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }
    
    
    
}

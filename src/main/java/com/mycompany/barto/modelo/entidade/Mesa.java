/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barto.modelo.entidade;

/**
 *
 * @author guidi
 */
public class Mesa {
    private int id_mesa;
    private int numero;
    private int capacidade;
    
public Mesa() {
    }

    public Mesa(int id_mesa, int numero, int capacidade) {
        this.id_mesa = id_mesa;
        this.numero = numero;
        this.capacidade = capacidade;
        
    }

    public Mesa(int numero, int capacidade) {
        this.numero = numero;
        this.capacidade = capacidade;
    }
    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    
    
}

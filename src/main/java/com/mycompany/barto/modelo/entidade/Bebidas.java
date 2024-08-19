/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barto.modelo.entidade;

/**
 *
 * @author guidi
 */
public class Bebidas {
    private int id_bebida;
    private String nome;
    private int preco;
    private String tipo;
    
public Bebidas() {
    }

    public Bebidas(int id_bebida, String nome, int preco, String tipo) {
        this.id_bebida = id_bebida;
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
    }

    public Bebidas(String nome, int preco, String tipo) {
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
    }
    public int getId_bebida() {
        return id_bebida;
    }

    public void setId_bebida(int id_babida) {
        this.id_bebida = id_babida;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barto.modelo.entidade;

public class Cardapio {
    private int id_cardapio;
    private int id_prato;
    private int id_bebida;
   private String descricao;

    public Cardapio() {}

    public Cardapio(int id_cardapio, int id_prato, int id_bebida, String descricao) {
        this.id_cardapio = id_cardapio;
        this.id_prato = id_prato;
        this.id_bebida = id_bebida;
        this.descricao = descricao;
       
    }
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public int getId_cardapio() {
        return id_cardapio;
    }

    public void setId_cardapio(int id_cardapio) {
        this.id_cardapio = id_cardapio;
    }

    public int getId_prato() {
        return id_prato;
    }

    public void setId_prato(int id_prato) {
        this.id_prato = id_prato;
    }

    public int getId_bebida() {
        return id_bebida;
    }

    public void setId_bebida(int id_bebida) {
        this.id_bebida = id_bebida;
    }
}

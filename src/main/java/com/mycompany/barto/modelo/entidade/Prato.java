/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barto.modelo.entidade;

/**
 *
 * @author guidi
 */
public class Prato {
    private int id_prato;
    private String nome;
    private int preco;
    private String descricao;

    public Prato() {
    }

    public Prato(int id_prato, String nome, int preco, String descricao) {
        this.id_prato = id_prato;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        
    }

    public Prato(String nome, int preco, String descricao) {
       this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }
    public int getId_prato() {
        return id_prato;
    }

    public void setId_prato(int id_prato) {
        this.id_prato = id_prato;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}

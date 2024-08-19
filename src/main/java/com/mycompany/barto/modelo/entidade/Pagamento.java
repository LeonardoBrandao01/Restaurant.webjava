/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.barto.modelo.entidade;

/**
 *
 * @author guidi
 */
public class Pagamento {
    private int id_pagamento;
    private int valor;
    private String metodo_pagamento;

    
    public Pagamento() {
    }

    public Pagamento (int id_pagamento, int valor, String metodo_pagamento) {
        this.id_pagamento = id_pagamento;
        this.valor = valor;
        this.metodo_pagamento = metodo_pagamento;
        
    }

    public Pagamento(int valor, String metodo_pagamento) {
         this.valor = valor;
        this.metodo_pagamento = metodo_pagamento;
    }
    public int getId_pagamento() {
        return id_pagamento;
    }

    public void setId_pagamento(int id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getMetodo_pagamento() {
        return metodo_pagamento;
    }

    public void setMetodo_pagamento(String metodo_pagamento) {
        this.metodo_pagamento = metodo_pagamento;
    }
    
    
}

package com.mycompany.barto.modelo.entidade;

public class Cliente {

    private int id_cliente;
    private String nome;
    private String telefone;
    private String email;

    public Cliente(int idCliente, String nome, String cpf, String telefone, String email) {
        this.id_cliente = idCliente;
        this.nome = nome;  
        this.telefone = telefone;
        this.email = email;
     
    }

    public Cliente(String nome, String cpf, String telefone, String email) {
        this.nome = nome;       
        this.telefone = telefone;
        this.email = email;
   
    }

    public Cliente() {
    }

    public int getid_cliente() {
        return id_cliente;
    }

    public void setid_cliente(int idCliente) {
        this.id_cliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

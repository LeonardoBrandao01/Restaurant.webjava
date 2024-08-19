package com.mycompany.barto.modelo.entidade;


public class Funcionario {

    private int idFuncionario;
    private String nome;
    private String telefone;
    private String cargo;
    private float salario;

    public Funcionario(int idFuncionario, String nome, String telefone, String cargo, float salario) {
        super();
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.telefone = telefone;
        this.cargo = cargo;
        this.salario = salario;
    }

    public Funcionario(String nome, String telefone, String cargo, float salario) {
        super();
        this.nome = nome;
        this.telefone = telefone;
        this.cargo = cargo;
        this.salario = salario;
    }

    public Funcionario() {
        super();
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

}

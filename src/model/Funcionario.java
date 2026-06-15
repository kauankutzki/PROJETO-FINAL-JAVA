package model;

import model.interfaces.Avaliavel;

import java.util.ArrayList;
import java.util.List;


public abstract class Funcionario extends Pessoa implements Avaliavel {

    protected double salarioBase;
    protected List<Double> notas; 

    public Funcionario(String nome, String cpf, String email, double salarioBase) {
        super(nome, cpf, email);
        setSalarioBase(salarioBase);
        this.notas = new ArrayList<>(); 
    }

    public abstract double calcularSalarioLiquido();

    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) {this.salarioBase = salarioBase;}

    @Override
    public void adicionarNota(double nota) {
        notas.add(nota);
    }

    @Override
    public double calcularMediaAvaliacoes() {
        if (notas.isEmpty()) {
            return 0;
        }
        double soma = 0;
        for (double n : notas) {
            soma = soma + n;
        }
        return soma / notas.size();
    }
}

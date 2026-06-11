package model;

import model.enums.NivelCargo;

/**
 * Cargo — representa um cargo dentro da empresa (ex.: "Desenvolvedor Pleno").
 *
 * ENCAPSULAMENTO:
 * - Todos os atributos sao "private": ninguem acessa diretamente.
 * - Acesso so pelos getters e setters.
 *
 * ASSOCIACAO:
 * - Funcionario referencia Cargo (Funcionario --> Cargo).
 * - Os dois existem de forma independente: apagar um cargo nao apaga o funcionario.
 *
 * Cargo pertence ao modulo do Aluno 2 (Estrutura Organizacional).
 */
public class Cargo {

    private static int contadorId = 1; // gerador simples de id unico

    private int id;
    private String titulo;
    private NivelCargo nivel;       // enum: JUNIOR, PLENO, SENIOR, GESTAO
    private double salarioMinimo;
    private double salarioMaximo;

    // CONSTRUTOR
    public Cargo(String titulo, NivelCargo nivel, double salarioMinimo, double salarioMaximo) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.nivel = nivel;
        this.salarioMinimo = salarioMinimo;
        this.salarioMaximo = salarioMaximo;
    }

    /**
     * Verifica se um salario esta dentro da faixa permitida para este cargo.
     * Usado na validacao ao cadastrar/alterar o salario de um funcionario.
     */
    public boolean salarioDentroDaFaixa(double valor) {
        return valor >= salarioMinimo && valor <= salarioMaximo;
    }

    // --- GETTERS ---
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public NivelCargo getNivel() { return nivel; }
    public double getSalarioMinimo() { return salarioMinimo; }
    public double getSalarioMaximo() { return salarioMaximo; }

    // --- SETTERS (com validacao basica) ---
    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty()) {
            this.titulo = titulo;
        }
    }

    public void setNivel(NivelCargo nivel) {
        if (nivel != null) {
            this.nivel = nivel;
        }
    }

    public void setSalarioMinimo(double salarioMinimo) {
        if (salarioMinimo >= 0) {
            this.salarioMinimo = salarioMinimo;
        }
    }

    public void setSalarioMaximo(double salarioMaximo) {
        if (salarioMaximo >= salarioMinimo) {
            this.salarioMaximo = salarioMaximo;
        }
    }

    // SOBRESCRITA de toString: representacao textual do objeto.
    @Override
    public String toString() {
        return String.format("[%d] %s | Nivel: %s | Faixa: R$ %.2f - R$ %.2f",
                id, titulo, nivel, salarioMinimo, salarioMaximo);
    }
}

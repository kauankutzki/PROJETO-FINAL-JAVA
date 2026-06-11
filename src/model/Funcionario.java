package model;

import model.interfaces.Avaliavel;

import java.util.ArrayList;
import java.util.List;

/**
 * CLASSE ABSTRATA Funcionario.
 *
 * "extends Pessoa" = HERANCA.
 * O Funcionario ja GANHA de presente nome, cpf e email da Pessoa.
 * Nao precisa reescrever, so usar.
 *
 * "implements Avaliavel" = assinou o contrato.
 * E obrigado a ter adicionarNota() e calcularMediaAvaliacoes().
 *
 * "abstract" tambem aqui porque nao existe funcionario "sem regime".
 * Todo funcionario e CLT, PJ ou Estagiario.
 *
 * METODO ABSTRATO calcularSalarioLiquido() = declarado SEM corpo.
 * Cada filha (CLT, PJ, Estagiario) e OBRIGADA a escrever a versao dela.
 * E disso que nasce o POLIMORFISMO.
 */
public abstract class Funcionario extends Pessoa implements Avaliavel {

    protected double salarioBase;
    protected List<Double> notas; // notas recebidas em avaliacoes

    public Funcionario(String nome, String cpf, String email, double salarioBase) {
        // super(...) = chama o construtor da MAE (Pessoa) pra ela cuidar do nome/cpf/email.
        super(nome, cpf, email);
        setSalarioBase(salarioBase);
        this.notas = new ArrayList<>(); // lista comeca vazia
    }

    // METODO ABSTRATO (sem corpo — cada filha escreve o seu)
    public abstract double calcularSalarioLiquido();

    // Getter e setter do salario
    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) {this.salarioBase = salarioBase;}

    // METODOS DA INTERFACE Avaliavel (cumprindo o contrato)
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
        // FOREACH: "para cada nota n dentro da lista notas, faca..."
        for (double n : notas) {
            soma = soma + n;
        }
        return soma / notas.size();
    }
}

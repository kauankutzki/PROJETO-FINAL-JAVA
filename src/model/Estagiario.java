package model;

/**
 * Estagiario — terceira filha de Funcionario.
 *
 * Regra simplificada: recebe a bolsa (salarioBase) + auxilio fixo de R$ 220.
 * Sem desconto.
 *
 * Tres filhas, tres formulas diferentes para a MESMA pergunta.
 * Esse e o ponto do polimorfismo.
 */
public class Estagiario extends Funcionario {

    public Estagiario(String nome, String cpf, String email, double salarioBase) {
        super(nome, cpf, email, salarioBase);
    }

    // SOBRESCRITA: bolsa + auxilio fixo.
    @Override
    public double calcularSalarioLiquido() {
        return salarioBase + 220;
    }
}

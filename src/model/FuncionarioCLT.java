package model;

/**
 * FuncionarioCLT — primeira filha de Funcionario.
 *
 * Essa SIM pode ser criada com "new", porque agora sabemos o regime
 * e sabemos calcular o salario.
 *
 * Aqui acontece a SOBRESCRITA (@Override):
 * a mae declarou calcularSalarioLiquido() sem corpo,
 * a filha escreve a versao DELA.
 *
 * Regra simplificada do CLT: salario base menos 11% de desconto (INSS+IR juntos).
 */
public class FuncionarioCLT extends Funcionario {

    public FuncionarioCLT(String nome, String cpf, String email, double salarioBase) {
        super(nome, cpf, email, salarioBase);
    }

    // SOBRESCRITA: CLT tem desconto.
    @Override
    public double calcularSalarioLiquido() {
        double desconto = salarioBase * 0.11; // 11% de desconto
        return salarioBase - desconto;
    }
}

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
    private static final double desconto_Clt = 0.11;

    public FuncionarioCLT(String nome, String cpf, String email, double salarioBase) {
        super(nome, cpf, email, salarioBase);
    }

    // SOBRESCRITA: CLT tem desconto.
    @Override
    public double calcularSalarioLiquido() {
        return salarioBase - (salarioBase * desconto_Clt);
    }
}

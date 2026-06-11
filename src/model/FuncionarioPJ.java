package model;

/**
 * FuncionarioPJ — segunda filha de Funcionario.
 *
 * PJ presta servico pela empresa dele (tem CNPJ).
 *
 * Regra simplificada do PJ: recebe o valor cheio do contrato, SEM desconto.
 *
 * Repare: a mesma pergunta ("qual o salario liquido?") tem
 * resposta DIFERENTE da do CLT. Isso e POLIMORFISMO.
 */
public class FuncionarioPJ extends Funcionario {

    public FuncionarioPJ(String nome, String cpf, String email, double salarioBase) {
        super(nome, cpf, email, salarioBase);
    }

    // SOBRESCRITA: PJ nao tem desconto, recebe tudo.
    @Override
    public double calcularSalarioLiquido() {
        return salarioBase;
    }
}

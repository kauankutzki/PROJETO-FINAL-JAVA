package model;

public class FuncionarioCLT extends Funcionario {
    private static final double desconto_Clt = 0.11;

    public FuncionarioCLT(String nome, String cpf, String email, double salarioBase) {
        super(nome, cpf, email, salarioBase);
    }

    @Override
    public double calcularSalarioLiquido() {
        return salarioBase - (salarioBase * desconto_Clt);
    }
}

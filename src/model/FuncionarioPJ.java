package model;

public class FuncionarioPJ extends Funcionario {

    public FuncionarioPJ(String nome, String cpf, String email, double salarioBase) {
        super(nome, cpf, email, salarioBase);
    }

    @Override
    public double calcularSalarioLiquido() {
        return salarioBase;
    }
}

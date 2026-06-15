package model;

public class Estagiario extends Funcionario {

    private static final double AUXILIO_TRANSPORTE = 220.0;

    private String instituicaoEnsino;

    public Estagiario(String nome, String cpf, String email, double salarioBase,
                      String instituicaoEnsino) {
        super(nome, cpf, email, salarioBase);
        this.instituicaoEnsino = instituicaoEnsino;
    }

    @Override
    public double calcularSalarioLiquido() {
        return salarioBase + AUXILIO_TRANSPORTE;
    }

    public String getInstituicaoEnsino() { return instituicaoEnsino; }
}

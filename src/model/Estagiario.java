package model;

/**
 * Estagiario — terceira filha de Funcionario.
 *
 * Regra simplificada: recebe a bolsa (salarioBase) + auxilio transporte de R$ 220.
 * Sem desconto.
 *
 * Tres filhas, tres formulas diferentes para a MESMA pergunta.
 * Esse e o ponto do polimorfismo.
 */
public class Estagiario extends Funcionario {

    private static final double AUXILIO_TRANSPORTE = 220.0;

    private String instituicaoEnsino;

    public Estagiario(String nome, String cpf, String email, double salarioBase,
                      String instituicaoEnsino) {
        super(nome, cpf, email, salarioBase);
        this.instituicaoEnsino = instituicaoEnsino;
    }

    // SOBRESCRITA: bolsa + auxilio transporte fixo.
    @Override
    public double calcularSalarioLiquido() {
        return salarioBase + AUXILIO_TRANSPORTE;
    }

    public String getInstituicaoEnsino() { return instituicaoEnsino; }
}

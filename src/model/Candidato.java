package model;

import model.enums.StatusCandidato;

/**
 * Candidato — pessoa em processo seletivo.
 *
 * "extends Pessoa" = HERANCA.
 * O Candidato ja ganha de presente nome, cpf e email da Pessoa.
 * Nao precisa reescrever, so usar.
 *
 * ASSOCIACAO:
 * - Candidato --> Vaga: o candidato se inscreveu para uma vaga ja cadastrada.
 *
 * Candidato pertence ao modulo do Aluno 5 (Recrutamento).
 */
public class Candidato extends Pessoa {

    private static int contadorId = 1; // gerador simples de id unico

    private int id;
    private Vaga vagaPretendida;       // ASSOCIACAO
    private double pretensaoSalarial;
    private StatusCandidato statusProcesso;
    private String curriculoResumo;

    // CONSTRUTOR
    public Candidato(String nome, String cpf, String email, Vaga vagaPretendida,
                      double pretensaoSalarial, String curriculoResumo) {
        // super(...) = chama o construtor da MAE (Pessoa) pra ela cuidar do nome/cpf/email.
        super(nome, cpf, email);
        this.id = contadorId++;
        this.vagaPretendida = vagaPretendida;
        this.pretensaoSalarial = pretensaoSalarial;
        this.curriculoResumo = curriculoResumo;
        this.statusProcesso = StatusCandidato.INSCRITO; // todo candidato comeca INSCRITO
    }

    /**
     * Avanca o candidato para a proxima etapa do processo seletivo.
     * INSCRITO -> ENTREVISTA -> APROVADO.
     * Um candidato REPROVADO ou ja APROVADO nao avanca mais.
     */
    public void avancarEtapa() {
        switch (statusProcesso) {
            case INSCRITO:
                statusProcesso = StatusCandidato.ENTREVISTA;
                break;
            case ENTREVISTA:
                statusProcesso = StatusCandidato.APROVADO;
                break;
            case APROVADO:
            case REPROVADO:
                // ja chegou ao fim do processo, nao faz nada
                break;
        }
    }

    /** Marca o candidato como reprovado, encerrando o processo dele. */
    public void reprovar() {
        statusProcesso = StatusCandidato.REPROVADO;
    }

    // --- GETTERS ---
    public int getId() { return id; }
    public Vaga getVagaPretendida() { return vagaPretendida; }
    public double getPretensaoSalarial() { return pretensaoSalarial; }
    public StatusCandidato getStatusProcesso() { return statusProcesso; }
    public String getCurriculoResumo() { return curriculoResumo; }

    // --- SETTERS (com validacao basica) ---
    public void setVagaPretendida(Vaga vagaPretendida) {
        if (vagaPretendida != null) {
            this.vagaPretendida = vagaPretendida;
        }
    }

    public void setPretensaoSalarial(double pretensaoSalarial) {
        if (pretensaoSalarial >= 0) {
            this.pretensaoSalarial = pretensaoSalarial;
        }
    }

    public void setCurriculoResumo(String curriculoResumo) {
        if (curriculoResumo != null && !curriculoResumo.trim().isEmpty()) {
            this.curriculoResumo = curriculoResumo;
        }
    }

    // SOBRESCRITA de toString: representacao textual do objeto.
    @Override
    public String toString() {
        String descricaoVaga = (vagaPretendida != null)
                ? "vaga [" + vagaPretendida.getId() + "]"
                : "sem vaga";
        return String.format("[%d] %s | CPF: %s | Pretensao: R$ %.2f | %s | Status: %s",
                id, getNome(), getCpf(), pretensaoSalarial, descricaoVaga, statusProcesso);
    }
}

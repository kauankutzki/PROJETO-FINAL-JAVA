package model;

import model.enums.StatusCandidato;

public class Candidato extends Pessoa {

    private static int contadorId = 1; 

    private int id;
    private Vaga vagaPretendida;       
    private double pretensaoSalarial;
    private StatusCandidato statusProcesso;
    private String curriculoResumo;

    
    public Candidato(String nome, String cpf, String email, Vaga vagaPretendida,
                      double pretensaoSalarial, String curriculoResumo) {
        super(nome, cpf, email);
        this.id = contadorId++;
        this.vagaPretendida = vagaPretendida;
        this.pretensaoSalarial = pretensaoSalarial;
        this.curriculoResumo = curriculoResumo;
        this.statusProcesso = StatusCandidato.INSCRITO; 
    }

    
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
                break;
        }
    }

    public void reprovar() {
        statusProcesso = StatusCandidato.REPROVADO;
    }

    public int getId() { return id; }
    public Vaga getVagaPretendida() { return vagaPretendida; }
    public double getPretensaoSalarial() { return pretensaoSalarial; }
    public StatusCandidato getStatusProcesso() { return statusProcesso; }
    public String getCurriculoResumo() { return curriculoResumo; }

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

    @Override
    public String toString() {
        String descricaoVaga = (vagaPretendida != null)
                ? "vaga [" + vagaPretendida.getId() + "]"
                : "sem vaga";
        return String.format("[%d] %s | CPF: %s | Pretensao: R$ %.2f | %s | Status: %s",
                id, getNome(), getCpf(), pretensaoSalarial, descricaoVaga, statusProcesso);
    }
}

package model;

import model.enums.StatusVaga;

/**
 * Vaga — representa uma posicao aberta na empresa (ex.: "2 vagas de Dev Pleno em TI").
 *
 * ENCAPSULAMENTO:
 * - Todos os atributos sao "private": ninguem acessa diretamente.
 * - Acesso so pelos getters e setters.
 *
 * ASSOCIACAO:
 * - Vaga --> Cargo: a vaga e para um cargo ja cadastrado (Aluno 2).
 * - Vaga --> Departamento: a vaga pertence a um departamento ja cadastrado (Aluno 2).
 * - Os tres existem de forma independente.
 *
 * Vaga pertence ao modulo do Aluno 5 (Recrutamento).
 */
public class Vaga {

    private static int contadorId = 1; // gerador simples de id unico

    private int id;
    private Cargo cargo;               // ASSOCIACAO
    private Departamento departamento; // ASSOCIACAO
    private int quantidade;
    private StatusVaga status;

    // CONSTRUTOR
    public Vaga(Cargo cargo, Departamento departamento, int quantidade) {
        this.id = contadorId++;
        this.cargo = cargo;
        this.departamento = departamento;
        this.quantidade = quantidade;
        this.status = StatusVaga.ABERTA; // toda vaga nasce ABERTA
    }

    /**
     * Diminui em 1 a quantidade de posicoes disponiveis nesta vaga.
     * Usado quando um candidato e contratado (RecrutamentoService).
     * Se a quantidade chegar a zero, a vaga e encerrada automaticamente.
     */
    public void diminuirQuantidade() {
        if (quantidade > 0) {
            quantidade--;
        }
        if (quantidade == 0) {
            status = StatusVaga.FECHADA;
        }
    }

    // --- GETTERS ---
    public int getId() { return id; }
    public Cargo getCargo() { return cargo; }
    public Departamento getDepartamento() { return departamento; }
    public int getQuantidade() { return quantidade; }
    public StatusVaga getStatus() { return status; }

    // --- SETTERS (com validacao basica) ---
    public void setCargo(Cargo cargo) {
        if (cargo != null) {
            this.cargo = cargo;
        }
    }

    public void setDepartamento(Departamento departamento) {
        if (departamento != null) {
            this.departamento = departamento;
        }
    }

    public void setQuantidade(int quantidade) {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        }
    }

    public void setStatus(StatusVaga status) {
        if (status != null) {
            this.status = status;
        }
    }

    // SOBRESCRITA de toString: representacao textual do objeto.
    @Override
    public String toString() {
        String nomeCargo = (cargo != null) ? cargo.getTitulo() : "Sem cargo";
        String nomeDepto = (departamento != null) ? departamento.getNome() : "Sem departamento";
        return String.format("[%d] Cargo: %s | Departamento: %s | Quantidade: %d | Status: %s",
                id, nomeCargo, nomeDepto, quantidade, status);
    }
}

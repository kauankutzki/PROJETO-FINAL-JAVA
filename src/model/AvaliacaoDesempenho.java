package model;

import java.time.LocalDate;

public class AvaliacaoDesempenho {

    private int id;
    private Funcionario avaliado;
    private Funcionario avaliador;
    private double nota;
    private String comentario;
    private LocalDate data;

    public AvaliacaoDesempenho(
            int id,
            Funcionario avaliado,
            Funcionario avaliador,
            double nota,
            String comentario
    ) {
        this.id = id;
        this.avaliado = avaliado;
        this.avaliador = avaliador;
        this.nota = nota;
        this.comentario = comentario;

        this.data = LocalDate.now();

        avaliado.adicionarNota(nota);
    }

    public int getId() {
        return id;
    }

    public Funcionario getAvaliado() {
        return avaliado;
    }

    public Funcionario getAvaliador() {
        return avaliador;
    }

    public double getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return
                "\nAvaliacao #" + id +
                        "\nAvaliado: " + avaliado.getNome() +
                        "\nAvaliador: " + avaliador.getNome() +
                        "\nNota: " + nota +
                        "\nComentario: " + comentario +
                        "\nData: " + data;
    }
}
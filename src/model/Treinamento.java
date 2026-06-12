package model;

import java.util.ArrayList;
import java.util.List;

public class Treinamento {

    private int id;
    private String titulo;
    private int cargaHoraria;
    private double custoPorParticipante;
    private List<Funcionario> participantes;

    public Treinamento(int id, String titulo, int cargaHoraria, double custoPorParticipante) {
        this.id = id;
        this.titulo = titulo;
        this.cargaHoraria = cargaHoraria;
        this.custoPorParticipante = custoPorParticipante;
        this.participantes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public double getCustoPorParticipante() {
        return custoPorParticipante;
    }

    public List<Funcionario> getParticipantes() {
        return participantes;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public void setCustoPorParticipante(double custoPorParticipante) {
        this.custoPorParticipante = custoPorParticipante;
    }

    public void adicionarParticipante(Funcionario funcionario) {
        participantes.add(funcionario);
    }

    public double calcularCustoTotal() {
        return participantes.size() * custoPorParticipante;
    }

    @Override
    public String toString() {
        return
                "\nTreinamento #" + id +
                        "\nTitulo: " + titulo +
                        "\nCarga horaria: " + cargaHoraria + " horas" +
                        "\nCusto por participante: R$ " + custoPorParticipante +
                        "\nQuantidade de participantes: " + participantes.size() +
                        "\nCusto total: R$ " + calcularCustoTotal();
    }
}
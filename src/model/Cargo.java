package model;

import model.enums.NivelCargo;

public class Cargo {

    private static int contadorId = 1; 

    private int id;
    private String titulo;
    private NivelCargo nivel;       
    private double salarioMinimo;
    private double salarioMaximo;

    public Cargo(String titulo, NivelCargo nivel, double salarioMinimo, double salarioMaximo) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.nivel = nivel;
        this.salarioMinimo = salarioMinimo;
        this.salarioMaximo = salarioMaximo;
    }

    public boolean salarioDentroDaFaixa(double valor) {
        return valor >= salarioMinimo && valor <= salarioMaximo;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public NivelCargo getNivel() { return nivel; }
    public double getSalarioMinimo() { return salarioMinimo; }
    public double getSalarioMaximo() { return salarioMaximo; }

    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty()) {
            this.titulo = titulo;
        }
    }

    public void setNivel(NivelCargo nivel) {
        if (nivel != null) {
            this.nivel = nivel;
        }
    }

    public void setSalarioMinimo(double salarioMinimo) {
        if (salarioMinimo >= 0) {
            this.salarioMinimo = salarioMinimo;
        }
    }

    public void setSalarioMaximo(double salarioMaximo) {
        if (salarioMaximo >= salarioMinimo) {
            this.salarioMaximo = salarioMaximo;
        }
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | Nivel: %s | Faixa: R$ %.2f - R$ %.2f",
                id, titulo, nivel, salarioMinimo, salarioMaximo);
    }
}

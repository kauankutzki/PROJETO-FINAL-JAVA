package model;

import java.io.Serializable;

public class Holerite implements Serializable {

    private Funcionario funcionario;
    private double totalProventos;
    private double totalDescontos;
    private double valorLiquido;

    public Holerite(Funcionario funcionario) {
        setFuncionario(funcionario);
        calcularValores();
    }

    private void calcularValores() {
        if (funcionario == null) {
            totalProventos = 0;
            totalDescontos = 0;
            valorLiquido = 0;
        } else {
            totalProventos = funcionario.getSalarioBase();
            valorLiquido = funcionario.calcularSalarioLiquido();
            totalDescontos = totalProventos - valorLiquido;

            if (totalDescontos < 0) {
                totalDescontos = 0;
            }
        }
    }

    public String imprimir() {
        if (funcionario == null) {
            return "Holerite sem funcionario vinculado.";
        }

        return "Funcionario: " + funcionario.getNome()
                + " | Proventos: R$ " + totalProventos
                + " | Descontos: R$ " + totalDescontos
                + " | Liquido: R$ " + valorLiquido;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public double getTotalProventos() {
        return totalProventos;
    }

    public void setTotalProventos(double totalProventos) {
        this.totalProventos = totalProventos;
    }

    public double getTotalDescontos() {
        return totalDescontos;
    }

    public void setTotalDescontos(double totalDescontos) {
        this.totalDescontos = totalDescontos;
    }

    public double getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(double valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    @Override
    public String toString() {
        return imprimir();
    }
}

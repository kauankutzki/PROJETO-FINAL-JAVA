package model;
import java.io.Serializable;

public class Holerite implements Serializable{
    private Funcionario funcionario;
    private double totalProventos;
    private double totalDescontos;
    private double valorLiquido;

    public Holerite(Funcionario funcionario) {
        this.funcionario = funcionario;
        calcularValores();
    }
    private void calcularValores(){
        if (funcionario == null) {
            totalProventos = 0;
            totalDescontos = 0;
            valorLiquido = 0;
            return;
        }
        totalProventos = funcionario.getSalarioBase(); + fu
    }

}

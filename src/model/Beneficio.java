package model;
import java.io.Serializable;

import model.enums.TipoBeneficio;
import model.interfaces.Identificavel;

public class Beneficio implements Identificavel, Serializable {
    private int id;
    private String nome;
    private TipoBeneficio tipo;
    private double valorMensal;

    public Beneficio(String nome, TipoBeneficio tipo, double valorMensal) {
        setNome(nome);
        setTipo(tipo);
        setValorMensal(valorMensal);

    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public TipoBeneficio getTipo(){
        return tipo;
    }
    public void setTipo(TipoBeneficio tipo){
        this.tipo = tipo;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        if (valorMensal > 0) {
            this.valorMensal = valorMensal;
        }
    }
    @Override
    public int getId(){
        return id;
    }
    @Override
    public void setId(int id){
        this.id=id;
    }
    @Override
    public String toString() {
        return "id" + id + "|" + nome + "|" + tipo + "|R$" + String.format("%.2f", valorMensal);
    }
}




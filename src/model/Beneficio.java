package model;

import exceptions.ValorInvalidoException;
import java.io.Serializable;
import model.enums.TipoBeneficio;
import model.interfaces.Identificavel;

public class Beneficio implements Identificavel, Serializable {

    private int id;
    private String nome;
    private TipoBeneficio tipo;
    private double valorMensal;

    public Beneficio(String nome, TipoBeneficio tipo, double valorMensal)
            throws ValorInvalidoException {
        setNome(nome);
        setTipo(tipo);
        setValorMensal(valorMensal);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws ValorInvalidoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValorInvalidoException("Nome do beneficio nao pode ser vazio.");
        }
        this.nome = nome;
    }

    public TipoBeneficio getTipo() {
        return tipo;
    }

    public void setTipo(TipoBeneficio tipo) throws ValorInvalidoException {
        if (tipo == null) {
            throw new ValorInvalidoException("Tipo do beneficio invalido.");
        }
        this.tipo = tipo;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) throws ValorInvalidoException {
        if (valorMensal <= 0) {
            throw new ValorInvalidoException("Valor mensal deve ser maior que zero.");
        }
        this.valorMensal = valorMensal;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Nome: " + nome
                + " | Tipo: " + tipo
                + " | Valor mensal: R$ " + valorMensal;
    }
}

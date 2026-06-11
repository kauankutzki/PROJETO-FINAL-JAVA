package model;

import exceptions.ValorInvalidoException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import model.interfaces.Identificavel;

public class FolhaPagamento implements Identificavel, Serializable {

    private int id;
    private int mes;
    private int ano;
    private Map<String, Holerite> holerites;

    public FolhaPagamento(int mes, int ano) throws ValorInvalidoException {
        setMes(mes);
        setAno(ano);
        setHolerites(new HashMap<String, Holerite>());
    }

    public void adicionarHolerite(Holerite holerite) {
        if (holerite != null && holerite.getFuncionario() != null) {
            String nomeFuncionario = holerite.getFuncionario().getNome();
            holerites.put(nomeFuncionario, holerite);
        }
    }

    public Holerite buscarHolerite(String nomeFuncionario) {
        return holerites.get(nomeFuncionario);
    }

    public double calcularTotalFolha() {
        double total = 0;

        for (Holerite holerite : holerites.values()) {
            total = total + holerite.getValorLiquido();
        }

        return total;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) throws ValorInvalidoException {
        if (mes < 1 || mes > 12) {
            throw new ValorInvalidoException("Mes invalido.");
        }
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) throws ValorInvalidoException {
        if (ano <= 0) {
            throw new ValorInvalidoException("Ano invalido.");
        }
        this.ano = ano;
    }

    public Map<String, Holerite> getHolerites() {
        return holerites;
    }

    public void setHolerites(Map<String, Holerite> holerites) {
        this.holerites = holerites;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Folha de pagamento: " + mes + "/" + ano
                + " | Total: R$ " + calcularTotalFolha();
    }
}

package model;

import java.util.ArrayList;
import java.util.List;

public class Departamento {

    private static int contadorId = 1; 

    private int id;
    private String nome;
    private String sigla;
    private double orcamentoMensal;
    private Funcionario gestor;                 
    private List<Funcionario> funcionarios;      

    public Departamento(String nome, String sigla, double orcamentoMensal) {
        this.id = contadorId++;
        this.nome = nome;
        this.sigla = sigla;
        this.orcamentoMensal = orcamentoMensal;
        this.funcionarios = new ArrayList<>();   
    }

    public void adicionarFuncionario(Funcionario f) {
        if (f != null && !funcionarios.contains(f)) {
            funcionarios.add(f);
        }
    }

    public void removerFuncionario(String cpf) {
        funcionarios.removeIf(f -> f.getCpf().equals(cpf));
    }

    public double calcularCustoFolha() {
        double total = 0;
        for (Funcionario f : funcionarios) {
            total += f.calcularSalarioLiquido(); 
        }
        return total;
    }

    public int quantidadeFuncionarios() {
        return funcionarios.size();
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getSigla() { return sigla; }
    public double getOrcamentoMensal() { return orcamentoMensal; }
    public Funcionario getGestor() { return gestor; }
    public List<Funcionario> getFuncionarios() { return funcionarios; }

    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        }
    }

    public void setSigla(String sigla) {
        if (sigla != null && !sigla.trim().isEmpty()) {
            this.sigla = sigla;
        }
    }

    public void setOrcamentoMensal(double orcamentoMensal) {
        if (orcamentoMensal >= 0) {
            this.orcamentoMensal = orcamentoMensal;
        }
    }

    public void setGestor(Funcionario gestor) {
        this.gestor = gestor;
    }

    @Override
    public String toString() {
        String nomeGestor = (gestor != null) ? gestor.getNome() : "Sem gestor";
        return String.format("[%d] %s (%s) | Orcamento: R$ %.2f | Gestor: %s | Funcionarios: %d",
                id, nome, sigla, orcamentoMensal, nomeGestor, funcionarios.size());
    }
}

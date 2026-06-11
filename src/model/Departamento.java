package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Departamento — representa um setor da empresa (ex.: "Tecnologia da Informacao").
 *
 * AGREGACAO (Departamento "tem" funcionarios, mas nao eh dono do ciclo de vida deles):
 * - O Departamento guarda uma lista de Funcionarios.
 * - Se o Departamento for excluido, os Funcionarios CONTINUAM EXISTINDO.
 * - Simbolo UML: losango vazio (◇)
 * - Diferente de Composicao (◆), onde a parte morre com o todo.
 *
 * ASSOCIACAO (Departamento --> Funcionario gestor):
 * - O gestor eh um Funcionario ja cadastrado.
 * - Os dois existem de forma independente.
 *
 * Departamento pertence ao modulo do Aluno 2 (Estrutura Organizacional).
 */
public class Departamento {

    private static int contadorId = 1; // gerador simples de id unico

    private int id;
    private String nome;
    private String sigla;
    private double orcamentoMensal;
    private Funcionario gestor;                  // ASSOCIACAO
    private List<Funcionario> funcionarios;      // AGREGACAO

    // CONSTRUTOR
    public Departamento(String nome, String sigla, double orcamentoMensal) {
        this.id = contadorId++;
        this.nome = nome;
        this.sigla = sigla;
        this.orcamentoMensal = orcamentoMensal;
        this.funcionarios = new ArrayList<>();   // lista comeca vazia
    }

    /**
     * Adiciona um funcionario ao departamento.
     * AGREGACAO: o funcionario ja existia antes; aqui apenas referenciamos ele.
     */
    public void adicionarFuncionario(Funcionario f) {
        if (f != null && !funcionarios.contains(f)) {
            funcionarios.add(f);
        }
    }

    /**
     * Remove um funcionario pelo CPF.
     * O funcionario continua existindo no sistema, apenas sai deste departamento.
     */
    public void removerFuncionario(String cpf) {
        // FOREACH com iterator implicito — varre a lista procurando pelo cpf.
        funcionarios.removeIf(f -> f.getCpf().equals(cpf));
    }

    /**
     * Calcula o custo total da folha deste departamento.
     *
     * POLIMORFISMO em acao:
     * - A lista eh do tipo Funcionario (mae abstrata).
     * - Mas em runtime cada objeto pode ser CLT, PJ ou Estagiario.
     * - Cada um aplica a formula DELE ao chamar calcularSalarioLiquido().
     * - Mesma chamada, resultados diferentes — isso e ligacao dinamica.
     */
    public double calcularCustoFolha() {
        double total = 0;
        for (Funcionario f : funcionarios) {
            total += f.calcularSalarioLiquido(); // polimorfismo aqui!
        }
        return total;
    }

    /**
     * Retorna quantos funcionarios estao lotados neste departamento.
     * Usado para impedir a exclusao de departamento com funcionarios ativos.
     */
    public int quantidadeFuncionarios() {
        return funcionarios.size();
    }

    // --- GETTERS ---
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getSigla() { return sigla; }
    public double getOrcamentoMensal() { return orcamentoMensal; }
    public Funcionario getGestor() { return gestor; }
    public List<Funcionario> getFuncionarios() { return funcionarios; }

    // --- SETTERS ---
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

    // SOBRESCRITA de toString.
    @Override
    public String toString() {
        String nomeGestor = (gestor != null) ? gestor.getNome() : "Sem gestor";
        return String.format("[%d] %s (%s) | Orcamento: R$ %.2f | Gestor: %s | Funcionarios: %d",
                id, nome, sigla, orcamentoMensal, nomeGestor, funcionarios.size());
    }
}

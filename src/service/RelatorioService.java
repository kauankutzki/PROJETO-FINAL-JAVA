package service;

import model.Departamento;
import model.Funcionario;
import repository.RepositorioDepartamento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RelatorioService — regras de negocio que cruzam mais de uma entidade.
 *
 * SERVICE: quando uma logica envolve varias entidades ao mesmo tempo,
 * ela nao cabe bem nem no Model nem no Controller.
 * O Service e o lugar certo para isso.
 *
 * COLECOES usadas aqui:
 * - Map<Departamento, Double>: mapeia cada departamento ao seu custo de folha.
 *   Map e um "dicionario": chave (Departamento) -> valor (custo em reais).
 * - List<Funcionario>: para o ranking de avaliacao.
 *
 * Pertence ao modulo do Aluno 2 (Estrutura Organizacional).
 */
public class RelatorioService {

    private RepositorioDepartamento repositorioDepartamento;

    public RelatorioService(RepositorioDepartamento repositorioDepartamento) {
        this.repositorioDepartamento = repositorioDepartamento;
    }

    /**
     * Gera um mapa com o custo de folha de cada departamento.
     *
     * MAP em acao: cada departamento e uma "chave" e o custo calculado e o "valor".
     *
     * Demonstra POLIMORFISMO: calcularCustoFolha() chama calcularSalarioLiquido()
     * em cada funcionario — CLT, PJ e Estagiario respondem com suas proprias regras.
     */
    public Map<Departamento, Double> custoPorDepartamento() {
        Map<Departamento, Double> mapa = new HashMap<>();

        // FOR: percorre todos os departamentos pelo indice (variante com contador).
        List<Departamento> lista = repositorioDepartamento.listarTodos();
        for (int i = 0; i < lista.size(); i++) {
            Departamento dep = lista.get(i);
            double custo = dep.calcularCustoFolha(); // polimorfismo interno
            mapa.put(dep, custo);
        }

        return mapa;
    }

    /**
     * Formata o relatorio de custo por departamento como texto para exibir na View.
     */
    public String gerarTextoRelatorio() {
        Map<Departamento, Double> mapa = custoPorDepartamento();

        if (mapa.isEmpty()) {
            return "Nenhum departamento cadastrado.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n===== RELATORIO: CUSTO POR DEPARTAMENTO =====\n");

        double totalGeral = 0;

        // FOREACH sobre as entradas do Map (par chave-valor).
        for (Map.Entry<Departamento, Double> entrada : mapa.entrySet()) {
            Departamento dep = entrada.getKey();
            double custo = entrada.getValue();
            totalGeral += custo;

            sb.append(String.format("%-25s (%s) | Funcionarios: %2d | Custo folha: R$ %10.2f%n",
                    dep.getNome(),
                    dep.getSigla(),
                    dep.quantidadeFuncionarios(),
                    custo));
        }

        sb.append("---------------------------------------------\n");
        sb.append(String.format("TOTAL GERAL DA FOLHA: R$ %.2f%n", totalGeral));
        return sb.toString();
    }

    /**
     * Ranking de funcionarios por media de avaliacao (todos os departamentos).
     * WHILE: constroi a lista ate cobrir todos os departamentos.
     */
    public List<Funcionario> rankingAvaliacoes() {
        List<Funcionario> todos = new ArrayList<>();
        List<Departamento> departamentos = repositorioDepartamento.listarTodos();

        int i = 0;
        // WHILE: alternativa ao for, usada aqui para variar as estruturas de repeticao.
        while (i < departamentos.size()) {
            for (Funcionario f : departamentos.get(i).getFuncionarios()) {
                if (!todos.contains(f)) {
                    todos.add(f);
                }
            }
            i++;
        }

        // Ordena do maior para o menor usando Comparable anonimo.
        todos.sort((a, b) -> Double.compare(b.calcularMediaAvaliacoes(), a.calcularMediaAvaliacoes()));
        return todos;
    }
}

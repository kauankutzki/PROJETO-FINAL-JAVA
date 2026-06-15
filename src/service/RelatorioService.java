package service;

import model.Departamento;
import model.Funcionario;
import repository.RepositorioDepartamento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioService {

    private RepositorioDepartamento repositorioDepartamento;

    public RelatorioService(RepositorioDepartamento repositorioDepartamento) {
        this.repositorioDepartamento = repositorioDepartamento;
    }

    public Map<Departamento, Double> custoPorDepartamento() {
        Map<Departamento, Double> mapa = new HashMap<>();

        List<Departamento> lista = repositorioDepartamento.listarTodos();
        for (int i = 0; i < lista.size(); i++) {
            Departamento dep = lista.get(i);
            double custo = dep.calcularCustoFolha(); 
            mapa.put(dep, custo);
        }

        return mapa;
    }

    public String gerarTextoRelatorio() {
        Map<Departamento, Double> mapa = custoPorDepartamento();

        if (mapa.isEmpty()) {
            return "Nenhum departamento cadastrado.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n===== RELATORIO: CUSTO POR DEPARTAMENTO =====\n");

        double totalGeral = 0;

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

    public List<Funcionario> rankingAvaliacoes() {
        List<Funcionario> todos = new ArrayList<>();
        List<Departamento> departamentos = repositorioDepartamento.listarTodos();

        int i = 0;

        while (i < departamentos.size()) {
            for (Funcionario f : departamentos.get(i).getFuncionarios()) {
                if (!todos.contains(f)) {
                    todos.add(f);
                }
            }
            i++;
        }

        todos.sort((a, b) -> Double.compare(b.calcularMediaAvaliacoes(), a.calcularMediaAvaliacoes()));
        return todos;
    }
}

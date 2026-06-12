package controller;

import model.AvaliacaoDesempenho;
import repository.RepositorioAvaliacao;

import java.util.List;

public class AvaliacaoController {

    private RepositorioAvaliacao repositorio;

    public AvaliacaoController() {
        repositorio = new RepositorioAvaliacao();
    }

    public void cadastrar(AvaliacaoDesempenho avaliacao) {
        repositorio.salvar(avaliacao);
    }

    public void excluir(int id) {
        repositorio.excluir(id);
    }

    public List<AvaliacaoDesempenho> listarTodas() {
        return repositorio.listarTodos();
    }

    public AvaliacaoDesempenho buscar(int id) {
        return repositorio.buscarPorId(id);
    }

    public void atualizar(AvaliacaoDesempenho avaliacao) {
        repositorio.atualizar(avaliacao);
    }
}
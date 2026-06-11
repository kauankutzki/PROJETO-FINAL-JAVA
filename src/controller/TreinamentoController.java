package controller;

import model.Funcionario;
import model.Treinamento;
import repository.RepositorioTreinamento;

import java.util.List;

public class TreinamentoController {

    private RepositorioTreinamento repositorio;

    public TreinamentoController() {
        repositorio = new RepositorioTreinamento();
    }

    public void cadastrar(Treinamento treinamento) {
        repositorio.salvar(treinamento);
    }

    public void excluir(int id) {
        repositorio.excluir(id);
    }

    public List<Treinamento> listarTodos() {
        return repositorio.listarTodos();
    }

    public Treinamento buscar(int id) {
        return repositorio.buscarPorId(id);
    }

    public void atualizar(Treinamento treinamento) {
        repositorio.atualizar(treinamento);
    }

    public void adicionarParticipante(
            int idTreinamento,
            Funcionario funcionario
    ) {

        Treinamento treinamento =
                repositorio.buscarPorId(idTreinamento);

        if (treinamento != null) {
            treinamento.adicionarParticipante(funcionario);
        }
    }
}
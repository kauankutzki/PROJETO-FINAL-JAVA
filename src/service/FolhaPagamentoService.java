package service;

import exceptions.ValorInvalidoException;
import java.util.List;
import model.FolhaPagamento;
import model.Funcionario;
import model.Holerite;
import repository.RepositorioFolhaPagamento;

public class FolhaPagamentoService {

    private RepositorioFolhaPagamento repositorio;

    public FolhaPagamentoService() {
        repositorio = new RepositorioFolhaPagamento();
    }

    public void gerarFolha(int mes, int ano, List<Funcionario> funcionarios)
            throws ValorInvalidoException {
        if (funcionarios == null || funcionarios.isEmpty()) {
            throw new ValorInvalidoException("Nao existem funcionarios cadastrados.");
        }

        FolhaPagamento folha = new FolhaPagamento(mes, ano);

        for (Funcionario funcionario : funcionarios) {
            Holerite holerite = new Holerite(funcionario);
            folha.adicionarHolerite(holerite);
        }

        repositorio.salvar(folha);
    }

    public boolean alterarMesAno(int id, int mes, int ano)
            throws ValorInvalidoException {
        FolhaPagamento folha = repositorio.buscarPorId(id);

        if (folha == null) {
            return false;
        }

        folha.setMes(mes);
        folha.setAno(ano);
        repositorio.atualizar(folha);
        return true;
    }

    public boolean excluir(int id) {
        FolhaPagamento folha = repositorio.buscarPorId(id);

        if (folha == null) {
            return false;
        }

        repositorio.excluir(id);
        return true;
    }

    public List<FolhaPagamento> listarTodas() {
        return repositorio.listarTodos();
    }

    public FolhaPagamento buscarPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    public Holerite consultarHolerite(int idFolha, String nomeFuncionario)
            throws ValorInvalidoException {
        FolhaPagamento folha = repositorio.buscarPorId(idFolha);

        if (folha == null) {
            throw new ValorInvalidoException("Folha nao encontrada.");
        }

        if (nomeFuncionario == null || nomeFuncionario.trim().isEmpty()) {
            throw new ValorInvalidoException("Nome do funcionario invalido.");
        }

        Holerite holerite = folha.buscarHolerite(nomeFuncionario);

        if (holerite == null) {
            throw new ValorInvalidoException("Holerite nao encontrado para esse funcionario.");
        }

        return holerite;
    }
}

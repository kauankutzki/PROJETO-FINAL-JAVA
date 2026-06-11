package controller;

import exceptions.ValorInvalidoException;
import java.util.List;
import model.FolhaPagamento;
import model.Funcionario;
import model.Holerite;
import service.FolhaPagamentoService;

public class FolhaPagamentoController {

    private FolhaPagamentoService service;
    private FuncionarioController funcionarioController;

    public FolhaPagamentoController(FuncionarioController funcionarioController) {
        service = new FolhaPagamentoService();
        setFuncionarioController(funcionarioController);
    }

    public void gerarFolha(int mes, int ano) throws ValorInvalidoException {
        List<Funcionario> funcionarios = funcionarioController.listarTodos();
        service.gerarFolha(mes, ano, funcionarios);
    }

    public boolean alterarMesAno(int id, int mes, int ano)
            throws ValorInvalidoException {
        return service.alterarMesAno(id, mes, ano);
    }

    public Holerite consultarHolerite(int idFolha, String nomeFuncionario)
            throws ValorInvalidoException {
        return service.consultarHolerite(idFolha, nomeFuncionario);
    }

    public boolean excluir(int id) {
        return service.excluir(id);
    }

    public List<FolhaPagamento> listarTodas() {
        return service.listarTodas();
    }

    public FolhaPagamento buscarPorId(int id) {
        return service.buscarPorId(id);
    }

    public FuncionarioController getFuncionarioController() {
        return funcionarioController;
    }

    public void setFuncionarioController(FuncionarioController funcionarioController) {
        this.funcionarioController = funcionarioController;
    }
}

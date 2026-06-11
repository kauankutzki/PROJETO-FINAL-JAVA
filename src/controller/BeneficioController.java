package controller;

import exceptions.ValorInvalidoException;
import java.util.List;
import model.Beneficio;
import service.BeneficioService;

public class BeneficioController {

    private BeneficioService service;

    public BeneficioController() {
        service = new BeneficioService();
    }

    public void cadastrar(String nome, String tipoTexto, double valorMensal)
            throws ValorInvalidoException {
        service.cadastrar(nome, tipoTexto, valorMensal);
    }

    public boolean alterarNome(int id, String novoNome)
            throws ValorInvalidoException {
        return service.alterarNome(id, novoNome);
    }

    public boolean alterarTipo(int id, String tipoTexto)
            throws ValorInvalidoException {
        return service.alterarTipo(id, tipoTexto);
    }

    public boolean alterarValor(int id, double novoValor)
            throws ValorInvalidoException {
        return service.alterarValor(id, novoValor);
    }

    public boolean excluir(int id) {
        return service.excluir(id);
    }

    public List<Beneficio> listarTodos() {
        return service.listarTodos();
    }

    public Beneficio buscarPorId(int id) {
        return service.buscarPorId(id);
    }
}

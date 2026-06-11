package service;

import exceptions.ValorInvalidoException;
import java.util.List;
import model.Beneficio;
import model.enums.TipoBeneficio;
import repository.RepositorioBeneficio;

public class BeneficioService {

    private RepositorioBeneficio repositorio;

    public BeneficioService() {
        repositorio = new RepositorioBeneficio();
    }

    public void cadastrar(String nome, String tipoTexto, double valorMensal)
            throws ValorInvalidoException {
        TipoBeneficio tipo = converterTipo(tipoTexto);
        Beneficio beneficio = new Beneficio(nome, tipo, valorMensal);
        repositorio.salvar(beneficio);
    }

    public boolean alterarNome(int id, String novoNome)
            throws ValorInvalidoException {
        Beneficio beneficio = repositorio.buscarPorId(id);

        if (beneficio == null) {
            return false;
        }

        beneficio.setNome(novoNome);
        repositorio.atualizar(beneficio);
        return true;
    }

    public boolean alterarTipo(int id, String tipoTexto)
            throws ValorInvalidoException {
        Beneficio beneficio = repositorio.buscarPorId(id);

        if (beneficio == null) {
            return false;
        }

        beneficio.setTipo(converterTipo(tipoTexto));
        repositorio.atualizar(beneficio);
        return true;
    }

    public boolean alterarValor(int id, double novoValor)
            throws ValorInvalidoException {
        Beneficio beneficio = repositorio.buscarPorId(id);

        if (beneficio == null) {
            return false;
        }

        beneficio.setValorMensal(novoValor);
        repositorio.atualizar(beneficio);
        return true;
    }

    public boolean excluir(int id) {
        Beneficio beneficio = repositorio.buscarPorId(id);

        if (beneficio == null) {
            return false;
        }

        repositorio.excluir(id);
        return true;
    }

    public List<Beneficio> listarTodos() {
        return repositorio.listarTodos();
    }

    public Beneficio buscarPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    private TipoBeneficio converterTipo(String texto) throws ValorInvalidoException {
        if (texto == null) {
            throw new ValorInvalidoException("Tipo invalido.");
        }

        switch (texto.trim().toUpperCase()) {
            case "ALIMENTACAO":
                return TipoBeneficio.ALIMENTACAO;
            case "TRANSPORTE":
                return TipoBeneficio.TRANSPORTE;
            case "SAUDE":
                return TipoBeneficio.SAUDE;
            case "EDUCACAO":
                return TipoBeneficio.EDUCACAO;
            case "OUTRO":
                return TipoBeneficio.OUTRO;
            default:
                throw new ValorInvalidoException("Tipo invalido. Use ALIMENTACAO, TRANSPORTE, SAUDE, EDUCACAO ou OUTRO.");
        }
    }
}

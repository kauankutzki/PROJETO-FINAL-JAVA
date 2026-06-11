package controller;

import exception.DadoInvalidoException;
import model.Estagiario;

import java.util.ArrayList;
import java.util.List;

public class EstagiarioController {

    private List<Estagiario> estagiarios = new ArrayList<>();

    public void cadastrar(String nome, String cpf, String email, double bolsa,
                          String instituicao) throws DadoInvalidoException {
        if (bolsa <= 0) {
            throw new DadoInvalidoException("A bolsa deve ser maior que zero.");
        }
        estagiarios.add(new Estagiario(nome, cpf, email, bolsa, instituicao));
    }

    public List<Estagiario> listarTodos() {
        return estagiarios;
    }
}

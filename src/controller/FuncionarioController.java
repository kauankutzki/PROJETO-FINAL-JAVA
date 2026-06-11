package controller;

import exception.DadoInvalidoException;
import model.Funcionario;
import model.FuncionarioCLT;
import model.FuncionarioPJ;

import java.util.ArrayList;
import java.util.List;

/**
 * FuncionarioController — o "C" do MVC.
 *
 * Fluxo:
 *   View (tela) capta o que o usuario digitou
 *      -> Controller decide o que fazer
 *         -> guarda na lista
 *      <- Controller devolve o resultado
 *   <- View mostra na tela
 *
 * Aqui dentro fica a LISTA com todos os funcionarios cadastrados.
 */
public class FuncionarioController {

    // A lista que guarda todo mundo na memoria.
    private List<Funcionario> funcionarios = new ArrayList<>();

    // Cadastrar CLT
    public void cadastrarCLT(String nome, String cpf, String email, double salario) throws DadoInvalidoException {
        if (salario <= 0) {
            throw new DadoInvalidoException("O salario deve ser maior que zero.");
        }
        funcionarios.add(new FuncionarioCLT(nome, cpf, email, salario));
    }

    // Cadastrar PJ
    public void cadastrarPJ(String nome, String cpf, String email, double salario) throws DadoInvalidoException {
        if (salario <= 0) {
            throw new DadoInvalidoException("O salario deve ser maior que zero.");
        }
        funcionarios.add(new FuncionarioPJ(nome, cpf, email, salario));
    }

    // Devolver todos os funcionarios cadastrados.
    public List<Funcionario> listarTodos() {
        return funcionarios;
    }
}

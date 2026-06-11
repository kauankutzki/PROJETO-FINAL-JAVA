package controller;

import model.Estagiario;
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
    public void cadastrarCLT(String nome, String cpf, String email, double salario) {
        FuncionarioCLT novo = new FuncionarioCLT(nome, cpf, email, salario);
        funcionarios.add(novo);
    }

    // Cadastrar PJ
    public void cadastrarPJ(String nome, String cpf, String email, double salario) {
        FuncionarioPJ novo = new FuncionarioPJ(nome, cpf, email, salario);
        funcionarios.add(novo);
    }

    // Cadastrar Estagiario
    public void cadastrarEstagiario(String nome, String cpf, String email, double salario) {
        Estagiario novo = new Estagiario(nome, cpf, email, salario);
        funcionarios.add(novo);
    }

    // Devolver todos os funcionarios cadastrados.
    public List<Funcionario> listarTodos() {
        return funcionarios;
    }
}

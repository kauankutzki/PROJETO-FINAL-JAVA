package controller;

import exceptions.DadoInvalidoException;
import model.Funcionario;
import model.FuncionarioCLT;
import model.FuncionarioPJ;
import util.LoggerService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            LoggerService.log("ERROR", "Tentativa de cadastro CLT com salario invalido: " + salario);
            throw new DadoInvalidoException("O salario deve ser maior que zero.");
        }
        funcionarios.add(new FuncionarioCLT(nome, cpf, email, salario));
        LoggerService.log("INFO", "Funcionario CLT cadastrado: " + nome);
    }

    // Cadastrar PJ
    public void cadastrarPJ(String nome, String cpf, String email, double salario) throws DadoInvalidoException {
        if (salario <= 0) {
            LoggerService.log("ERROR", "Tentativa de cadastro PJ com salario invalido: " + salario);
            throw new DadoInvalidoException("O salario deve ser maior que zero.");
        }
        funcionarios.add(new FuncionarioPJ(nome, cpf, email, salario));
        LoggerService.log("INFO", "Funcionario PJ cadastrado: " + nome);
    }

    // Devolver todos os funcionarios cadastrados.
    public List<Funcionario> listarTodos() {
        return funcionarios;
    }

    // SALVAR: grava a lista de funcionarios no arquivo dados/funcionarios.dat
    public void salvarEmArquivo() {
        // Garante que a pasta "dados" existe (cria se nao existir)
        File pasta = new File("dados");
        if (!pasta.exists()) {
            pasta.mkdir();
        }

        try {
            // Abre o fluxo para escrever no arquivo
            FileOutputStream file = new FileOutputStream("dados/funcionarios.dat");
            // Cria o fluxo que converte objetos em bytes
            ObjectOutputStream out = new ObjectOutputStream(file);
            // Grava a lista inteira de uma vez
            out.writeObject(funcionarios);
            // Fecha o fluxo
            out.close();
            LoggerService.log("INFO", "Dados de funcionarios salvos em arquivo");
        } catch (Exception e) {
            LoggerService.log("ERROR", "Erro ao salvar funcionarios: " + e.getMessage());
            System.out.println("Erro ao salvar funcionarios: " + e.getMessage());
        }
    }

    // CARREGAR: le o arquivo e recupera a lista de funcionarios
    @SuppressWarnings("unchecked")
    public void carregarDoArquivo() {
        File arquivo = new File("dados/funcionarios.dat");

        // Se o arquivo nao existe, e a primeira execucao — nao faz nada
        if (!arquivo.exists()) {
            return;
        }

        try {
            // Abre o fluxo para ler o arquivo
            FileInputStream file = new FileInputStream("dados/funcionarios.dat");
            // Cria o fluxo que converte bytes de volta em objetos
            ObjectInputStream in = new ObjectInputStream(file);
            // Le o objeto e converte para List<Funcionario>
            funcionarios = (List<Funcionario>) in.readObject();
            // Fecha o fluxo
            in.close();
            LoggerService.log("INFO", "Dados de funcionarios carregados do arquivo");
        } catch (Exception e) {
            LoggerService.log("ERROR", "Erro ao carregar funcionarios: " + e.getMessage());
            System.out.println("Erro ao carregar funcionarios: " + e.getMessage());
        }
    }
}

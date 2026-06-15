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


public class FuncionarioController {

    
    private List<Funcionario> funcionarios = new ArrayList<>();

    
    public void cadastrarCLT(String nome, String cpf, String email, double salario) throws DadoInvalidoException {
        if (salario <= 0) {
            LoggerService.log("ERROR", "Tentativa de cadastro CLT com salario invalido: " + salario);
            throw new DadoInvalidoException("O salario deve ser maior que zero.");
        }
        funcionarios.add(new FuncionarioCLT(nome, cpf, email, salario));
        LoggerService.log("INFO", "Funcionario CLT cadastrado: " + nome);
    }

    
    public void cadastrarPJ(String nome, String cpf, String email, double salario) throws DadoInvalidoException {
        if (salario <= 0) {
            LoggerService.log("ERROR", "Tentativa de cadastro PJ com salario invalido: " + salario);
            throw new DadoInvalidoException("O salario deve ser maior que zero.");
        }
        funcionarios.add(new FuncionarioPJ(nome, cpf, email, salario));
        LoggerService.log("INFO", "Funcionario PJ cadastrado: " + nome);
    }

    
    public List<Funcionario> listarTodos() {
        return funcionarios;
    }

    
    public void salvarEmArquivo() {
        
        File pasta = new File("dados");
        if (!pasta.exists()) {
            pasta.mkdir();
        }

        try {
            
            FileOutputStream file = new FileOutputStream("dados/funcionarios.dat");
            
            ObjectOutputStream out = new ObjectOutputStream(file);
            
            out.writeObject(funcionarios);
            
            out.close();
            LoggerService.log("INFO", "Dados de funcionarios salvos em arquivo");
        } catch (Exception e) {
            LoggerService.log("ERROR", "Erro ao salvar funcionarios: " + e.getMessage());
            System.out.println("Erro ao salvar funcionarios: " + e.getMessage());
        }
    }

    
    @SuppressWarnings("unchecked")
    public void carregarDoArquivo() {
        File arquivo = new File("dados/funcionarios.dat");

        
        if (!arquivo.exists()) {
            return;
        }

        try {
            
            FileInputStream file = new FileInputStream("dados/funcionarios.dat");
            
            ObjectInputStream in = new ObjectInputStream(file);
            
            funcionarios = (List<Funcionario>) in.readObject();
            
            in.close();
            LoggerService.log("INFO", "Dados de funcionarios carregados do arquivo");
        } catch (Exception e) {
            LoggerService.log("ERROR", "Erro ao carregar funcionarios: " + e.getMessage());
            System.out.println("Erro ao carregar funcionarios: " + e.getMessage());
        }
    }
}

package view;

import controller.EstagiarioController;
import exceptions.DadoInvalidoException;
import model.Estagiario;

import java.util.List;
import java.util.Scanner;

public class EstagiarioView {

    private EstagiarioController controller;
    private Scanner scanner;

    public EstagiarioView(Scanner scanner, EstagiarioController controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n===== MENU ESTAGIARIOS =====");
            System.out.println("1 - Cadastrar Estagiario");
            System.out.println("2 - Listar todos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida! Digite um numero.");
                opcao = -1;
            }

            switch (opcao) {
                case 1: cadastrar(); break;
                case 2: listar();    break;
                case 0: System.out.println("Voltando..."); break;
                default: System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
    }

    private void cadastrar() {

        // NOME: apenas letras
        String nome;
        boolean nomeValido = false;
        nome = "";
        while (!nomeValido) {
            System.out.print("Nome: ");
            nome = scanner.nextLine();
            if (nome.matches("[a-zA-Z ]+")) {
                nomeValido = true;
            } else {
                System.out.println("Nome invalido! Use apenas letras.");
            }
        }

        // CPF: 11 numeros
        String cpf;
        boolean cpfValido = false;
        cpf = "";
        while (!cpfValido) {
            System.out.print("CPF (11 numeros, sem pontos): ");
            cpf = scanner.nextLine();
            if (cpf.length() == 11 && cpf.matches("\\d+")) {
                cpfValido = true;
            } else {
                System.out.println("CPF invalido! Digite exatamente 11 numeros.");
            }
        }

        // EMAIL
        String email;
        boolean emailValido = false;
        email = "";
        while (!emailValido) {
            System.out.print("Email: ");
            email = scanner.nextLine();
            if (email.contains("@")) {
                emailValido = true;
            } else {
                System.out.println("Email invalido! O email deve conter '@'.");
            }
        }

        // BOLSA
        double bolsa = 0;
        boolean bolsaValida = false;
        while (!bolsaValida) {
            System.out.print("Bolsa: ");
            try {
                bolsa = Double.parseDouble(scanner.nextLine());
                bolsaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Bolsa invalida! Digite um numero.");
            }
        }

        // INSTITUICAO
        System.out.print("Instituicao de ensino: ");
        String instituicao = scanner.nextLine();

        try {
            controller.cadastrar(nome, cpf, email, bolsa, instituicao);
            System.out.println("Estagiario cadastrado com sucesso!");
        } catch (DadoInvalidoException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    private void listar() {
        List<Estagiario> lista = controller.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("Nenhum estagiario cadastrado.");
            return;
        }

        System.out.println("\n--- ESTAGIARIOS ---");
        for (Estagiario e : lista) {
            System.out.println("Nome: " + e.getNome()
                    + " | CPF: " + e.getCpf()
                    + " | Instituicao: " + e.getInstituicaoEnsino()
                    + " | Bolsa liquida: R$ " + e.calcularSalarioLiquido());
        }
    }
}

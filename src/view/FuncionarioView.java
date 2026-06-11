package view;

import controller.FuncionarioController;
import model.Funcionario;

import java.util.List;
import java.util.Scanner;

/**
 * FuncionarioView — o "V" do MVC: a TELA (console).
 *
 * So faz duas coisas:
 *   1. Mostrar texto (System.out)
 *   2. Ler o que o usuario digita (Scanner)
 *
 * Estruturas exigidas que aparecem aqui:
 *   - DO-WHILE: o menu repete ate o usuario escolher sair.
 *   - SWITCH: decide o que fazer conforme a opcao.
 *   - FOR / FOREACH: percorrendo a lista de funcionarios.
 */
public class FuncionarioView {

    private FuncionarioController controller;
    private Scanner scanner;

    // Construtor padrao (usado quando a View gerencia tudo sozinha).
    public FuncionarioView() {
        this.controller = new FuncionarioController();
        this.scanner = new Scanner(System.in);
    }

    // Construtor com injecao: permite compartilhar scanner e controller com outros modulos.
    public FuncionarioView(Scanner scanner, FuncionarioController controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    public void exibirMenu() {
        int opcao;

        // DO-WHILE: roda pelo menos UMA vez e repete enquanto opcao for diferente de 0.
        do {
            System.out.println("\n===== MENU FUNCIONARIOS =====");
            System.out.println("1 - Cadastrar CLT");
            System.out.println("2 - Cadastrar PJ");
            System.out.println("3 - Cadastrar Estagiario");
            System.out.println("4 - Listar todos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = Integer.parseInt(scanner.nextLine());

            // SWITCH: cada numero leva pra uma acao.
            switch (opcao) {
                case 1: cadastrar("CLT"); break;
                case 2: cadastrar("PJ"); break;
                case 3: cadastrar("ESTAGIARIO"); break;
                case 4: listar(); break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opcao invalida!");
            }
        } while (opcao != 0);
    }

    // Cadastra um funcionario do tipo escolhido.
    private void cadastrar(String tipo) {

        // 1. NOME: pede até digitar apenas letras e espaços
        String nome = "";
        boolean nomeValido = false;
        while (!nomeValido) {
            System.out.print("Nome: ");
            nome = scanner.nextLine();

            if (nome.matches("[a-zA-Z ]+")) {
                nomeValido = true;
            } else {
                System.out.println("Nome invalido! Use apenas letras.");
            }
        }

        // 2. CPF: pede até digitar exatamente 11 caracteres
        String cpf = "";
        boolean cpfValido = false;
        while (!cpfValido) {
            System.out.print("CPF (11 numeros, sem pontos): ");
            cpf = scanner.nextLine();

            if (cpf.length() == 11 && cpf.matches("\\d+")) {
                cpfValido = true;
            } else {
                System.out.println("CPF invalido! Digite exatamente 11 numeros.");
            }
        }

        // 3. EMAIL: pede até encontrar o caractere '@'
        String email = "";
        boolean emailValido = false;
        while (!emailValido) {
            System.out.print("Email: ");
            email = scanner.nextLine();

            if (email.contains("@")) {
                emailValido = true;
            } else {
                System.out.println("Email invalido! O email deve conter '@'.");
            }
        }

        // 4. SALÁRIO: pede até digitar um número maior que zero
        double salario = 0;
        while (salario <= 0) {
            System.out.print("Salario: ");
            if (scanner.hasNextDouble()) {
                salario = scanner.nextDouble();
                if (salario <= 0) {
                    System.out.println("Salario invalido! O valor deve ser maior que zero.");
                }
            } else {
                System.out.println("Salario invalido! Digite um numero valido.");
            }
            scanner.nextLine();
        }
        // IF/ELSE decidindo qual metodo do controller chamar.
        if (tipo.equals("CLT")) {
            controller.cadastrarCLT(nome, cpf, email, salario);
        } else if (tipo.equals("PJ")) {
            controller.cadastrarPJ(nome, cpf, email, salario);
        } else {
            controller.cadastrarEstagiario(nome, cpf, email, salario);
        }
        System.out.println("Cadastrado com sucesso!");
    }

    // Lista todos os funcionarios cadastrados.
    private void listar() {
        List<Funcionario> lista = controller.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }

        System.out.println("\n--- FUNCIONARIOS ---");
        // FOREACH: percorre a lista, um por um.
        // Repare: CLT, PJ e Estagiario imprimem valores DIFERENTES no salario liquido.
        // Esse e o POLIMORFISMO funcionando.
        for (Funcionario f : lista) {
            System.out.println("Nome: " + f.getNome()
                    + " | CPF: " + f.getCpf()
                    + " | Salario liquido: R$ " + f.calcularSalarioLiquido());
        }
    }
}

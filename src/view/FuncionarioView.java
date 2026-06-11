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
            System.out.println("0 - Sair");
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
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Salario: ");
        double salario = Double.parseDouble(scanner.nextLine());

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

package view;

import controller.FuncionarioController;
import controller.TreinamentoController;
import model.Funcionario;
import model.Treinamento;

import java.util.List;
import java.util.Scanner;

public class TreinamentoView {

    private Scanner scanner;
    private TreinamentoController treinamentoController;
    private FuncionarioController funcionarioController;
    private int proximoId = 1;

    public TreinamentoView(FuncionarioController funcionarioController) {
        this.scanner = new Scanner(System.in);
        this.treinamentoController = new TreinamentoController();
        this.funcionarioController = funcionarioController;
    }

    public void menu() {
        int opcao;

        do {
            System.out.println("\n=== MENU TREINAMENTOS ===");
            System.out.println("1 - Cadastrar treinamento");
            System.out.println("2 - Listar treinamentos");
            System.out.println("3 - Excluir treinamento");
            System.out.println("4 - Adicionar participante");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarTreinamento();
                    break;
                case 2:
                    listarTreinamentos();
                    break;
                case 3:
                    excluirTreinamento();
                    break;
                case 4:
                    adicionarParticipante();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);
    }

    private void cadastrarTreinamento() {
        System.out.print("Titulo do treinamento: ");
        String titulo = scanner.nextLine();

        System.out.print("Carga horaria: ");
        int cargaHoraria = scanner.nextInt();

        System.out.print("Custo por participante: ");
        double custo = scanner.nextDouble();
        scanner.nextLine();

        Treinamento treinamento = new Treinamento(
                proximoId,
                titulo,
                cargaHoraria,
                custo
        );

        treinamentoController.cadastrar(treinamento);
        proximoId++;

        System.out.println("Treinamento cadastrado com sucesso.");
    }

    private void listarTreinamentos() {
        List<Treinamento> treinamentos = treinamentoController.listarTodos();

        if (treinamentos.isEmpty()) {
            System.out.println("Nenhum treinamento cadastrado.");
            return;
        }

        for (Treinamento treinamento : treinamentos) {
            System.out.println(treinamento);
        }
    }

    private void excluirTreinamento() {
        System.out.print("Informe o id do treinamento: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        treinamentoController.excluir(id);

        System.out.println("Treinamento excluido, se o id existia.");
    }

    private void adicionarParticipante() {
        List<Treinamento> treinamentos = treinamentoController.listarTodos();
        List<Funcionario> funcionarios = funcionarioController.listarTodos();

        if (treinamentos.isEmpty()) {
            System.out.println("Nenhum treinamento cadastrado.");
            return;
        }

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }

        System.out.println("\nTreinamentos:");
        for (Treinamento treinamento : treinamentos) {
            System.out.println(treinamento.getId() + " - " + treinamento.getTitulo());
        }

        System.out.print("Informe o id do treinamento: ");
        int idTreinamento = scanner.nextInt();

        System.out.println("\nFuncionarios:");
        for (int i = 0; i < funcionarios.size(); i++) {
            System.out.println((i + 1) + " - " + funcionarios.get(i).getNome());
        }

        System.out.print("Escolha o funcionario: ");
        int indiceFuncionario = scanner.nextInt() - 1;
        scanner.nextLine();

        if (indiceFuncionario < 0 || indiceFuncionario >= funcionarios.size()) {
            System.out.println("Funcionario invalido.");
            return;
        }

        treinamentoController.adicionarParticipante(
                idTreinamento,
                funcionarios.get(indiceFuncionario)
        );

        System.out.println("Participante adicionado ao treinamento.");
    }
}
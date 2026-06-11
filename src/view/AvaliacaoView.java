package view;

import controller.AvaliacaoController;
import controller.FuncionarioController;
import model.AvaliacaoDesempenho;
import model.Funcionario;

import java.util.List;
import java.util.Scanner;

public class AvaliacaoView {

    private Scanner scanner;
    private AvaliacaoController avaliacaoController;
    private FuncionarioController funcionarioController;
    private int proximoId = 1;

    public AvaliacaoView(FuncionarioController funcionarioController) {
        this.scanner = new Scanner(System.in);
        this.avaliacaoController = new AvaliacaoController();
        this.funcionarioController = funcionarioController;
    }

    public void menu() {
        int opcao;

        do {
            System.out.println("\n=== MENU AVALIACAO DE DESEMPENHO ===");
            System.out.println("1 - Cadastrar avaliacao");
            System.out.println("2 - Listar avaliacoes");
            System.out.println("3 - Excluir avaliacao");
            System.out.println("4 - Ranking de funcionarios");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarAvaliacao();
                    break;
                case 2:
                    listarAvaliacoes();
                    break;
                case 3:
                    excluirAvaliacao();
                    break;
                case 4:
                    mostrarRanking();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);
    }

    private void cadastrarAvaliacao() {
        List<Funcionario> funcionarios = funcionarioController.listarTodos();

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }

        System.out.println("\nFuncionarios cadastrados:");
        for (int i = 0; i < funcionarios.size(); i++) {
            System.out.println((i + 1) + " - " + funcionarios.get(i).getNome());
        }

        System.out.print("Escolha o funcionario avaliado: ");
        int indiceAvaliado = scanner.nextInt() - 1;

        System.out.print("Escolha o funcionario avaliador: ");
        int indiceAvaliador = scanner.nextInt() - 1;
        scanner.nextLine();

        if (indiceAvaliado < 0 || indiceAvaliado >= funcionarios.size()
                || indiceAvaliador < 0 || indiceAvaliador >= funcionarios.size()) {
            System.out.println("Funcionario invalido.");
            return;
        }

        System.out.print("Nota da avaliacao (0 a 10): ");
        double nota = scanner.nextDouble();
        scanner.nextLine();

        if (nota < 0 || nota > 10) {
            System.out.println("Nota invalida.");
            return;
        }

        System.out.print("Comentario: ");
        String comentario = scanner.nextLine();

        AvaliacaoDesempenho avaliacao = new AvaliacaoDesempenho(
                proximoId,
                funcionarios.get(indiceAvaliado),
                funcionarios.get(indiceAvaliador),
                nota,
                comentario
        );

        avaliacaoController.cadastrar(avaliacao);
        proximoId++;

        System.out.println("Avaliacao cadastrada com sucesso.");
    }

    private void listarAvaliacoes() {
        List<AvaliacaoDesempenho> avaliacoes = avaliacaoController.listarTodas();

        if (avaliacoes.isEmpty()) {
            System.out.println("Nenhuma avaliacao cadastrada.");
            return;
        }

        for (AvaliacaoDesempenho avaliacao : avaliacoes) {
            System.out.println(avaliacao);
        }
    }

    private void excluirAvaliacao() {
        System.out.print("Informe o id da avaliacao: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        avaliacaoController.excluir(id);

        System.out.println("Avaliacao excluida, se o id existia.");
    }

    private void mostrarRanking() {
        List<Funcionario> funcionarios = funcionarioController.listarTodos();

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }

        funcionarios.sort(
                (f1, f2) -> Double.compare(
                        f2.calcularMediaAvaliacoes(),
                        f1.calcularMediaAvaliacoes()
                )
        );

        System.out.println("\n=== RANKING DE AVALIACOES ===");

        for (Funcionario funcionario : funcionarios) {
            System.out.println(
                    funcionario.getNome()
                            + " - Media: "
                            + funcionario.calcularMediaAvaliacoes()
            );
        }
    }
}
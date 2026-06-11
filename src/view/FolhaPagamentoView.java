package view;

import controller.FolhaPagamentoController;
import exceptions.ValorInvalidoException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.FolhaPagamento;
import model.Holerite;

public class FolhaPagamentoView {

    private Scanner scanner;
    private FolhaPagamentoController controller;

    public FolhaPagamentoView(Scanner scanner, FolhaPagamentoController controller) {
        setScanner(scanner);
        setController(controller);
    }

    public void exibirMenu() {
        int opcao = -1;

        do {
            try {
                System.out.println("\n===== MENU FOLHA DE PAGAMENTO =====");
                System.out.println("1 - Gerar folha");
                System.out.println("2 - Listar folhas");
                System.out.println("3 - Listar holerites de uma folha");
                System.out.println("4 - Consultar holerite por funcionario");
                System.out.println("5 - Alterar mes e ano da folha");
                System.out.println("6 - Excluir folha");
                System.out.println("0 - Voltar");
                System.out.print("Escolha: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        gerarFolha();
                        break;
                    case 2:
                        listarFolhas();
                        break;
                    case 3:
                        listarHolerites();
                        break;
                    case 4:
                        consultarHolerite();
                        break;
                    case 5:
                        alterarMesAno();
                        break;
                    case 6:
                        excluir();
                        break;
                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opcao invalida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: digite apenas numeros.");
                scanner.nextLine();
            }
        } while (opcao != 0);
    }

    private void gerarFolha() {
        try {
            System.out.println("\n-- Gerar Folha --");

            System.out.print("Mes: ");
            int mes = scanner.nextInt();

            System.out.print("Ano: ");
            int ano = scanner.nextInt();
            scanner.nextLine();

            controller.gerarFolha(mes, ano);
            System.out.println("Folha gerada com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Erro: digite apenas numeros.");
            scanner.nextLine();
        } catch (ValorInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarFolhas() {
        List<FolhaPagamento> folhas = controller.listarTodas();

        System.out.println("\n-- Folhas de Pagamento --");

        if (folhas.isEmpty()) {
            System.out.println("Nenhuma folha cadastrada.");
        } else {
            for (FolhaPagamento folha : folhas) {
                System.out.println(folha);
            }
        }
    }

    private void listarHolerites() {
        try {
            listarFolhas();

            System.out.print("ID da folha: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            FolhaPagamento folha = controller.buscarPorId(id);

            if (folha == null) {
                System.out.println("Folha nao encontrada.");
            } else if (folha.getHolerites().isEmpty()) {
                System.out.println("Essa folha nao possui holerites.");
            } else {
                System.out.println("\n-- Holerites --");

                for (String nomeFuncionario : folha.getHolerites().keySet()) {
                    Holerite holerite = folha.getHolerites().get(nomeFuncionario);
                    System.out.println(holerite.imprimir());
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: digite apenas numeros.");
            scanner.nextLine();
        }
    }

    private void consultarHolerite() {
        try {
            listarFolhas();

            System.out.print("ID da folha: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nome do funcionario: ");
            String nome = scanner.nextLine();

            Holerite holerite = controller.consultarHolerite(id, nome);
            System.out.println(holerite.imprimir());
        } catch (InputMismatchException e) {
            System.out.println("Erro: digite apenas numeros.");
            scanner.nextLine();
        } catch (ValorInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void alterarMesAno() {
        try {
            listarFolhas();

            System.out.print("ID da folha: ");
            int id = scanner.nextInt();

            System.out.print("Novo mes: ");
            int mes = scanner.nextInt();

            System.out.print("Novo ano: ");
            int ano = scanner.nextInt();
            scanner.nextLine();

            boolean alterado = controller.alterarMesAno(id, mes, ano);

            if (alterado) {
                System.out.println("Folha atualizada com sucesso!");
            } else {
                System.out.println("Folha nao encontrada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: digite apenas numeros.");
            scanner.nextLine();
        } catch (ValorInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void excluir() {
        try {
            listarFolhas();

            System.out.print("ID da folha a excluir: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            boolean removido = controller.excluir(id);

            if (removido) {
                System.out.println("Folha excluida com sucesso!");
            } else {
                System.out.println("Folha nao encontrada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: digite apenas numeros.");
            scanner.nextLine();
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public FolhaPagamentoController getController() {
        return controller;
    }

    public void setController(FolhaPagamentoController controller) {
        this.controller = controller;
    }
}

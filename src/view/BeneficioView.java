package view;

import controller.BeneficioController;
import exceptions.ValorInvalidoException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.Beneficio;

public class BeneficioView {

    private BeneficioController controller;
    private Scanner scanner;

    public BeneficioView(Scanner scanner, BeneficioController controller) {
        setScanner(scanner);
        setController(controller);
    }

    public void exibirMenu() {
        int opcao = -1;

        do {
            try {
                System.out.println("\n===== MENU BENEFICIOS =====");
                System.out.println("1 - Cadastrar beneficio");
                System.out.println("2 - Listar beneficios");
                System.out.println("3 - Alterar nome");
                System.out.println("4 - Alterar tipo");
                System.out.println("5 - Alterar valor");
                System.out.println("6 - Excluir beneficio");
                System.out.println("0 - Voltar");
                System.out.print("Escolha: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrar();
                        break;
                    case 2:
                        listarTodos();
                        break;
                    case 3:
                        alterarNome();
                        break;
                    case 4:
                        alterarTipo();
                        break;
                    case 5:
                        alterarValor();
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

    private void cadastrar() {
        try {
            System.out.println("\n-- Cadastrar Beneficio --");

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.println("Tipo: ALIMENTACAO / TRANSPORTE / SAUDE / EDUCACAO / OUTRO");
            String tipo = scanner.nextLine();

            System.out.print("Valor mensal: R$ ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            controller.cadastrar(nome, tipo, valor);
            System.out.println("Beneficio cadastrado com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Erro: digite apenas numeros.");
            scanner.nextLine();
        } catch (ValorInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarTodos() {
        List<Beneficio> beneficios = controller.listarTodos();

        System.out.println("\n-- Lista de Beneficios --");

        if (beneficios.isEmpty()) {
            System.out.println("Nenhum beneficio cadastrado.");
        } else {
            for (Beneficio beneficio : beneficios) {
                System.out.println(beneficio);
            }
        }
    }

    private void alterarNome() {
        try {
            listarTodos();

            System.out.print("ID do beneficio: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();

            boolean alterado = controller.alterarNome(id, nome);

            if (alterado) {
                System.out.println("Nome do beneficio atualizado!");
            } else {
                System.out.println("Beneficio nao encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: digite apenas numeros.");
            scanner.nextLine();
        } catch (ValorInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void alterarTipo() {
        try {
            listarTodos();

            System.out.print("ID do beneficio: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Novo tipo: ALIMENTACAO / TRANSPORTE / SAUDE / EDUCACAO / OUTRO");
            String tipo = scanner.nextLine();

            boolean alterado = controller.alterarTipo(id, tipo);

            if (alterado) {
                System.out.println("Tipo do beneficio atualizado!");
            } else {
                System.out.println("Beneficio nao encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: digite apenas numeros.");
            scanner.nextLine();
        } catch (ValorInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void alterarValor() {
        try {
            listarTodos();

            System.out.print("ID do beneficio: ");
            int id = scanner.nextInt();

            System.out.print("Novo valor mensal: R$ ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            boolean alterado = controller.alterarValor(id, valor);

            if (alterado) {
                System.out.println("Valor do beneficio atualizado!");
            } else {
                System.out.println("Beneficio nao encontrado.");
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
            listarTodos();

            System.out.print("ID do beneficio a excluir: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            boolean removido = controller.excluir(id);

            if (removido) {
                System.out.println("Beneficio excluido com sucesso!");
            } else {
                System.out.println("Beneficio nao encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: digite apenas numeros.");
            scanner.nextLine();
        }
    }

    public BeneficioController getController() {
        return controller;
    }

    public void setController(BeneficioController controller) {
        this.controller = controller;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}

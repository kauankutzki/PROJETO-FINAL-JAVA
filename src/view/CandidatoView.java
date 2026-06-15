package view;

import controller.CandidatoController;
import controller.VagaController;
import model.Candidato;
import model.Vaga;

import java.util.List;
import java.util.Scanner;

public class CandidatoView {

    private CandidatoController controller;
    private VagaController vagaController;
    private Scanner scanner;

    public CandidatoView(Scanner scanner, CandidatoController controller, VagaController vagaController) {
        this.scanner = scanner;
        this.controller = controller;
        this.vagaController = vagaController;
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n===== MENU CANDIDATOS =====");
            System.out.println("1 - Cadastrar candidato");
            System.out.println("2 - Listar todos os candidatos");
            System.out.println("3 - Listar por status");
            System.out.println("4 - Avancar etapa do processo");
            System.out.println("5 - Reprovar candidato");
            System.out.println("6 - Alterar dados (pretensao/curriculo)");
            System.out.println("7 - Remover candidato");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = lerInt();

            switch (opcao) {
                case 1: cadastrar();      break;
                case 2: listarTodos();    break;
                case 3: listarPorStatus(); break;
                case 4: avancarEtapa();   break;
                case 5: reprovar();       break;
                case 6: alterarDados();   break;
                case 7: remover();        break;
                case 0: System.out.println("Voltando..."); break;
                default: System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);
    }


    private void cadastrar() {
        System.out.println("\n-- Cadastrar Candidato --");

        Vaga vaga = escolherVaga();
        if (vaga == null) {
            System.out.println("Cadastro cancelado: nenhuma vaga escolhida.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Pretensao salarial: R$ ");
        double pretensao = lerDouble();

        System.out.print("Resumo do curriculo: ");
        String curriculo = scanner.nextLine();

        String resultado = controller.cadastrar(nome, cpf, email, vaga, pretensao, curriculo);
        System.out.println(resultado);
    }

    private void listarTodos() {
        List<Candidato> lista = controller.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("Nenhum candidato cadastrado.");
            return;
        }

        System.out.println("\n-- Lista de Candidatos --");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
    }

    private void listarPorStatus() {
        System.out.println("Status (INSCRITO / ENTREVISTA / APROVADO / REPROVADO): ");
        String status = scanner.nextLine().trim().toUpperCase();
        List<Candidato> lista = controller.listarPorStatus(status);

        if (lista.isEmpty()) {
            System.out.println("Nenhum candidato encontrado com status: " + status);
            return;
        }

        System.out.println("\n-- Candidatos " + status + " --");
        for (Candidato c : lista) {
            System.out.println(c);
        }
    }

    private void avancarEtapa() {
        listarTodos();
        System.out.print("ID do candidato: ");
        int id = lerInt();
        System.out.println(controller.avancarEtapa(id));
    }

    private void reprovar() {
        listarTodos();
        System.out.print("ID do candidato: ");
        int id = lerInt();
        System.out.println(controller.reprovar(id));
    }

    private void alterarDados() {
        listarTodos();
        System.out.print("ID do candidato: ");
        int id = lerInt();
        System.out.print("Nova pretensao salarial: R$ ");
        double pretensao = lerDouble();
        System.out.print("Novo resumo do curriculo: ");
        String curriculo = scanner.nextLine();
        System.out.println(controller.alterarDados(id, pretensao, curriculo));
    }

    private void remover() {
        listarTodos();
        System.out.print("ID do candidato a remover: ");
        int id = lerInt();
        System.out.println(controller.excluir(id));
    }


    private Vaga escolherVaga() {
        List<Vaga> vagas = vagaController.listarTodos();
        if (vagas.isEmpty()) {
            System.out.println("Nenhuma vaga cadastrada. Cadastre uma vaga primeiro.");
            return null;
        }

        System.out.println("-- Vagas disponiveis --");
        for (int i = 0; i < vagas.size(); i++) {
            System.out.println((i + 1) + ". " + vagas.get(i));
        }
        System.out.print("ID da vaga: ");
        int id = lerInt();
        return vagaController.buscarPorId(id);
    }


    private int lerInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Usando 0.");
            return 0;
        }
    }

    private double lerDouble() {
        try {
            return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Usando 0.0.");
            return 0.0;
        }
    }
}

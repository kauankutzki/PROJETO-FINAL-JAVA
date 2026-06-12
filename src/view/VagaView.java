package view;

import controller.CargoController;
import controller.DepartamentoController;
import controller.VagaController;
import model.Cargo;
import model.Departamento;
import model.Vaga;

import java.util.List;
import java.util.Scanner;

/**
 * VagaView — o "V" do MVC para Vaga.
 *
 * Responsabilidade UNICA: entrada e saida de dados no console.
 * - Le o que o usuario digita (Scanner).
 * - Exibe resultados (System.out).
 * - NUNCA contem regra de negocio — isso fica no Controller.
 *
 * Estruturas obrigatorias que aparecem aqui:
 * - DO-WHILE: loop principal do menu.
 * - SWITCH: roteia cada opcao do menu.
 * - FOR indexado: ao listar vagas, cargos e departamentos por indice.
 */
public class VagaView {

    private VagaController controller;
    private CargoController cargoController;
    private DepartamentoController departamentoController;
    private Scanner scanner;

    // Recebe Scanner e os controllers dos Alunos 2 (Cargo/Departamento) de fora,
    // para poder compartilhar com outros modulos.
    public VagaView(Scanner scanner, VagaController controller,
                     CargoController cargoController, DepartamentoController departamentoController) {
        this.scanner = scanner;
        this.controller = controller;
        this.cargoController = cargoController;
        this.departamentoController = departamentoController;
    }

    public void exibirMenu() {
        int opcao;

        // DO-WHILE: executa ao menos uma vez, repete ate o usuario sair.
        do {
            System.out.println("\n===== MENU VAGAS =====");
            System.out.println("1 - Cadastrar vaga");
            System.out.println("2 - Listar todas as vagas");
            System.out.println("3 - Listar por status");
            System.out.println("4 - Alterar status");
            System.out.println("5 - Alterar quantidade");
            System.out.println("6 - Cancelar vaga");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = lerInt();

            // SWITCH: cada numero leva para uma acao especifica.
            switch (opcao) {
                case 1: cadastrar();        break;
                case 2: listarTodos();      break;
                case 3: listarPorStatus();  break;
                case 4: alterarStatus();    break;
                case 5: alterarQuantidade(); break;
                case 6: cancelar();         break;
                case 0: System.out.println("Voltando..."); break;
                default: System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);
    }

    // --- acoes privadas (cada uma cuida de uma tela) ---

    private void cadastrar() {
        System.out.println("\n-- Cadastrar Vaga --");

        Cargo cargo = escolherCargo();
        if (cargo == null) {
            System.out.println("Cadastro cancelado: nenhum cargo escolhido.");
            return;
        }

        Departamento departamento = escolherDepartamento();
        if (departamento == null) {
            System.out.println("Cadastro cancelado: nenhum departamento escolhido.");
            return;
        }

        System.out.print("Quantidade de vagas: ");
        int quantidade = lerInt();

        String resultado = controller.cadastrar(cargo, departamento, quantidade);
        System.out.println(resultado);
    }

    private void listarTodos() {
        List<Vaga> lista = controller.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("Nenhuma vaga cadastrada.");
            return;
        }

        System.out.println("\n-- Lista de Vagas --");
        // FOR indexado: percorre pelo indice, mostrando a posicao.
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
    }

    private void listarPorStatus() {
        System.out.println("Status (ABERTA / EM_SELECAO / FECHADA): ");
        String status = scanner.nextLine().trim().toUpperCase();
        List<Vaga> lista = controller.listarPorStatus(status);

        if (lista.isEmpty()) {
            System.out.println("Nenhuma vaga encontrada com status: " + status);
            return;
        }

        System.out.println("\n-- Vagas " + status + " --");
        for (Vaga v : lista) {
            System.out.println(v);
        }
    }

    private void alterarStatus() {
        listarTodos();
        System.out.print("ID da vaga a alterar: ");
        int id = lerInt();
        System.out.println("Novo status (ABERTA / EM_SELECAO / FECHADA): ");
        String status = scanner.nextLine().trim().toUpperCase();
        System.out.println(controller.alterarStatus(id, status));
    }

    private void alterarQuantidade() {
        listarTodos();
        System.out.print("ID da vaga a alterar: ");
        int id = lerInt();
        System.out.print("Nova quantidade: ");
        int quantidade = lerInt();
        System.out.println(controller.alterarQuantidade(id, quantidade));
    }

    private void cancelar() {
        listarTodos();
        System.out.print("ID da vaga a cancelar: ");
        int id = lerInt();
        System.out.println(controller.excluir(id));
    }

    // --- auxiliares de escolha (cargo e departamento sao dos Alunos 2) ---

    private Cargo escolherCargo() {
        List<Cargo> cargos = cargoController.listarTodos();
        if (cargos.isEmpty()) {
            System.out.println("Nenhum cargo cadastrado. Cadastre um cargo primeiro.");
            return null;
        }

        System.out.println("-- Cargos disponiveis --");
        for (int i = 0; i < cargos.size(); i++) {
            System.out.println((i + 1) + ". " + cargos.get(i));
        }
        System.out.print("ID do cargo: ");
        int id = lerInt();
        return cargoController.buscarPorId(id);
    }

    private Departamento escolherDepartamento() {
        List<Departamento> departamentos = departamentoController.listarTodos();
        if (departamentos.isEmpty()) {
            System.out.println("Nenhum departamento cadastrado. Cadastre um departamento primeiro.");
            return null;
        }

        System.out.println("-- Departamentos disponiveis --");
        for (int i = 0; i < departamentos.size(); i++) {
            System.out.println((i + 1) + ". " + departamentos.get(i));
        }
        System.out.print("ID do departamento: ");
        int id = lerInt();
        return departamentoController.buscarPorId(id);
    }

    // --- leituras seguras com try/catch ---

    private int lerInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Usando 0.");
            return 0;
        }
    }
}

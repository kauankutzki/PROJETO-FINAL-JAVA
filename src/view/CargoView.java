package view;

import controller.CargoController;
import model.Cargo;
import model.enums.NivelCargo;

import java.util.List;
import java.util.Scanner;

/**
 * CargoView — o "V" do MVC para Cargo.
 *
 * Responsabilidade UNICA: entrada e saida de dados no console.
 * - Le o que o usuario digita (Scanner).
 * - Exibe resultados (System.out).
 * - NUNCA contém regra de negocio — isso fica no Controller.
 *
 * Estruturas obrigatorias que aparecem aqui:
 * - DO-WHILE: loop principal do menu.
 * - SWITCH: roteia cada opcao do menu.
 * - FOR indexado: ao listar cargos por indice.
 */
public class CargoView {

    private CargoController controller = new CargoController();
    private Scanner scanner;

    // Recebe o Scanner de fora para poder compartilhar com outras Views.
    public CargoView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao;

        // DO-WHILE: executa ao menos uma vez, repete ate o usuario sair.
        do {
            System.out.println("\n===== MENU CARGOS =====");
            System.out.println("1 - Cadastrar cargo");
            System.out.println("2 - Listar todos os cargos");
            System.out.println("3 - Listar por nivel");
            System.out.println("4 - Alterar faixa salarial");
            System.out.println("5 - Alterar nivel");
            System.out.println("6 - Excluir cargo");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = lerInt();

            // SWITCH: cada numero leva para uma acao especifica.
            switch (opcao) {
                case 1: cadastrar();        break;
                case 2: listarTodos();      break;
                case 3: listarPorNivel();   break;
                case 4: alterarFaixa();     break;
                case 5: alterarNivel();     break;
                case 6: excluir();          break;
                case 0: System.out.println("Voltando..."); break;
                default: System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);
    }

    // --- acoes privadas (cada uma cuida de uma tela) ---

    private void cadastrar() {
        System.out.println("\n-- Cadastrar Cargo --");
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();

        System.out.println("Nivel (JUNIOR / PLENO / SENIOR / GESTAO): ");
        String nivel = scanner.nextLine().trim().toUpperCase();

        System.out.print("Salario minimo: R$ ");
        double salMin = lerDouble();

        System.out.print("Salario maximo: R$ ");
        double salMax = lerDouble();

        String resultado = controller.cadastrar(titulo, nivel, salMin, salMax);
        System.out.println(resultado);
    }

    private void listarTodos() {
        List<Cargo> lista = controller.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("Nenhum cargo cadastrado.");
            return;
        }

        System.out.println("\n-- Lista de Cargos --");
        // FOR indexado: percorre pelo indice, mostrando a posicao.
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }
    }

    private void listarPorNivel() {
        System.out.println("Nivel (JUNIOR / PLENO / SENIOR / GESTAO): ");
        String nivel = scanner.nextLine().trim().toUpperCase();
        List<Cargo> lista = controller.listarPorNivel(nivel);

        if (lista.isEmpty()) {
            System.out.println("Nenhum cargo encontrado para o nivel: " + nivel);
            return;
        }

        System.out.println("\n-- Cargos nivel " + nivel + " --");
        for (Cargo c : lista) {
            System.out.println(c);
        }
    }

    private void alterarFaixa() {
        listarTodos();
        System.out.print("ID do cargo a alterar: ");
        int id = lerInt();
        System.out.print("Novo salario minimo: R$ ");
        double min = lerDouble();
        System.out.print("Novo salario maximo: R$ ");
        double max = lerDouble();
        System.out.println(controller.alterarFaixa(id, min, max));
    }

    private void alterarNivel() {
        listarTodos();
        System.out.print("ID do cargo a alterar: ");
        int id = lerInt();
        System.out.println("Novo nivel (JUNIOR / PLENO / SENIOR / GESTAO): ");
        String nivel = scanner.nextLine().trim().toUpperCase();
        System.out.println(controller.alterarNivel(id, nivel));
    }

    private void excluir() {
        listarTodos();
        System.out.print("ID do cargo a excluir: ");
        int id = lerInt();
        System.out.println(controller.excluir(id));
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

    private double lerDouble() {
        try {
            return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Usando 0.0.");
            return 0.0;
        }
    }
}

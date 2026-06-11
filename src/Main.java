import controller.DepartamentoController;
import controller.FuncionarioController;
import repository.RepositorioDepartamento;
import view.CargoView;
import view.DepartamentoView;
import view.FuncionarioView;

import java.util.Scanner;

/**
 * Main TEMPORARIA — integra os modulos do Aluno 1 e do Aluno 2.
 *
 * Na integracao final, o Aluno 5 substitui isto pela MenuPrincipalView
 * que une todos os modulos dos 5 alunos.
 *
 * IMPORTANTE: o Scanner e criado aqui e passado para todas as Views,
 * para que compartilhem o mesmo fluxo de entrada do console.
 * Se cada View criasse o proprio Scanner, haveria conflitos de leitura.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instancias compartilhadas: o FuncionarioController e passado para
        // o DepartamentoView, que precisa listar funcionarios ao definir gestor.
        FuncionarioController funcController = new FuncionarioController();
        DepartamentoController depController  = new DepartamentoController();
        RepositorioDepartamento repoDep       = new RepositorioDepartamento();

        FuncionarioView  funcView  = new FuncionarioView(scanner, funcController);
        CargoView        cargoView = new CargoView(scanner);
        DepartamentoView depView   = new DepartamentoView(scanner, depController, funcController, repoDep);

        int opcao;

        do {
            System.out.println("\n==============================");
            System.out.println("   SISTEMA DE GESTAO DE RH   ");
            System.out.println("==============================");
            System.out.println("1 - Funcionarios  (Aluno 1)");
            System.out.println("2 - Cargos        (Aluno 2)");
            System.out.println("3 - Departamentos (Aluno 2)");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1: funcView.exibirMenu();  break;
                case 2: cargoView.exibirMenu(); break;
                case 3: depView.exibirMenu();   break;
                case 0: System.out.println("Sistema encerrado."); break;
                default: System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}

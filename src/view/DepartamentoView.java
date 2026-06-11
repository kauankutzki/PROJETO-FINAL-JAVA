package view;

import controller.DepartamentoController;
import controller.FuncionarioController;
import model.Departamento;
import model.Funcionario;
import repository.RepositorioDepartamento;
import service.RelatorioService;

import java.util.List;
import java.util.Scanner;

/**
 * DepartamentoView — o "V" do MVC para Departamento.
 *
 * Exibe menus e le dados do console.
 * Integra com RelatorioService para mostrar o custo por departamento.
 *
 * IMPORTANTE (para a defesa):
 * - A listagem com custo de folha demonstra POLIMORFISMO ao vivo:
 *   cada funcionario do departamento calcula o proprio salario.
 * - A exclusao com validacao demonstra REGRA DE NEGOCIO no Controller.
 */
public class DepartamentoView {

    private DepartamentoController controller;
    private FuncionarioController funcController;
    private RelatorioService relatorioService;
    private Scanner scanner;

    /**
     * Recebe os controllers e o scanner como parametros.
     * Isso permite que esta View acesse funcionarios ja cadastrados
     * pelo Aluno 1, sem duplicar dados.
     */
    public DepartamentoView(Scanner scanner,
                            DepartamentoController controller,
                            FuncionarioController funcController,
                            RepositorioDepartamento repoDep) {
        this.scanner = scanner;
        this.controller = controller;
        this.funcController = funcController;
        this.relatorioService = new RelatorioService(repoDep);
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n===== MENU DEPARTAMENTOS =====");
            System.out.println("1 - Cadastrar departamento");
            System.out.println("2 - Listar todos (com custo de folha)");
            System.out.println("3 - Alterar nome");
            System.out.println("4 - Alterar orcamento");
            System.out.println("5 - Definir gestor");
            System.out.println("6 - Adicionar funcionario ao departamento");
            System.out.println("7 - Remover funcionario do departamento");
            System.out.println("8 - Excluir departamento");
            System.out.println("9 - Relatorio: custo por departamento");
            System.out.println("10 - Ranking de avaliacoes");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = lerInt();

            switch (opcao) {
                case 1:  cadastrar();                   break;
                case 2:  listarTodos();                 break;
                case 3:  alterarNome();                 break;
                case 4:  alterarOrcamento();            break;
                case 5:  definirGestor();               break;
                case 6:  adicionarFuncionario();        break;
                case 7:  removerFuncionario();          break;
                case 8:  excluir();                     break;
                case 9:  relatorioCusto();              break;
                case 10: rankingAvaliacoes();           break;
                case 0:  System.out.println("Voltando..."); break;
                default: System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);
    }

    // --- acoes privadas ---

    private void cadastrar() {
        System.out.println("\n-- Cadastrar Departamento --");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Sigla (ex.: TI, RH, FIN): ");
        String sigla = scanner.nextLine();
        System.out.print("Orcamento mensal: R$ ");
        double orc = lerDouble();
        System.out.println(controller.cadastrar(nome, sigla, orc));
    }

    private void listarTodos() {
        List<Departamento> lista = controller.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum departamento cadastrado.");
            return;
        }
        System.out.println("\n-- Departamentos --");
        for (Departamento d : lista) {
            System.out.println(d);
            System.out.printf("   Custo de folha: R$ %.2f%n", d.calcularCustoFolha());
        }
    }

    private void alterarNome() {
        listarTodos();
        System.out.print("ID do departamento: ");
        int id = lerInt();
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.println(controller.alterarNome(id, nome));
    }

    private void alterarOrcamento() {
        listarTodos();
        System.out.print("ID do departamento: ");
        int id = lerInt();
        System.out.print("Novo orcamento: R$ ");
        double orc = lerDouble();
        System.out.println(controller.alterarOrcamento(id, orc));
    }

    private void definirGestor() {
        listarTodos();
        System.out.print("ID do departamento: ");
        int id = lerInt();

        // Lista os funcionarios disponiveis (modulo do Aluno 1).
        List<Funcionario> funcs = funcController.listarTodos();
        if (funcs.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado. Cadastre funcionarios primeiro.");
            return;
        }
        System.out.println("-- Funcionarios disponiveis --");
        for (int i = 0; i < funcs.size(); i++) {
            System.out.println((i + 1) + ". " + funcs.get(i).getNome()
                    + " | CPF: " + funcs.get(i).getCpf());
        }
        System.out.print("Numero do funcionario (0 para remover gestor): ");
        int escolha = lerInt();

        Funcionario gestor = null;
        if (escolha > 0 && escolha <= funcs.size()) {
            gestor = funcs.get(escolha - 1);
        }
        System.out.println(controller.alterarGestor(id, gestor));
    }

    private void adicionarFuncionario() {
        listarTodos();
        System.out.print("ID do departamento: ");
        int idDep = lerInt();

        List<Funcionario> funcs = funcController.listarTodos();
        if (funcs.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }
        System.out.println("-- Funcionarios disponiveis --");
        for (int i = 0; i < funcs.size(); i++) {
            System.out.println((i + 1) + ". " + funcs.get(i).getNome());
        }
        System.out.print("Numero do funcionario: ");
        int escolha = lerInt();

        if (escolha < 1 || escolha > funcs.size()) {
            System.out.println("Escolha invalida.");
            return;
        }
        System.out.println(controller.adicionarFuncionario(idDep, funcs.get(escolha - 1)));
    }

    private void removerFuncionario() {
        listarTodos();
        System.out.print("ID do departamento: ");
        int idDep = lerInt();
        System.out.print("CPF do funcionario a remover: ");
        String cpf = scanner.nextLine();
        System.out.println(controller.removerFuncionario(idDep, cpf));
    }

    private void excluir() {
        listarTodos();
        System.out.print("ID do departamento a excluir: ");
        int id = lerInt();
        System.out.println(controller.excluir(id));
    }

    private void relatorioCusto() {
        System.out.println(relatorioService.gerarTextoRelatorio());
    }

    private void rankingAvaliacoes() {
        List<Funcionario> ranking = relatorioService.rankingAvaliacoes();
        if (ranking.isEmpty()) {
            System.out.println("Nenhum funcionario com avaliacao registrada.");
            return;
        }
        System.out.println("\n===== RANKING DE AVALIACOES =====");
        for (int i = 0; i < ranking.size(); i++) {
            Funcionario f = ranking.get(i);
            System.out.printf("%2d. %-25s | Media: %.1f%n",
                    (i + 1), f.getNome(), f.calcularMediaAvaliacoes());
        }
    }

    // --- leituras seguras ---

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

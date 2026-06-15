package view;

import controller.BeneficioController;
import controller.CandidatoController;
import controller.CargoController;
import controller.DepartamentoController;
import controller.EstagiarioController;
import controller.FolhaPagamentoController;
import controller.FuncionarioController;
import controller.VagaController;
import repository.RepositorioDepartamento;
import service.RecrutamentoService;
import controller.AvaliacaoController;
import controller.TreinamentoController;

import util.LoggerService;
import java.util.Scanner;

public class MenuPrincipalView {

    private Scanner scanner;

    public MenuPrincipalView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void iniciar() {

        FuncionarioController funcionarioController = new FuncionarioController();

        LoggerService.log("INFO", "Sistema iniciado");
        funcionarioController.carregarDoArquivo();

        EstagiarioController estagiarioController = new EstagiarioController();
        EstagiarioView estagiarioView = new EstagiarioView(scanner, estagiarioController);
        FuncionarioView funcionarioView = new FuncionarioView(scanner, funcionarioController, estagiarioView);

        CargoController cargoController = new CargoController();
        CargoView cargoView = new CargoView(scanner);

        DepartamentoController departamentoController = new DepartamentoController();
        RepositorioDepartamento repositorioDepartamento = new RepositorioDepartamento();
        DepartamentoView departamentoView = new DepartamentoView(
                scanner, departamentoController, funcionarioController, repositorioDepartamento);

        BeneficioController beneficioController = new BeneficioController();
        BeneficioView beneficioView = new BeneficioView(scanner, beneficioController);

        FolhaPagamentoController folhaPagamentoController = new FolhaPagamentoController(funcionarioController);
        FolhaPagamentoView folhaPagamentoView = new FolhaPagamentoView(scanner, folhaPagamentoController);
  
        AvaliacaoView avaliacaoView = new AvaliacaoView(funcionarioController);
        TreinamentoView treinamentoView = new TreinamentoView(funcionarioController);

        VagaController vagaController = new VagaController();
        VagaView vagaView = new VagaView(scanner, vagaController, cargoController, departamentoController);

        CandidatoController candidatoController = new CandidatoController();
        CandidatoView candidatoView = new CandidatoView(scanner, candidatoController, vagaController);

        RecrutamentoService recrutamentoService = new RecrutamentoService(
                candidatoController, vagaController, funcionarioController, estagiarioController);

        int opcao;

        do {
            System.out.println("\n==============================");
            System.out.println("   SISTEMA DE GESTAO DE RH   ");
            System.out.println("==============================");
            System.out.println("1 - Funcionarios       (Aluno 1)");
            System.out.println("2 - Cargos             (Aluno 2)");
            System.out.println("3 - Departamentos      (Aluno 2)");
            System.out.println("4 - Beneficios         (Aluno 3)");
            System.out.println("5 - Folha de Pagamento (Aluno 3)");
            System.out.println("6 - Vagas              (Aluno 5)");
            System.out.println("7 - Candidatos         (Aluno 5)");
            System.out.println("8 - Contratar candidato aprovado (Aluno 5)");
            System.out.println("9 - Avaliações de Desempenho (Aluno 4)");
            System.out.println("10 - Treinamentos (Aluno 4)");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = lerInt();

            switch (opcao) {
                case 1: funcionarioView.exibirMenu();      break;
                case 2: cargoView.exibirMenu();            break;
                case 3: departamentoView.exibirMenu();     break;
                case 4: beneficioView.exibirMenu();        break;
                case 5: folhaPagamentoView.exibirMenu();   break;
                case 6: vagaView.exibirMenu();             break;
                case 7: candidatoView.exibirMenu();        break;
                case 8: contratarCandidato(recrutamentoService, candidatoView); break;
                case 9: avaliacaoView.menu(); break;
                case 10: treinamentoView.menu(); break;
                case 0:
    
                    funcionarioController.salvarEmArquivo();
                    LoggerService.log("INFO", "Sistema encerrado");
                    System.out.println("Dados salvos. Sistema encerrado.");
                    break;
                default: System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);
    }

    private void contratarCandidato(RecrutamentoService recrutamentoService, CandidatoView candidatoView) {
        System.out.println("\n-- Contratar Candidato Aprovado --");

        System.out.print("ID do candidato: ");
        int idCandidato = lerInt();

        System.out.println("Tipo de contrato (CLT / PJ / ESTAGIO): ");
        String tipoContrato = scanner.nextLine().trim().toUpperCase();

        System.out.print("Salario base (ou bolsa, se ESTAGIO): R$ ");
        double salario = lerDouble();

        String instituicaoEnsino = "";
        if (tipoContrato.equals("ESTAGIO")) {
            System.out.print("Instituicao de ensino: ");
            instituicaoEnsino = scanner.nextLine();
        }

        String resultado = recrutamentoService.contratar(idCandidato, tipoContrato, salario, instituicaoEnsino);
        System.out.println(resultado);
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

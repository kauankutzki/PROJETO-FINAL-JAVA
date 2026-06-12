package service;

import controller.CandidatoController;
import controller.EstagiarioController;
import controller.FuncionarioController;
import controller.VagaController;
import exception.DadoInvalidoException;
import model.Candidato;
import model.Vaga;
import model.enums.StatusCandidato;

/**
 * RecrutamentoService — regra de negocio que envolve MAIS DE UMA entidade.
 *
 * A contratacao usa:
 * - CandidatoController (Aluno 5): pega os dados do candidato e o remove do processo.
 * - VagaController (Aluno 5): diminui a quantidade de posicoes da vaga.
 * - FuncionarioController e EstagiarioController (Aluno 1): cadastram o novo funcionario.
 *
 * E por isso que essa regra fica no "service" e nao dentro de um Controller so:
 * ela "cruza" o modulo de Recrutamento com o modulo de Pessoal.
 */
public class RecrutamentoService {

    private CandidatoController candidatoController;
    private VagaController vagaController;
    private FuncionarioController funcionarioController;
    private EstagiarioController estagiarioController;

    public RecrutamentoService(CandidatoController candidatoController,
                                VagaController vagaController,
                                FuncionarioController funcionarioController,
                                EstagiarioController estagiarioController) {
        this.candidatoController = candidatoController;
        this.vagaController = vagaController;
        this.funcionarioController = funcionarioController;
        this.estagiarioController = estagiarioController;
    }

    /**
     * Contrata um candidato APROVADO, transformando-o em Funcionario.
     *
     * @param idCandidato       id do candidato no RepositorioCandidato
     * @param tipoContrato      "CLT", "PJ" ou "ESTAGIO"
     * @param salario           salario base (CLT/PJ) ou bolsa (Estagio)
     * @param instituicaoEnsino usado somente se tipoContrato for "ESTAGIO"
     */
    public String contratar(int idCandidato, String tipoContrato, double salario, String instituicaoEnsino) {
        Candidato candidato = candidatoController.buscarPorId(idCandidato);
        if (candidato == null) {
            return "ERRO: candidato com id " + idCandidato + " nao encontrado.";
        }
        if (candidato.getStatusProcesso() != StatusCandidato.APROVADO) {
            return "ERRO: somente candidatos com status APROVADO podem ser contratados.";
        }
        if (salario <= 0) {
            return "ERRO: o salario/bolsa deve ser maior que zero.";
        }
        if (tipoContrato == null) {
            return "ERRO: tipo de contrato invalido. Use: CLT, PJ ou ESTAGIO.";
        }

        // TRY/CATCH: o cadastro do funcionario pode lancar DadoInvalidoException
        try {
            switch (tipoContrato.trim().toUpperCase()) {
                case "CLT":
                    funcionarioController.cadastrarCLT(candidato.getNome(), candidato.getCpf(), candidato.getEmail(), salario);
                    break;
                case "PJ":
                    funcionarioController.cadastrarPJ(candidato.getNome(), candidato.getCpf(), candidato.getEmail(), salario);
                    break;
                case "ESTAGIO":
                    estagiarioController.cadastrar(candidato.getNome(), candidato.getCpf(), candidato.getEmail(), salario, instituicaoEnsino);
                    break;
                default:
                    return "ERRO: tipo de contrato invalido. Use: CLT, PJ ou ESTAGIO.";
            }
        } catch (DadoInvalidoException e) {
            return "ERRO: " + e.getMessage();
        }

        // O candidato virou funcionario: sai do processo seletivo.
        candidatoController.removerAposContratacao(idCandidato);

        // A vaga tem uma posicao a menos disponivel.
        Vaga vaga = candidato.getVagaPretendida();
        if (vaga != null) {
            vagaController.diminuirQuantidade(vaga);
        }

        return "Candidato " + candidato.getNome() + " contratado como " + tipoContrato.trim().toUpperCase() + " com sucesso!";
    }
}

package service;

import controller.CandidatoController;
import controller.EstagiarioController;
import controller.FuncionarioController;
import controller.VagaController;
import exceptions.DadoInvalidoException;
import model.Candidato;
import model.Vaga;
import model.enums.StatusCandidato;

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


        candidatoController.removerAposContratacao(idCandidato);

        Vaga vaga = candidato.getVagaPretendida();
        if (vaga != null) {
            vagaController.diminuirQuantidade(vaga);
        }

        return "Candidato " + candidato.getNome() + " contratado como " + tipoContrato.trim().toUpperCase() + " com sucesso!";
    }
}

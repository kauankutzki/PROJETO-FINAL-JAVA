package controller;

import model.Candidato;
import model.Vaga;
import model.enums.StatusCandidato;
import repository.RepositorioCandidato;

import java.util.List;

/**
 * CandidatoController — o "C" do MVC para Candidato.
 *
 * Fluxo:
 *   CandidatoView le o que o usuario digitou
 *     -> CandidatoController valida e decide o que fazer
 *        -> chama RepositorioCandidato (que guarda os dados)
 *     <- devolve o resultado
 *   <- CandidatoView exibe na tela
 *
 * Regra importante:
 * - Nunca tem System.out aqui (isso e papel da View).
 * - Nunca tem regra de negocio na View (isso e papel do Controller/Model).
 */
public class CandidatoController {

    private RepositorioCandidato repositorio = new RepositorioCandidato();

    // --- CADASTRAR ---

    public String cadastrar(String nome, String cpf, String email, Vaga vaga,
                             double pretensaoSalarial, String curriculoResumo) {
        if (nome == null || nome.trim().isEmpty()) {
            return "ERRO: nome nao pode ser vazio.";
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            return "ERRO: cpf nao pode ser vazio.";
        }
        if (vaga == null) {
            return "ERRO: e preciso escolher uma vaga.";
        }
        if (pretensaoSalarial < 0) {
            return "ERRO: a pretensao salarial nao pode ser negativa.";
        }

        Candidato novo = new Candidato(nome, cpf, email, vaga, pretensaoSalarial, curriculoResumo);
        repositorio.salvar(novo);
        return "Candidato cadastrado com sucesso! " + novo;
    }

    // --- ALTERAR ---

    public String avancarEtapa(int id) {
        Candidato candidato = repositorio.buscarPorId(id);
        if (candidato == null) {
            return "ERRO: candidato com id " + id + " nao encontrado.";
        }
        if (candidato.getStatusProcesso() == StatusCandidato.APROVADO) {
            return "ERRO: candidato ja esta APROVADO, nao ha proxima etapa.";
        }
        if (candidato.getStatusProcesso() == StatusCandidato.REPROVADO) {
            return "ERRO: candidato esta REPROVADO, processo encerrado.";
        }
        candidato.avancarEtapa();
        repositorio.atualizar(candidato);
        return "Etapa avancada! " + candidato;
    }

    public String reprovar(int id) {
        Candidato candidato = repositorio.buscarPorId(id);
        if (candidato == null) {
            return "ERRO: candidato com id " + id + " nao encontrado.";
        }
        candidato.reprovar();
        repositorio.atualizar(candidato);
        return "Candidato reprovado. " + candidato;
    }

    public String alterarDados(int id, double novaPretensao, String novoCurriculo) {
        Candidato candidato = repositorio.buscarPorId(id);
        if (candidato == null) {
            return "ERRO: candidato com id " + id + " nao encontrado.";
        }
        if (novaPretensao < 0) {
            return "ERRO: a pretensao salarial nao pode ser negativa.";
        }
        candidato.setPretensaoSalarial(novaPretensao);
        candidato.setCurriculoResumo(novoCurriculo);
        repositorio.atualizar(candidato);
        return "Dados atualizados! " + candidato;
    }

    // --- EXCLUIR ---

    public String excluir(int id) {
        Candidato candidato = repositorio.buscarPorId(id);
        if (candidato == null) {
            return "ERRO: candidato com id " + id + " nao encontrado.";
        }
        repositorio.excluir(id);
        return "Candidato '" + candidato.getNome() + "' removido do processo seletivo.";
    }

    // --- LISTAR ---

    public List<Candidato> listarTodos() {
        return repositorio.listarTodos();
    }

    public List<Candidato> listarPorVaga(Vaga vaga) {
        return repositorio.listarPorVaga(vaga);
    }

    public List<Candidato> listarPorStatus(String statusTexto) {
        StatusCandidato status = converterStatus(statusTexto);
        if (status == null) return List.of();
        return repositorio.listarPorStatus(status);
    }

    public Candidato buscarPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    /**
     * Remove o candidato do processo seletivo.
     * Usado pelo RecrutamentoService apos a contratacao
     * (o candidato passa a ser Funcionario, nao faz mais sentido como Candidato).
     */
    public void removerAposContratacao(int id) {
        repositorio.excluir(id);
    }

    // --- AUXILIAR ---

    // Converte o texto digitado pelo usuario no enum correspondente.
    private StatusCandidato converterStatus(String texto) {
        if (texto == null) return null;
        switch (texto.trim().toUpperCase()) {
            case "INSCRITO":  return StatusCandidato.INSCRITO;
            case "ENTREVISTA": return StatusCandidato.ENTREVISTA;
            case "APROVADO":  return StatusCandidato.APROVADO;
            case "REPROVADO": return StatusCandidato.REPROVADO;
            default:          return null;
        }
    }
}

package repository;

import model.Candidato;
import model.Vaga;
import model.enums.StatusCandidato;

import java.util.ArrayList;
import java.util.List;

/**
 * RepositorioCandidato — guarda todos os candidatos em memoria e faz o CRUD deles.
 *
 * "implements Repositorio<Candidato>" = cumpre o contrato da interface,
 * agora com T = Candidato (substituindo o coringa pelo tipo concreto).
 *
 * SOBRECARGA:
 * - buscarPorId(int id)                  — busca pelo id numerico
 * - listarPorStatus(StatusCandidato status) — busca por etapa do processo
 * Sao operacoes de busca diferentes, com assinaturas diferentes.
 *
 * COLECAO usada: ArrayList<Candidato> — lista dinamica que cresce conforme cadastramos.
 */
public class RepositorioCandidato implements Repositorio<Candidato> {

    // A lista que guarda todos os candidatos na memoria durante a execucao.
    private List<Candidato> candidatos = new ArrayList<>();

    // --- CRUD ---

    @Override
    public void salvar(Candidato candidato) {
        candidatos.add(candidato);
    }

    @Override
    public void atualizar(Candidato candidato) {
        // Substitui o candidato antigo (mesmo id) pelo novo.
        for (int i = 0; i < candidatos.size(); i++) {
            if (candidatos.get(i).getId() == candidato.getId()) {
                candidatos.set(i, candidato);
                return;
            }
        }
    }

    @Override
    public void excluir(int id) {
        candidatos.removeIf(c -> c.getId() == id);
    }

    @Override
    public List<Candidato> listarTodos() {
        return candidatos;
    }

    @Override
    public Candidato buscarPorId(int id) {
        for (Candidato c : candidatos) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null; // nao encontrado
    }

    // Lista apenas os candidatos que estao em uma etapa especifica do processo.
    public List<Candidato> listarPorStatus(StatusCandidato status) {
        List<Candidato> resultado = new ArrayList<>();
        for (Candidato c : candidatos) {
            if (c.getStatusProcesso() == status) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    // Lista apenas os candidatos inscritos em uma vaga especifica.
    public List<Candidato> listarPorVaga(Vaga vaga) {
        List<Candidato> resultado = new ArrayList<>();
        for (Candidato c : candidatos) {
            if (c.getVagaPretendida() == vaga) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}

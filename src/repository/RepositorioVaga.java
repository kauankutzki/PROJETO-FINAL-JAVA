package repository;

import model.Departamento;
import model.Vaga;
import model.enums.StatusVaga;

import java.util.ArrayList;
import java.util.List;

/**
 * RepositorioVaga — guarda todas as vagas em memoria e faz o CRUD delas.
 *
 * "implements Repositorio<Vaga>" = cumpre o contrato da interface,
 * agora com T = Vaga (substituindo o coringa pelo tipo concreto).
 *
 * SOBRECARGA:
 * - buscarPorId(int id)              — busca pelo id numerico
 * - listarPorStatus(StatusVaga status) — busca por status
 * Sao operacoes de busca diferentes, com assinaturas diferentes.
 *
 * COLECAO usada: ArrayList<Vaga> — lista dinamica que cresce conforme cadastramos.
 */
public class RepositorioVaga implements Repositorio<Vaga> {

    // A lista que guarda todas as vagas na memoria durante a execucao.
    private List<Vaga> vagas = new ArrayList<>();

    // --- CRUD ---

    @Override
    public void salvar(Vaga vaga) {
        vagas.add(vaga);
    }

    @Override
    public void atualizar(Vaga vaga) {
        // Substitui a vaga antiga (mesmo id) pela nova.
        for (int i = 0; i < vagas.size(); i++) {
            if (vagas.get(i).getId() == vaga.getId()) {
                vagas.set(i, vaga);
                return;
            }
        }
    }

    @Override
    public void excluir(int id) {
        vagas.removeIf(v -> v.getId() == id);
    }

    @Override
    public List<Vaga> listarTodos() {
        return vagas;
    }

    @Override
    public Vaga buscarPorId(int id) {
        for (Vaga v : vagas) {
            if (v.getId() == id) {
                return v;
            }
        }
        return null; // nao encontrada
    }

    // Lista apenas as vagas que estao em um status especifico.
    public List<Vaga> listarPorStatus(StatusVaga status) {
        List<Vaga> resultado = new ArrayList<>();
        for (Vaga v : vagas) {
            if (v.getStatus() == status) {
                resultado.add(v);
            }
        }
        return resultado;
    }

    // Lista apenas as vagas de um departamento especifico.
    public List<Vaga> listarPorDepartamento(Departamento departamento) {
        List<Vaga> resultado = new ArrayList<>();
        for (Vaga v : vagas) {
            if (v.getDepartamento() == departamento) {
                resultado.add(v);
            }
        }
        return resultado;
    }
}

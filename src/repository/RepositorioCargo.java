package repository;

import model.Cargo;
import model.enums.NivelCargo;

import java.util.ArrayList;
import java.util.List;

/**
 * RepositorioCargo — guarda todos os cargos em memoria e faz o CRUD deles.
 *
 * "implements Repositorio<Cargo>" = cumpre o contrato da interface,
 * agora com T = Cargo (substituindo o coringa pelo tipo concreto).
 *
 * SOBRECARGA:
 * - buscarPorId(int id)    — busca pelo id numerico
 * - buscarPorTitulo(String titulo) — busca pelo nome do cargo
 * Mesmo proposito, assinaturas diferentes: isso e SOBRECARGA (overload).
 *
 * COLECAO usada: ArrayList<Cargo> — lista dinamica que cresce conforme cadastramos.
 */
public class RepositorioCargo implements Repositorio<Cargo> {

    // A lista que guarda todos os cargos na memoria durante a execucao.
    private List<Cargo> cargos = new ArrayList<>();

    // --- CRUD ---

    @Override
    public void salvar(Cargo cargo) {
        cargos.add(cargo);
    }

    @Override
    public void atualizar(Cargo cargo) {
        // Substitui o cargo antigo (mesmo id) pelo novo.
        for (int i = 0; i < cargos.size(); i++) {
            if (cargos.get(i).getId() == cargo.getId()) {
                cargos.set(i, cargo);
                return;
            }
        }
    }

    @Override
    public void excluir(int id) {
        cargos.removeIf(c -> c.getId() == id);
    }

    @Override
    public List<Cargo> listarTodos() {
        return cargos;
    }

    @Override
    public Cargo buscarPorId(int id) {
        for (Cargo c : cargos) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null; // nao encontrado
    }

    // SOBRECARGA: mesma operacao de busca, mas recebe String em vez de int.
    public List<Cargo> buscarPorTitulo(String titulo) {
        List<Cargo> resultado = new ArrayList<>();
        for (Cargo c : cargos) {
            if (c.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    // Lista apenas os cargos de um nivel especifico.
    public List<Cargo> listarPorNivel(NivelCargo nivel) {
        List<Cargo> resultado = new ArrayList<>();
        for (Cargo c : cargos) {
            if (c.getNivel() == nivel) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}

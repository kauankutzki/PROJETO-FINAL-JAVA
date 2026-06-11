package repository;

import model.Departamento;

import java.util.ArrayList;
import java.util.List;

/**
 * RepositorioDepartamento — guarda todos os departamentos em memoria e faz o CRUD.
 *
 * "implements Repositorio<Departamento>" = mesmo contrato, agora T = Departamento.
 *
 * SOBRECARGA:
 * - buscarPorId(int id)       — busca pelo id
 * - buscarPorSigla(String s)  — busca pela sigla (ex.: "TI", "RH")
 */
public class RepositorioDepartamento implements Repositorio<Departamento> {

    private List<Departamento> departamentos = new ArrayList<>();

    // --- CRUD ---

    @Override
    public void salvar(Departamento departamento) {
        departamentos.add(departamento);
    }

    @Override
    public void atualizar(Departamento departamento) {
        for (int i = 0; i < departamentos.size(); i++) {
            if (departamentos.get(i).getId() == departamento.getId()) {
                departamentos.set(i, departamento);
                return;
            }
        }
    }

    @Override
    public void excluir(int id) {
        departamentos.removeIf(d -> d.getId() == id);
    }

    @Override
    public List<Departamento> listarTodos() {
        return departamentos;
    }

    @Override
    public Departamento buscarPorId(int id) {
        for (Departamento d : departamentos) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    // SOBRECARGA: busca pelo texto da sigla.
    public Departamento buscarPorSigla(String sigla) {
        for (Departamento d : departamentos) {
            if (d.getSigla().equalsIgnoreCase(sigla)) {
                return d;
            }
        }
        return null;
    }
}

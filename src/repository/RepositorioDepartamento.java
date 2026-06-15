package repository;

import model.Departamento;

import java.util.ArrayList;
import java.util.List;


public class RepositorioDepartamento implements Repositorio<Departamento> {

    private List<Departamento> departamentos = new ArrayList<>();


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

    public Departamento buscarPorSigla(String sigla) {
        for (Departamento d : departamentos) {
            if (d.getSigla().equalsIgnoreCase(sigla)) {
                return d;
            }
        }
        return null;
    }
}

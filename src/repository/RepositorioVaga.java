package repository;

import model.Departamento;
import model.Vaga;
import model.enums.StatusVaga;

import java.util.ArrayList;
import java.util.List;


public class RepositorioVaga implements Repositorio<Vaga> {

    private List<Vaga> vagas = new ArrayList<>();


    @Override
    public void salvar(Vaga vaga) {
        vagas.add(vaga);
    }

    @Override
    public void atualizar(Vaga vaga) {
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
        return null; 
    }

    public List<Vaga> listarPorStatus(StatusVaga status) {
        List<Vaga> resultado = new ArrayList<>();
        for (Vaga v : vagas) {
            if (v.getStatus() == status) {
                resultado.add(v);
            }
        }
        return resultado;
    }

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

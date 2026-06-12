package repository;

import model.Treinamento;

import java.util.ArrayList;
import java.util.List;

public class RepositorioTreinamento implements Repositorio<Treinamento> {

    private List<Treinamento> treinamentos = new ArrayList<>();

    @Override
    public void salvar(Treinamento treinamento) {
        treinamentos.add(treinamento);
    }

    @Override
    public void atualizar(Treinamento treinamento) {

        for (int i = 0; i < treinamentos.size(); i++) {

            if (treinamentos.get(i).getId() == treinamento.getId()) {

                treinamentos.set(i, treinamento);

                return;
            }
        }
    }

    @Override
    public void excluir(int id) {

        treinamentos.removeIf(
                treinamento -> treinamento.getId() == id
        );
    }

    @Override
    public List<Treinamento> listarTodos() {
        return treinamentos;
    }

    @Override
    public Treinamento buscarPorId(int id) {

        for (Treinamento treinamento : treinamentos) {

            if (treinamento.getId() == id) {
                return treinamento;
            }
        }

        return null;
    }
}
package repository;

import model.AvaliacaoDesempenho;

import java.util.ArrayList;
import java.util.List;

public class RepositorioAvaliacao implements Repositorio<AvaliacaoDesempenho> {

    private List<AvaliacaoDesempenho> avaliacoes = new ArrayList<>();

    @Override
    public void salvar(AvaliacaoDesempenho avaliacao) {
        avaliacoes.add(avaliacao);
    }

    @Override
    public void atualizar(AvaliacaoDesempenho avaliacao) {
        for (int i = 0; i < avaliacoes.size(); i++) {
            if (avaliacoes.get(i).getId() == avaliacao.getId()) {
                avaliacoes.set(i, avaliacao);
                return;
            }
        }
    }

    @Override
    public void excluir(int id) {
        avaliacoes.removeIf(avaliacao -> avaliacao.getId() == id);
    }

    @Override
    public List<AvaliacaoDesempenho> listarTodos() {
        return avaliacoes;
    }

    @Override
    public AvaliacaoDesempenho buscarPorId(int id) {
        for (AvaliacaoDesempenho avaliacao : avaliacoes) {
            if (avaliacao.getId() == id) {
                return avaliacao;
            }
        }

        return null;
    }
}
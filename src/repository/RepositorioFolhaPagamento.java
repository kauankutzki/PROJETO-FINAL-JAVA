package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.FolhaPagamento;

public class RepositorioFolhaPagamento implements Repositorio<FolhaPagamento> {

    private Map<Integer, FolhaPagamento> folhas;
    private int proximoId;

    public RepositorioFolhaPagamento() {
        folhas = new HashMap<Integer, FolhaPagamento>();
        proximoId = 1;
    }

    @Override
    public void salvar(FolhaPagamento folha) {
        folha.setId(proximoId);
        folhas.put(proximoId, folha);
        proximoId++;
    }

    @Override
    public void atualizar(FolhaPagamento folha) {
        folhas.put(folha.getId(), folha);
    }

    @Override
    public void excluir(int id) {
        folhas.remove(id);
    }

    @Override
    public List<FolhaPagamento> listarTodos() {
        return new ArrayList<FolhaPagamento>(folhas.values());
    }

    @Override
    public FolhaPagamento buscarPorId(int id) {
        return folhas.get(id);
    }

    public FolhaPagamento buscarPorMesAno(int mes, int ano) {
        for (FolhaPagamento folha : folhas.values()) {
            if (folha.getMes() == mes && folha.getAno() == ano) {
                return folha;
            }
        }

        return null;
    }
}

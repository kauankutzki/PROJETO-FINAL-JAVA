package repository;

import model.Funcionario;

import java.util.ArrayList;
import java.util.List;

/**
 * RepositorioFuncionario — guarda todos os funcionarios em memoria e faz o CRUD deles.
 *
 * SOBRECARGA:
 * - buscarPorId(int id)       — busca pelo id numerico
 * - buscarPorNome(String nome) — busca pelo nome do funcionario
 */
public class RepositorioFuncionario implements Repositorio<Funcionario> {

    private List<Funcionario> funcionarios = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public void salvar(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    @Override
    public void atualizar(Funcionario funcionario) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getId() == funcionario.getId()) {
                funcionarios.set(i, funcionario);
                return;
            }
        }
    }

    @Override
    public void excluir(int id) {
        funcionarios.removeIf(f -> f.getId() == id);
    }

    @Override
    public List<Funcionario> listarTodos() {
        return funcionarios;
    }

    @Override
    public Funcionario buscarPorId(int id) {
        for (Funcionario f : funcionarios) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    // SOBRECARGA: mesma operacao de busca, mas recebe String em vez de int.
    public List<Funcionario> buscarPorNome(String nome) {
        List<Funcionario> resultado = new ArrayList<>();
        for (Funcionario f : funcionarios) {
            if (f.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(f);
            }
        }
        return resultado;
    }
}

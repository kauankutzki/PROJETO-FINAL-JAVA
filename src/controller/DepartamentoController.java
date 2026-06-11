package controller;

import model.Departamento;
import model.Funcionario;
import repository.RepositorioDepartamento;

import java.util.List;

/**
 * DepartamentoController — o "C" do MVC para Departamento.
 *
 * Gerencia o CRUD de departamentos:
 * - Cadastrar: cria o departamento com nome, sigla e orcamento.
 * - Alterar: atualiza gestor, orcamento ou nome.
 * - Excluir: so permite se nao houver funcionarios lotados (REGRA DE NEGOCIO).
 * - Listar: todos, com custo de folha calculado.
 *
 * A regra de excluir so se vazio e um exemplo de validacao de negocio:
 * a View pede, o Controller decide se pode ou nao.
 */
public class DepartamentoController {

    private RepositorioDepartamento repositorio = new RepositorioDepartamento();

    // --- CADASTRAR ---

    public String cadastrar(String nome, String sigla, double orcamento) {
        if (nome == null || nome.trim().isEmpty()) {
            return "ERRO: nome nao pode ser vazio.";
        }
        if (sigla == null || sigla.trim().isEmpty()) {
            return "ERRO: sigla nao pode ser vazia.";
        }
        if (orcamento < 0) {
            return "ERRO: orcamento nao pode ser negativo.";
        }

        Departamento novo = new Departamento(nome, sigla, orcamento);
        repositorio.salvar(novo);
        return "Departamento cadastrado com sucesso! " + novo;
    }

    // --- ALTERAR ---

    public String alterarNome(int id, String novoNome) {
        Departamento dep = repositorio.buscarPorId(id);
        if (dep == null) return "ERRO: departamento nao encontrado.";
        if (novoNome == null || novoNome.trim().isEmpty()) return "ERRO: nome invalido.";
        dep.setNome(novoNome);
        repositorio.atualizar(dep);
        return "Nome atualizado! " + dep;
    }

    public String alterarOrcamento(int id, double novoOrcamento) {
        Departamento dep = repositorio.buscarPorId(id);
        if (dep == null) return "ERRO: departamento nao encontrado.";
        if (novoOrcamento < 0) return "ERRO: orcamento invalido.";
        dep.setOrcamentoMensal(novoOrcamento);
        repositorio.atualizar(dep);
        return "Orcamento atualizado! " + dep;
    }

    public String alterarGestor(int id, Funcionario novoGestor) {
        Departamento dep = repositorio.buscarPorId(id);
        if (dep == null) return "ERRO: departamento nao encontrado.";
        dep.setGestor(novoGestor);
        repositorio.atualizar(dep);
        String nomeGestor = (novoGestor != null) ? novoGestor.getNome() : "removido";
        return "Gestor atualizado para: " + nomeGestor;
    }

    // --- EXCLUIR ---

    /**
     * Regra de negocio: so exclui se o departamento estiver vazio.
     * Demonstra validacao antes de uma operacao destrutiva.
     */
    public String excluir(int id) {
        Departamento dep = repositorio.buscarPorId(id);
        if (dep == null) {
            return "ERRO: departamento nao encontrado.";
        }
        if (dep.quantidadeFuncionarios() > 0) {
            return "ERRO: nao e possivel excluir. O departamento '"
                    + dep.getNome() + "' possui "
                    + dep.quantidadeFuncionarios() + " funcionario(s) lotado(s). "
                    + "Transfira-os primeiro.";
        }
        repositorio.excluir(id);
        return "Departamento '" + dep.getNome() + "' excluido com sucesso.";
    }

    // --- FUNCIONARIOS NO DEPARTAMENTO ---

    public String adicionarFuncionario(int idDep, Funcionario f) {
        Departamento dep = repositorio.buscarPorId(idDep);
        if (dep == null) return "ERRO: departamento nao encontrado.";
        if (f == null) return "ERRO: funcionario invalido.";
        dep.adicionarFuncionario(f);
        repositorio.atualizar(dep);
        return "Funcionario '" + f.getNome() + "' adicionado ao departamento '" + dep.getNome() + "'.";
    }

    public String removerFuncionario(int idDep, String cpfFuncionario) {
        Departamento dep = repositorio.buscarPorId(idDep);
        if (dep == null) return "ERRO: departamento nao encontrado.";
        dep.removerFuncionario(cpfFuncionario);
        repositorio.atualizar(dep);
        return "Funcionario removido do departamento '" + dep.getNome() + "'.";
    }

    // --- LISTAR ---

    public List<Departamento> listarTodos() {
        return repositorio.listarTodos();
    }

    public Departamento buscarPorId(int id) {
        return repositorio.buscarPorId(id);
    }
}

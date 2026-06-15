package controller;

import model.Departamento;
import model.Funcionario;
import repository.RepositorioDepartamento;

import java.util.List;


public class DepartamentoController {

    private RepositorioDepartamento repositorio = new RepositorioDepartamento();

    

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

    

    public List<Departamento> listarTodos() {
        return repositorio.listarTodos();
    }

    public Departamento buscarPorId(int id) {
        return repositorio.buscarPorId(id);
    }
}

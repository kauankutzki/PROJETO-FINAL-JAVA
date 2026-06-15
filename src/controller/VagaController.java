package controller;

import model.Cargo;
import model.Departamento;
import model.Vaga;
import model.enums.StatusVaga;
import repository.RepositorioVaga;

import java.util.List;

public class VagaController {

    private RepositorioVaga repositorio = new RepositorioVaga();

    

    public String cadastrar(Cargo cargo, Departamento departamento, int quantidade) {
        if (cargo == null) {
            return "ERRO: a vaga precisa de um cargo.";
        }
        if (departamento == null) {
            return "ERRO: a vaga precisa de um departamento.";
        }
        if (quantidade <= 0) {
            return "ERRO: a quantidade deve ser maior que zero.";
        }

        Vaga nova = new Vaga(cargo, departamento, quantidade);
        repositorio.salvar(nova);
        return "Vaga cadastrada com sucesso! " + nova;
    }

    

    public String alterarStatus(int id, String statusTexto) {
        Vaga vaga = repositorio.buscarPorId(id);
        if (vaga == null) {
            return "ERRO: vaga com id " + id + " nao encontrada.";
        }
        StatusVaga status = converterStatus(statusTexto);
        if (status == null) {
            return "ERRO: status invalido. Use: ABERTA, EM_SELECAO ou FECHADA.";
        }
        vaga.setStatus(status);
        repositorio.atualizar(vaga);
        return "Status atualizado! " + vaga;
    }

    public String alterarQuantidade(int id, int novaQuantidade) {
        Vaga vaga = repositorio.buscarPorId(id);
        if (vaga == null) {
            return "ERRO: vaga com id " + id + " nao encontrada.";
        }
        if (novaQuantidade < 0) {
            return "ERRO: a quantidade nao pode ser negativa.";
        }
        vaga.setQuantidade(novaQuantidade);
        repositorio.atualizar(vaga);
        return "Quantidade atualizada! " + vaga;
    }

    

    public String excluir(int id) {
        Vaga vaga = repositorio.buscarPorId(id);
        if (vaga == null) {
            return "ERRO: vaga com id " + id + " nao encontrada.";
        }
        repositorio.excluir(id);
        return "Vaga [" + id + "] cancelada com sucesso.";
    }

    

    public List<Vaga> listarTodos() {
        return repositorio.listarTodos();
    }

    public List<Vaga> listarPorStatus(String statusTexto) {
        StatusVaga status = converterStatus(statusTexto);
        if (status == null) return List.of();
        return repositorio.listarPorStatus(status);
    }

    public List<Vaga> listarPorDepartamento(Departamento departamento) {
        return repositorio.listarPorDepartamento(departamento);
    }

    public Vaga buscarPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    
    public void diminuirQuantidade(Vaga vaga) {
        if (vaga != null) {
            vaga.diminuirQuantidade();
            repositorio.atualizar(vaga);
        }
    }

    private StatusVaga converterStatus(String texto) {
        if (texto == null) return null;
        switch (texto.trim().toUpperCase()) {
            case "ABERTA":      return StatusVaga.ABERTA;
            case "EM_SELECAO":  return StatusVaga.EM_SELECAO;
            case "FECHADA":     return StatusVaga.FECHADA;
            default:            return null;
        }
    }
}

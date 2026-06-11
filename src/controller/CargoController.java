package controller;

import model.Cargo;
import model.enums.NivelCargo;
import repository.RepositorioCargo;

import java.util.List;

/**
 * CargoController — o "C" do MVC para Cargo.
 *
 * Fluxo:
 *   CargoView le o que o usuario digitou
 *     -> CargoController valida e decide o que fazer
 *        -> chama RepositorioCargo (que guarda os dados)
 *     <- devolve o resultado
 *   <- CargoView exibe na tela
 *
 * Regra importante:
 * - Nunca tem System.out aqui (isso e papel da View).
 * - Nunca tem regra de negocio na View (isso e papel do Controller/Model).
 */
public class CargoController {

    private RepositorioCargo repositorio = new RepositorioCargo();

    // --- CADASTRAR ---

    public String cadastrar(String titulo, String nivelTexto, double salMin, double salMax) {
        // Valida os dados antes de criar o objeto.
        if (titulo == null || titulo.trim().isEmpty()) {
            return "ERRO: titulo nao pode ser vazio.";
        }
        if (salMin < 0 || salMax < salMin) {
            return "ERRO: faixa salarial invalida. Minimo deve ser >= 0 e maximo >= minimo.";
        }

        NivelCargo nivel = converterNivel(nivelTexto);
        if (nivel == null) {
            return "ERRO: nivel invalido. Use: JUNIOR, PLENO, SENIOR ou GESTAO.";
        }

        Cargo novo = new Cargo(titulo, nivel, salMin, salMax);
        repositorio.salvar(novo);
        return "Cargo cadastrado com sucesso! " + novo;
    }

    // --- ALTERAR ---

    public String alterarFaixa(int id, double novoMin, double novoMax) {
        Cargo cargo = repositorio.buscarPorId(id);
        if (cargo == null) {
            return "ERRO: cargo com id " + id + " nao encontrado.";
        }
        if (novoMin < 0 || novoMax < novoMin) {
            return "ERRO: faixa salarial invalida.";
        }
        cargo.setSalarioMinimo(novoMin);
        cargo.setSalarioMaximo(novoMax);
        repositorio.atualizar(cargo);
        return "Faixa salarial atualizada! " + cargo;
    }

    public String alterarNivel(int id, String nivelTexto) {
        Cargo cargo = repositorio.buscarPorId(id);
        if (cargo == null) {
            return "ERRO: cargo com id " + id + " nao encontrado.";
        }
        NivelCargo nivel = converterNivel(nivelTexto);
        if (nivel == null) {
            return "ERRO: nivel invalido. Use: JUNIOR, PLENO, SENIOR ou GESTAO.";
        }
        cargo.setNivel(nivel);
        repositorio.atualizar(cargo);
        return "Nivel atualizado! " + cargo;
    }

    // --- EXCLUIR ---

    /**
     * Exclui um cargo.
     * Regra de negocio: nao podemos excluir se o repositorio de funcionarios
     * referenciar este cargo. Por ora, a verificacao fica por conta da View/usuario
     * (na integracao final, o Aluno 1 fornece o RepositorioFuncionario para checar).
     */
    public String excluir(int id) {
        Cargo cargo = repositorio.buscarPorId(id);
        if (cargo == null) {
            return "ERRO: cargo com id " + id + " nao encontrado.";
        }
        repositorio.excluir(id);
        return "Cargo '" + cargo.getTitulo() + "' excluido com sucesso.";
    }

    // --- LISTAR ---

    public List<Cargo> listarTodos() {
        return repositorio.listarTodos();
    }

    public List<Cargo> listarPorNivel(String nivelTexto) {
        NivelCargo nivel = converterNivel(nivelTexto);
        if (nivel == null) return List.of();
        return repositorio.listarPorNivel(nivel);
    }

    public Cargo buscarPorId(int id) {
        return repositorio.buscarPorId(id);
    }

    // --- AUXILIAR ---

    // Converte o texto digitado pelo usuario no enum correspondente.
    // IF/ELSE para cobrir os 4 casos validos e o caso invalido.
    private NivelCargo converterNivel(String texto) {
        if (texto == null) return null;
        switch (texto.trim().toUpperCase()) {
            case "JUNIOR":  return NivelCargo.JUNIOR;
            case "PLENO":   return NivelCargo.PLENO;
            case "SENIOR":  return NivelCargo.SENIOR;
            case "GESTAO":  return NivelCargo.GESTAO;
            default:        return null;
        }
    }
}

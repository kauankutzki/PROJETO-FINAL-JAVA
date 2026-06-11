package repository;

import java.util.List;

/**
 * INTERFACE Repositorio<T> — contrato generico de CRUD.
 *
 * INTERFACE: um "contrato" que define o que toda classe de repositorio PRECISA ter.
 * Quem escrever "implements Repositorio<Cargo>" e obrigado a ter esses 5 metodos.
 * Se faltar um, o Java nao compila.
 *
 * GENERICS (<T>):
 * - T e um "coringa" que representa o tipo concreto.
 * - RepositorioCargo usa T = Cargo.
 * - RepositorioDepartamento usa T = Departamento.
 * - O mesmo contrato serve para os 7 CRUDs — sem duplicar codigo.
 *
 * Isso resolve o requisito de INTERFACE + GENERICS com um unico arquivo.
 */
public interface Repositorio<T> {

    // Salvar uma nova entidade.
    void salvar(T entidade);

    // Atualizar uma entidade ja existente (substitui pelo mesmo id).
    void atualizar(T entidade);

    // Excluir pelo id.
    void excluir(int id);

    // Devolver a lista completa.
    List<T> listarTodos();

    // Buscar um unico objeto pelo id. Retorna null se nao encontrar.
    T buscarPorId(int id);
}

package repository;

import java.util.List;


public interface Repositorio<T> {

    
    void salvar(T entidade);

    
    void atualizar(T entidade);

    
    void excluir(int id);

    
    List<T> listarTodos();

    
    T buscarPorId(int id);
}

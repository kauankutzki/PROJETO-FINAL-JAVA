package repository;

import model.Cargo;
import model.enums.NivelCargo;

import java.util.ArrayList;
import java.util.List;


public class RepositorioCargo implements Repositorio<Cargo> {

  
    private List<Cargo> cargos = new ArrayList<>();


    @Override
    public void salvar(Cargo cargo) {
        cargos.add(cargo);
    }

    @Override
    public void atualizar(Cargo cargo) {
        for (int i = 0; i < cargos.size(); i++) {
            if (cargos.get(i).getId() == cargo.getId()) {
                cargos.set(i, cargo);
                return;
            }
        }
    }

    @Override
    public void excluir(int id) {
        cargos.removeIf(c -> c.getId() == id);
    }

    @Override
    public List<Cargo> listarTodos() {
        return cargos;
    }

    @Override
    public Cargo buscarPorId(int id) {
        for (Cargo c : cargos) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null; 
    }

    public List<Cargo> buscarPorTitulo(String titulo) {
        List<Cargo> resultado = new ArrayList<>();
        for (Cargo c : cargos) {
            if (c.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<Cargo> listarPorNivel(NivelCargo nivel) {
        List<Cargo> resultado = new ArrayList<>();
        for (Cargo c : cargos) {
            if (c.getNivel() == nivel) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}

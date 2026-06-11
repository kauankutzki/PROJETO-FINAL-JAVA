package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Beneficio;
import model.enums.TipoBeneficio;

public class RepositorioBeneficio implements Repositorio<Beneficio> {

    private Map<Integer, Beneficio> beneficios;
    private int proximoId;

    public RepositorioBeneficio() {
        beneficios = new HashMap<Integer, Beneficio>();
        proximoId = 1;
    }

    @Override
    public void salvar(Beneficio beneficio) {
        beneficio.setId(proximoId);
        beneficios.put(proximoId, beneficio);
        proximoId++;
    }

    @Override
    public void atualizar(Beneficio beneficio) {
        beneficios.put(beneficio.getId(), beneficio);
    }

    @Override
    public void excluir(int id) {
        beneficios.remove(id);
    }

    @Override
    public List<Beneficio> listarTodos() {
        return new ArrayList<Beneficio>(beneficios.values());
    }

    @Override
    public Beneficio buscarPorId(int id) {
        return beneficios.get(id);
    }

    public List<Beneficio> listarPorTipo(TipoBeneficio tipo) {
        List<Beneficio> resultado = new ArrayList<Beneficio>();

        for (Beneficio beneficio : beneficios.values()) {
            if (beneficio.getTipo() == tipo) {
                resultado.add(beneficio);
            }
        }

        return resultado;
    }
}

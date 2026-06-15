package repository;

import model.Candidato;
import model.Vaga;
import model.enums.StatusCandidato;

import java.util.ArrayList;
import java.util.List;


public class RepositorioCandidato implements Repositorio<Candidato> {

    
    private List<Candidato> candidatos = new ArrayList<>();

    

    @Override
    public void salvar(Candidato candidato) {
        candidatos.add(candidato);
    }

    @Override
    public void atualizar(Candidato candidato) {
        
        for (int i = 0; i < candidatos.size(); i++) {
            if (candidatos.get(i).getId() == candidato.getId()) {
                candidatos.set(i, candidato);
                return;
            }
        }
    }

    @Override
    public void excluir(int id) {
        candidatos.removeIf(c -> c.getId() == id);
    }

    @Override
    public List<Candidato> listarTodos() {
        return candidatos;
    }

    @Override
    public Candidato buscarPorId(int id) {
        for (Candidato c : candidatos) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null; 
    }

    
    public List<Candidato> listarPorStatus(StatusCandidato status) {
        List<Candidato> resultado = new ArrayList<>();
        for (Candidato c : candidatos) {
            if (c.getStatusProcesso() == status) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    
    public List<Candidato> listarPorVaga(Vaga vaga) {
        List<Candidato> resultado = new ArrayList<>();
        for (Candidato c : candidatos) {
            if (c.getVagaPretendida() == vaga) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}

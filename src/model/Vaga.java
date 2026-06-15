package model;

import model.enums.StatusVaga;

public class Vaga {

    private static int contadorId = 1; 

    private int id;
    private Cargo cargo;              
    private Departamento departamento;
    private int quantidade;
    private StatusVaga status;


    public Vaga(Cargo cargo, Departamento departamento, int quantidade) {
        this.id = contadorId++;
        this.cargo = cargo;
        this.departamento = departamento;
        this.quantidade = quantidade;
        this.status = StatusVaga.ABERTA; 
    }

  
    public void diminuirQuantidade() {
        if (quantidade > 0) {
            quantidade--;
        }
        if (quantidade == 0) {
            status = StatusVaga.FECHADA;
        }
    }

    public int getId() { return id; }
    public Cargo getCargo() { return cargo; }
    public Departamento getDepartamento() { return departamento; }
    public int getQuantidade() { return quantidade; }
    public StatusVaga getStatus() { return status; }


    public void setCargo(Cargo cargo) {
        if (cargo != null) {
            this.cargo = cargo;
        }
    }

    public void setDepartamento(Departamento departamento) {
        if (departamento != null) {
            this.departamento = departamento;
        }
    }

    public void setQuantidade(int quantidade) {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        }
    }

    public void setStatus(StatusVaga status) {
        if (status != null) {
            this.status = status;
        }
    }

    @Override
    public String toString() {
        String nomeCargo = (cargo != null) ? cargo.getTitulo() : "Sem cargo";
        String nomeDepto = (departamento != null) ? departamento.getNome() : "Sem departamento";
        return String.format("[%d] Cargo: %s | Departamento: %s | Quantidade: %d | Status: %s",
                id, nomeCargo, nomeDepto, quantidade, status);
    }
}

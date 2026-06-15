package model;

public abstract class Pessoa implements java.io.Serializable {

    private static int contadorId = 1;

    protected int id;
    protected String nome;
    protected String cpf;
    protected String email;

    public Pessoa(String nome, String cpf, String email) {
        this.id = contadorId++;
        setNome(nome);
        setCpf(cpf);
        setEmail(email);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }

    public void setNome(String nome) {this.nome = nome;}

    public void setCpf(String cpf) {this.cpf = cpf;}

    public void setEmail(String email) {this.email = email;}
}

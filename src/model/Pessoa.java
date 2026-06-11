package model;

/**
 * CLASSE ABSTRATA Pessoa.
 *
 * "abstract" = essa classe e so um MOLDE.
 * Voce NAO consegue fazer "new Pessoa()" — o Java nao deixa.
 * Porque no RH ninguem e "uma pessoa generica":
 * ou e Funcionario, ou e Candidato (do aluno 5).
 *
 * ENCAPSULAMENTO:
 * - atributos sao "protected" = so essa classe e as filhas enxergam.
 * - quem esta de fora muda os dados pelos setters.
 */
public abstract class Pessoa {

    protected String nome;
    protected String cpf;
    protected String email;

    // CONSTRUTOR = a "receita" de como nasce uma Pessoa.
    public Pessoa(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    // GETTERS (pegar o valor)
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }

    // SETTERS (mudar o valor)
    public void setNome(String nome) { this.nome = nome; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setEmail(String email) { this.email = email; }
}

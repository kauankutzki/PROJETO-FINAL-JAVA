package model.interfaces;

/**
 * INTERFACE = um "contrato".
 *
 * Quem escrever "implements Avaliavel" PROMETE ter esses dois metodos.
 * Se nao tiver, o programa nao compila.
 *
 * No nosso sistema, quem implementa e o Funcionario.
 */
public interface Avaliavel {

    // Promessa 1: receber uma nota de avaliacao (de 0 a 10).
    void adicionarNota(double nota);

    // Promessa 2: calcular a media das notas recebidas.
    double calcularMediaAvaliacoes();
}

package model.enums;

/**
 * ENUM NivelCargo — lista fechada de niveis possiveis para um cargo.
 *
 * Enum = tipo especial onde os valores sao fixos e conhecidos de antemao.
 * Vantagem: o compilador impede que alguem invente um nivel inexistente
 * como "SUPERINTENDENTE_MASTER" — so valem esses quatro aqui.
 *
 * Usado em: Cargo.nivel
 */
public enum NivelCargo {
    JUNIOR,
    PLENO,
    SENIOR,
    GESTAO
}

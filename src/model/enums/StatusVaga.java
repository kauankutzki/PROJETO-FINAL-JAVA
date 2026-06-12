package model.enums;

/**
 * ENUM StatusVaga — lista fechada de status possiveis para uma vaga.
 *
 * Enum = tipo especial onde os valores sao fixos e conhecidos de antemao.
 * Vantagem: o compilador impede que alguem invente um status inexistente
 * como "PAUSADA" — so valem esses tres aqui.
 *
 * Usado em: Vaga.status
 */
public enum StatusVaga {
    ABERTA,
    EM_SELECAO,
    FECHADA
}

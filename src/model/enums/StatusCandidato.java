package model.enums;

/**
 * ENUM StatusCandidato — etapas do processo seletivo de um candidato.
 *
 * O candidato comeca em INSCRITO e vai avancando etapa por etapa
 * ate ser APROVADO (podendo ser contratado) ou REPROVADO (sai do processo).
 *
 * Usado em: Candidato.statusProcesso
 */
public enum StatusCandidato {
    INSCRITO,
    ENTREVISTA,
    APROVADO,
    REPROVADO
}

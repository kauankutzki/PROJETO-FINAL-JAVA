package exception;

/**
 * Lancada quando um dado informado nao passa na validacao de negocio.
 * Exemplo: salario negativo, nome vazio.
 */
public class DadoInvalidoException extends Exception {

    public DadoInvalidoException(String mensagem) {
        super(mensagem);
    }
}

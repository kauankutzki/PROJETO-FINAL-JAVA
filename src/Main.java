import view.MenuPrincipalView;

import java.util.Scanner;

/**
 * Main — ponto de entrada do sistema.
 *
 * Agora so cria o Scanner (compartilhado por todas as Views) e
 * entrega o controle para o MenuPrincipalView, que e responsabilidade
 * do Aluno 5 (integracao final dos 5 modulos).
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MenuPrincipalView menuPrincipal = new MenuPrincipalView(scanner);
        menuPrincipal.iniciar();

        scanner.close();
    }
}

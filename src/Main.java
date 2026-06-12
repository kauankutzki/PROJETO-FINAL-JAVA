import view.MenuPrincipalView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MenuPrincipalView menuPrincipal = new MenuPrincipalView(scanner);
        menuPrincipal.iniciar();

        scanner.close();
    }
}
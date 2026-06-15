package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LoggerService {

    private static final String ARQUIVO = "logs/log.txt";

    public static void log(String tipo, String mensagem) {
        try {
            File pasta = new File("logs");
            if (!pasta.exists()) {
                pasta.mkdir();
            }

            FileWriter writer = new FileWriter(ARQUIVO, true);
            LocalDateTime agora = LocalDateTime.now();
            String linha = "[" + agora + "] " + tipo + " - " + mensagem + "\n";

            writer.write(linha);
            writer.close();

        } catch (IOException e) {
            System.out.println("Erro ao registrar log.");
        }
    }
}

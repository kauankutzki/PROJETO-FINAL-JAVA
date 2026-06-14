package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

// Classe responsavel por registrar eventos do sistema em arquivo de log
public class LoggerService {

    // Caminho do arquivo de log
    private static final String ARQUIVO = "logs/log.txt";

    // Metodo principal para registrar log
    // tipo: "INFO", "WARNING" ou "ERROR"
    // mensagem: o que aconteceu
    public static void log(String tipo, String mensagem) {
        try {
            // Cria a pasta logs se nao existir
            File pasta = new File("logs");
            if (!pasta.exists()) {
                pasta.mkdir();
            }

            // Abre o arquivo em modo append (true = nao sobrescreve, apenas adiciona)
            FileWriter writer = new FileWriter(ARQUIVO, true);

            // Pega a data e hora atual
            LocalDateTime agora = LocalDateTime.now();

            // Monta a linha do log no mesmo formato do slide do professor
            String linha = "[" + agora + "] " + tipo + " - " + mensagem + "\n";

            // Escreve no arquivo
            writer.write(linha);

            // Fecha o arquivo
            writer.close();

        } catch (IOException e) {
            System.out.println("Erro ao registrar log.");
        }
    }
}

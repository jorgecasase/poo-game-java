import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private static final String LOG_FILE_PATH = "log.txt";

    public Logger(boolean reset) throws FicheroException{
        if(reset){
            try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE_PATH, false))) {
            } catch (IOException e) {
               throw new FicheroException("Error al reiniciar el archivo de registro" + e.getMessage());
            }
        }
    }

    public static void log(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

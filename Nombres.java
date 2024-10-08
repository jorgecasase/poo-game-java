import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Nombres {
    public static List<String> leerArchivoYCrearArrayList(String filePath) throws IOException {
        List<String> listaNombres = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                listaNombres.add(linea.trim());
            }
        }
        return listaNombres;
    }
    public static String generarNombreFichero(){
        Random random = new Random();
        String filePath = "listanombres.txt";
        try {
            List<String> nombres = leerArchivoYCrearArrayList(filePath);

            System.out.println("Contenido del ArrayList:");
            for (String nombre : nombres) {
                System.out.println(nombre);
            }
            return nombres.get(random.nextInt(nombres.size()));


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

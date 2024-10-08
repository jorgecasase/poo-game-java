import java.util.Random;

public class Escopeta extends Arma{
    public Escopeta(boolean danioAleatorio){
        super("Escopeta", danioAleatorio ? generarDanioAleatorio() : 30);
    }

    private static int generarDanioAleatorio(){
        Random random = new Random();
        return random.nextInt(26) + 25;
    }
}

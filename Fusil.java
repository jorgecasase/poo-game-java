import java.util.Random;

public class Fusil extends Arma{
    public Fusil(boolean danioAleatorio){
        super("Fusil", generarDanioAleatorio());
    }

    private static int generarDanioAleatorio(){
        Random random = new Random();
        return random.nextInt(31) + 30;
    }
}

import java.util.Random;
public class Pistola extends Arma{
    public Pistola(boolean danioAleatorio){
        super("Pistola", danioAleatorio ? generarDanioAleatorio() : 25);
    }

    private static int generarDanioAleatorio(){
        Random random = new Random();
        return (random.nextInt(16) + 15);
    }
}

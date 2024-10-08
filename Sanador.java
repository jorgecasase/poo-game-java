import javax.swing.*;

public class Sanador extends Jugador{

    private int vidaBotiquin = 50;

    public Sanador(String nombre, boolean esHumano){
        super(nombre, esHumano);
    }
    public void setVidaBotiquin(int vidaBotiquin){
        this.vidaBotiquin = vidaBotiquin;
    }
    public int getVidaBotiquin(){
        return vidaBotiquin;
    }
    public void usarBotiquin(){
        super.sumarVida(vidaBotiquin);
        JOptionPane.showMessageDialog(null, super.getNombre() + "se ha restaurado " + vidaBotiquin + " de vida");
    }
}

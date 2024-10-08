import javax.swing.*;

public class Ingeniero extends Jugador{
    private float multiplicador = 2.5f;
    private boolean trampaUsada = false;
    public Ingeniero(String nombre, boolean esHumano){
        super(nombre, esHumano);
    }
    public void setMultiplicador(int multiplicador){
        this.multiplicador = multiplicador;
    }
    public float getMultiplicador(){
        return this.multiplicador;
    }
    public boolean getTrampaUsada(){
        return trampaUsada;
    }

    public void setTrampaUsada(boolean trampaUsada){
        this.trampaUsada = trampaUsada;
    }

}

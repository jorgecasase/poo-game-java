public class Arma {
    private String nombre;
    private int danio;
    public Arma(String nombre, int danio){
        this.nombre = nombre;
        this.danio = danio;
    }
    public int getDanio(){
        return danio;
    }
    public String getNombre(){
        return this.nombre;
    }

    @Override
    public String toString(){
        return "" + nombre + ", daño: " + danio;
    }
}

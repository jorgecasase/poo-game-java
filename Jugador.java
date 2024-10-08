import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
public class Jugador {
    private int vida = 100;
    private String nombre;
    private boolean esHumano;
    private int kills = 0;
    private ArrayList<Arma> armas = new ArrayList<>();
    public Jugador(String nombre, boolean esHumano){
        this.nombre = nombre;
        this.esHumano = esHumano;
    }
    public void anadirArma(Arma arma){
        armas.add(arma);
    }
    public void anadirArmasAleatorias(){
        Random random = new Random();
        int num = random.nextInt(3);
        if(num == 0){
            armas.add(new Pistola(true));
        }
        else if(num == 1){
            armas.add(new Escopeta(true));
        }
        else {
            armas.add(new Fusil(true));
        }
    }

    public ArrayList<Arma> getArmas(){
        return this.armas;
    }
    public void mostrarArmas(){
        for(Arma a : armas){
            System.out.println(a.toString());
        }
    }
    public void adKill(){
        kills++;
    }
    public int getKills(){
        return kills;
    }

    public String getNombre(){
        return nombre;
    }
    public boolean esHumano(){
        return esHumano;
    }
    public int getVida(){
        return this.vida;
    }
    public void atacar(Jugador jugador, int arma, int fallo, int multiplicador){

            int danio = armas.get(arma).getDanio();
            Random random = new Random();
            fallo = random.nextInt(fallo);
            Logger logger = null;
            try{
                logger = new Logger(false);
            }catch(FicheroException e){
                System.out.println("Error con el logger " + e.getMessage());
            }

            if(fallo == 0){
                jugador.restarVida(0);
                System.out.println("El ataque de " + this.nombre + " ha fallado");
                if(this.esHumano || jugador.esHumano) {
                    StringBuilder mensajeFallo = new StringBuilder();
                    mensajeFallo.append("El ataque ha fallado ").append(nombre).append(" : ").append(vida).append(" hp\n")
                            .append(jugador.getNombre()).append(" : ").append(jugador.getVida()).append(" hp");
                    JOptionPane.showMessageDialog(null, "El ataque de " + this.nombre + " ha fallado", "Ataque", JOptionPane.INFORMATION_MESSAGE);
                    Logger.log("El ataque de " + this.nombre + " ha fallado");
                }
            }else{
                jugador.restarVida(danio * multiplicador);
                System.out.println(this.nombre + " ha atacado a " + jugador.getNombre() + " con su " + armas.get(arma).getNombre() + " y le ha hecho " + danio * multiplicador + " de daño");
                if(this.esHumano || jugador.esHumano) {
                    StringBuilder mensajeAtaque = new StringBuilder();
                    mensajeAtaque.append(nombre).append(" ha atacado a ").append(jugador.getNombre())
                            .append(" con su ").append(armas.get(arma).getNombre())
                            .append(" y le ha hecho ").append(danio * multiplicador).append(" de daño\n")
                            .append(nombre).append(" : ").append(vida).append(" hp\n")
                            .append(jugador.getNombre()).append(" : ").append(jugador.getVida()).append(" hp");
                    JOptionPane.showMessageDialog(null, mensajeAtaque.toString(), "Ataque", JOptionPane.INFORMATION_MESSAGE);
                    Logger.log(mensajeAtaque.toString());
                }
            }
            System.out.println(this.nombre + " : " + this.vida + " hp");
            System.out.println(jugador.nombre + " : " + jugador.getVida() + " hp");
    }
    public void restarVida(int vida){
        if(this.vida - vida < 0){
            this.vida = 0;
        }else {
            this.vida -= vida;
        }
    }
    public void sumarVida(int vida){
        if(this.vida + vida > 100){
            this.vida = 100;
        }else {
            this.vida += vida;
        }
    }
    public boolean estaMuerto(){
        return vida <= 0;
    }
    @Override
    public String toString(){
        return nombre + ", kills: " + kills + ", " + vida + "hp";
    }


}

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
public class Partida {
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private static int fallo = 5;

    public  Partida(int jugadoresHumanos, int bots){
        Scanner escaner = new Scanner(System.in);
        for(int i = 0; i < jugadoresHumanos; i++){
            String nombre = JOptionPane.showInputDialog("Nombre de jugador humano " + i);
            int tipo = Integer.parseInt(JOptionPane.showInputDialog("Tipo de jugador:\n1 - Sanador\n2 - Ingeniero\n3 - Asesino"));
            Jugador jugador;
            if(tipo == 1){
                jugador = new Sanador(nombre, true);
            }
            else if(tipo == 2){
                jugador = new Ingeniero(nombre, true);
            }
            else if(tipo == 3){
                jugador = new Asesino(nombre, true);
            }
            else{
                jugador = new Jugador(nombre, true);
            }
            int dificultad = Integer.parseInt(JOptionPane.showInputDialog("Nivel de dificultad para " + jugador.getNombre() +" \n1 - fácil\n2 - media\n3 - difícil"));
            if(dificultad == 1){
                jugador.anadirArma(new Fusil(true));
            }
            else if(dificultad == 2){
                jugador.anadirArma(new Escopeta(true));
            }
            else if(dificultad == 3){
                jugador.anadirArma((new Pistola(true)));
            }
            jugadores.add(jugador);
        }
        for(int i = 0; i < bots; i++){
            Jugador bot = new Jugador("bot " + Nombres.generarNombreFichero(), false);
            bot.anadirArmasAleatorias();
            jugadores.add(bot);
        }

    }
    public void jugarBattleRoyale(){
        System.out.println("Nueva partida");

        Random random = new Random();
        Collections.shuffle(jugadores);
        Jugador ganador = null;
        Scanner escaner = new Scanner(System.in);
        try{
            Logger logger = new Logger(true);
        }catch(FicheroException e){
            System.out.println("Error en la escritura de archivo");
        }
        Logger.log("Nueva partida");
        // hace emparejamiento
        while(jugadores.size() != 1){
            imprimirJugadores();
            int indice1 = random.nextInt(jugadores.size());
            Jugador jugador1 = jugadores.get(indice1);

            int indice2;
            do{
                indice2 = random.nextInt(jugadores.size());
            } while(indice1 == indice2);
            Jugador jugador2 = jugadores.get(indice2);

            // combate
            System.out.println(jugador1.getNombre() + " vs " + jugador2.getNombre());
            // gui
            JOptionPane.showMessageDialog(null,jugador1.getNombre() + " vs " + jugador2.getNombre() , "BATALLA", JOptionPane.INFORMATION_MESSAGE);
            Logger.log(jugador1.getNombre() + " vs " + jugador2.getNombre());
            while(!jugador1.estaMuerto() && !jugador2.estaMuerto()){
                // JUGADOR 1 HUMANO
                if(jugador1.esHumano()){
                    //gui
                    opciones(jugador1, jugador2);
                }
                else{
                    jugador1.atacar(jugador2, 0, fallo, 1);
                }
                if(jugador2.estaMuerto()){
                    ganador = jugador1;
                    ganador(jugador1, indice2, jugadores);
                    break;
                }

                // JUGADOR 2 HUMANO
                if(jugador2.esHumano()){
                    opciones(jugador2, jugador1);
                }
                else{
                    jugador2.atacar(jugador1, 0, fallo, 1);
                }
                if(jugador1.estaMuerto()){
                    ganador = jugador2;
                    ganador(jugador2, indice1, jugadores);
                    break;
                }
            }

        }
        System.out.println("GANADOR DE LA PARTIDA: " + ganador.getNombre() + " con " + ganador.getKills() + " kills");
        Logger.log("GANADOR DE LA PARTIDA: " + ganador.getNombre() + " con " + ganador.getKills() + " kills");
        //guio
        JOptionPane.showMessageDialog(null,"GANADOR DE LA PARTIDA: " + ganador.getNombre() + " con " + ganador.getKills() + " kills");
    }
    public void imprimirJugadores(){
        System.out.println("----------- JUGADORES " + jugadores.size() + " -------------");
       for(Jugador j : jugadores){
           System.out.println(j.toString());
       }
        System.out.println("------------------------------------");

       // gui
        StringBuilder infoJugadores = new StringBuilder();
        infoJugadores.append("----------- JUGADORES ").append(jugadores.size()).append(" -------------\n");
        for (Jugador j : jugadores) {
            infoJugadores.append(j.toString()).append("\n");
        }
        infoJugadores.append("------------------------------------");
        JOptionPane.showMessageDialog(null, infoJugadores.toString(), "Información de Jugadores", JOptionPane.INFORMATION_MESSAGE);
        Logger.log(infoJugadores.toString());
    }
    public void setFallo(int fallo) {
        this.fallo = fallo;
    }

    private static void opciones(Jugador jugador, Jugador rival){
        String opciones = jugador.getNombre() + " Opciones de combate:\n";
        if(jugador instanceof Sanador)
            opciones = jugador.getNombre() +" Opciones: \n1 - Atacar\n2 - Curarse\n3 - Inventario";
        if(jugador instanceof Ingeniero){
            opciones = jugador.getNombre() +" Opciones: \n1 - Atacar\n2 - Usar trampa (x2.5 daño próximo ataque)\n3 - Inventario";
        }
        if(jugador instanceof Asesino){
            opciones = jugador.getNombre() +" Opciones: \n1 - Atacar\n2 - Obtener nueva arma\n3  Inventario";
        }
        int eleccion;
        do {
           eleccion = Integer.parseInt(JOptionPane.showInputDialog(opciones));
            if (eleccion == 1) { //atacar
                // gui
                StringBuilder mensajeArmas = new StringBuilder();
                mensajeArmas.append("Con qué arma atacas:\n");
                int i = 0;
                for (Arma a : jugador.getArmas()) {
                    mensajeArmas.append(i).append(" ").append(a.toString()).append("\n");
                    i++;
                }
                int arma = Integer.parseInt(JOptionPane.showInputDialog(mensajeArmas));
                if (jugador instanceof Ingeniero && ((Ingeniero) jugador).getTrampaUsada()) {
                    jugador.atacar(rival, arma, fallo, 2);
                    ((Ingeniero) jugador).setTrampaUsada(false);
                } else {
                    jugador.atacar(rival, arma, fallo, 1);
                }
            } else if (eleccion == 2) { //habilidad
                if (jugador instanceof Sanador) {
                    StringBuilder mensajeBotiquin = new StringBuilder();
                    mensajeBotiquin.append("Con qué te curas:\n");
                    mensajeBotiquin.append("0 - Botiquin, ");
                    mensajeBotiquin.append(((Sanador) jugador).getVidaBotiquin() + " hp");
                    int cura = Integer.parseInt(JOptionPane.showInputDialog(mensajeBotiquin));
                    System.out.println(jugador.getNombre() + " ha usado botiquin para curar " + ((Sanador) jugador).getVidaBotiquin() + " de hp");
                    ((Sanador) jugador).usarBotiquin();
                }
                if (jugador instanceof Ingeniero) {
                    JOptionPane.showMessageDialog(null, "Trampa usada");
                    System.out.println(jugador.getNombre() + " ha usado trampa");
                    ((Ingeniero) jugador).setTrampaUsada(true);
                }
                if (jugador instanceof Asesino) {
                    jugador.anadirArmasAleatorias();
                    JOptionPane.showMessageDialog(null, "Añadida nueva arma");
                    System.out.println(jugador.getNombre() + " ha obtenido una nueva arma");
                }
            } else if (eleccion == 3) {
                StringBuilder inventario = new StringBuilder();
                int i = 0;
                for (Arma a : jugador.getArmas()) {
                    inventario.append(i + " "+a.toString() + "\n");
                }
                if(jugador instanceof Sanador){
                    inventario.append("Habilidad: Botiquin 50 hp");
                }
                else if(jugador instanceof Ingeniero){
                    inventario.append("Habilidad: Trampa x2.5 de daño en próximo ataque");
                }
                else if(jugador instanceof Asesino){
                    inventario.append("Habilidad: Tienda para conseguir armas");
                }
                JOptionPane.showMessageDialog(null, inventario);
            }
            else{
                eleccion = 0;
            }
        }while(eleccion == 3 || eleccion == 0);
    }
    private static void ganador(Jugador jugador, int indice, ArrayList<Jugador> jugadores){
        System.out.println("Ganador: " + jugador.getNombre() + " se le restauran 50 hp");
        Logger.log("Ganador: " + jugador.getNombre() + " se le restauran 50 hp");
        jugador.adKill();
        jugador.sumarVida(50);


        // gui
        JOptionPane.showMessageDialog(null,"Ganador " + jugador.getNombre() + " se le restauran 50 hp" , "BATALLA", JOptionPane.INFORMATION_MESSAGE);


        if(jugador.esHumano()){
            StringBuilder armas = new StringBuilder();
            int i = 0;
            for(Arma a : jugadores.get(indice).getArmas()){
                armas.append( i +" "+ a.toString()+"\n");
            }
            int armaIndice = Integer.parseInt(JOptionPane.showInputDialog(null, "Lootear armas de quien has matado (cualquier número si no quieres robar nada): \n" + armas));
            if(armaIndice >= 0 && armaIndice < jugadores.get(indice).getArmas().size()) {
                jugador.anadirArma(jugadores.get(indice).getArmas().get(armaIndice));
                String mensaje = jugador.getNombre() + " ha robado el arma " + jugadores.get(indice).getArmas().get(armaIndice).toString() + " de " + jugadores.get(indice).getNombre();
                System.out.println(mensaje);
                Logger.log(mensaje);
                JOptionPane.showMessageDialog(null, mensaje);
            }
            else{
                System.out.println("No se ha robado ningún arma");
            }

        }
        jugadores.remove(indice);
    }
}

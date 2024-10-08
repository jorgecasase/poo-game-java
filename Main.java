import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int jugadoresHumanos = Integer.parseInt(JOptionPane.showInputDialog("Jugadores Humanos"));
        int jugadoresBots = Integer.parseInt(JOptionPane.showInputDialog("Jugadores bots"));
        Partida p = new Partida(jugadoresHumanos, jugadoresBots);
        p.jugarBattleRoyale();
    }
}
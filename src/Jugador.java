import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Jugador {

    public static final char arriba = 'w', abajo = 's', izquierda = 'a', derecha = 'd';
    private char id;
    private String nombre;

    public Jugador(char id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Recibe el movimiento del jugador
     * @return
     */
    public static char recibeMovimineto(){
        System.out.println("Haz un moviemiento: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine().toCharArray()[0];
        } catch (Exception e) {
            e.printStackTrace();
            return ' ';
        }
    }
    public char getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}

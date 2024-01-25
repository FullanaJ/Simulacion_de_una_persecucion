import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Simulador {

    private Tablero tablero;
    private Jugador jugador;

    private final Cazador[] cazadores = new Cazador[3];
    private static final String[] nombres = {"Lobo feroz","The hunter","Terminator"};

    public Simulador() {
    }

    /**
     * Constructor del videojuego
     * @param tablero
     * @param cantCazadores
     */
    public Simulador(Tablero tablero,int cantCazadores) {
        this.tablero = tablero;
        this.jugador = new Jugador('J', "Jugador 1");
        if (cantCazadores<1 )
            cantCazadores = 1;
        else if (cantCazadores>3)
            cantCazadores = 3;
        for(;cantCazadores>0;cantCazadores--)
            cazadores[cantCazadores-1] = new Cazador('x',nombres[cantCazadores-1]);
        Cazador.setIdACazar(jugador.getId());
        ObservableList<double[]> lista = FXCollections.observableArrayList();
        lista.addListener(new ListChangeListener<double[]>() {
            @Override
            public void onChanged(Change<? extends double[]> change) {
                for (Cazador cazadore: cazadores) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            cazadore.caza(tablero);
                        }
                    }).start();
                }
            }
        });
        tablero.list = lista;
    }

    /**
     * Inicia Simulación
     */
    public void iniciarSimulacion() {
        posicionaJugadores();
        tablero.imprimirTablero();
        while (true){
            if(!tablero.haceMovimiento(Jugador.recibeMovimineto(),jugador))
                break;
        }
    }

    /**
     * posiciona a el jugador y a los cazadores en la tabla
     */
    private void posicionaJugadores() {
        double[] corJugador = tablero.generaCordenadasAlazar();
        double[] cor;
        tablero.modificaTabla(corJugador,(char)jugador.getId());
        for (int i = 0;i<cazadores.length;i++) {
            while(true) {
                cor = tablero.generaCordenadasAlazar();
                if (tablero.checkPosicion(cor) == ' ' && (Math.abs(cor[0] - corJugador[0]) > 5 || Math.abs(cor[1] - corJugador[1]) > 5)){
                    tablero.modificaTabla(cor,  cazadores[i].getId());
                break;
                }
            }
        }
    }

}

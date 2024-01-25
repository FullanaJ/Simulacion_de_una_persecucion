public class Cazador extends Jugador {

    public static int ID_A_CAZAR;

    public Cazador(char id, String nombre) {
        super(id, nombre);
    }

    /**
     * El cazador calcula según su pocición y la del jugador cual sería el mejor movimiento
     * @param tablero
     */
    public void caza(Tablero tablero) {
        double[] posicionJugador = tablero.getPosicionJugador(ID_A_CAZAR);
        double[] posicionActual = tablero.getPosicionJugador(getId());
        double y = posicionJugador[0] - posicionActual[0];
        double x = posicionJugador[1] - posicionActual[0];
        if (Math.abs(x) > Math.abs(y))
            mueveX(tablero, x);
        else if (Math.abs(x) < Math.abs(y))
            mueveY(tablero, y);
        else if (Math.random() == 0)
            mueveX(tablero, x);
        else
            mueveY(tablero,y);
    }

    /**
     * chequea si tiene que moverse hacia arriba o hacia abajo
     * y hace el movimiento
     * @param tablero
     * @param y
     */
    private void mueveY(Tablero tablero, double y) {
        if (y < 0)
            tablero.haceMovimiento(Jugador.arriba, this);
        else
            tablero.haceMovimiento(Jugador.abajo, this);
    }

    /**
     * chequea si tiene que moverse hacia izquierda o hacia derecha
     * y hace el movimiento
     * @param tablero
     * @param x
     */
    private void mueveX(Tablero tablero, double x) {
        if (x < 0)
            tablero.haceMovimiento(Jugador.izquierda, this);
        else
            tablero.haceMovimiento(Jugador.derecha, this);
    }

    public static void setIdACazar(int idACazar) {
        ID_A_CAZAR = idACazar;
    }
}

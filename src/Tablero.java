import javafx.collections.ObservableList;

import java.util.Arrays;

public class Tablero {

    private  char[][] tablero;
    private int filas;
    private int columnas;

    public ObservableList list;

    /**
     * Hace moviemiento siempre que no choque contra los bordes de la matriz
     * devuelve true si ha perdido
     * @param movimiento
     * @param jugador
     * @return
     */
    public synchronized boolean haceMovimiento(char movimiento, Jugador jugador){
        double[] posicionActual = getPosicionJugador(jugador.getId());
        switch (movimiento){
            case Jugador.arriba:
                if (posicionActual[0] > 0){
                    posicionActual[0] -= 1;
                }
                break;
            case Jugador.abajo:
                if (posicionActual[0] < getFilas() - 1){
                    posicionActual[0] += 1;
                }
                break;
            case Jugador.izquierda:
                if (posicionActual[1] > 1){
                    posicionActual[1] -= 1;
                }
                break;
            case Jugador.derecha:
                if (posicionActual[1] < getColumnas() - 1)
                    posicionActual[1] += 1;
                break;
            default:
                System.out.println("Movimiento no valido");
        }
        return moverJugador(jugador, posicionActual[0], posicionActual[1]);
    }

    /**
     * Constructor del tablero
     * @param filas
     * @param columnas
     */
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        tablero = new char[filas][columnas];
        Arrays.fill(tablero[0], '-');
        Arrays.fill(tablero[filas - 1], '-');
        for (int i = 1; i < filas - 1; i++) {
            Arrays.fill(tablero[i], ' ');
            tablero[i][0] = '|';
            tablero[i][columnas - 1] = '|';
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
    /**
     * Imprime el tablero
     */
    public void imprimirTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.println();
        }
    }
    /**
     * Devuelve la posicion del jugador
     * @param id
     * @return
     */
    public synchronized double[] getPosicionJugador(int id) {
        double[] posicion = new double[2];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j] == id) {
                    posicion[0] = i;
                    posicion[1] = j;
                    return posicion;
                }
            }
        }
        return posicion;
    }

    /**
     * Mueve jugador o cazador devuelve false si ha perdido
     * @param jugador
     * @param x
     * @param y
     * @return
     */
    public boolean moverJugador(Jugador jugador, double x, double y) {
        double[] posicionActual = getPosicionJugador(jugador.getId());
        double[] posicionAMover = new double[]{x, y};
        if (jugador instanceof Cazador) {
            if (checkPosicion(posicionAMover) == ' ') {
                modificaTabla(posicionActual,' ');
                modificaTabla(posicionAMover,jugador.getId());
            }else if (checkPosicion(posicionAMover) == Cazador.ID_A_CAZAR){
                pierde();
                return false;
            }
        }else {
            if (checkPosicion(posicionAMover)!= ' ') {
                pierde();
                return false;
            }else{
                modificaTabla(posicionActual,' ');
                modificaTabla(posicionAMover,jugador.getId());
                list.add(posicionAMover);
            }
        }
        imprimirTablero();
        return true;
    }

    /**
     * Devuelve el caracter que hay en la posicion
     * @param cor
     * @return
     */
    public char checkPosicion(double[] cor){
        return tablero[(int)cor[0]][(int)cor[1]];
    }
    /**
     * Modifica la tabla
     * @param cor
     * @param id
     */
    public void modificaTabla(double[] cor, char id){
        tablero[(int)cor[0]][(int)cor[1]] = id;
    }
    /**
     * Imprime que ha perdido
     */
    private void pierde() {
        System.out.println("Juego finalizado");

    }
    /**
     * Genera coordenadas al azar
     * @return
     */
    public double[] generaCordenadasAlazar(){
        return new double[]{1+(Math.random() * getFilas()-2), 1+(Math.random() *  getColumnas()-2)};
    }
}

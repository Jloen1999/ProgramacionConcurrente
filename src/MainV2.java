import java.util.ArrayList;
import java.util.List;

/**
 * Lanzar 5 hilos que muestren por pantalla el numero del hilo y el orden en que se muestra con la interfaz Runnable y la clase Thread.
 * Cual es mejor Runnable o la clase Thread?
 */
public class MainV2 implements Runnable {

    private static final int numeroNucleo = 16;
    private static final int totalIncremento = 10000;
    private static int contador = 0;
    private final String nameHilo;

    private static Object cond = new Object();

    public MainV2(String nameHilo) {
        this.nameHilo = nameHilo;
    }

    public String getNameHilo() {
        return nameHilo;
    }

    public static void main(String[] args) {
        // Lista para almacenar los hilos (uno por piloto)
        List<Thread> hilos = new ArrayList<>();
        // Llenamos el vector con id de los hilos
        MainV2[] vectorHilos = new MainV2[numeroNucleo];
        for (int i = 0; i < vectorHilos.length; i++) {
            vectorHilos[i] = new MainV2("Hilo: " + i);
        }

        // Creamos los hilos de la clase Thread
        for (MainV2 vectorHilo : vectorHilos) {
            Thread hiloThread = new Thread(vectorHilo);
            hilos.add(hiloThread); // Agregamos cada hilo a la lista
            hiloThread.start(); // Ejecutamos cada hilo
        }

        // Esperamos a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Numero de nucleos ejecutados: " + ANSIColors.GREEN + numeroNucleo + "\nContador final: " + ANSIColors.BLUE + contador + ANSIColors.RESET);


    }

    public void incrementarPar(){
        System.out.println(getNameHilo() + " Antes del contador: " + contador);
        for (int i = 0; i < totalIncremento; i++) {
            contador++;
        }

        System.out.println(getNameHilo() + " Despues del contador: " + contador);
    }

    public void incrementarImpar(){
        System.out.println(getNameHilo() + " Antes del contador: " + contador);
        for (int i = 0; i < totalIncremento; i++) {
            contador++;
        }

        System.out.println(getNameHilo() + " Despues del contador: " + contador);
    }

    @Override
    public void run() {
        //Condicion de sincronizacion
        synchronized (cond) { // Pone en espera el resto de hilos cuando un hilo esta accediendo al recurso compartido.
            if (((int) getNameHilo().charAt(getNameHilo().length() - 1) % 2 == 0)) {
                incrementarPar();
            }else {
                incrementarImpar();
            }
        }


    }
}
public class Sesion1V1 implements Runnable {

    private static int contador = 0;
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        Thread[] hilos = new Thread[Runtime.getRuntime().availableProcessors()];

        // Crear hilos
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new Sesion1());
        }

        // Iniciar y ejecutar hilos con ID impar
        for (int i = 0; i < hilos.length; i++) {
            if (i % 2 != 0) {
                hilos[i].start();
                try {
                    hilos[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Iniciar y ejecutar hilos con ID par
        for (int i = 0; i < hilos.length; i++) {
            if (i % 2 == 0) {
                hilos[i].start();
                try {
                    hilos[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Número de núcleos lógicos: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Resultado final del contador: " + contador);
    }

    @Override
    public void run() {
        int idHilo = (int) Thread.currentThread().getId() % Runtime.getRuntime().availableProcessors(); // Para obtener un ID entre 0 y 15
        System.out.println("ID Hilo: " + idHilo + " - Contador antes de ejecución: " + contador);

        for (int i = 0; i < 10000; i++) {
            incrementar();
        }

        System.out.println("ID Hilo: " + idHilo + " - Contador después de ejecución: " + contador);
    }

    public void incrementar() {
        synchronized(LOCK) {
            contador++;
        }
    }
}

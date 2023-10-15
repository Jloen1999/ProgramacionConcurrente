/**
 * Clase que implementa la interfaz Runnable.
 * Esta clase simula la ejecución concurrente de múltiples hilos para incrementar
 * un contador compartido. El número de hilos creados es equivalente al número de
 * núcleos lógicos del procesador del sistema.
 *
 * Cada hilo, al ejecutarse, incrementa el contador 10.000 veces. La clase garantiza
 * un incremento seguro del contador usando sincronización. La ejecución se lleva a
 * cabo de manera que primero se ejecutan los hilos con un ID impar y, posteriormente,
 * los hilos con un ID par.
 *
 * Al final de la ejecución, se muestra el valor final del contador y el número de núcleos
 * lógicos del sistema.
 *
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0
 * <dl>
 *     <dt style="color: blue">¿Se produce indeterminismo?</dt>
 *     <dd>En el código proporcionado, no se produce indeterminismo en el valor final del contador ni en el orden de los mensajes impresos. Esto se debe a dos factores principales:
 * <ol>
 *  <li>Uso de <code style="font-weight: bold">synchronized</code>: Garantiza que el contador se incremente de forma atómica y segura, evitando condiciones de carrera.</li>
 *  <li>Uso de <code style="font-weight: bold">join()</code>: Serializa la ejecución de los hilos, primero ejecutando todos los hilos impares y luego los pares, lo que asegura un orden específico en los mensajes impresos.</li>
 * </ol>
 * Por lo tanto, cada vez que se ejecute el programa, se obtendrá el mismo resultado y el mismo orden de impresión de mensajes.</dd>
 * </dl>
 * </span>
 */
public class Sesion1 implements Runnable {

    // Contador compartido por todos los hilos
    private static int contador = 0;
    // Objeto de bloqueo para sincronizar el acceso al contador
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        // Crea un array de hilos basado en el número de núcleos lógicos del procesador
        Thread[] hilos = new Thread[Runtime.getRuntime().availableProcessors()];

        // Inicializa cada hilo para ejecutar una instancia de Sesion1
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new Sesion1(), String.valueOf(i + 1)); // Asigna un nombre para que el ID del hilo comience desde 1
        }

        // Ejecuta hilos con índice impar
        ejecutarHilosPorParidad(hilos, false);

        // Ejecuta hilos con índice par
        ejecutarHilosPorParidad(hilos, true);

        // Imprime el número de núcleos lógicos y el valor final del contador
        System.out.println("Número de núcleos lógicos: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Resultado final del contador: " + contador);
    }

    /**
     * Método para ejecutar hilos basado en su paridad (par o impar)
     * @param hilos Array de hilos a ejecutar
     * @param esPar Indica si el hilo es par o impar
     */
    private static void ejecutarHilosPorParidad(Thread[] hilos, boolean esPar) {
        for (int i = 0; i < hilos.length; i++) {
            if (esPar && (i + 1) % 2 == 0) { // Para hilos pares
                iniciarYEsperarHilo(hilos[i]);
            } else if (!esPar && (i + 1) % 2 != 0) { // Para hilos impares
                iniciarYEsperarHilo(hilos[i]);
            }
        }
    }

    /**
     * Método para iniciar un hilo y esperar a que termine su ejecución
     * @param hilo Hilo a iniciar
     */
    private static void iniciarYEsperarHilo(Thread hilo) {
        hilo.start(); //Iniciar la ejecucion de los hilos impares
        try {
            hilo.join(); //Esperar a que se complete la ejecución de los hilos impares.
        } catch (InterruptedException e) {
            e.printStackTrace(); // Manejo de la excepción si el hilo es interrumpido
        }
    }

    @Override
    public void run() {
        // Obtiene el ID del hilo desde el nombre que se le asignó (comienza desde 1)
        int idHilo = Integer.parseInt(Thread.currentThread().getName());

        // Muestra el valor actual del contador
        System.out.println("ID Hilo: " + idHilo + " - Contador antes de ejecución: " + contador);

        // Incrementa el contador 10.000 veces
        for (int i = 0; i < 10000; i++) {
            incrementar();
        }

        // Muestra el valor del contador después de incrementarlo
        System.out.println("ID Hilo: " + idHilo + " - Contador después de ejecución: " + contador);
    }

    /**
     * Método sincronizado para incrementar el contador de manera segura
     */
    public void incrementar() {
        synchronized (LOCK) { // Bloque sincronizado para garantizar un acceso seguro al contador
            contador++;
        }
    }
}

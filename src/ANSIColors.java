/**
 * Define constantes ANSI para colorear la salida de texto en la consola.
 * Estos códigos son útiles para resaltar la salida de texto en consolas que soportan colores ANSI.
 *
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0
 * @since 1.0
 */
public interface ANSIColors {
    /**
     * Código ANSI para resetear el color.
     */
    public static final String RESET = "\u001B[0m";

    /**
     * Código ANSI para el color rojo.
     */
    public static final String RED = "\u001B[31m";

    /**
     * Código ANSI para el color verde.
     */
    public static final String GREEN = "\u001B[32m";

    /**
     * Código ANSI para el color amarillo.
     */
    public static final String YELLOW = "\u001B[33m";

    /**
     * Código ANSI para el color azul.
     */
    public static final String BLUE = "\u001B[34m";

    /**
     * Código ANSI para el color púrpura.
     */
    public static final String PURPLE = "\u001B[35m";

    /**
     * Código ANSI para el color cian.
     */
    public static final String CYAN = "\u001B[36m";

    /**
     * Código ANSI para el color blanco.
     */
    public static final String WHITE = "\u001B[37m";

    /**
     * Código ANSI para un tono brillante de verde.
     */
    public static final String BRIGHT_GREEN = "\u001B[92m";
}

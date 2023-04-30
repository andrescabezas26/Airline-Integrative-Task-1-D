package exceptions;

/**
 * Excepción que se lanza cuando se intenta insertar una clave mayor en una estructura que no lo permite.
 */
public class KeyIsBigger extends Exception {

    /**
     * Mensaje de la excepción.
     */
    private String msj;

    /**
     * Crea una nueva excepción KeyIsBigger con el mensaje especificado.
     * 
     * @param msj Mensaje de la excepción
     */
    public KeyIsBigger(String msj) {
        super(msj);
        this.msj = msj;
    }

    /**
     * Obtiene el mensaje de la excepción.
     * 
     * @return El mensaje de la excepción
     */
    public String getMsj() {
        return msj;
    }
}

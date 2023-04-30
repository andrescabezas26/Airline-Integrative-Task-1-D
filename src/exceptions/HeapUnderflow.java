package exceptions;

/**
 * Excepción que se lanza cuando se intenta realizar una operación en un Heap vacío.
 */
public class HeapUnderflow extends Exception {

    /**
     * Mensaje de la excepción.
     */
    private String msj;

    /**
     * Crea una nueva excepción HeapUnderflow con el mensaje especificado.
     * 
     * @param msj Mensaje de la excepción
     */
    public HeapUnderflow(String msj) {
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

package exceptions;

/**
 * Esta excepcion es lanzada cuando una llave es menor a otra en una estructura de datos que requiere un ordenamiento por llaves.
 */
public class KeyIsSmaller extends Exception {

    private String msj;

    /**
     * Crea una nueva instancia de la excepcion con el mensaje especificado.
     * 
     * @param msj mensaje de la excepcion
     */
    public KeyIsSmaller(String msj) {
        super(msj);
        this.msj = msj;
    }

    /**
     * Obtiene el mensaje de la excepcion.
     * 
     * @return mensaje de la excepcion
     */
    public String getMsj() {
        return msj;
    }

}

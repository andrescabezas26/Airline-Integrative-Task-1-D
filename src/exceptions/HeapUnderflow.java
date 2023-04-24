package exceptions;

public class HeapUnderflow extends Exception {

    private String msj;

    public HeapUnderflow(String msj) {
        super(msj);
        this.msj = msj;
    }

    public String getMsj() {
        return msj;
    }

}

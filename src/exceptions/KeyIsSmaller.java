package exceptions;

public class KeyIsSmaller extends Exception {

    private String msj;

    public KeyIsSmaller(String msj) {
        super(msj);
        this.msj = msj;
    }

    public String getMsj() {
        return msj;
    }

}

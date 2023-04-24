package exceptions;

public class KeyIsBigger extends Exception {

    private String msj;

    public KeyIsBigger(String msj) {
        super(msj);
        this.msj = msj;
    }

    public String getMsj() {
        return msj;
    }

}

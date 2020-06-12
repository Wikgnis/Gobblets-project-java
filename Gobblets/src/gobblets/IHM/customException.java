package gobblets.IHM;

import gobblets.IHM.Erreur;

public class customException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Erreur e;
    private String s = "";

    public customException(Erreur e) {
        this.e = e;
    }

    public customException(Erreur e, String s) {
        this.e = e;
        this.s = s;
    }

    public Erreur getE() {
        return e;
    }

    public void setE(Erreur e) {
        this.e = e;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
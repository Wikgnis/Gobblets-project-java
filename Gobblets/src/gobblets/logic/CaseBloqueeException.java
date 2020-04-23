package gobblets.logic;

import gobblets.IHM.Erreur;

public class CaseBloqueeException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 312734242322755571L;

    private Erreur e;

    public CaseBloqueeException() {}
    public CaseBloqueeException(String s) {}
    public CaseBloqueeException(Throwable t) {}
    public CaseBloqueeException(String s, Throwable t) {}
    public CaseBloqueeException(String s, Throwable t, boolean a, boolean b) {}
    public CaseBloqueeException(Erreur e) {
        this.e = e;
    }

    public static long getSerialversionuid() { return serialVersionUID; }

    public Erreur getErreur() { return e; }

    @Override
    public String toString() {
        return "CaseBloqueeException [e=" + e + "]";
    }
}
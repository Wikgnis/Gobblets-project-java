package gobblets.logic;

import gobblets.IHM.Erreur;

public class PiecePasdisponibleException extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 426118904980236417L;

    private Erreur e;

    public PiecePasdisponibleException() {}
    public PiecePasdisponibleException(String s) {}
    public PiecePasdisponibleException(Throwable t) {}
    public PiecePasdisponibleException(String s, Throwable t) {}
    public PiecePasdisponibleException(String s, Throwable t, boolean a, boolean b) {}
    public PiecePasdisponibleException(Erreur e) { this.e = e; }

    public static long getSerialversionuid() { return serialVersionUID; }

    public Erreur getErreur() { return e; }

    @Override
    public String toString() { return "PiecePasdisponibleException [e=" + e + "]"; }
}
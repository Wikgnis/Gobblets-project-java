package gobblets.data;

import org.fusesource.jansi.Ansi.Color;

public enum Couleur{
    NOIR(0,0,0,Color.BLACK),
    BLEU(0,0,255,Color.BLUE),
    CYAN(0,255,255,Color.CYAN),
    VERT(0,255,0,Color.GREEN),
    MAGENTA(255,0,255,Color.MAGENTA),
    ROUGE(255,0,0,Color.RED),
    BLANC(255,255,255,Color.WHITE),
    JAUNE(255,255,0,Color.YELLOW);

    /**
     * utilisé pour afficher les couleurs dans Saisie Console
     * 
     * @see SaisieConsole.java
     * @see lib/jansi-1.17.jar
     */
    private final Color ansiColor;
    private int R, G, B;

    Couleur(int r, int g, int b, Color ansiColor) {
        this.ansiColor = ansiColor;
        R = r;
        G = g;
        B = b;
    }

    /**
     * return le code ansi de la couleur
     * 
     * @return le code ansi de la couleur
     */
    public Color getAnsiColor() {
        return ansiColor;
    }

    public int getR() {
        return R;
    }

    public int getG() {
        return G;
    }

    public int getB() {
        return B;
    }
}
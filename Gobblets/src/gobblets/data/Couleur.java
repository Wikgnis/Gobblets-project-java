package gobblets.data;

import org.fusesource.jansi.Ansi.Color;

public enum Couleur{
    NOIR(0,0,0,Color.BLACK, javafx.scene.paint.Color.BLACK),
    BLEU(0,0,255,Color.BLUE, javafx.scene.paint.Color.BLUE),
    CYAN(0,255,255,Color.CYAN, javafx.scene.paint.Color.CYAN),
    VERT(0,255,0,Color.GREEN, javafx.scene.paint.Color.GREEN),
    MAGENTA(255,0,255,Color.MAGENTA, javafx.scene.paint.Color.MAGENTA),
    ROUGE(255,0,0,Color.RED, javafx.scene.paint.Color.RED),
    BLANC(255,255,255,Color.WHITE, javafx.scene.paint.Color.WHITE),
    JAUNE(255,255,0,Color.YELLOW, javafx.scene.paint.Color.YELLOW);

    /**
     * utilisé pour afficher les couleurs dans Saisie Console
     * 
     * @see SaisieConsole.java
     * @see lib/jansi-1.17.jar
     */
    private final Color ansiColor;
    private final javafx.scene.paint.Color fxColor;
    private int R, G, B;

    Couleur(int r, int g, int b, Color ansiColor, javafx.scene.paint.Color fxColor) {
        this.fxColor = fxColor;
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

    public javafx.scene.paint.Color getFxColor() {
        return fxColor;
    }
}
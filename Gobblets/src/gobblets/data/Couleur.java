package gobblets.data;

import org.fusesource.jansi.Ansi.Color;

public enum Couleur {
    /**
     * BLACK BLUE CYAN DEFAULT GREEN MAGENTA RED WHITE YELLOW
     */
    NOIR(Color.BLACK),
    BLEU(Color.BLUE),
    CYAN(Color.CYAN),
    VERT(Color.GREEN),
    MAGENTA(Color.MAGENTA),
    ROUGE(Color.RED),
    BLANC(Color.WHITE),
    JAUNE(Color.YELLOW);

    private final Color ansiColor;

    Couleur(Color ansiColor) {
        this.ansiColor = ansiColor;
    }

    public Color getAnsiColor() {
        return ansiColor;
    }
}
package gobblets.IHM.texte;

public class Piece {
    private gobblets.data.Piece contenu;

    public Piece(gobblets.data.Piece p) {
        this.contenu = p;
    }

    public String getRepresentationTextuelle() {
        try {
            return SaisieConsole.generateColoredFGString(contenu.getTaille().getSymbole() + "  ", contenu.getCouleur());
        } catch (Exception e) {
            return "   ";
        }
    }
}
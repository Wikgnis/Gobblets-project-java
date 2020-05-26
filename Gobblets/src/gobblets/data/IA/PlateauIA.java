package gobblets.data.IA;

import gobblets.data.JoueurIA;
import gobblets.data.Piece;
import gobblets.data.Plateau;

public class PlateauIA {
    /**
     * null : no piece;
     * true : ia;
     * false : opponent;
     */
    public Boolean[][][] plateau;

    public PlateauIA() {}

    public PlateauIA(Plateau p, JoueurIA ia) {
        plateau = new Boolean[p.getPlateau().length][p.getPlateau()[0].length][3];
        // init plateau
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                for (int j2 = 0; j2 < plateau[i][j].length; j2++) {
                    plateau[i][j][j2] = null;
                }
            }
        }
        // fill plateau with pieces according to player
        for (int i = 0; i < p.getPlateau().length; i++) {
            for (int j = 0; j < p.getPlateau()[i].length; j++) {
                for (Piece piece : p.getPlateau()[i][j].getPieces()) {
                    switch (piece.getTaille()) {
                        case PETITE:
                            if (piece.getCouleur() == ia.getCouleur()) {
                                plateau[i][j][0] = true;
                            } else {
                                plateau[i][j][0] = false;
                            }
                            break;
                        case MOYENNE:
                            if (piece.getCouleur() == ia.getCouleur()) {
                                plateau[i][j][1] = true;
                            } else {
                                plateau[i][j][1] = false;
                            }
                            break;
                        case GRANDE:
                            if (piece.getCouleur() == ia.getCouleur()) {
                                plateau[i][j][2] = true;
                            } else {
                                plateau[i][j][2] = false;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    @Override
    protected Object clone(){
        PlateauIA clone = new PlateauIA();
        clone.plateau = plateau.clone();
        return clone;
    }
}
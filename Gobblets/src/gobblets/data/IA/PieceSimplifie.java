package gobblets.data.IA;

import gobblets.data.Piece;
import gobblets.data.Taille;

public class PieceSimplifie {
    public final Taille taille;
    public final int ownerID;

    public PieceSimplifie(Piece piece, int ownerID) {
        this.taille = piece.getTaille();
        this.ownerID = ownerID;
    }
    
}
package gobblets.data.IA;

import gobblets.data.Piece;
import gobblets.data.Case;

public class CaseSimplifie {
    private PieceSimplifie[] pieces = new PieceSimplifie[3];
    public CaseSimplifie(Case c) {
        for (Piece p : c.getPieces()) {
            switch (p.getTaille()) {
                case PETITE:
                    
                    break;

                case MOYENNE:

                    break;

                case GRANDE:

                    break;
            
                default:
                    break;
            }
        }
    }
}
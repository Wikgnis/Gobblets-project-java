package gobblets.data;

import gobblets.IHM.Erreur;
import gobblets.logic.CaseBloqueeException;
import gobblets.logic.PiecePasdisponibleException;

public class Case {
    private Piece petite, moyenne, grande;
    public Case() {}

    public boolean acceptePiece(Taille t) {
        return t.recouvre(plusGrandePiece());
    }

    public Piece plusGrandePiece() {
        return grande != null ? grande : moyenne != null ? moyenne : petite;
    }

    public Piece enlevePiece() throws PiecePasdisponibleException {
        Piece rPiece = plusGrandePiece();
        /** raise exception si il n'y a pas de piece a enlever */
        if (rPiece == null) throw new PiecePasdisponibleException(Erreur.PASDEPIECEICI);
        /** sinon enlever la piece presente au plus grand index */
        else if (grande != null) grande = null;
        else if (moyenne != null) moyenne = null;
        else petite = null;
        /** enfin return la piece enlevée */
        return rPiece;
    }

    public void placePiece(Piece p) throws CaseBloqueeException {
        /** verification si la piece peut être placée */
        if (acceptePiece(p.getTaille())) {
            /** placement de la piece dans la variable lui correspondant */
            switch (p.getTaille()) {
                case GRANDE:
                    grande = p;
                    break;
                case MOYENNE:
                    moyenne = p;
                    break;
                case PETITE:
                    petite = p;
                    break;
                default:
                    break;
            }
        }
        else throw new CaseBloqueeException(Erreur.CASEBLOQUE);
    }

    public Object clone() {
        Case c = null;
        try {
            c = new Case();
            if (petite != null) c.placePiece((Piece) petite.clone());
            if (moyenne != null) c.placePiece((Piece) moyenne.clone());
            if (grande != null) c.placePiece((Piece) grande.clone());
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return "Case(pieces=[" + grande + "," + moyenne + "," + petite + "])";
    }
}
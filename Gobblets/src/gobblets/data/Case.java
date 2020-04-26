package gobblets.data;

public class Case {
    private Piece petite, moyenne, grande;
    public Case() {}

    public boolean acceptePiece(Taille t) {
        return t.reouvre(plusGrandePiece());
    }

    public Piece plusGrandePiece() {
        return grande != null ? grande : moyenne != null ? moyenne : petite;
    }

    public Piece enlevePiece() {
        Piece rPiece = plusGrandePiece();
        if (grande != null) grande = null;
        else if (moyenne != null) moyenne = null;
        else petite = null;
        return rPiece;
    }

    public void placePiece(Piece p) {
        if (acceptePiece(p.getTaille())) {
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
    }

    public String toString() {
        return "Case(pieces=["+grande+","+moyenne+","+petite+"])";
    }

    public Object clone() {
        Case c = null;
        try {
            c = new Case();
            if (petite != null) c.placePiece((Piece) petite.clone());
            if (moyenne != null) c.placePiece((Piece) moyenne.clone());
            if (grande != null) c.placePiece((Piece) grande.clone());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
}
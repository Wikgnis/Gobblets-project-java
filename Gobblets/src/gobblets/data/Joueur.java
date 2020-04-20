package gobblets.data;

import java.util.ArrayList;

public abstract class Joueur {
    private final String nom;
    private final Couleur couleur;
    private ArrayList<Piece> pieces;
    public Joueur(String nom, Couleur couleur) {
        this.nom = nom; this.couleur=couleur;
    }

    public String getNom() {
        return nom;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public void ajoutPiece(Piece p) {
        if (p != null) {
            pieces.add(p);
        }
    }

    public boolean aPiece(Piece p) {
        return pieces.contains(p);
    }

    public Piece enlevePiece(Taille t) {
        if (aPieceDeTaille(t)) {
            for (Object o : pieces.toArray()) {
                if (((Piece)o).getTaille() == t) return pieces.remove(pieces.indexOf(o));
            }
        }
        return null;
    }

    public boolean aPieceDeTaille(Taille t) {
        if (t != null && pieces != null && pieces.size() != 0) {
            for (Object o : pieces.toArray()) {
                if (((Piece) o).getTaille() == t)
                    return true;
            }
        }
        return false;
    }

    public String toString() {
        return "Joueur(nom="+nom+",couleur="+couleur+",pieces+"+pieces+")";
    }
}
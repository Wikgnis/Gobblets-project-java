package gobblets.data;

public class Piece {
    private Couleur couleur;
    private final Taille taille;
    public Piece(Taille t) {
        taille = t;
    }

    public Taille getTaille() {
        return taille;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur c) {
        couleur = c;
    }

    public String toString() {
        return "Piece(taille="+taille+", couleur="+couleur+")";
    }
}
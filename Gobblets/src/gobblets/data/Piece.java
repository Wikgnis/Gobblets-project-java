package gobblets.data;

import java.io.Serializable;

public class Piece implements Serializable{
    /**
     * utilisé pour la sauvegarde d'une piece
     */
    private static final long serialVersionUID = 1L;
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

    @Override
    public String toString() {
        return "Piece(taille="+taille+", couleur="+couleur+")";
    }

    @Override
    public Object clone() {
        Piece p = new Piece(taille);
        p.setCouleur(couleur);
        return p;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Piece other = (Piece) obj;
        if (couleur != other.getCouleur())
            return false;
        if (taille != other.getTaille())
            return false;
        return true;
    }
}
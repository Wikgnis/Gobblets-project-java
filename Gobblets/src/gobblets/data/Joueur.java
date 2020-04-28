package gobblets.data;

import java.util.ArrayList;

import gobblets.IHM.Erreur;
import gobblets.interaction.Action;
import gobblets.logic.PiecePasdisponibleException;

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

    public void ajoutPiece(Piece p) throws PiecePasdisponibleException {
        if (p != null) {
            pieces.add(p);
        }
        else throw new PiecePasdisponibleException(Erreur.ARGUMENTINCORECT);
    }

    public boolean aPiece(Piece p) {
        return pieces.contains(p);
    }

    public Piece enlevePiece(Taille t) throws Exception {
        try {
            if (aPieceDeTaille(t)) {
                for (Object o : pieces.toArray()) {
                    if (((Piece) o).getTaille() == t)
                        return pieces.remove(pieces.indexOf(o));
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    public boolean aPieceDeTaille(Taille t) throws PiecePasdisponibleException {
        if (t == null || pieces == null) throw new PiecePasdisponibleException(Erreur.ARGUMENTINCORECT);
        if (pieces.size() != 0) {
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

    public abstract Action choisirAction(Plateau p) throws Exception;
    public abstract Object clone();
}
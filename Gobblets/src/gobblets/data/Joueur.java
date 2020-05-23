package gobblets.data;

import java.io.Serializable;
import java.util.ArrayList;

import gobblets.IHM.Erreur;
import gobblets.interaction.Action;
import gobblets.logic.PiecePasdisponibleException;

public abstract class Joueur implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
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

    public void ajoutPiece(Piece p) throws Exception {
        if (p == null) throw new Exception("error : null value");
        if (p.getCouleur() != getCouleur()) throw new PiecePasdisponibleException(Erreur.PASTAPIECE);
        pieces.add(p);
    }

    public boolean aPiece(Piece p) {
        return pieces.contains(p);
    }

    public Piece enlevePiece(Taille t) throws Exception {
        if (pieces == null) throw new Exception("error : pieces Joueur null");
        if (aPieceDeTaille(t)) {
            for (Object o : pieces.toArray()) { // parcour des pieces du joueur
                if (((Piece) o).getTaille() == t) // si même taille
                    return pieces.remove(pieces.indexOf(o)); // enleve et retourne piece
            }
        }
        return null;
    }

    public boolean aPieceDeTaille(Taille t) throws Exception {
        if (pieces == null) throw new Exception("error : pieces Joueur null");
        if (t == null) throw new Exception("error : taille is null");
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
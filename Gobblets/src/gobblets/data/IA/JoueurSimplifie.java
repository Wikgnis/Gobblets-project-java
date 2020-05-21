package gobblets.data.IA;

import java.util.ArrayList;

import gobblets.data.Piece;
import gobblets.data.Couleur;
import gobblets.data.Joueur;

public class JoueurSimplifie {
    private ArrayList<Piece> pieces;
    private Couleur couleur;
    public final int id;
    public JoueurSimplifie(Joueur j, int id) {
        pieces = j.getPieces();
        couleur = j.getCouleur();
        this.id = id;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public Couleur getCouleur() {
        return couleur;
    }
}
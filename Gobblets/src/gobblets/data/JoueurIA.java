package gobblets.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import gobblets.interaction.Action;
import gobblets.interaction.Deplacement;
import gobblets.interaction.Placement;

public class JoueurIA extends Joueur {
    private Joueur adversaire;
    private static final int depthMax = 3;

    public JoueurIA(String nom, Couleur couleur) {
        super(nom, couleur);
    }

    public Joueur getAdversaire() {
        return adversaire;
    }

    public void setAdversaire(Joueur adversaire) {
        this.adversaire = adversaire;
    }

    @Override
    public Action choisirAction(Plateau p) {
        // min max
        return minMAxAlgo(p);
    }

    private Case[][] clonePlateau(Case[][] cases) {
        Case[][] casesClone = new Case[cases.length][cases[0].length];
        for (int i = 0; i < casesClone.length; i++) {
            for (int j = 0; j < casesClone[i].length; j++) {
                casesClone[i][j] = (Case) cases[i][j].clone();
            }
        }
        return casesClone;
    }

    public Object clone() {
        JoueurIA cloneObject = new JoueurIA(getNom(), getCouleur());
        cloneObject.setPieces(getPieces());
        return cloneObject;
    }

    /** Algorythmes de choix */
    /** Min Max */
    private Action minMAxAlgo(Plateau p) {
        /** Main elements used in algo */
        Action a = null; // used to decide the action
        Integer bestScore = null; // an Integer which will decide if the Action is worth or not
        /** Parcour du Plateau */
        for (int i = 0; i < p.getPlateau().length; i++) { // ligne
            for (int j = 0; j < p.getPlateau()[i].length; j++) { // colonne
                /** test Placement */
                for (Piece piece : getPiecesPossibles(getPieces())) {
                    if (p.getPlateau()[i][j].acceptePiece(piece.getTaille())) {
                        Case[][] pClone = clonePlateau(p.getPlateau());
                        int score = minMax(pClone, 0);
                        if (bestScore == null || bestScore < score) {
                            bestScore = score;
                            a = new Placement(piece.getTaille(), p.getPlateau()[i][j]);
                        }
                    }
                }
                /** test deplacement */
                if (
                    p.getPlateau()[i][j].plusGrandePiece() != null // test if we have a piece on the case
                    && p.getPlateau()[i][j].plusGrandePiece().getCouleur() == getCouleur() // test if the Player have this Piece
                    ) {
                        for (int i2 = 0; i2 < p.getPlateau().length; i2++) {
                            for (int j2 = 0; j2 < p.getPlateau()[0].length; j2++) {
                                if (i2 != i && j2 != j) {
                                    Case[][] pClone = clonePlateau(p.getPlateau());
                                    int score = minMax(pClone, 0);
                                    if (bestScore == null || bestScore < score) {
                                        bestScore = score;
                                        a = new Deplacement(p.getPlateau()[i][j], p.getPlateau()[i2][j2]);
                                    }
                                }
                            }
                        }
                    }
            }
        }
        /** return l'action */
        return a;
    }

    private int minMax(Case[][] p, int depth) {
        /** init variables */
        int bestScore = 0;
        /** detection fin de jeu */
        if (depth > depthMax) {
            return 0;
        }
        /** parcours du plateau */
        for (int i = 0; i < p.length; i++) { // ligne
            for (int j = 0; j < p[i].length; j++) { // colonne
                /** test Placement */
                for (Piece piece : getPiecesPossibles(getPieces())) {
                    if (p[i][j].acceptePiece(piece.getTaille())) {
                        Case[][] pClone = clonePlateau(p);
                        int score = minMax(pClone, depth+1);
                        if (bestScore < score) {
                            bestScore = score;
                        }
                    }
                }
                /** test deplacement */
                if (
                    p[i][j].plusGrandePiece() != null // test if we have a piece on the case
                    && p[i][j].plusGrandePiece().getCouleur() == getCouleur() // test if the Player have this Piece
                    ) {
                        for (int i2 = 0; i2 < p.length; i2++) {
                            for (int j2 = 0; j2 < p[0].length; j2++) {
                                if (i2 != i && j2 != j) {
                                    Case[][] pClone = clonePlateau(p);
                                    int score = minMax(pClone, depth+1);
                                    if (bestScore < score) {
                                        bestScore = score;
                                    }
                                }
                            }
                        }
                    }
            }
        }
        /** return score */
        return bestScore;
    }

    private ArrayList<Piece> getPiecesPossibles(ArrayList<Piece> pieces) {
        ArrayList<Piece> possiblePieces = new ArrayList<Piece>(), piecesClone = ((ArrayList<Piece>)pieces.clone());
        Object last = null;
        for (Object o : piecesClone) {
            if (!o.equals(last)) possiblePieces.add((Piece)o);
            last = o;
        }
        return possiblePieces;
    }

    private Case[][] clonePlateau(Plateau p) {
        try {
            Case[][] pClone = new Case[p.getPlateau().length][p.getPlateau()[0].length];
            for (int i = 0; i < pClone.length; i++) {
                for (int j = 0; j < pClone[i].length; j++) {
                    pClone[i][j] = (Case) p.getPlateau()[i][j].clone();
                }
            }
            return pClone;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
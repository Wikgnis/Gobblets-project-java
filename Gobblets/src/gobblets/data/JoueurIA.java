package gobblets.data;

import java.util.ArrayList;

import gobblets.IHM.IHM;
import gobblets.interaction.Action;
import gobblets.interaction.Deplacement;
import gobblets.interaction.Placement;

public class JoueurIA extends Joueur {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Joueur adversaire;
    private static final int depthMax = 5;

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
                casesClone[i][j] = (Case)cases[i][j].clone();
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
    private class JoueurSimplifie {
        private ArrayList<Piece> pieces;
        private Couleur couleur;

        public JoueurSimplifie(ArrayList<Piece> pieces, Couleur couleur) {
            this.pieces = pieces;
            this.couleur = couleur;
        }

        public ArrayList<Piece> getPieces() {
            return pieces;
        }

        public void setPieces(ArrayList<Piece> pieces) {
            this.pieces = pieces;
        }

        public Couleur getCouleur() {
            return couleur;
        }

        public void setCouleur(Couleur couleur) {
            this.couleur = couleur;
        }

        @Override
        public String toString() {
            return "JoueurSimplifie [couleur=" + couleur + ", pieces=" + pieces + "]";
        }
    }

    /** Min Max */
    private Action minMAxAlgo(Plateau p) {
        /** Main elements used in algo */
        Action a = null; // used to decide the action
        Integer bestScore = null; // an Integer which will decide if the Action is worth or not
        /** Parcour du Plateau */
        try {
            for (int i = 0; i < p.getPlateau().length; i++) { // ligne
                for (int j = 0; j < p.getPlateau()[i].length; j++) { // colonne
                    /** test Placement */
                    for (Piece piece : getPiecesPossibles(getPieces())) {
                        if (p.getPlateau()[i][j].acceptePiece(piece.getTaille())) {
                            Case[][] pClone = clonePlateau(p.getPlateau());
                            int score = minMax(pClone,
                                    new JoueurSimplifie((ArrayList<Piece>) getPieces().clone(), getCouleur()),
                                    new JoueurSimplifie((ArrayList<Piece>) getAdversaire().getPieces().clone(),
                                    getAdversaire().getCouleur()),
                                    0);
                            if (bestScore == null || bestScore < score) {
                                bestScore = score;
                                Action choice = new Placement(piece.getTaille(), p.getPlateau()[i][j]);
                                try {
                                    if (choice.verifier(this))
                                        a = choice;
                                } catch (Exception e) {
                                    IHM.getIHM().display(e);
                                }
                            }
                        }
                    }
                    /** test deplacement */
                    if (p.getPlateau()[i][j].plusGrandePiece() != null // test if we have a piece on the case
                            && p.getPlateau()[i][j].plusGrandePiece().getCouleur() == getCouleur() // test if the Player have this Piece
                    ) {
                        for (int i2 = 0; i2 < p.getPlateau().length; i2++) {
                            for (int j2 = 0; j2 < p.getPlateau()[0].length; j2++) {
                                if (i2 != i && j2 != j && p.getPlateau()[i2][j2].acceptePiece(p.getPlateau()[i][j].plusGrandePiece().getTaille())) {
                                    Case[][] pClone = clonePlateau(p.getPlateau());
                                    int score = minMax(pClone,
                                            new JoueurSimplifie((ArrayList<Piece>) getPieces().clone(), getCouleur()),
                                            new JoueurSimplifie((ArrayList<Piece>) getAdversaire().getPieces().clone(),
                                            getAdversaire().getCouleur()),
                                            0);
                                    if (bestScore == null || bestScore < score) {
                                        bestScore = score;
                                        Action choice = new Deplacement(p.getPlateau()[i][j], p.getPlateau()[i2][j2]);
                                        try {
                                            if (choice.verifier(this))
                                                a = choice;
                                        } catch (Exception e) {
                                            IHM.getIHM().display(e);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            IHM.getIHM().display(e);
        }
        /** return l'action */
        return a;
    }

    private int minMax(Case[][] p, JoueurSimplifie joueur, JoueurSimplifie adversaire, int depth) throws Exception{
        /** init variables */
        int bestScore = 0;
        /** detection fin de jeu et implementation score */
        Couleur winner;
        winner = getVictoire(p);
        if (winner == joueur.getCouleur()) {
            return 100 / (depth == 0 ? 1 : depth);
        } else if (winner == adversaire.getCouleur()) {
            return -100 / (depth == 0 ? 1 : depth);
        }
        winner = getNearVictorySituation();
        if (winner == joueur.getCouleur()) {
            return 50 / (depth == 0 ? 1 : depth);
        } else if (winner == adversaire.getCouleur()) {
            return -50 / (depth == 0 ? 1 : depth);
        }
        if (depth > depthMax) {
            return 0;
        }
        /** parcours du plateau */
        for (int i = 0; i < p.length; i++) { // ligne
            for (int j = 0; j < p[i].length; j++) { // colonne
                /** test Placement */
                for (Piece piece : getPiecesPossibles(joueur.getPieces())) {
                    if (p[i][j].acceptePiece(piece.getTaille())) {
                        Case[][] pClone = clonePlateau(p);
                        pClone[i][j].placePiece(joueur.getPieces().remove(joueur.getPieces().indexOf(piece)));
                        int score = 0;
                        score -= minMax(
                                pClone,
                                new JoueurSimplifie((ArrayList<Piece>) adversaire.getPieces().clone(),
                                adversaire.getCouleur()),
                                new JoueurSimplifie((ArrayList<Piece>) joueur.getPieces().clone(), joueur.getCouleur()),
                                depth + 1
                                );
                        if (bestScore < score) {
                            bestScore = score;
                        }
                    }
                }
                /** test deplacement */
                if (p[i][j].plusGrandePiece() != null // test if we have a piece on the case
                        && p[i][j].plusGrandePiece().getCouleur() == getCouleur() // test if the Player have this Piece
                ) {
                    for (int i2 = 0; i2 < p.length; i2++) {
                        for (int j2 = 0; j2 < p[0].length; j2++) {
                            if (i2 != i && j2 != j && p[i2][j2].acceptePiece(p[i][j].plusGrandePiece().getTaille())) {
                                Case[][] pClone = clonePlateau(p);
                                pClone[i2][j2].placePiece(pClone[i][j].enlevePiece());
                                int score = 0;
                                score -= minMax(pClone,
                                        new JoueurSimplifie((ArrayList<Piece>) adversaire.getPieces().clone(),
                                                adversaire.getCouleur()),
                                        new JoueurSimplifie((ArrayList<Piece>) joueur.getPieces().clone(),
                                                joueur.getCouleur()),
                                        depth + 1);
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

    private Couleur getNearVictorySituation() {
        return null;
    }

    private Couleur getVictoire(Case[][] p) {
        /** ligne colonne */
        for (int i = 0; i < p.length; i++) {
            if (p[i][0].plusGrandePiece() != null && p[i][1].plusGrandePiece() != null
                    && p[i][2].plusGrandePiece() != null
                    && p[i][0].plusGrandePiece().getCouleur() == p[i][1].plusGrandePiece().getCouleur()
                    && p[i][1].plusGrandePiece().getCouleur() == p[i][2].plusGrandePiece().getCouleur())
                return p[i][0].plusGrandePiece().getCouleur();
            else if (p[0][i].plusGrandePiece() != null && p[1][i].plusGrandePiece() != null
                    && p[2][i].plusGrandePiece() != null
                    && p[0][i].plusGrandePiece().getCouleur() == p[1][i].plusGrandePiece().getCouleur()
                    && p[1][i].plusGrandePiece().getCouleur() == p[2][i].plusGrandePiece().getCouleur())
                return p[0][i].plusGrandePiece().getCouleur();
        }
        /** diagonale */
        return null;
    }

    private ArrayList<Piece> getPiecesPossibles(ArrayList<Piece> pieces) {
        ArrayList<Piece> possiblePieces = new ArrayList<Piece>(), piecesClone = ((ArrayList<Piece>) pieces.clone());
        Object last = null;
        for (Object o : piecesClone) {
            if (!o.equals(last))
                possiblePieces.add((Piece) o);
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
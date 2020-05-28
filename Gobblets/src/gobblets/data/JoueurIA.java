package gobblets.data;

import java.util.ArrayList;

import gobblets.IHM.IHM;
import gobblets.interaction.Action;
import gobblets.interaction.Deplacement;
import gobblets.interaction.Placement;

public class JoueurIA extends Joueur {
    /**
     * utilisé pour la copie d'un joueur IA
     */
    private static final long serialVersionUID = 1L;

    /**
     * adverdaire de l'IA (utilisé dans l'algo min-max)
     */
    private Joueur adversaire;
    /**
     * max depth de l'algorythme min max
     */
    private static final int depthMax = 5;

    public JoueurIA(String nom, Couleur couleur) {
        super(nom, couleur);
    }

    /**
     * get l'adversaire de cette IA
     * 
     * @return l'advesaire de l'IA
     */
    public Joueur getAdversaire() {
        return adversaire;
    }

    /**
     * set l'adversaire de l'IA
     * 
     * @param adversaire l'adversaire de l'IA
     */
    public void setAdversaire(Joueur adversaire) {
        this.adversaire = adversaire;
    }

    @Override
    public Action choisirAction(Plateau p) {
        // min max
        return minMAxAlgo(p);
    }

    /**
     * clone les cases d'un plateau
     * @param cases les cases du plateau
     * @return la copie des cases
     */
    private Case[][] clonePlateau(Case[][] cases) {
        Case[][] casesClone = new Case[cases.length][cases[0].length];
        for (int i = 0; i < casesClone.length; i++) {
            for (int j = 0; j < casesClone[i].length; j++) {
                casesClone[i][j] = (Case)cases[i][j].clone();
            }
        }
        return casesClone;
    }

    @Override
    public Object clone() {
        JoueurIA cloneObject = new JoueurIA(getNom(), getCouleur());
        cloneObject.setPieces(getPieces());
        return cloneObject;
    }

    /**
     * classe qui sert a manipuler plus simplement les info d'un Joueur
     */
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

        public Couleur getCouleur() {
            return couleur;
        }

        @Override
        public String toString() {
            return "JoueurSimplifie [couleur=" + couleur + ", pieces=" + pieces + "]";
        }
    }

    /**
     * Algorythme de debut de l'algorythme min max
     * 
     * @param p le plateau de Jeu
     * @return l'Action choisie
     */
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

    /**
     * algorythme min max pour le gobblets gobblers
     * 
     * @param p les cases du plateau de jeu
     * @param joueur joueur actuel
     * @param adversaire adversaire du joueur actuel
     * @param depth profondeur de l'algorythme actuellement
     * @return la valeur du coup qui a été joué au tour précedent (en fonction des possibles actions des joueurs sur les tours qui suivent)
     * @throws Exception si jamais l'agorythme se trouve confronter a une erreur
     */
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

    /**
     * /!\ ne fonctionne pas mais sert a detecter des situations dans lesquels l'IA a plus de chances de gagner
     * @return la couleur du joueur en position avantageuse
     */
    private Couleur getNearVictorySituation() {
        return null;
    }

    /**
     * retourne, si il y a un gagnant, la couleur de ce dernier
     * @param p les cases du plateau de jeu
     * @return la couleur du gagnant sinon null
     */
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
        // TODO test
        try {
            Couleur winner = p[0][0].plusGrandePiece().getCouleur();
            for (int i = 1; i < p.length && winner != null; i++) {
                if (p[i][i].plusGrandePiece().getCouleur() != winner) winner = null;
            }
            if (winner != null) return winner;
        } catch (Exception e) {}
        try {
            Couleur winner = p[2][2].plusGrandePiece().getCouleur();
            for (int i = 1; i < p.length && winner != null; i++) {
                if (p[i][2-i].plusGrandePiece().getCouleur() != winner) winner = null;
            }
            if (winner != null) return winner;
        } catch (Exception e) {}
        return null;
    }

    /**
     * permet de savoir quels sont les pieces que l'on peut possiblement mettre sur cette case
     * @param pieces pieces du joueur
     * @return un ArrayList des pieces qui peuvent être posées
     */
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
}
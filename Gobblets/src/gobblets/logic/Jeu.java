package gobblets.logic;

import java.util.Random;

import gobblets.IHM.Erreur;
import gobblets.IHM.IHM;
import gobblets.data.*;
import gobblets.interaction.*;

public class Jeu {
    private Plateau plateau;
    private Joueur j1 = null, j2 = null, joueurActif = null;
    private Etat etat;

    public Jeu() {
        plateau = Plateau.initPlateau();
        /* choix IHM */
        etat = Etat.JEUENCOURS;
        /* init j1 */
        // saisie j1
        while (j1 == null) {
            try {
                j1 = IHM.getIHM().saisirJoueur(1);
            } catch (Exception e) {
                IHM.getIHM().display(e);
            }
        }
        // set Pieces J1
        j1.setPieces(plateau.getMaisonJ1());
        // set color Pieces
        for (Object o : j1.getPieces().toArray()) {
            ((Piece) o).setCouleur(j1.getCouleur());
        }
        /* init j2 */
        // saisie j2
        while (j2 == null) {
            try {
                j2 = IHM.getIHM().saisirJoueur(2);
                if (j2.getNom().equals(j1.getNom()) && j2.getCouleur() == j1.getCouleur()){
                    j2 = null;
                    throw new Exception(IHM.getIHM().getLanguage().erreur(Erreur.ARGUMENTINCORECT) + " j1 == j2");
                }
            } catch (Exception e) {
                IHM.getIHM().display(e);
            }
        }
        // set maison J2
        j2.setPieces(plateau.getMaisonJ2());
        // set color pieces
        for (Object o : j2.getPieces().toArray()) {
            ((Piece) o).setCouleur(j2.getCouleur());
        }
        /** IA setup */
        if (j1 instanceof JoueurIA) {
            ((JoueurIA)j1).setAdversaire(j2);
        }
        if (j2 instanceof JoueurIA) {
            ((JoueurIA)j2).setAdversaire(j1);
        }
        /* set starting player */
        Random r = new Random();
        joueurActif = r.nextBoolean() ? j1 : j2;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public Joueur getJ1() {
        return j1;
    }

    public Joueur getJ2() {
        return j2;
    }

    public Joueur getJoueurActif() {
        return joueurActif;
    }

    public void changeJoueur() {
        if (joueurActif == j1) joueurActif = j2;
        else joueurActif = j1;
    }

    /**
     * @param current state of the game
     * @return Etat which will be the current etat of the game at the end of the round
     */
    private Etat updateEtat(Etat current) {
        // TODO
        try {
            for (int i = 0; i < 3; i++) { // parcour ligne et colonnes
                if (plateau.verifierLigne(i) != null) {
                    changeEtat(current, plateau.verifierLigne(i));
                }
                if (plateau.verifierColonne(i) != null) {
                    changeEtat(current, plateau.verifierColonne(i));
                }
            }
            // premiere diagonale
            if (plateau.verifierDiagonale('a') != null) {
                changeEtat(current, plateau.verifierDiagonale('a'));
            }
            // seconde diagonale
            if (plateau.verifierDiagonale('b') != null) {
                changeEtat(current, plateau.verifierDiagonale('b'));
            }
        } catch (Exception e) {
            IHM.getIHM().display(e);
        }
        return current;
    }

    /**
     * @param current current state of the game which will be updated by the function
     * @param winner winner of the game
     */
    private void changeEtat(Etat current, Couleur winner) {
        // TODO
        if (current != Etat.MATCHNUL && winner != null) { // si le jeu est deja match nul ou pas de gagnant rien a faire
            if (current == Etat.JEUENCOURS) { // pas encore de gagnant
                if (winner == j1.getCouleur()) { // j1 = gagnant
                    current = Etat.JOUEUR1GAGNE;
                }
                else if (winner == j2.getCouleur()) { // j2 = gagnant
                    current = Etat.JOUEUR2GAGNE;
                }
            }
            // deja un gagnant
            else if
                (
                    (current == Etat.JOUEUR1GAGNE && winner == j2.getCouleur())
                    || (current == Etat.JOUEUR2GAGNE
                    && winner == j1.getCouleur())
                ) { current = Etat.MATCHNUL; }
        }
    }

    public Etat play() {
        Action a = null;
        Etat etatPlay = etat;
        /* action par le joueur */
        try {
            a = joueurActif.choisirAction(plateau);
            if (a != null) {
                /* detection termination */
                if (a instanceof Termination)
                    return Etat.JEUQUITTE;
                /* autres */
                if (a.verifier(joueurActif)) {
                    a.appliquer(joueurActif);
                    changeJoueur();
                }
                etatPlay = updateEtat(etatPlay);
            }
        } catch (Exception e) {
            IHM.getIHM().display(e);
        }
        return etatPlay;
    }

    @Override
    public String toString() {
        return "Jeu(j1=" + j1 + ", j2=" + j2 + ", joueurActif=" + joueurActif + ", plateau=" + plateau + ")";
    }
}
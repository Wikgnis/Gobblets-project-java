package gobblets.logic;

import java.io.Serializable;
import java.util.Random;

import gobblets.IHM.Erreur;
import gobblets.IHM.IHM;
import gobblets.data.*;
import gobblets.interaction.*;

public class Jeu implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

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
        try {
            for (int i = 0; i < 3; i++) { // parcour ligne et colonnes
                if ( plateau.verifierLigne(i) != null) {
                    if (current == Etat.JEUENCOURS) {
                        Couleur winner = plateau.getPlateau()[i][0].plusGrandePiece().getCouleur();
                        if (winner == j1.getCouleur()) current = Etat.JOUEUR1GAGNE;
                        else current = Etat.JOUEUR2GAGNE;
                    }
                    else {
                        current = Etat.MATCHNUL;
                    }
                }
                if (plateau.verifierColonne(i) != null) {
                    if (current == Etat.JEUENCOURS) {
                        Couleur winner = plateau.getPlateau()[0][i].plusGrandePiece().getCouleur();
                        if (winner == j1.getCouleur()) current = Etat.JOUEUR1GAGNE;
                        else current = Etat.JOUEUR2GAGNE;
                    }
                    else {
                        current = Etat.MATCHNUL;
                    }
                }
            }
            // premiere diagonale
            if (plateau.verifierDiagonale('a') != null) {
                if (current == Etat.JEUENCOURS) {
                    Couleur winner = plateau.getPlateau()[1][1].plusGrandePiece().getCouleur();
                    if (winner == j1.getCouleur()) current = Etat.JOUEUR1GAGNE;
                    else current = Etat.JOUEUR2GAGNE;
                }
                else {
                    current = Etat.MATCHNUL;
                }
            }
            // seconde diagonale
            if (plateau.verifierDiagonale('b') != null) {
                if (current == Etat.JEUENCOURS) {
                    Couleur winner = plateau.getPlateau()[1][1].plusGrandePiece().getCouleur();
                    if (winner == j1.getCouleur()) current = Etat.JOUEUR1GAGNE;
                    else current = Etat.JOUEUR2GAGNE;
                }
                else {
                    current = Etat.MATCHNUL;
                }
            }
            return current;
        } catch (Exception e) {
            IHM.getIHM().display(e);
            return null;
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
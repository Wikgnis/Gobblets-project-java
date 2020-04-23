package gobblets.logic;

import java.util.Random;

import gobblets.IHM.IHM;
import gobblets.IHM.texte.SaisieConsole;
import gobblets.data.*;
import gobblets.interaction.*;

public class Jeu {
    private Plateau plateau;
    private Joueur j1 = null, j2 = null, joueurActif = null;
    private Etat etat;

    public Jeu() {
        plateau = Plateau.initPlateau();
        /* temp */
        IHM saisie = new SaisieConsole();
        etat = Etat.JEUENCOURS;
        /* init j1 */
        do {
            try {
                j1 = saisie.saisirJoueur(1);
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (j1 == null || j1.getNom() == "" || j1.getCouleur() == null);
        j1.setPieces(plateau.getMaisonJ1());
        for (Object o : j1.getPieces().toArray()) {
            ((Piece) o).setCouleur(j1.getCouleur());
        }
        /* init j2 */
        do {
            try {
                j2 = saisie.saisirJoueur(2);
            } catch (Exception e) {
                System.out.println(e);
                j2 = null;
            }
        } while ((j2 == null || j2.getNom() == "" || j2.getCouleur() == null) || j2.getNom().equals(j1.getNom()) && j2.getCouleur() == j1.getCouleur());
        j2.setPieces(plateau.getMaisonJ2());
        for (Object o : j2.getPieces().toArray()) {
            ((Piece) o).setCouleur(j2.getCouleur());
        }
        /* set starting player */
        Random r = new Random();
        joueurActif = r.nextBoolean() ? j1 : j2;
        /* temp */
    }

    public void changeJoueur() {
        if (joueurActif == j1) joueurActif = j2;
        else joueurActif = j1;
    }

    private Couleur aVictoire() {
        try {
            for (int i = 0; i < 3; i++) {
                if (plateau.verifierLigne(i) != null) return plateau.verifierLigne(i);
                if (plateau.verifierColonne(i) != null) return plateau.verifierColonne(i);
            }
            if (plateau.verifierDiagonale('a') != null) return plateau.verifierDiagonale('a');
            if (plateau.verifierDiagonale('b') != null) return plateau.verifierDiagonale('b');
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
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
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        /* detection victoire */
        Couleur gagnant = aVictoire();
        if ( gagnant != null) etatPlay = gagnant == j1.getCouleur() ? Etat.JOUEUR1GAGNE : Etat.JOUEUR2GAGNE;
        /* changement de joueur et return de l'etat apres avoir joué */
        return etatPlay;
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

    @Override
    public String toString() {
        return "Jeu(j1=" + j1 + ", j2=" + j2 + ", joueurActif=" + joueurActif + ", plateau=" + plateau + ")";
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }
}
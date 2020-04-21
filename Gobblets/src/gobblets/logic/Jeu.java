package gobblets.logic;

import java.util.Random;

import gobblets.IHM.IHM;
import gobblets.IHM.texte.SaisieConsole;
import gobblets.data.*;

public class Jeu {
    private Plateau plateau;
    private Joueur j1, j2, joueurActif;

    public Jeu() {
        plateau = Plateau.initPlateau();
        /* temp */
        IHM saisie = new SaisieConsole();
        do {
            j1 = saisie.saisirJoueur(1);
        } while (j1 == null);
        do {
            j2 = saisie.saisirJoueur(2);
        } while (j2 == null);
        Random r = new Random();
        joueurActif = r.nextBoolean() ? j1 : j2;
        /* temp */
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
}
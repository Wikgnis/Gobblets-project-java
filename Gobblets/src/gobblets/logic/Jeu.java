package gobblets.logic;

import gobblets.data.*;

public class Jeu {
    private Plateau plateau;
    private Joueur j1, j2, joueurActif;

    public Jeu() {
        plateau = Plateau.initPlateau();
        /* temp */
        j1 = new JoueurHumain("J1", Couleur.BLEU);
        j2 = new JoueurHumain("J2", Couleur.ROUGE);
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
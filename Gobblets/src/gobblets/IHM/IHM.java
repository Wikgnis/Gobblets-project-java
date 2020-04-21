package gobblets.IHM;

import gobblets.data.*;

public abstract class IHM {
    public abstract Joueur saisirJoueur(int n);
    public abstract Taille saisirTaille();
    public abstract int[] saisirCoordonnees();
    public abstract void display(Plateau p, Joueur j);
    public abstract ActionType saisirAction(Joueur j);
}
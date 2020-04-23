package gobblets.IHM;

import gobblets.data.*;

public abstract class IHM {
    public abstract Joueur saisirJoueur(int n) throws Exception;
    public abstract Taille saisirTaille() throws Exception;
    public abstract int[] saisirCoordonnees() throws Exception;
    public abstract void display(Plateau p, Joueur j) throws Exception;
    public abstract ActionType saisirAction(Joueur j) throws Exception;
}
package gobblets.interaction;

import gobblets.data.Joueur;

public abstract class Action {
    public abstract boolean verifier(Joueur j);
    public abstract void appliquer(Joueur j);
}
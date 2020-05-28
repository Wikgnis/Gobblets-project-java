package gobblets.interaction;

import gobblets.data.Joueur;

public abstract class Action {
    public abstract boolean verifier(Joueur j) throws Exception;
    public abstract void appliquer(Joueur j) throws Exception;
}
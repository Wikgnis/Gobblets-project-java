package gobblets.interaction;

import gobblets.data.Joueur;
import gobblets.logic.PiecePasdisponibleException;

public abstract class Action {
    public abstract boolean verifier(Joueur j) throws Exception;
    public abstract void appliquer(Joueur j);
}
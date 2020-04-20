package gobblets.interaction;

import gobblets.data.Joueur;

public class Termination extends Action {
    public Termination() {}

    @Override
    public boolean verifier(Joueur j) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean appliquer(Joueur j) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String toString() {
        return "Termination";
    }

}
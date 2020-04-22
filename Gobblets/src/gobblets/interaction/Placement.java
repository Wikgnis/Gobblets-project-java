package gobblets.interaction;

import gobblets.data.*;

public class Placement extends Action {
    private Case destination;
    private Taille taille;
    public Placement(Taille t, Case destination) {
        taille = t; this.destination = destination;
    }

    @Override
    public boolean verifier(Joueur j) {
        if (j.aPieceDeTaille(taille) && destination.acceptePiece(taille)) {
            return true;
        }
        return false;
    }

    @Override
    public void appliquer(Joueur j) {
        destination.placePiece(j.enlevePiece(taille));
    }

    @Override
    public String toString() {
        return "Placement(destination=" + destination + ", taille=" + taille + ")";
    }

    public Case getDestination() {
        return destination;
    }

    public Taille getTaille() {
        return taille;
    }
}
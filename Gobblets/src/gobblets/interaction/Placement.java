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
        return "Placement(destination=" + destination + ", taille=" + taille + ")";
    }

    public Case getDestination() {
        return destination;
    }

    public Taille getTaille() {
        return taille;
    }
}
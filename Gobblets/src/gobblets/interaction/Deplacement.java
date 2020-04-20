package gobblets.interaction;

import gobblets.data.*;

public class Deplacement extends Action {
    private Case origin, destination;
    public Deplacement(Case origin, Case destination) {
        this.origin = origin; this.destination = destination;
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
        return "Deplacement(destination=" + destination + ", origin=" + origin + ")";
    }

    public Case getOrigin() {
        return origin;
    }

    public Case getDestination() {
        return destination;
    }
}
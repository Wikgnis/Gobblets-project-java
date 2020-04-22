package gobblets.interaction;

import gobblets.data.*;

public class Deplacement extends Action {
    private Case origin, destination;
    public Deplacement(Case origin, Case destination) {
        this.origin = origin; this.destination = destination;
    }

    private boolean estCouleurJoueur(Joueur j) {
        return origin.plusGrandePiece().getCouleur() == j.getCouleur();
    }

    @Override
    public boolean verifier(Joueur j) {
        if (origin.plusGrandePiece() != null && destination.acceptePiece(origin.plusGrandePiece().getTaille())) {
            return true;
        }
        return false;
    }

    @Override
    public void appliquer(Joueur j) {
        destination.placePiece(origin.enlevePiece());
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
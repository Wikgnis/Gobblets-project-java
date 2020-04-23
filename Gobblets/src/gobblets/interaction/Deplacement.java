package gobblets.interaction;

import gobblets.IHM.Erreur;
import gobblets.data.*;
import gobblets.logic.CaseBloqueeException;
import gobblets.logic.PiecePasdisponibleException;

public class Deplacement extends Action {
    private Case origin, destination;
    public Deplacement(Case origin, Case destination) {
        this.origin = origin; this.destination = destination;
    }

    private boolean estCouleurJoueur(Joueur j) {
        return origin.plusGrandePiece().getCouleur() == j.getCouleur();
    }

    @Override
    public boolean verifier(Joueur j) throws PiecePasdisponibleException, CaseBloqueeException {
        if (origin == null || destination == null || j == null) throw new PiecePasdisponibleException(Erreur.ARGUMENTINCORECT);
        if (origin.plusGrandePiece() == null) throw new CaseBloqueeException(Erreur.ORIGINVIDE);
        if (origin.plusGrandePiece().getCouleur() != j.getCouleur()) throw new PiecePasdisponibleException(Erreur.PASTAPIECE);
        if (!destination.acceptePiece(origin.plusGrandePiece().getTaille())) throw new CaseBloqueeException(Erreur.CASEBLOQUE);
        return true;
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
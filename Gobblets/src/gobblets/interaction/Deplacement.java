package gobblets.interaction;

import gobblets.IHM.Erreur;
import gobblets.data.*;
import gobblets.logic.CaseBloqueeException;
import gobblets.logic.PiecePasdisponibleException;

public class Deplacement extends Action {
    private Case origin, destination;
    public Deplacement(Case origin, Case destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Case getOrigin() {
        return origin;
    }

    public Case getDestination() {
        return destination;
    }

    @Override
    public boolean verifier(Joueur j) throws Exception {
        if (origin == null) throw new CaseBloqueeException(Erreur.ORIGINVIDE);
        else if (destination == null || j == null) throw new CaseBloqueeException(Erreur.ARGUMENTINCORECT);
        else if (origin.plusGrandePiece() == null) throw new PiecePasdisponibleException(Erreur.PASDEPIECEICI);
        else if (origin.plusGrandePiece().getCouleur() != j.getCouleur()) throw new PiecePasdisponibleException(Erreur.PASTAPIECE);
        else if (!destination.acceptePiece(origin.plusGrandePiece().getTaille())) throw new CaseBloqueeException(Erreur.CASEBLOQUE);
        return true;
    }

    @Override
    public void appliquer(Joueur j)throws Exception {
        destination.placePiece(origin.enlevePiece());
    }

    @Override
    public String toString() {
        return "Deplacement(destination=" + destination + ", origin=" + origin + ")";
    }
}
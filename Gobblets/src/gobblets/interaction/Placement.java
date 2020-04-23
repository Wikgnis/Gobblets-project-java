package gobblets.interaction;

import gobblets.IHM.Erreur;
import gobblets.data.*;
import gobblets.logic.CaseBloqueeException;
import gobblets.logic.PiecePasdisponibleException;

public class Placement extends Action {
    private Case destination;
    private Taille taille;

    public Placement(Taille t, Case destination) {
        taille = t;
        this.destination = destination;
    }

    @Override
    public boolean verifier(Joueur j) throws Exception {
        if (j == null || taille == null || destination == null) throw new PiecePasdisponibleException(Erreur.ARGUMENTINCORECT);
        try {
            if (j.aPieceDeTaille(taille) && destination.acceptePiece(taille)) {
                return true;
            }
        } catch (Exception e) {
            throw e;
        }
        throw new CaseBloqueeException(Erreur.CASEBLOQUE);
    }

    @Override
    public void appliquer(Joueur j) {
        try {
            destination.placePiece(j.enlevePiece(taille));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
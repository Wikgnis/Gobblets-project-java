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

    public Case getDestination() {
        return destination;
    }

    public Taille getTaille() {
        return taille;
    }

    @Override
    public boolean verifier(Joueur j) throws Exception {
        if (j == null || taille == null || destination == null) throw new PiecePasdisponibleException(Erreur.ARGUMENTINCORECT);
        if (!j.aPieceDeTaille(taille)) throw new PiecePasdisponibleException(Erreur.PASDEPIECEDISPONIBLE);
        if (!destination.acceptePiece(taille)) throw new CaseBloqueeException(Erreur.CASEBLOQUE);
        return true;
    }

    @Override
    public void appliquer(Joueur j)throws Exception {
        destination.placePiece(j.enlevePiece(taille));
    }

    @Override
    public String toString() {
        return "Placement(destination=" + destination + ", taille=" + taille + ")";
    }
}
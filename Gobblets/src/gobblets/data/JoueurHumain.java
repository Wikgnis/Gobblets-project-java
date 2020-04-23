package gobblets.data;

import gobblets.IHM.Erreur;
import gobblets.IHM.IHM;
import gobblets.IHM.texte.SaisieConsole;
import gobblets.interaction.*;
import gobblets.logic.PiecePasdisponibleException;

public class JoueurHumain extends Joueur {
    public JoueurHumain(String nom, Couleur couleur) {
        super(nom, couleur);
    }

    @Override
    public Action choisirAction(Plateau p) {
        IHM i = new SaisieConsole();
        ActionType choix = i.saisirAction(this);
        switch (choix) {
            case PLACER: return creerActionPlacer(i, p);
            case DEPLACER: return creerActionDeplacer(i, p);
            case QUITTER: return creerActionQuitter(i, p);
            default: return null;
        }
    }

    private Action creerActionPlacer(IHM i, Plateau p) {
        try {
            int[] coord = i.saisirCoordonnees();
            if (checkCoord(coord)) throw new PiecePasdisponibleException(Erreur.ARGUMENTINCORECT);
            Taille t = i.saisirTaille();
            Case destination = p.getPlateau()[coord[0]][coord[1]];
            return new Placement(t, destination);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private Action creerActionDeplacer(IHM i, Plateau p) {
        try {
            int[] coord;
            coord = i.saisirCoordonnees();
            if (checkCoord(coord)) throw new PiecePasdisponibleException(Erreur.ARGUMENTINCORECT);
            Case origin = p.getPlateau()[coord[0]][coord[1]];
            coord = i.saisirCoordonnees();
            if (checkCoord(coord)) throw new PiecePasdisponibleException(Erreur.ARGUMENTINCORECT);
            Case destination = p.getPlateau()[coord[0]][coord[1]];
            return new Deplacement(origin, destination);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private boolean checkCoord(int[] coord) {
        return coord[0] > 2 || coord[0] < 0 || coord[1] > 2 || coord[1] < 0;
    }

    private Action creerActionQuitter(IHM i, Plateau p) {
        return new Termination();
    }
}
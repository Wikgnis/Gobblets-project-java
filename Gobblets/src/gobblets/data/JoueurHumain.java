package gobblets.data;

import gobblets.IHM.Avertissement;
import gobblets.IHM.Erreur;
import gobblets.IHM.IHM;
import gobblets.interaction.*;
import gobblets.logic.PiecePasdisponibleException;

public class JoueurHumain extends Joueur {
    /**
     * utilisé pour la sauvegarde d'un joueur Humain
     */
    private static final long serialVersionUID = 1L;

    public JoueurHumain(String nom, Couleur couleur) {
        super(nom, couleur);
    }

    @Override
    public Action choisirAction(Plateau p) throws Exception {
        IHM i = IHM.getIHM();
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
            System.out.println(i.getLanguage().avertissement(Avertissement.CHOIXDESTINATION));
            int[] coord = i.saisirCoordonnees();
            if (checkCoord(coord)) throw new PiecePasdisponibleException(Erreur.ARGUMENTINCORECT);
            Taille t = i.saisirTaille();
            Case destination = p.getPlateau()[coord[0]][coord[1]];
            return new Placement(t, destination);
        } catch (Exception e) {
            IHM.getIHM().display(e);
            return null;
        }
    }

    private Action creerActionDeplacer(IHM i, Plateau p) {
        try {
            int[] coord;
            System.out.println(i.getLanguage().avertissement(Avertissement.CHOIXORIGIN));
            coord = i.saisirCoordonnees();
            if (checkCoord(coord)) throw new PiecePasdisponibleException(Erreur.ARGUMENTINCORECT);
            Case origin = p.getPlateau()[coord[0]][coord[1]];
            System.out.println(i.getLanguage().avertissement(Avertissement.CHOIXDESTINATION));
            coord = i.saisirCoordonnees();
            if (checkCoord(coord)) throw new PiecePasdisponibleException(Erreur.ARGUMENTINCORECT);
            Case destination = p.getPlateau()[coord[0]][coord[1]];
            return new Deplacement(origin, destination);
        } catch (Exception e) {
            IHM.getIHM().display(e);
            return null;
        }
    }

    /**
     * test si les coordonnées rentrés sont valides
     * @param coord les coordonnées a tester
     * @return un booléen indiquant si oui ou non les coordonnées sont valides
     */
    private boolean checkCoord(int[] coord) {
        return coord[0] > 2 || coord[0] < 0 || coord[1] > 2 || coord[1] < 0;
    }

    private Action creerActionQuitter(IHM i, Plateau p) {
        return new Termination();
    }

    @Override
    public Object clone() {
        JoueurHumain cloneObject = new JoueurHumain(getNom(), getCouleur());
        cloneObject.setPieces(getPieces());
        return cloneObject;
    }
}
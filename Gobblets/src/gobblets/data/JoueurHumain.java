package gobblets.data;

import gobblets.IHM.IHM;
import gobblets.IHM.texte.SaisieConsole;
import gobblets.interaction.Action;
import gobblets.interaction.Deplacement;
import gobblets.interaction.Placement;
import gobblets.interaction.Termination;

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
        int[] coord = i.saisirCoordonnees();
        Taille t = i.saisirTaille();
        Case destination = p.getPlateau()[coord[0]][coord[1]];
        return new Placement(t, destination);
    }

    private Action creerActionDeplacer(IHM i, Plateau p) {
        int[] coord;
        coord = i.saisirCoordonnees();
        Case origin = p.getPlateau()[coord[0]][coord[1]];
        coord = i.saisirCoordonnees();
        Case destination = p.getPlateau()[coord[0]][coord[1]];
        return new Deplacement(origin, destination);
    }

    private Action creerActionQuitter(IHM i, Plateau p) {
        return new Termination();
    }
}
package gobblets.data;

import java.util.ArrayList;

import gobblets.interaction.Action;
import gobblets.interaction.Deplacement;
import gobblets.interaction.Placement;

public class JoueurIA extends Joueur {
    private Joueur adversaire;

    public JoueurIA(String nom, Couleur couleur) {
        super(nom, couleur);
    }

    public Joueur getAdversaire() {
        return adversaire;
    }

    public void setAdversaire(Joueur adversaire) {
        this.adversaire = adversaire;
    }

    @Override
    public Action choisirAction(Plateau p) {
        // min max
        return minMAxAlgo(p);
    }

    /** Min Max */
    private Action minMAxAlgo(Plateau p) {
        return null;
    }

    public Object clone() {
        JoueurIA cloneObject = new JoueurIA(getNom(), getCouleur());
        cloneObject.setPieces(getPieces());
        return cloneObject;
    }
}
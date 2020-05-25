package gobblets.data;

import gobblets.data.IA.JoueurSimplifie;
import gobblets.data.IA.PlateauSimplifie;
import gobblets.interaction.Action;

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
        /** setup values */
        Action choice = null;
        /** setup simplified gameboard */
        /** return */
        return choice;
    }

    public Object clone() {
        JoueurIA cloneObject = new JoueurIA(getNom(), getCouleur());
        cloneObject.setPieces(getPieces());
        return cloneObject;
    }
}
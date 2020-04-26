package gobblets.data;

import java.util.Arrays;
import java.util.List;

import gobblets.interaction.Action;

public class JoueurIA extends Joueur {
    public JoueurIA(String nom, Couleur couleur) {
        super(nom, couleur);
    }

    @Override
    public Action choisirAction(Plateau p) {

        return null;
    }

    private Taille[] getTaillesPossibles(Case c) {
        List<Taille> tList = Arrays.asList(Taille.values());
        for (int i = 0; i < tList.size(); i++) {
            if (c.acceptePiece(tList.get(i))) return tList.subList(i, tList.size()).toArray(new Taille[tList.size() - i]);
        }
        return null;
    }

    private Object minMax(Plateau p, int profondeur) {
        try {
            /** check for winner */
            Couleur winner = null;
            
            for (Case[] cL : p.getPlateau()) {
                for (Case c : cL) {
                    /* Deplacement
                     * if (c.plusGrandePiece().getCouleur() == super.getCouleur()) {}
                     */
                    /** Placement */
                    for (Taille t : getTaillesPossibles(c)) { // tailles possibles pour la case
                        if (super.aPieceDeTaille(t)) { // si le joueur a une piece de la taille

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
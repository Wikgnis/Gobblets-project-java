package gobblets.IHM;

import gobblets.data.*;

public interface Dictionnaire {
    public String couleur(Couleur c);
    public String taille(Taille t);
    public String etat(Etat e);
    public String action(ActionType a);
    public String erreur(Erreur e);
    public String avertissement(Avertissement a);
    public String menu(Menu m);
    /**
     * texte des menus
     */
    public String texteMenu(Menu m)
}
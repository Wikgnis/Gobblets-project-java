package gobblets.IHM;

import gobblets.data.*;

public interface Dictionnaire {
    public String couleur(Couleur c);
    public String taille(Taille t);
    public String etat(Etat e);
    public String action(ActionType a);
    public String erreur(Erreur e);
}
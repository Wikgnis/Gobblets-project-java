package gobblets.IHM;

import gobblets.IHM.langues.Francais;
import gobblets.data.*;

public abstract class IHM {
    private Dictionnaire language;

    public IHM() {
        language = new Francais();
    }

    public abstract Joueur saisirJoueur(int n) throws Exception;
    public abstract Taille saisirTaille() throws Exception;
    public abstract int[] saisirCoordonnees() throws Exception;
    public abstract void display(Plateau p, Joueur j) throws Exception;
    public abstract ActionType saisirAction(Joueur j) throws Exception;

    public Dictionnaire getLanguage() {
        return language;
    }

    public void setLanguage(Dictionnaire language) {
        this.language = language;
    }

    public IHM getIHM() {
        return this;
    }

    public void setIHM(IHM i) {
        // todo
    }

    public String couleur(Couleur c) {
        return language.couleur(c);
    }

    public String taille(Taille t) {
        return language.taille(t);
    }

    public String etat(Etat e) {
        return language.etat(e);
    }

    public String action(ActionType a) {
        return language.action(a);
    }

    public String erreur(Erreur e) {
        return language.erreur(e);
    }

    public String avertissement(Avertissement a) {
        return language.avertissement(a);
    }
}
package gobblets.IHM;

import gobblets.IHM.langues.Francais;
import gobblets.data.*;

public abstract class IHM {
    private Dictionnaire language;
    private static IHM courante = null;

    public IHM() {
        language = new Francais();
    }

    public abstract Joueur saisirJoueur(int n) throws Exception;
    public abstract Taille saisirTaille() throws Exception;
    public abstract int[] saisirCoordonnees() throws Exception;
    public abstract void display(Plateau p, Joueur j);
    public abstract void display(Exception e);
    public abstract ActionType saisirAction(Joueur j) throws Exception;
    public abstract void finalize();

    public Dictionnaire getLanguage() {
        return language;
    }

    public void setLanguage(Dictionnaire language) {
        this.language = language;
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

    public static IHM getIHM() {
        return courante;
    }

    public static void setIHM(IHM c) {
        courante = c;
    }
}
package gobblets.IHM.langues;

import java.util.HashMap;

import gobblets.IHM.*;
import gobblets.data.ActionType;
import gobblets.data.Couleur;
import gobblets.data.Etat;
import gobblets.data.Taille;

public class Allemand implements Dictionnaire {
	private HashMap<Couleur, String> couleurs;
    private HashMap<Taille, String> tailles;
    private HashMap<Etat, String> etats;
    private HashMap<ActionType, String> actions;
    private HashMap<Erreur,String> erreurs;
    private HashMap<Avertissement, String> avertissements;

    public Allemand() {
        /* couleurs */
        couleurs = new HashMap<Couleur, String>();
        couleurs.put(Couleur.ROUGE, "Rot");
        couleurs.put(Couleur.VERT, "Grün");
        couleurs.put(Couleur.JAUNE, "Gelb");
        couleurs.put(Couleur.BLEU, "Blau");
        couleurs.put(Couleur.BLANC, "Weiß");
        couleurs.put(Couleur.CYAN, "Cyan");
        couleurs.put(Couleur.VIOLET, "Lila");
        /* tailles */
        tailles = new HashMap<Taille, String>();
        tailles.put(Taille.GRANDE, "Groß");
        tailles.put(Taille.MOYENNE, "Durchschnittlich");
        tailles.put(Taille.PETITE, "Klein");
        /* etats */
        etats = new HashMap<Etat, String>();
        etats.put(Etat.JEUENCOURS, "Gerade stattfindendes Spiel");
        etats.put(Etat.JEUQUITTE, "Spiel übrig");
        etats.put(Etat.JOUEUR1GAGNE, "Spieler 1 hat gewonnen !");
        etats.put(Etat.JOUEUR2GAGNE, "Spieler 2 hat gewonnen !");
        etats.put(Etat.MATCHNUL, "Zeichnen");
        /* actions */
        actions = new HashMap<ActionType, String>();
        actions.put(ActionType.DEPLACER, "Bewegung");
        actions.put(ActionType.PLACER, "Hinstellen");
        actions.put(ActionType.QUITTER, "Verlassen");
        /* erreurs */
        erreurs = new HashMap<Erreur, String>();
        erreurs.put(Erreur.ARGUMENTINCORECT, "Falsches Argument");
        erreurs.put(Erreur.CASEBLOQUE, "Blockierte Box");
        erreurs.put(Erreur.DIAGONALEINCORECTE, "Falsche Diagonale");
        erreurs.put(Erreur.ORIGINVIDE, "Leerer Ursprung");
        erreurs.put(Erreur.PASDEPIECEDISPONIBLE, "Kein Teil verfügbar");
        erreurs.put(Erreur.PASTAPIECE, "Nicht dein teil");
        /* avertissements */
        avertissements = new HashMap<Avertissement, String>();
        avertissements.put(Avertissement.CHOIXACTION, "Wählen Sie Ihre Aktion");
        avertissements.put(Avertissement.CHOIXDESTINATION, "Ziel ?");
        avertissements.put(Avertissement.CHOIXORIGIN, "Ursprung ?");
        avertissements.put(Avertissement.CHOIXTAILLE, "Schnitt ?");
        avertissements.put(Avertissement.COULEURJOUEUR, "Spielerfarbe");
        avertissements.put(Avertissement.NOMJOUEUR, "Spielername");
        avertissements.put(Avertissement.TONTOUR, "Turm von");
        avertissements.put(Avertissement.SAISIECOORDONNEES, "Koordinateneingabe");
        avertissements.put(Avertissement.SAISIECOORDONNEE1, "Erste Koordinate");
        avertissements.put(Avertissement.SAISIECOORDONNEE2, "Zweite Koordinate");
        avertissements.put(Avertissement.MAISON, "Haus");
        avertissements.put(Avertissement.CHOIXTYPEJOUEUR, "Welche Art von Spieler ?");
    }

    @Override
    public String couleur(Couleur c) {
        return couleurs.get(c);
    }

    @Override
    public String taille(Taille t) {
        return tailles.get(t);
    }

    @Override
    public String etat(Etat e) {
        return etats.get(e);
    }

    @Override
    public String action(ActionType a) {
        return actions.get(a);
    }

    @Override
    public String erreur(Erreur e) {
        return erreurs.get(e);
    }

    @Override
    public String avertissement(Avertissement a) {
        return avertissements.get(a);
    }
	
}

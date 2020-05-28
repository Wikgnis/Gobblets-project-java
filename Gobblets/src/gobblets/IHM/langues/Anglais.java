package gobblets.IHM.langues;

import java.util.HashMap;

import gobblets.IHM.*;
import gobblets.data.ActionType;
import gobblets.data.Couleur;
import gobblets.data.Etat;
import gobblets.data.Taille;

public class Anglais implements Dictionnaire {
    private HashMap<Couleur, String> couleurs;
    private HashMap<Taille, String> tailles;
    private HashMap<Etat, String> etats;
    private HashMap<ActionType, String> actions;
    private HashMap<Erreur, String> erreurs;
    private HashMap<Avertissement, String> avertissements;
    private HashMap<Menu, String> menus;

    public Anglais() {
        /* couleurs */
        couleurs = new HashMap<Couleur, String>();
        couleurs.put(Couleur.NOIR, "black");
        couleurs.put(Couleur.BLEU, "blue");
        couleurs.put(Couleur.CYAN, "cyan");
        couleurs.put(Couleur.VERT, "green");
        couleurs.put(Couleur.MAGENTA, "magenta");
        couleurs.put(Couleur.ROUGE, "red");
        couleurs.put(Couleur.BLANC, "white");
        couleurs.put(Couleur.JAUNE, "yellow");
        /* tailles */
        tailles = new HashMap<Taille, String>();
        tailles.put(Taille.GRANDE, "Big");
        tailles.put(Taille.MOYENNE, "Medium");
        tailles.put(Taille.PETITE, "Small");
        /* etats */
        etats = new HashMap<Etat, String>();
        etats.put(Etat.JEUENCOURS, "Game running");
        etats.put(Etat.JEUQUITTE, "Game quitted");
        etats.put(Etat.JOUEUR1GAGNE, "Player 1 won !");
        etats.put(Etat.JOUEUR2GAGNE, "Player 2 won !");
        etats.put(Etat.MATCHNUL, "Tie");
        /* actions */
        actions = new HashMap<ActionType, String>();
        actions.put(ActionType.DEPLACER, "Move");
        actions.put(ActionType.PLACER, "Place");
        actions.put(ActionType.QUITTER, "Quit");
        /* erreurs */
        erreurs = new HashMap<Erreur, String>();
        erreurs.put(Erreur.ARGUMENTINCORECT, "Incorrect argument");
        erreurs.put(Erreur.CASEBLOQUE, "Blocked");
        erreurs.put(Erreur.DIAGONALEINCORECTE, "Incorrect diagonal");
        erreurs.put(Erreur.ORIGINVIDE, "origin is empty");
        erreurs.put(Erreur.PASDEPIECEDISPONIBLE, "Piece not available");
        erreurs.put(Erreur.PASTAPIECE, "the piece is not yours");
        /* avertissements */
        avertissements = new HashMap<Avertissement, String>();
        avertissements.put(Avertissement.CHOIXACTION, "Choose your action");
        avertissements.put(Avertissement.CHOIXDESTINATION, "Destination ?");
        avertissements.put(Avertissement.CHOIXORIGIN, "Origin ?");
        avertissements.put(Avertissement.CHOIXTAILLE, "Size ?");
        avertissements.put(Avertissement.COULEURJOUEUR, "Player color");
        avertissements.put(Avertissement.NOMJOUEUR, "Player name");
        avertissements.put(Avertissement.TONTOUR, "turn of : ");
        avertissements.put(Avertissement.SAISIECOORDONNEES, "Coordinate entry");
        avertissements.put(Avertissement.SAISIECOORDONNEE1, "First coordinate");
        avertissements.put(Avertissement.SAISIECOORDONNEE2, "Second coordinate");
        avertissements.put(Avertissement.MAISON, "House");
        avertissements.put(Avertissement.CHOIXTYPEJOUEUR, "What kind of player ?");
        avertissements.put(Avertissement.ANNULATIONSAISIE, "Cancel entry");
        avertissements.put(Avertissement.SAISIEJOUEUR, "Player entry");
        avertissements.put(Avertissement.CONFIRMER, "Confirm");
        avertissements.put(Avertissement.AUCUNESAUVEGARDE, "Wow such empty !");
        avertissements.put(Avertissement.APPUYERSURENTREE, "Press <Enter>");
        avertissements.put(Avertissement.ENTRERLENOMDELASAUVEGARDE, "save name");
        avertissements.put(Avertissement.CONTINUER, "continue");
        avertissements.put(Avertissement.FICHIERDEJAEXISTANT, "the file already exist");
        avertissements.put(Avertissement.SUPPRIMER, "delete");
        /** menus */
        menus = new HashMap<Menu, String>();
        menus.put(Menu.MENU_AIDE, "help");
        menus.put(Menu.MENU_APROPOS, "about");
        menus.put(Menu.MENU_ENREGISTRER, "save");
        menus.put(Menu.MENU_FICHIER, "folders");
        menus.put(Menu.MENU_LANGUE, "language choice");
        menus.put(Menu.MENU_NOUVEAU, "new game");
        menus.put(Menu.MENU_OUVRIR, "open game");
        menus.put(Menu.MENU_QUITTER, "quit");
        menus.put(Menu.MENU_ACCEUIL, "home");
        menus.put(Menu.REPRENDRE, "resume");
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

    @Override
    public String menu(Menu m) {
        return menus.get(m);
    }

}
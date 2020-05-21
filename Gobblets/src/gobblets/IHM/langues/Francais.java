package gobblets.IHM.langues;

import java.util.HashMap;

import gobblets.IHM.*;
import gobblets.data.ActionType;
import gobblets.data.Couleur;
import gobblets.data.Etat;
import gobblets.data.Taille;

public class Francais implements Dictionnaire {
    private HashMap<Couleur, String> couleurs;
    private HashMap<Taille, String> tailles;
    private HashMap<Etat, String> etats;
    private HashMap<ActionType, String> actions;
    private HashMap<Erreur,String> erreurs;
    private HashMap<Avertissement, String> avertissements;

    public Francais() {
        /* couleurs */
        couleurs = new HashMap<Couleur, String>();
        couleurs.put(Couleur.NOIR, "noir");
        couleurs.put(Couleur.BLEU, "bleu");
        couleurs.put(Couleur.CYAN, "cyan");
        couleurs.put(Couleur.VERT, "vert");
        couleurs.put(Couleur.MAGENTA, "magenta");
        couleurs.put(Couleur.ROUGE, "rouge");
        couleurs.put(Couleur.BLANC, "blanc");
        couleurs.put(Couleur.JAUNE, "jaune");
        /* tailles */
        tailles = new HashMap<Taille, String>();
        tailles.put(Taille.GRANDE, "Grande");
        tailles.put(Taille.MOYENNE, "Moyenne");
        tailles.put(Taille.PETITE, "Petite");
        /* etats */
        etats = new HashMap<Etat, String>();
        etats.put(Etat.JEUENCOURS, "Jeu en cours");
        etats.put(Etat.JEUQUITTE, "Jeu quitté");
        etats.put(Etat.JOUEUR1GAGNE, "Joueur 1 à gagné !");
        etats.put(Etat.JOUEUR2GAGNE, "Joueur 2 à gagné !");
        etats.put(Etat.MATCHNUL, "Match nul");
        /* actions */
        actions = new HashMap<ActionType, String>();
        actions.put(ActionType.DEPLACER, "Deplacer");
        actions.put(ActionType.PLACER, "Placer");
        actions.put(ActionType.QUITTER, "Quitter");
        /* erreurs */
        erreurs = new HashMap<Erreur, String>();
        erreurs.put(Erreur.ARGUMENTINCORECT, "Argument incorrect");
        erreurs.put(Erreur.CASEBLOQUE, "Case Bloquée");
        erreurs.put(Erreur.DIAGONALEINCORECTE, "Diagonale incorrecte");
        erreurs.put(Erreur.ORIGINVIDE, "Origine vide");
        erreurs.put(Erreur.PASDEPIECEDISPONIBLE, "Pas de pièce disponible");
        erreurs.put(Erreur.PASTAPIECE, "Pas ta pièce");
        /* avertissements */
        avertissements = new HashMap<Avertissement, String>();
        avertissements.put(Avertissement.CHOIXACTION, "Choisir votre action");
        avertissements.put(Avertissement.CHOIXDESTINATION, "Destination ?");
        avertissements.put(Avertissement.CHOIXORIGIN, "Origine ?");
        avertissements.put(Avertissement.CHOIXTAILLE, "Taille ?");
        avertissements.put(Avertissement.COULEURJOUEUR, "Couleur joueur");
        avertissements.put(Avertissement.NOMJOUEUR, "Nom joueur");
        avertissements.put(Avertissement.TONTOUR, "Tour de");
        avertissements.put(Avertissement.SAISIECOORDONNEES, "Saisie coordonnées");
        avertissements.put(Avertissement.SAISIECOORDONNEE1, "Première coordonnée");
        avertissements.put(Avertissement.SAISIECOORDONNEE2, "Seconde coordonnée");
        avertissements.put(Avertissement.MAISON, "Maison");
        avertissements.put(Avertissement.CHOIXTYPEJOUEUR, "Quel type de joueur ?");
        avertissements.put(Avertissement.ANNULATIONSAISIE, "Annulation saisie");
        avertissements.put(Avertissement.SAISIEJOUEUR, "Saisie Joeur");
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
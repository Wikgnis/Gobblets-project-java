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
    private HashMap<Menu, String> menus;
    private HashMap<Menu, String> texteMenu;

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
        etats.put(Etat.JOUEUR1GAGNE, "Joueur 1 à gagn� !");
        etats.put(Etat.JOUEUR2GAGNE, "Joueur 2 à gagn� !");
        etats.put(Etat.MATCHNUL, "Match nul");
        /* actions */
        actions = new HashMap<ActionType, String>();
        actions.put(ActionType.DEPLACER, "Deplacer");
        actions.put(ActionType.PLACER, "Placer");
        actions.put(ActionType.QUITTER, "Quitter");
        /* erreurs */
        erreurs = new HashMap<Erreur, String>();
        erreurs.put(Erreur.ARGUMENTINCORECT, "Argument incorrect");
        erreurs.put(Erreur.CASEBLOQUE, "Case Bloqu�e");
        erreurs.put(Erreur.DIAGONALEINCORECTE, "Diagonale incorrecte");
        erreurs.put(Erreur.ORIGINVIDE, "Origine vide");
        erreurs.put(Erreur.PASDEPIECEDISPONIBLE, "Pas de pi�ce disponible");
        erreurs.put(Erreur.PASTAPIECE, "Pas ta pi�ce");
        /* avertissements */
        avertissements = new HashMap<Avertissement, String>();
        avertissements.put(Avertissement.CHOIXACTION, "Choisir votre action");
        avertissements.put(Avertissement.CHOIXDESTINATION, "Destination ?");
        avertissements.put(Avertissement.CHOIXORIGIN, "Origine ?");
        avertissements.put(Avertissement.CHOIXTAILLE, "Taille ?");
        avertissements.put(Avertissement.COULEURJOUEUR, "Couleur joueur");
        avertissements.put(Avertissement.NOMJOUEUR, "Nom joueur");
        avertissements.put(Avertissement.TONTOUR, "Tour de");
        avertissements.put(Avertissement.SAISIECOORDONNEES, "Saisie coordonn�es");
        avertissements.put(Avertissement.SAISIECOORDONNEE1, "Premi�re coordonn�e");
        avertissements.put(Avertissement.SAISIECOORDONNEE2, "Seconde coordonn�e");
        avertissements.put(Avertissement.MAISON, "Maison");
        avertissements.put(Avertissement.CHOIXTYPEJOUEUR, "Quel type de joueur ?");
        avertissements.put(Avertissement.ANNULATIONSAISIE, "Annulation saisie");
        avertissements.put(Avertissement.SAISIEJOUEUR, "Saisie Joueur");
        avertissements.put(Avertissement.CONFIRMER, "Confirmer");
        avertissements.put(Avertissement.AUCUNESAUVEGARDE, "Pas encore de sauvegarde d�sol�.");
        avertissements.put(Avertissement.APPUYERSURENTREE, "Appuyez sur <Enter>");
        avertissements.put(Avertissement.ENTRERLENOMDELASAUVEGARDE, "Nom de la sauvegarde");
        avertissements.put(Avertissement.CONTINUER, "CONTINUER");
        avertissements.put(Avertissement.FICHIERDEJAEXISTANT, "Le fichier existe d�j�");
        avertissements.put(Avertissement.SUPPRIMER, "Supprimer");
        /** menus */
        menus = new HashMap<Menu, String>();
        menus.put(Menu.MENU_AIDE, "Menu d'aide");
        menus.put(Menu.MENU_APROPOS, "A propos");
        menus.put(Menu.MENU_ENREGISTRER, "Enregistrer");
        menus.put(Menu.MENU_FICHIER, "Fichiers");
        menus.put(Menu.MENU_LANGUE, "Choix langue");
        menus.put(Menu.MENU_NOUVEAU, "Nouvelle partie");
        menus.put(Menu.MENU_OUVRIR, "Ouvrir une partie");
        menus.put(Menu.MENU_QUITTER, "Quitter");
        menus.put(Menu.MENU_ACCEUIL, "Accueil");
        menus.put(Menu.REPRENDRE, "Reprendre");
        
        texteMenu = new HashMap<Menu, String>();
        texteMenu.put(Menu.MENU_AIDE, "Le but du jeu est d'obtenir, une ligne, une colonne, ou une diagonale de votre couleur de mani�re strat�gique.\n"+"Vous disposez de 2 pi�ces de 3 types, petites, moyennes et grandes.\n"+"Vous pouvez poser une pi�ce sur une autre tant qu'elle est strictement plus grande que celle-ci.\n");
        texteMenu.put(Menu.MENU_APROPOS, "Gobblet Gobblers est un jeu familiale cr�� par Thierry Denoual.\n"+"Il allie concentration et attention ainsi que la m�moire, la perception visuelle et la r�solution de probl�mes.\n"+"C'est une jeu de strat�gie id�al pour tout le monde, m�me pour les enfants mais conseill� pour les plus de 5 ans.");
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

	@Override
	public String texteMenu(Menu m) {
		return texteMenu.get(m);
	}

}
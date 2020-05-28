package gobblets.IHM.texte;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import gobblets.IHM.Avertissement;
import gobblets.IHM.Erreur;
import gobblets.IHM.IHM;
import gobblets.IHM.Menu;
import gobblets.IHM.langues.Anglais;
import gobblets.IHM.langues.Francais;
import gobblets.data.*;
import gobblets.logic.CaseBloqueeException;
import gobblets.logic.PiecePasdisponibleException;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.AnsiRenderer;

import app.App;

public class SaisieConsole extends IHM {
    private final static Scanner sc = new Scanner(System.in);

    public SaisieConsole() {
        AnsiConsole.systemInstall();
    }

    @Override
    public Joueur saisirJoueur(int n) throws Exception {
        System.out.println(getLanguage().avertissement(Avertissement.SAISIEJOUEUR) + " " + n);
        Joueur j = null;
        System.out.println(getLanguage().avertissement(Avertissement.CHOIXTYPEJOUEUR));
        System.out.println("1 : " + JoueurHumain.class.getSimpleName() + " | 2 : " + JoueurIA.class.getSimpleName());
        String in = sc.nextLine();
        try {
            switch (in) {
                case "1": j = saisieJoueurHumain();
                    break;
                case "2": j = saisieJoueurIA();
                    break;
                default: throw new Exception(getLanguage().erreur(Erreur.ARGUMENTINCORECT) + " " + (in.equals(" ") ? "<space>" : in.equals("") ? "<enter>" : in));
            }
        } catch (Exception e) {
            String msg = getLanguage().avertissement(Avertissement.ANNULATIONSAISIE) + " : " + e.getMessage();
            throw new Exception(msg);
        }
        return j;
    }

    private JoueurIA saisieJoueurIA() throws Exception {
        /** choix nom joueur ia */
        String nom;
        List<String> listOfNames = Arrays.asList("ASTROBOY", "QRIO", "DANTE", "SPEEDY", "GENGHIS", "ALBERT HUBO", "R2-D2", "VAUCANSON", "THE IRON GIANT", "R.O.B.", "ROOMBA");
        nom = listOfNames.get(new Random().nextInt(listOfNames.size()));
        /** display nom */
        System.out.println(getLanguage().avertissement(Avertissement.NOMJOUEUR) + " : " + nom);
        /** choix couleur */
        Couleur couleur;
        System.out.println(getLanguage().avertissement(Avertissement.COULEURJOUEUR) + " : ");
        String s = "";
        // display colors
        for (int i = 0; i < Couleur.values().length; i++) {
            s += i + 1 + " : ";
            s += AnsiRenderer.render(couleur(Couleur.values()[i]), Couleur.values()[i].getAnsiColor().name());
            s += "   ";
        }
        System.out.println(s);
        /** choix */
        String in = sc.nextLine();
        switch (in) {
            case "1": couleur = Couleur.NOIR;
                break;
            case "2": couleur = Couleur.BLEU;
                break;
            case "3": couleur = Couleur.CYAN;
                break;
            case "4": couleur = Couleur.VERT;
                break;
            case "5": couleur = Couleur.MAGENTA;
                break;
            case "6": couleur = Couleur.ROUGE;
                break;
            case "7": couleur = Couleur.BLANC;
                break;
            case "8": couleur = Couleur.JAUNE;
                break;
            default: throw new Exception(getLanguage().erreur(Erreur.ARGUMENTINCORECT) + " " + in);
        }
        return new JoueurIA(nom, couleur);
    }

    private JoueurHumain saisieJoueurHumain() throws Exception {
        /** choix nom */
        System.out.println(getLanguage().avertissement(Avertissement.NOMJOUEUR) + " : ");
        String nom;
        nom = sc.nextLine();
        /** choix couleur */
        Couleur couleur;
        System.out.println(getLanguage().avertissement(Avertissement.COULEURJOUEUR) + " : ");
        // display colors
        String s = "";
        for (int i = 0; i < Couleur.values().length; i++) {
            s += i + 1 + " : ";
            s += AnsiRenderer.render(couleur(Couleur.values()[i]), Couleur.values()[i].getAnsiColor().name());
            s += "   ";
        }
        System.out.println(s);
        /** choix */
        String in = sc.nextLine();
        switch (in) {
            case "1": couleur = Couleur.NOIR;
                break;
            case "2": couleur = Couleur.BLEU;
                break;
            case "3": couleur = Couleur.CYAN;
                break;
            case "4": couleur = Couleur.VERT;
                break;
            case "5": couleur = Couleur.MAGENTA;
                break;
            case "6": couleur = Couleur.ROUGE;
                break;
            case "7": couleur = Couleur.BLANC;
                break;
            case "8": couleur = Couleur.JAUNE;
                break;
            default: throw new Exception(getLanguage().erreur(Erreur.ARGUMENTINCORECT) + " " + in);
        }
        return new JoueurHumain(nom, couleur);
    }

    @Override
    public Taille saisirTaille() throws Exception {
        System.out.println(getLanguage().avertissement(Avertissement.CHOIXTAILLE));
        String s = "";
        for (int i = 0; i < Taille.values().length; i++) {
            s += i + 1 + " : " + super.getLanguage().taille(Taille.values()[i]) + "   ";
        }
        System.out.println(s);
        String in = sc.nextLine();
        switch (in) {
            case "1":
                return Taille.PETITE;
            case "2":
                return Taille.MOYENNE;
            case "3":
                return Taille.GRANDE;
            default:
                throw new Exception(getLanguage().erreur(Erreur.ARGUMENTINCORECT) + " " + in);
        }
    }

    @Override
    public int[] saisirCoordonnees() throws Exception {
        int[] coord = new int[2];
        System.out.println(getLanguage().avertissement(Avertissement.SAISIECOORDONNEES) + " : ");
        Integer in = null;
        for (int i = 0; i < coord.length; i++) {
            // display du type saisie ( saisie coordonn�e 1 ou saisie coordonn�e 2)
            System.out.println((i == 0 ? getLanguage().avertissement(Avertissement.SAISIECOORDONNEE1)
                    : getLanguage().avertissement(Avertissement.SAISIECOORDONNEE2)));
            // saisie
            String s = sc.nextLine();
            try {
                in = Integer.parseInt(s);
            } catch (Exception e) {
                throw new Exception(getLanguage().erreur(Erreur.ARGUMENTINCORECT) + " " + e.getMessage());
            }
            coord[i] = in;
        }
        return coord;
    }

    public static String generateColoredBGString(String s, Couleur c) throws Exception {
        if (s == null)
            throw new Exception(getIHM().getLanguage().erreur(Erreur.ARGUMENTINCORECT) + " " + s);
        if (c == null)
            throw new Exception(getIHM().getLanguage().erreur(Erreur.ARGUMENTINCORECT) + " " + c);
        return AnsiRenderer.render("@|BG_" + c.getAnsiColor().name() + " " + s + "|@");
    }

    public static String generateColoredFGString(String s, Couleur c) throws Exception {
        if (s == null)
            throw new Exception("No string to operate on.");
        if (c == null)
            throw new Exception("No color for the String");
        return AnsiRenderer.render("@|FG_" + c.getAnsiColor().name() + " " + s + "|@");
    }

    private String displayHouse(Joueur j) {
        String houseDis = "";
        Piece pTxt;
        for (Object o : j.getPieces().toArray()) {
            pTxt = new Piece((gobblets.data.Piece) o);
            houseDis += " " + pTxt.getRepresentationTextuelle();
        }
        return houseDis;
    }

    @Override
    public void display(gobblets.data.Plateau p, Joueur j) {
        try {
            System.out.println(getLanguage().avertissement(Avertissement.NOMJOUEUR) + " : "
                    + generateColoredBGString(" " + j.getNom() + " ", j.getCouleur()));
            System.out.println(getLanguage().avertissement(Avertissement.MAISON) + " : " + displayHouse(j));
            Plateau pl = new Plateau(p);
            System.out.print(pl.getRepresentationTextuelle());
            ;
        } catch (Exception e) {
            IHM.getIHM().display(e);
        }
    }

    @Override
    public ActionType saisirAction(Joueur j) throws Exception {
        System.out.println(getLanguage().avertissement(Avertissement.CHOIXACTION));
        String s = "";
        for (int i = 0; i < ActionType.values().length; i++) {
            s += i + 1 + " : " + action(ActionType.values()[i]) + "   ";
        }
        System.out.println(s);
        String in = sc.nextLine();
        switch (in) {
            case "1":
                return ActionType.PLACER;
            case "2":
                return ActionType.DEPLACER;
            case "3":
                return ActionType.QUITTER;
            default:
                throw new Exception(getLanguage().erreur(Erreur.ARGUMENTINCORECT) + " " + in);
        }
    }

    public boolean valider() {
        System.out.println(getLanguage().avertissement(Avertissement.CONFIRMER) + " ?");
        System.out.println("y | n");
        String in = "";
        do {
            in = sc.nextLine();
            if (in.equals("y")) {
                return true;
            } else if (in.equals("n")) {
                return false;
            }
            display(new Exception(getLanguage().erreur(Erreur.ARGUMENTINCORECT) + " " + in));
        } while (true);
    }

    @Override
    public void finalize() {
        AnsiConsole.systemUninstall();
    }

    @Override
    public void display(Exception e) {
        if (e instanceof CaseBloqueeException) {
            System.out.println(AnsiRenderer
                    .render("@|bold,red " + getLanguage().erreur(((CaseBloqueeException) e).getErreur()) + "|@"));
        } else if (e instanceof PiecePasdisponibleException) {
            System.out.println(AnsiRenderer.render("@|bold,red " + getLanguage().erreur(((PiecePasdisponibleException) e).getErreur()) + "|@"));
        } else
            System.out.println(AnsiRenderer.render("@|bold,red " + e.getMessage() + "|@"));
    }

    @Override
    public Menu display(Menu m) {
        try {
            System.out.println(Ansi.ansi().eraseScreen());
            System.out.println(generateColoredBGString(getLanguage().menu(m), Couleur.MAGENTA));
        } catch (Exception e) {
            display(e);
        }
        switch (m) {
            case MENU_ACCEUIL:
                return displayMenuAcceuil();
            case MENU_AIDE:
                return displayMenuAide();
            case MENU_APROPOS:
                return displayMenuAPropos();
            case MENU_ENREGISTRER:
                return displayMenuEnregistrer();
            case MENU_LANGUE:
                return displayMenuLangue();
            case MENU_OUVRIR:
                return displayMenuOuvrir();
            case MENU_QUITTER:
                return displayMenuQuitter();
            default:
                return null;
        }
    }

    private Menu displayMenuAcceuil() {
        Menu[] choices = { Menu.MENU_NOUVEAU, Menu.MENU_OUVRIR, Menu.MENU_LANGUE, Menu.MENU_APROPOS, Menu.MENU_AIDE,
                Menu.MENU_QUITTER };
        for (int i = 0; i < choices.length; i++) {
            System.out.println(i + 1 + " - " + getLanguage().menu(choices[i]));
        }
        String in = sc.nextLine();
        try {
            Integer inValue = Integer.parseInt(in);
            if (inValue > 0 && inValue <= choices.length) {
                return choices[inValue - 1];
            }
        } catch (Exception e) {
        }
        return Menu.MENU_ACCEUIL;
    }

    private Menu displayMenuOuvrir() {
        File saveFolder = new File("ressources");
        try {
            // création fichier enregistrement si n'existe pas
            saveFolder.mkdir();
        } catch (Exception e) {
            IHM.getIHM().display(e);
        }
        // toutes les sauvegardes présentes dans le fichier
        File[] saves = saveFolder.listFiles();
        if (saves.length == 0) {
            System.out.println(getLanguage().avertissement(Avertissement.AUCUNESAUVEGARDE));
            System.out.println(getLanguage().avertissement(Avertissement.APPUYERSURENTREE));
            sc.nextLine();
            return Menu.MENU_ACCEUIL;
        } else {
            for (int i = 0; i < saves.length; i++) {
                System.out.println(i + 1 + " - " + saves[i].getName());
            }
            System.out.println(saves.length + 1 + " - Quitter");
            String in = sc.nextLine();
            try {
                Integer inValue = Integer.parseInt(in) - 1;
                if (inValue == saves.length) {
                    return Menu.MENU_ACCEUIL;
                } else if (inValue >= 0 && inValue < saves.length) {
                    App.charger(saves[inValue]);
                    return null;
                }
            } catch (Exception e) {
                display(new Exception(getLanguage().erreur(Erreur.ARGUMENTINCORECT) + " " + e.getMessage()));
                try {
                    System.out.println(generateColoredBGString(getLanguage().menu(Menu.MENU_OUVRIR), Couleur.MAGENTA));
                } catch (Exception e1) {
                    display(e1);
                }
            }
            return displayMenuOuvrir();
        }
    }

    private Menu displayMenuEnregistrer() {
        try {
            if (valider()) {
                System.out.print(getLanguage().avertissement(Avertissement.ENTRERLENOMDELASAUVEGARDE) + " : ");
                String inputName = sc.nextLine();
                File saveFolder = new File("ressources" + File.separatorChar + "" + inputName + ".save");
                if (saveFolder.createNewFile()) {
                    App.sauvegarder(saveFolder);
                    System.out.println(getLanguage().avertissement(Avertissement.CONTINUER) + " ?");
                    if (!valider()) {
                        return Menu.MENU_ACCEUIL;
                    }
                } else {
                    System.out.println(getLanguage().avertissement(Avertissement.FICHIERDEJAEXISTANT) + "=>" + getLanguage().avertissement(Avertissement.SUPPRIMER) + " ?");
                    if (valider()) {
                        App.sauvegarder(saveFolder);
                        if (!valider()) {
                            return Menu.MENU_ACCEUIL;
                        }
                    }
                }
            }
        } catch (Exception e) {
            display(e);
        }
        return null;
    }

    private Menu displayMenuQuitter() {
        Menu next = Menu.MENU_QUITTER;
        while (next == Menu.MENU_QUITTER) {
            System.out.println("1 - " + getLanguage().menu(Menu.REPRENDRE));
            System.out.println("2 - " + getLanguage().menu(Menu.MENU_ENREGISTRER));
            System.out.println("3 - " + getLanguage().menu(Menu.MENU_QUITTER) + "(" + getLanguage().menu(Menu.MENU_ACCEUIL) + ")");
            String in = sc.nextLine();
            try {
                Integer inValue = Integer.parseInt(in);
                switch (inValue) {
                    case 1: return null;
                    case 2: return Menu.MENU_ENREGISTRER;
                    case 3: return Menu.MENU_ACCEUIL;
                    default: break;
                }
            } catch (Exception e) {
                display(new Exception(getLanguage().erreur(Erreur.ARGUMENTINCORECT) + " " + in));
            }
        }
        return next;
    }

    private Menu displayMenuAide() {
        // TODO texte
        System.out.println("need text here");
        System.out.println(getLanguage().avertissement(Avertissement.APPUYERSURENTREE));
        sc.nextLine();
        return Menu.MENU_ACCEUIL;
    }

    private Menu displayMenuAPropos() {
        // TODO texte
        System.out.println("need text here");
        System.out.println(getLanguage().avertissement(Avertissement.APPUYERSURENTREE));
        sc.nextLine();
        return Menu.MENU_ACCEUIL;
    }

    private Menu displayMenuLangue() {
        String[] textMenu = {"Français", "English", getLanguage().menu(Menu.MENU_QUITTER)};
        for (int i = 0; i < textMenu.length; i++) {
            System.out.print(i + 1 + " - " + textMenu[i]);
            if (i == 0 && getLanguage() instanceof Francais) System.out.print(" <Actuellement utilisée>");
            if (i == 1 && getLanguage() instanceof Anglais) System.out.print(" <Used Currently>");
            System.out.println();
        }
        String in = sc.nextLine();
        try {
            Integer inValue = Integer.parseInt(in) - 1;
            switch (inValue) {
                case 0:
                    if (!(getLanguage() instanceof Francais)) setLanguage(new Francais());
                    break;
                case 1:
                    if (!(getLanguage() instanceof Anglais)) setLanguage(new Anglais());
                    break;
                default:
                    if (inValue == textMenu.length - 1) return Menu.MENU_ACCEUIL;
                    break;
            }
        } catch (Exception e) {}
        return Menu.MENU_LANGUE;
    }
}
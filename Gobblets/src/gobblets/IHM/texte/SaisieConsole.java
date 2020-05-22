package gobblets.IHM.texte;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import gobblets.IHM.Avertissement;
import gobblets.IHM.Erreur;
import gobblets.IHM.IHM;
import gobblets.IHM.Menu;
import gobblets.data.*;
import gobblets.logic.CaseBloqueeException;
import gobblets.logic.PiecePasdisponibleException;

import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.AnsiRenderer;

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
        // TODO
        System.out.println("1 : JoueurHumain | 2 : JoueurIA");
        String in = sc.nextLine();
        try {
            switch (in) {
                case "1":
                    j = saisieJoueurHumain();
                    break;

                case "2":
                    j = saisieJoueurIA();
                    break;

                default:
                    throw new Exception(getLanguage().erreur(Erreur.ARGUMENTINCORECT) + " "
                            + (in.equals(" ") ? "<space>" : in.equals("") ? "<enter>" : in));
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
        List<String> listOfNames = Arrays.asList("ASTROBOY", "QRIO", "DANTE", "SPEEDY", "GENGHIS", "ALBERT HUBO",
                "R2-D2", "VAUCANSON", "THE IRON GIANT", "R.O.B.", "ROOMBA");
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
            case "1":
                couleur = Couleur.NOIR;
                break;

            case "2":
                couleur = Couleur.BLEU;
                break;

            case "3":
                couleur = Couleur.CYAN;
                break;

            case "4":
                couleur = Couleur.VERT;
                break;

            case "5":
                couleur = Couleur.MAGENTA;
                break;

            case "6":
                couleur = Couleur.ROUGE;
                break;

            case "7":
                couleur = Couleur.BLANC;
                break;

            case "8":
                couleur = Couleur.JAUNE;
                break;

            default:
                throw new Exception(getLanguage().erreur(Erreur.ARGUMENTINCORECT) + " " + in);
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
            case "1":
                couleur = Couleur.NOIR;
                break;

            case "2":
                couleur = Couleur.BLEU;
                break;

            case "3":
                couleur = Couleur.CYAN;
                break;

            case "4":
                couleur = Couleur.VERT;
                break;

            case "5":
                couleur = Couleur.MAGENTA;
                break;

            case "6":
                couleur = Couleur.ROUGE;
                break;

            case "7":
                couleur = Couleur.BLANC;
                break;

            case "8":
                couleur = Couleur.JAUNE;
                break;

            default:
                throw new Exception(getLanguage().erreur(Erreur.ARGUMENTINCORECT) + " " + in);
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
            // display du type saisie ( saisie coordonnée 1 ou saisie coordonnée 2)
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
        // TODO
        if (s == null)
            throw new Exception("No string to operate on.");
        if (c == null)
            throw new Exception("No color for the String");
        // "@|red Hello|@ @|green World|@"
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
            System.out.println(AnsiRenderer.render(
                    "@|bold,red " + getLanguage().erreur(((PiecePasdisponibleException) e).getErreur()) + "|@"));
        } else
            System.out.println(AnsiRenderer.render("@|bold,red " + e.getMessage() + "|@"));
    }

    @Override
    public void display(Menu m) {
        switch (m) {
            case MENU_AIDE:
                displatMenuAide();
                break;
            case MENU_APROPOS:
                displayMenuAPropos();
                break;
            case MENU_ENREGISTRER:
                displayMenuEnregistrer();
                break;
            case MENU_FICHIER:
                displayMenuFichier();
                break;
            case MENU_LANGUE:
                displayMenuLangue();
                break;
            case MENU_NOUVEAU:
                displayMenuNouveau();
                break;
            case MENU_OUVRIR:
                displayMenuOuvrir();
                break;
            case MENU_QUITTER:
                displayMenuQuitter();
                break;
            default:
                break;
        }
    }

    private void displayMenuFichier() {
        // TODO
    }

    private void displayMenuOuvrir() {
        // TODO
    }

    private void displayMenuEnregistrer() {
        // TODO
    }

    private void displayMenuNouveau() {
        // TODO
    }

    private void displayMenuQuitter() {
        // TODO
    }

    private void displatMenuAide() {
        // TODO
    }

    private void displayMenuAPropos() {
        // TODO
    }

    private void displayMenuLangue() {
        // TODO
    }
}
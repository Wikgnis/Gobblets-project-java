package gobblets.IHM.texte;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import gobblets.IHM.Avertissement;
import gobblets.IHM.IHM;
import gobblets.data.*;

import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.AnsiRenderer;

import static org.fusesource.jansi.Ansi.*;

public class SaisieConsole extends IHM {
    private final static Scanner sc = new Scanner(System.in);

    public SaisieConsole() {
        AnsiConsole.systemInstall();
    }

    @Override
    public Joueur saisirJoueur(int n) throws Exception {
        System.out.println("Saisie Joueur" + n);
        Joueur j = null;
        System.out.println(getLanguage().avertissement(Avertissement.CHOIXTYPEJOUEUR));
        System.out.println("1 : JoueurHumain | 2 : JoueurIA | * : annuler");
        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            // Cancel Saisie joueur
            throw new Exception("annulation saisie.");
        }
        try {
            switch (choice) {
                case 1:
                    j = saisieJoueurHumain();
                    break;

                case 2:
                    j = saisieJoueurIA();
                    break;

                default:
                    throw new Exception("Pas de type joueur.");
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e + " ... Annulation saisie joueur");
            throw new Exception("annulation saisie.");
        }
        return j;
    }

    private JoueurIA saisieJoueurIA() throws Exception {
        /** choix nom joueur ia */
        String nom ;
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
        s += "* : annuler";
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
                throw new Exception("Pas de couleur choisie.");
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
            s += i+1 + " : ";
            s += AnsiRenderer.render(couleur(Couleur.values()[i]), Couleur.values()[i].getAnsiColor().name());
            s += "   ";
        }
        s += "* : annuler";
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
                throw new Exception("Pas de couleur choisie.");
        }
        return new JoueurHumain(nom, couleur);
    }

    @Override
    public Taille saisirTaille() throws Exception {
        System.out.println(getLanguage().avertissement(Avertissement.CHOIXTAILLE));
        String s = "";
        for (int i = 0; i < Taille.values().length; i++) {
            s += i+1 + " : " + super.getLanguage().taille(Taille.values()[i]) + "   ";
        }
        s += "* : annuler";
        System.out.println(s);
        String in = sc.nextLine();
        switch (in) {
            case "1": return Taille.PETITE;
            case "2": return Taille.MOYENNE;
            case "3": return Taille.GRANDE;
            default: throw new Exception("annulation action");
        }
    }

    @Override
    public int[] saisirCoordonnees() {
        int[] coord = new int[2];
        System.out.println(getLanguage().avertissement(Avertissement.SAISIECOORDONNEES) + "(0->2) : ");
        Integer in = null;
        for (int i = 0; i < coord.length; i++) {
            do {
                System.out.println((i==0 ? getLanguage().avertissement(Avertissement.SAISIECOORDONNEE1):getLanguage().avertissement(Avertissement.SAISIECOORDONNEE2)));
                String s = sc.nextLine();
                try {
                    in = Integer.parseInt(s);
                } catch (Exception e) {
                    System.out.println("Erreur : " + e + " Veuillez entrer un valuer correcte.");
                }
            } while (in == null);
            coord[i] = in;
        }
        return coord;
    }

    public static String generateColoredBGString(String s, Couleur c) throws Exception {
        if (s == null) throw new Exception("No string to operate on.");
        if (c == null) throw new Exception("No color for the String");
        // "@|red Hello|@ @|green World|@"
        return AnsiRenderer.render("@|BG_"+c.getAnsiColor().name() +" "+ s + "|@");
    }

    public static String generateColoredFGString(String s, Couleur c) throws Exception {
        if (s == null) throw new Exception("No string to operate on.");
        if (c == null) throw new Exception("No color for the String");
        return AnsiRenderer.render("@|FG_" + c.getAnsiColor().name() + " " + s + "|@");
    }

    private String displayHouse(Joueur j) {
        String houseDis = "";
        Piece pTxt;
        for (Object o : j.getPieces().toArray()) {
            pTxt = new Piece((gobblets.data.Piece)o);
            houseDis += " " + pTxt.getRepresentationTextuelle();
        }
        return houseDis;
    }

    @Override
    public void display(gobblets.data.Plateau p, Joueur j) {
        try {
            System.out.println(ansi().eraseScreen());
            System.out.println(getLanguage().avertissement(Avertissement.NOMJOUEUR) + " : " + generateColoredBGString(" "+j.getNom()+" ", j.getCouleur()));
            System.out.println(getLanguage().avertissement(Avertissement.MAISON) + " : " + displayHouse(j));
            Plateau pl = new Plateau(p);
            System.out.print(pl.getRepresentationTextuelle());;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public ActionType saisirAction(Joueur j) throws Exception {
        System.out.println(getLanguage().avertissement(Avertissement.CHOIXACTION));
        String s = "";
        for (int i = 0; i < ActionType.values().length; i++) {
            s += i+1 + " : " + action(ActionType.values()[i]) + "   ";
        }
        s += "* : annuler";
        System.out.println(s);
        String in = sc.nextLine();
        switch (in) {
            case "1": return ActionType.PLACER;
            case "2": return ActionType.DEPLACER;
            case "3": return ActionType.QUITTER;
            default: throw new Exception("annulation action");
        }
    }

    @Override
    public void finalize() {
        AnsiConsole.systemUninstall();
    }
}
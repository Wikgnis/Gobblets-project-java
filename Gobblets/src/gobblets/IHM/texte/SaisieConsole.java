package gobblets.IHM.texte;

import java.util.Scanner;

import gobblets.IHM.IHM;
import gobblets.data.*;

public class SaisieConsole extends IHM {
    private final static Scanner sc = new Scanner(System.in);

    @Override
    public Joueur saisirJoueur(int n) throws Exception {
        System.out.println("Saisie Joueur"+n);
        Joueur j = null;
        System.out.println("Saisir type joueur :\n\t1 : JoueurHumain | * : annuler");
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

                default:
                    throw new Exception("Pas de type joueur.");
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e + " ... Annulation saisie joueur");
            throw new Exception("annulation saisie.");
        }
        return j;
    }

    private JoueurHumain saisieJoueurHumain() throws Exception {
        System.out.println("Saisir nom joueur :");
        String nom;
        nom = sc.nextLine();
        Couleur couleur;
        System.out.println("Saisir couleur joueur:\n\t1 : Rouge | 2 : Bleu | * : annuler");
        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            // Cancel Saisie couleur
        }
        switch (choice) {
            case 1:
                couleur = Couleur.ROUGE;
                break;

            case 2:
                couleur = Couleur.BLEU;
                break;

            default:
                throw new Exception("Pas de couleur choisie.");
        }
        return new JoueurHumain(nom, couleur);
    }

    @Override
    public Taille saisirTaille() throws Exception {
        System.out.println("Quelle Taille?\n 1 : Grande | 2 : Moyenne | 3 : Petite | * : annuler");
        switch (sc.nextLine()) {
            case "1": return Taille.GRANDE;
            case "2": return Taille.MOYENNE;
            case "3": return Taille.PETITE;
            default: throw new Exception("annulation action");
        }
    }

    @Override
    public int[] saisirCoordonnees() {
        int[] coord = new int[2];
        System.out.println("Saisissez les coordonnées : ");
        Integer in = null;
        for (int i = 0; i < coord.length; i++) {
            do {
                System.out.println("entrez " + (i==0 ? "la premiere":"la seconde") + " coordonnee");
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
        String color = "\u001B[48;2;"+c.getR()+";"+c.getG()+";"+c.getB()+"m";
        return color + s + "\u001B[m";
    }

    public static String generateColoredFGString(String s, Couleur c) throws Exception {
        if (s == null) throw new Exception("No string to operate on.");
        if (c == null) throw new Exception("No color for the String");
        String color = "\u001B[38;2;"+c.getR()+";"+c.getG()+";"+c.getB()+"m";
        return color + s + "\u001B[m";
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
            System.out.println(generateColoredBGString(" "+j.getNom()+" ", j.getCouleur()));
            System.out.println("House : " + displayHouse(j));
            Plateau pl = new Plateau(p);
            System.out.print(pl.getRepresentationTextuelle());;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public ActionType saisirAction(Joueur j) throws Exception {
        System.out.println("Que voulez vous faire ?\n 1 : Placer | 2 : Deplacer | 3 : Quitter | * : annuler");
        switch (sc.nextLine()) {
            case "1": return ActionType.PLACER;
            case "2": return ActionType.DEPLACER;
            case "3": return ActionType.QUITTER;
            default: throw new Exception("annulation action");
        }
    }
}
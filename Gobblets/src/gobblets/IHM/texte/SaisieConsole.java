package gobblets.IHM.texte;

import java.util.Scanner;

import gobblets.IHM.IHM;
import gobblets.data.*;

public class SaisieConsole extends IHM {
    private final static Scanner sc = new Scanner(System.in);

    @Override
    public Joueur saisirJoueur(int n){
        System.out.println("Saisie Joueur"+n);
        Joueur j = null;
        System.out.println("Saisir type joueur :\n\t1 : JoueurHumain | * : annuler");
        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            // Cancel Saisie joueur
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
    public Taille saisirTaille() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] saisirCoordonnees() {
        // TODO Auto-generated method stub
        return null;
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

    @Override
    public void display(gobblets.data.Plateau p, Joueur j) {
        try {
            System.out.println(generateColoredBGString(j.getNom(), j.getCouleur()));
            Plateau pl = new Plateau(p);
            System.out.print(pl.getRepresentationTextuelle());;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
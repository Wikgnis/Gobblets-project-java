package gobblets.data;

import java.util.ArrayList;
import java.util.Arrays;

public class Plateau {
    private Case[][] cases = new Case[3][3];
    private ArrayList<Piece> maisonJ1, maisonJ2;

    private Plateau() {
        // init cases
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                cases[i][j] = new Case();
            }
        }
        // init maisons
        maisonJ1 = new ArrayList<Piece>();
        
        for (Taille taille : Taille.values()) {
            for (int i = 0; i < 2; i++) {
                maisonJ1.add(new Piece(taille));
            }
        }
        maisonJ2 = new ArrayList<Piece>();
        for (Taille taille : Taille.values()) {
            for (int i = 0; i < 2; i++) {
                maisonJ2.add(new Piece(taille));
            }
        }
    }

    public Plateau(Case[][] plateauCases, ArrayList<Piece> maisonJ1, ArrayList<Piece>maisonJ2) {
        try {
            for (int i = 0; i < cases.length; i++) {
                for (int j = 0; j < cases[i].length; j++) {
                    cases[i][j] = (Case) plateauCases[i][j].clone();
                }
            }
            this.maisonJ1 = (ArrayList<Piece>) maisonJ1.clone();
            this.maisonJ2 = (ArrayList<Piece>) maisonJ2.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Plateau initPlateau() {
        return new Plateau();
    }

    public Case[][] getPlateau() {
        return cases;
    }

    public ArrayList<Piece> getMaisonJ1() {
        return maisonJ1;
    }

    public ArrayList<Piece> getMaisonJ2() {
        return maisonJ2;
    }

    public void placePiece(Piece p, int l, int c) {
        cases[l][c].placePiece(p);
    }

    public Piece enlevePiece(int l, int c) {
        return cases[l][c].enlevePiece();
    }

    public Piece plusGrandePiece(int l, int c) {
        return cases[l][c].plusGrandePiece();
    }

    public Couleur verifierLigne(int n) throws Exception {
        try {
            Couleur winner = null;
            for (Case c : getLigne(n)) {
                if (c == null || c.plusGrandePiece() == null)
                    return null;
                else {
                    if (winner == null)
                        winner = c.plusGrandePiece().getCouleur();
                    else if (winner != c.plusGrandePiece().getCouleur())
                        return null;
                }
            }
            return winner;
        } catch (Exception e) {
            throw e;
        }
    }

    private Case[] getLigne(int n) throws Exception {
        if (n >= 0 && n < 3) {
            return cases[n];
        }
        throw new Exception("Numéro ligne invalide.");
    }

    public Couleur verifierColonne(int n) throws Exception {
        try {
            Couleur winner = null;
            for (Case c : getColonne(n)) {
                if (c == null || c.plusGrandePiece() == null)
                    return null;
                else {
                    if (winner == null)
                        winner = c.plusGrandePiece().getCouleur();
                    else if (winner != c.plusGrandePiece().getCouleur())
                        return null;
                }
            }
            return winner;
        } catch (Exception e) {
            throw e;
        }
    }

    private Case[] getColonne(int n) throws Exception {
        if (n >= 0 && n < 3) {
            Case[] colonne = new Case[3];
            for (int i = 0; i < colonne.length; i++) {
                colonne[i] = cases[i][n];
            }
            return colonne;
        } else
            throw new Exception("Numéro colonne invalide");
    }

    public Couleur verifierDiagonale(char ch) throws Exception {
        Case[] diagonale;
        switch (ch) {
            case 'a':
                diagonale = getDiagonalePrincipale();
                break;
            case 'b':
                diagonale = getDiagonaleSecondaire();
                break;
            default:
                throw new Exception("Symbole colonne invalide ('a'=principale, 'b'=secondaire).");
        }
        Couleur winner = null;
        for (Case c : diagonale) {
            if (c == null || c.plusGrandePiece() == null)
                return null;
            else {
                if (winner == null)
                    winner = c.plusGrandePiece().getCouleur();
                else if (winner != c.plusGrandePiece().getCouleur())
                    return null;
            }
        }
        return winner;
    }

    private Case[] getDiagonalePrincipale() {
        Case[] d = new Case[3];
        for (int i = 0; i < d.length; i++) {
            d[i] = cases[i][i];
        }
        return d;
    }

    private Case[] getDiagonaleSecondaire() {
        Case[] d = new Case[3];
        for (int i = 0; i < d.length; i++) {
            d[i] = cases[2-i][2-i];
        }
        return d;
    }

    @Override
    public String toString() {
        return "Plateau(cases=[" + Arrays.toString(cases[0]) + Arrays.toString(cases[1]) + Arrays.toString(cases[2]) + "], maisonJ1=" + maisonJ1 + ", maisonJ2=" + maisonJ2 + ")";
    }

    public Object clone() {
        Plateau p = null;
        try {
            p = new Plateau(getPlateau(), maisonJ1, maisonJ2);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return p;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Plateau other = (Plateau) obj;
        if (cases.equals(other.getPlateau()))
            return false;
        if (maisonJ1 == null) {
            if (other.getMaisonJ1() != null)
                return false;
        } else if (!maisonJ1.equals(other.getMaisonJ1()))
            return false;
        if (maisonJ2 == null) {
            if (other.getMaisonJ2() != null)
                return false;
        } else if (!maisonJ2.equals(other.getMaisonJ2()))
            return false;
        return true;
    }
}
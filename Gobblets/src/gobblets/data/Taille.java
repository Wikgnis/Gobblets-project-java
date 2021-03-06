package gobblets.data;

public enum Taille {
    PETITE('P'),
    MOYENNE('M'),
    GRANDE('G');

    private char symbole;
    Taille(char c) {
        symbole = c;
    }

    public char getSymbole() { return symbole; }
    public boolean recouvre(Taille t) { return t == null || getSymbole()<t.getSymbole(); }
    public boolean recouvre(Piece p) { return p == null || recouvre(p.getTaille()); }
}
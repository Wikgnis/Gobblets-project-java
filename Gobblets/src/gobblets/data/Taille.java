package gobblets.data;

public enum Taille {
    PETITE('P'),
    MOYENNE('M'),
    GRANDE('G');

    private char symbole;
    Taille(char c) {
        symbole = c;
    }
    /**
     * @return the symbole
     */
    public char getSymbole() { return symbole; }
    public boolean recouvre(Taille t) { return t == null || getSymbole()<t.getSymbole(); }
    public boolean reouvre(Piece p) { return p == null || recouvre(p.getTaille()); }
}
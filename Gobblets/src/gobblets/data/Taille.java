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
    public boolean recouvre(Taille t) { return getSymbole()<t.getSymbole(); }
}
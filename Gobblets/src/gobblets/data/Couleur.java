package gobblets.data;

public enum Couleur {
    ROUGE(255, 0, 0),
    VERT(0, 255, 0),
    JAUNE(255, 255, 0),
    BLEU(0, 0, 255),
    BLANC(255, 255, 255),
    CYAN(0, 255, 255),
    VIOLET(255, 0, 255);

    private int r, g, b;
    Couleur(int r, int g, int b) {
        this.r = r; this.g=g; this.b=b;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }
    
    public String getCode() {
        return "("+r+","+g+","+b+")";
    }

    public String toString() {
        return name() + getCode();
    }
}
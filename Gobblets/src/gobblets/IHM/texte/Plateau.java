package gobblets.IHM.texte;

public class Plateau {
    private gobblets.data.Plateau contenu;
    private Case[][] plateauAffichable;
    public Plateau(gobblets.data.Plateau p) {
        contenu = p;
        plateauAffichable = new Case[3][3];
        for (int i = 0; i < plateauAffichable.length; i++) {
            for (int j = 0; j < plateauAffichable[i].length; j++) {
                plateauAffichable[i][j] = new Case(contenu.getPlateau()[i][j]);
            }
        }
    }

    public String getRepresentationTextuelle() {
        String s = "";
        s += "  0  1  2\n";
        for (int i = 0; i < plateauAffichable.length; i++) {
            s += i + " ";
            for (int j = 0; j < plateauAffichable[i].length; j++) {
                s+=plateauAffichable[i][j].getRepresentationTextuelle();
            }
            s+="\n";
        }
        return s;
    }
}
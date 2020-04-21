package gobblets.IHM.texte;

public class Case {
    private gobblets.data.Case contenu;
    public Case(gobblets.data.Case c) {
        contenu = c;
    }

    public String getRepresentationTextuelle() {
        String s = "";
        if (contenu.plusGrandePiece() == null) s = "   ";
        else {
            
        }
        return s;
    }
}
package app;

import gobblets.IHM.*;
import gobblets.IHM.texte.SaisieConsole;
import gobblets.data.*;
import gobblets.logic.Jeu;


// jar cvfe {nomJAR} {main} {noms de ttes les classes}
public class App {
    public static void main(String[] args) throws Exception {
        new App();
    }

    public App() {
        Jeu gobblets = new Jeu();
        System.out.println("Jeu initialisé :\n"+ gobblets);
        /** setup ihm */
        IHM ihm = new SaisieConsole();
        ihm.setIHM(ihm);
        /** play */
        try {
            jouer(gobblets, ihm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /** end */
        /** end saisie console */
        // cette partie sert à repasser la console dans laquelle s'execute le code dans son etat initial
        // afin d'éviter d'éventuels problèmes d'affichages dans cette meme console
        if(ihm.getIHM() instanceof SaisieConsole) {
            ihm.getIHM().finalize();
        }
        if(ihm != ihm.getIHM() && ihm instanceof SaisieConsole) {
            ((SaisieConsole)ihm).finalize();
        }
    }

    private void jouer(Jeu gobblets, IHM ihm) throws Exception {
        while (gobblets.getEtat() == Etat.JEUENCOURS) {
            ihm.display(gobblets.getPlateau(), gobblets.getJoueurActif());
            gobblets.setEtat(gobblets.play());
        }
    }
}
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

    private IHM current = null;

    public App() {
        /** setup ihm */
        current = new SaisieConsole();
        IHM.setIHM(current);
        Jeu gobblets = new Jeu();
        /** play */
        try {
            jouer(gobblets);
        } catch (Exception e) {
            IHM.getIHM().display(e);
        }
        /** end */
        /** end saisie console */
        // cette partie sert à repasser la console dans laquelle s'execute le code dans son etat initial
        // afin d'éviter d'éventuels problèmes d'affichages dans cette meme console
        if (IHM.getIHM() instanceof SaisieConsole) IHM.getIHM().finalize();
    }

    private void jouer(Jeu gobblets) {
        while (gobblets.getEtat() == Etat.JEUENCOURS) {
            IHM.getIHM().display(gobblets.getPlateau(), gobblets.getJoueurActif());
            gobblets.setEtat(gobblets.play());
        }
    }
}
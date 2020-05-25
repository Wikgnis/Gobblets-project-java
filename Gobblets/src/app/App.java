package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import gobblets.IHM.*;
import gobblets.IHM.texte.SaisieConsole;
import gobblets.data.*;
import gobblets.logic.Jeu;


// jar cvfe {nomJAR} {main} {noms de ttes les classes}
// TODO affihage plateau modifié
public class App {
    public static void main(String[] args) throws Exception {
        new App();
    }

    private IHM current = null;
    private static Jeu gobblets = null;

    public App() {
        /** setup ihm */
        current = new SaisieConsole();
        IHM.setIHM(current);
        /** Menus */
        Menu current = Menu.MENU_ACCEUIL;
        while (current != Menu.MENU_QUITTER) {
            if (current == null) { // pas de nouveau menu donc retour au jeu
                current = partie(gobblets);
            } else {
                current = IHM.getIHM().display(current);
                if (current == Menu.MENU_NOUVEAU) {
                    current = lancerPartie();
                }
            }
        }
        /** end */
        /** end saisie console */
        // cette partie sert à repasser la console dans laquelle s'execute le code dans
        // son etat initial
        // afin d'éviter d'éventuels problèmes d'affichages dans cette meme console
        if (IHM.getIHM() instanceof SaisieConsole)
            IHM.getIHM().finalize();
    }

    private Menu lancerPartie() {
        gobblets = new Jeu();
        return lancerPartie(gobblets);
    }

    private Menu lancerPartie(Jeu gobblets) {
        try {
            return partie(gobblets);
        } catch (Exception e) {
            IHM.getIHM().display(e);
            return null;
        }
    }

    private Menu partie(Jeu gobblets) {
        while (gobblets.getEtat() == Etat.JEUENCOURS) {
            IHM.getIHM().display(gobblets.getPlateau(), gobblets.getJoueurActif());
            gobblets.setEtat(gobblets.play());
        }
        Menu returnMenu = IHM.getIHM().display(Menu.MENU_QUITTER);
        if (returnMenu == null) gobblets.setEtat(Etat.JEUENCOURS);
        return returnMenu;
    }

    public static void sauvegarder(File destination) {
        try {
            FileOutputStream out = new FileOutputStream(destination);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(gobblets);
            oos.close();
            out.close();
        } catch (Exception e) {
            IHM.getIHM().display(e);
        }
    }

    public static void charger(File sauvegarde) {
        try {
            FileInputStream in = new FileInputStream(sauvegarde);
            ObjectInputStream ois = new ObjectInputStream(in);
            gobblets = (Jeu)ois.readObject();
            ois.close();
            in.close();
        } catch (Exception e) {
            IHM.getIHM().display(e);
        }
    }
}
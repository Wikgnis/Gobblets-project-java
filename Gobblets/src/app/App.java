package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
    private Jeu gobblets = null;

    public App() {
        /** setup ihm */
        current = new SaisieConsole();
        IHM.setIHM(current);
        /** Menus */
        Menu current = Menu.MENU_ACCEUIL;
        while (current != Menu.MENU_QUITTER) {
            current = IHM.getIHM().display(current);
            if (current == Menu.MENU_NOUVEAU) {
                lancerPartie();
            }
        }
        /** end */
        /** end saisie console */
        // cette partie sert à repasser la console dans laquelle s'execute le code dans son etat initial
        // afin d'éviter d'éventuels problèmes d'affichages dans cette meme console
        if (IHM.getIHM() instanceof SaisieConsole) IHM.getIHM().finalize();
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
        return Menu.MENU_ACCEUIL;
    }

    private void sauvegarder(File fileSave) {
        try {
            /** create file */
            fileSave.createNewFile();
            /** save object in file */
            FileOutputStream fileOut = new FileOutputStream(fileSave);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(gobblets);
            objectOut.close();
            // TODO dysplay action réalisée
        } catch (Exception e) {
            IHM.getIHM().display(e);
        }
    }

    private void charger(File save) {
        try {
            FileInputStream is = new FileInputStream(save);
            ObjectInputStream ois = new ObjectInputStream(is);
            gobblets = (Jeu) ois.readObject();
            ois.close();
            is.close();
        } catch (Exception e) {
            IHM.getIHM().display(e);
        }
    }
}
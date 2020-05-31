package app;

// IHM
import gobblets.IHM.*;
// Saisie console
import gobblets.IHM.texte.SaisieConsole;
// Gobblets
import gobblets.data.*;
import gobblets.logic.Jeu;
// save Gobblets
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// FX
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author LEBOCQ Titouan, SAVARY Mathieu
 * @version JAVA SE 11
 * @version 2.0 (Fx implementation)
 */
public class App extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * l'instance du jeu actuellement utilisé par l'Application
     */
    private static Jeu gobblets = null;

    /**
     * crée une nouvelle instance du jeu et lance la partie @see lancerPartie(Jeu
     * gobblets)
     * 
     * @return Menu : le menu apres que l'utilisateur est joué
     */
    private Menu lancerPartie() {
        gobblets = new Jeu();
        return lancerPartie(gobblets);
    }

    /**
     * lance une partie a partir de l'instance de jeu gobblets deja existante si
     * elle n'existe pas retourne MENU_ACCEUIL
     * 
     * @param gobblets l'instance du jeu à lancer
     * @return Menu : le menu apres que l'utilisateur est joué
     */
    private Menu lancerPartie(Jeu gobblets) {
        try {
            return partie(gobblets);
        } catch (Exception e) {
            IHM.getIHM().display(e);
            return Menu.MENU_ACCEUIL;
        }
    }

    /**
     * Va executer une partie de gobblets gobblers
     * 
     * @param gobblets l'instance de jeu à utiliser
     * @return returnMenu : le menu apres que la fin de partie
     */
    private Menu partie(Jeu gobblets) {
        while (gobblets.getEtat() == Etat.JEUENCOURS) {
            IHM.getIHM().display(gobblets.getPlateau(), gobblets.getJoueurActif());
            gobblets.setEtat(gobblets.play());
        }
        Menu returnMenu;
        if (gobblets.getEtat() == Etat.JEUQUITTE) {
            returnMenu = IHM.getIHM().display(Menu.MENU_QUITTER);
            if (returnMenu == null)
                gobblets.setEtat(Etat.JEUENCOURS);
        } else
            returnMenu = Menu.MENU_ACCEUIL;
        return returnMenu;
    }

    /**
     * va sauvegarder l'actuelle instance de gobblets dans le fichier donné en
     * argument
     * 
     * @param destination destination de la sauvegarde de la partie
     */
    public static void sauvegarder(File destination) {
        try {
            FileOutputStream out = new FileOutputStream(destination);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            gobblets.setEtat(Etat.JEUENCOURS);
            oos.writeObject(gobblets);
            oos.close();
            out.close();
        } catch (Exception e) {
            IHM.getIHM().display(e);
        }
    }

    /**
     * va charger une partie de gobblets a partir du fichier sauvegarde donné en
     * argumennt
     * 
     * @param sauvegarde fichier source de la sauvegarde
     */
    public static void charger(File sauvegarde) {
        try {
            FileInputStream in = new FileInputStream(sauvegarde);
            ObjectInputStream ois = new ObjectInputStream(in);
            Object o = ois.readObject();
            if (o instanceof Jeu)
                gobblets = (Jeu) o;
            else {
                ois.close();
                throw new Exception(IHM.getIHM().getLanguage().erreur(Erreur.ARGUMENTINCORECT) + " "
                        + sauvegarde.getAbsolutePath());
            }
            ois.close();
            in.close();
        } catch (Exception e) {
            IHM.getIHM().display(e);
        }
    }

    @Override
    public void start(Stage stage) {
        IHM saisie;
        saisie = new SaisieConsole();
        /** setup global IHM */
        System.out.println("Utiliser interface java-FX");
        boolean javaFx = ((SaisieConsole) saisie).valider();
        if (javaFx) {
            String javaVersion = System.getProperty("java.version");
            String javafxVersion = System.getProperty("javafx.version");
            Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
            Scene scene = new Scene(new StackPane(l), 640, 480);
            stage.setScene(scene);
            stage.show();        } else {
            IHM.setIHM(saisie);
            SaisieConsole();
        }
    }

    public void SaisieConsole() {
        /** Menus */
        Menu current = Menu.MENU_ACCEUIL;
        while (current != Menu.MENU_QUITTER) {
            if (current == null) {
                // pas de nouveau menu donc retour au jeu (=reprendre jeu)
                current = partie(gobblets);
            } else {
                // affichage du menu actuel
                current = IHM.getIHM().display(current);
                if (current == Menu.MENU_NOUVEAU)
                    current = lancerPartie(); // si le joueur a choisi le menu_nouveau : creation d'une nouvelle
                                              // partie
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
}
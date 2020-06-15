package app;

// IHM
import gobblets.IHM.*;
import gobblets.IHM.Fx.Accueil;
import gobblets.IHM.Fx.JeuFx;
// Saisie console
import gobblets.IHM.texte.SaisieConsole;
// Gobblets
import gobblets.data.*;
import gobblets.logic.Jeu;
// save Gobblets
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;

// FX
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author LEBOCQ Titouan, SAVARY Mathieu
 * @version JAVA SE 11
 * @version 2.0 (Fx implementation)
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
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

    public void changeScene(Menu m) throws IOException {
        File projet;
        String pathFXML;
        switch (m) {
            case MENU_ACCEUIL:
                if (gobblets == null)
                    jeu = null;
                accueil.update();
                current.setScene(acceuilScene);
                break;

            case MENU_NOUVEAU:
                projet = new File(".");
                pathFXML = projet.getCanonicalPath() + File.separator + "ressources" + File.separator + "fxml";
                jeu = null;
                gobblets = null;
                jeu = new JeuFx(pathFXML, this);
                jeuScene = new Scene(jeu);
                current.setScene(jeuScene);
                break;

            case REPRENDRE:
                current.setScene(jeuScene);
                break;

            default:
                break;
        }
    }

    public void changeScene(File save) throws IOException {
        App.charger(save);
        File projet = new File(".");
        String pathFXML = projet.getCanonicalPath() + File.separator + "ressources" + File.separator + "fxml";
        jeu = new JeuFx(pathFXML, this, this.gobblets);
        jeuScene = new Scene(jeu);
        current.setScene(jeuScene);
    }

    private Accueil accueil;
    private JeuFx jeu;
    private Scene acceuilScene, jeuScene;
    private Stage current;

    @Override
    public void start(Stage stage) throws IOException {
        current = stage;
        IHM saisie;
        saisie = new SaisieConsole();
        /** setup global IHM */
        System.out.println("Utiliser interface java-FX");
        boolean javaFx = ((SaisieConsole) saisie).valider();
        if (javaFx) {
            File project = new File(".");
            String pathFXML = project.getCanonicalPath() + File.separator + "ressources" + File.separator + "fxml";
            // accueil
            accueil = new Accueil(pathFXML, this);
            acceuilScene = new Scene(accueil);
            accueil.update();
            // stage
            stage.setTitle("Gobblets");
            stage.setScene(acceuilScene);
            stage.show();
        } else {
            IHM.setIHM(saisie);
            SaisieConsole();
        }
    }

    private void SaisieConsole() {
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

    public static Jeu getGobblets() {
        return gobblets;
    }

    public static void setGobblets(Jeu gobblets) {
        App.gobblets = gobblets;
    }

    public Accueil getAccueil() {
        return accueil;
    }

    public void setAccueil(Accueil accueil) {
        this.accueil = accueil;
    }

    public JeuFx getJeu() {
        return jeu;
    }

    public void setJeu(JeuFx jeu) {
        this.jeu = jeu;
    }

    public Scene getAcceuilScene() {
        return acceuilScene;
    }

    public void setAcceuilScene(Scene acceuilScene) {
        this.acceuilScene = acceuilScene;
    }

    public Scene getJeuScene() {
        return jeuScene;
    }

    public void setJeuScene(Scene jeuScene) {
        this.jeuScene = jeuScene;
    }

    public Stage getCurrent() {
        return current;
    }

    public void setCurrent(Stage current) {
        this.current = current;
    }
}
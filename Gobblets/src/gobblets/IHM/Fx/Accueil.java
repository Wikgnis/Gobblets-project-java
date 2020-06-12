package gobblets.IHM.Fx;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import app.App;
import gobblets.IHM.Dictionnaire;
import gobblets.IHM.Menu;
import gobblets.IHM.langues.Allemand;
import gobblets.IHM.langues.Anglais;
import gobblets.IHM.langues.Francais;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Accueil extends AnchorPane {

    @FXML
    private AnchorPane main;
    @FXML
    private MenuBar menuTop;
    @FXML
    private Button jouer, quitter;
    @FXML
    private ImageView logo;
    @FXML
    private Text logoText;
    @FXML
    private RadioButton fr, en, de;

    private App master;
    private Dictionnaire langue;

    public Accueil(String pathFXML, App master) throws MalformedURLException {
        this.master = master;
        langue = new Francais();
        File accueilFile = new File(pathFXML + File.separator + "Accueil.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(accueilFile.toURI().toURL());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        // langue radio button
        setupLangue();
    }

    private void setupLangue() {
        ToggleGroup langue = new ToggleGroup();
        fr.setToggleGroup(langue);
        en.setToggleGroup(langue);
        de.setToggleGroup(langue);
        fr.setOnAction((event) -> {
            if (!(this.langue instanceof Francais)) {
                this.langue = new Francais();
            }
        });
        en.setOnAction((event) -> {
            if (!(this.langue instanceof Anglais)) {
                this.langue = new Anglais();
            }
        });
        de.setOnAction((event) -> {
            if (!(this.langue instanceof Allemand)) {
                this.langue = new Allemand();
            }
        });
    }

    @FXML
    protected void quitter() {
        System.exit(0);
    }

    @FXML
    protected void changeScene() {
        master.changeScene(Menu.MENU_NOUVEAU);
    }
}
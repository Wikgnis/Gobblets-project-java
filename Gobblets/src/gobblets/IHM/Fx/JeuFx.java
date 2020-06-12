package gobblets.IHM.Fx;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import app.App;
import gobblets.IHM.Dictionnaire;
import gobblets.IHM.langues.Francais;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class JeuFx extends AnchorPane {

    private App master;
    private Dictionnaire langue;

    public JeuFx(String pathFXML, App master) throws MalformedURLException {
        this.master = master;
        langue = new Francais();
        File accueilFile = new File(pathFXML + File.separator + "Jeu.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(accueilFile.toURI().toURL());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
package gobblets.IHM.Fx;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import app.App;
import gobblets.IHM.Dictionnaire;
import gobblets.IHM.Menu;
import gobblets.IHM.customException;
import gobblets.IHM.langues.Allemand;
import gobblets.IHM.langues.Anglais;
import gobblets.IHM.langues.Francais;
import gobblets.data.Piece;
import gobblets.data.Taille;
import gobblets.data.Couleur;
import gobblets.data.Joueur;
import gobblets.data.JoueurHumain;
import gobblets.data.JoueurIA;
import gobblets.logic.Jeu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class JeuFx extends AnchorPane {

    @FXML
    private RadioButton fr, en, de;
    @FXML
    private CheckBox j1IA, j2IA;
    @FXML
    private TextField j1NameEntry, j2NameEntry;
    @FXML
    private ChoiceBox j1Couleur, j2Couleur;
    @FXML
    private Text j1Name, j2Name, j2NbPionG, j2NbPionM, j2NbPionP, j1NbPionG, j1NbPionM, j1NbPionP;
    @FXML
    private AnchorPane start;
    @FXML
    private Label bEtat;
    @FXML
    private Circle g1, m1, p1, g2, m2, p2;
    @FXML
    private HBox c1, c2, c3, c4, c5, c6, c7, c8, c9;

    private HBox[][] plateau;

    private App master;
    private Dictionnaire langue;
    private Jeu jeu;

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
        setupLangue();
        setupColorChoices();
        // plateau
        plateau = new HBox[3][3];
        plateau[0][0] = c1;
        plateau[0][1] = c2;
        plateau[0][2] = c3;
        plateau[1][0] = c4;
        plateau[1][1] = c5;
        plateau[1][2] = c6;
        plateau[2][0] = c7;
        plateau[2][1] = c8;
        plateau[2][2] = c9;
        // maison
        // TODO
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

    private void setupColorChoices() {
        ObservableList<Object> colors = FXCollections.observableArrayList(Couleur.values());
        j1Couleur.getItems().setAll(colors);
        j2Couleur.getItems().setAll(colors);
    }

    @FXML
    protected void quitter() {
        System.exit(0);
    }

    @FXML
    protected void changeScene() throws IOException {
        master.changeScene(Menu.MENU_ACCEUIL);
    }

    @FXML
    protected void lancer() {
        try {
            Joueur j1, j2;
            if (j1IA.isSelected()) j1 = new JoueurIA(j1NameEntry.getText(), (Couleur)j1Couleur.getValue());
            else j1 = new JoueurHumain(j1NameEntry.getText(), (Couleur)j1Couleur.getValue());

            if (j2IA.isSelected()) j2 = new JoueurIA(j2NameEntry.getText(), (Couleur)j2Couleur.getValue());
            else j2 = new JoueurHumain(j2NameEntry.getText(), (Couleur)j2Couleur.getValue());

            if (j1 != null && j2 != null) jeu = new Jeu(j1, j2);
            master.setGobblets(jeu);
            Piece p = new Piece(Taille.GRANDE);
            p.setCouleur(Couleur.ROUGE);
            jeu.getPlateau().placePiece(p, 0, 0);
            j1Name.setText(j1.getNom());
            j2Name.setText(j2.getNom());
            start.setVisible(false);
            bEtat.setText("");
            g1.setFill(j1.getCouleur().getFxColor());
            m1.setFill(j1.getCouleur().getFxColor());
            p1.setFill(j1.getCouleur().getFxColor());
            g2.setFill(j2.getCouleur().getFxColor());
            m2.setFill(j2.getCouleur().getFxColor());
            p2.setFill(j2.getCouleur().getFxColor());
            update();
        } catch (Exception e) {
            if (e instanceof customException) bEtat.setText(langue.erreur(((customException) e).getE()) + " " + ((customException) e).getS());
            else bEtat.setText(e.getMessage());
        }
    }

    private void update() {
        // TODO
        bEtat.setText(jeu.getJoueurActif().getNom());
        /** update houses */
        int p, m, g;
        p = 0; m = 0; g = 0;
        /** j1 */
        for (Piece piece : jeu.getJ1().getPieces()) {
            switch (piece.getTaille()) {
                case PETITE:
                    p++;
                    break;
                case MOYENNE:
                    m++;
                    break;
                case GRANDE:
                    g++;
                    break;
                default:
                    break;
            }
        }
        j1NbPionG.setText(g + "");
        j1NbPionM.setText(m + "");
        j1NbPionP.setText(p + "");
        /** j2 */
        p = 0; m = 0; g = 0;
        for (Piece piece : jeu.getJ2().getPieces()) {
            switch (piece.getTaille()) {
                case PETITE:
                    p++;
                    break;
                case MOYENNE:
                    m++;
                    break;
                case GRANDE:
                    g++;
                    break;
                default:
                    break;
            }
        }
        j2NbPionG.setText(g + "");
        j2NbPionM.setText(m + "");
        j2NbPionP.setText(p + "");
        // plateau
        for (int i = 0; i < jeu.getPlateau().getPlateau().length; i++) {
            for (int j = 0; j < jeu.getPlateau().getPlateau()[i].length; j++) {
                plateau[i][j].getChildren().clear();
                if (jeu.getPlateau().getPlateau()[i][j].plusGrandePiece() != null) {
                    Circle c = new Circle();
                    switch (jeu.getPlateau().getPlateau()[i][j].plusGrandePiece().getTaille()) {
                    case GRANDE:
                        c.setRadius(60);
                        c.setFill(jeu.getPlateau().getPlateau()[i][j].plusGrandePiece().getCouleur().getFxColor());
                        plateau[i][j].getChildren().add(c);
                        break;
                    case MOYENNE:
                        c.setRadius(45);
                        c.setFill(jeu.getPlateau().getPlateau()[i][j].plusGrandePiece().getCouleur().getFxColor());
                        plateau[i][j].getChildren().add(c);
                        break;
                    case PETITE:
                        c.setRadius(35);
                        c.setFill(jeu.getPlateau().getPlateau()[i][j].plusGrandePiece().getCouleur().getFxColor());
                        plateau[i][j].getChildren().add(c);
                        break;
                    default:
                        break;
                }
                }
            }
        }
    }
}
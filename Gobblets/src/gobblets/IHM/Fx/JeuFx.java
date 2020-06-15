package gobblets.IHM.Fx;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;

import app.App;
import gobblets.IHM.Dictionnaire;
import gobblets.IHM.Menu;
import gobblets.IHM.customException;
import gobblets.IHM.Fx.controller.jeuListener;
import gobblets.IHM.langues.Allemand;
import gobblets.IHM.langues.Anglais;
import gobblets.IHM.langues.Francais;
import gobblets.data.Piece;
import gobblets.data.Taille;
import gobblets.data.Case;
import gobblets.data.Couleur;
import gobblets.data.Etat;
import gobblets.data.Joueur;
import gobblets.data.JoueurHumain;
import gobblets.data.JoueurIA;
import gobblets.logic.CaseBloqueeException;
import gobblets.logic.Jeu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
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
    private TextField j1NameEntry, j2NameEntry, saveText;
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
    @FXML
    private AnchorPane savePanel, end;

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
    }

    public JeuFx(String pathFXML, App master, Jeu jeu) throws MalformedURLException {
        this.jeu = jeu;
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
        start.setVisible(false);
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
            if (j1IA.isSelected())
                j1 = new JoueurIA(j1NameEntry.getText(), (Couleur) j1Couleur.getValue());
            else
                j1 = new JoueurHumain(j1NameEntry.getText(), (Couleur) j1Couleur.getValue());

            if (j2IA.isSelected())
                j2 = new JoueurIA(j2NameEntry.getText(), (Couleur) j2Couleur.getValue());
            else
                j2 = new JoueurHumain(j2NameEntry.getText(), (Couleur) j2Couleur.getValue());

            if (j1 != null && j2 != null)
                jeu = new Jeu(j1, j2);
            App.setGobblets(jeu);
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
            this.update();
        } catch (Exception e) {
            if (e instanceof customException)
                bEtat.setText(langue.erreur(((customException) e).getE()) + " " + ((customException) e).getS());
            else
                bEtat.setText(e.getMessage());
        }
    }

    private void update() {
        if (jeu.updateEtat(jeu.getEtat()) != Etat.JEUENCOURS) {
            end.setVisible(true);
        }
        // TODO
        bEtat.setText(jeu.getJoueurActif().getNom());
        /** update houses */
        int p, m, g;
        p = 0;
        m = 0;
        g = 0;
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
        p = 0;
        m = 0;
        g = 0;
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
        if (jeu.getJoueurActif() instanceof JoueurIA) {
            ((JoueurIA) jeu.getJoueurActif()).choisirAction(jeu.getPlateau());
            update();
        }
    }

    @FXML
    protected void save() {
        savePanel.setVisible(true);
    }

    @FXML
    protected void validerSave() {
        File save = new File("." + File.separator + "ressources" + File.separator + saveText.getText() + ".save");
        App.sauvegarder(save);
        savePanel.setVisible(false);
    }

    private Piece choice;
    private Circle dummy;

    @FXML
    protected void startChoice(MouseEvent evt) {
        String idTarget = evt.getTarget().toString();
        String joueur = idTarget.split("_")[1];
        String taille = idTarget.split("_")[2].replace("]", "");
        // debug
        System.out.println("target : " + idTarget);
        System.out.println("j : " + joueur);
        System.out.println("t : " + taille);
        // piece
        Joueur j = jeu.getJoueurActif();
        if ((jeu.getJ1() == j && joueur.equals("1")) || (jeu.getJ2() == j && joueur.equals("2"))) {
            Taille t = null;
            int size = 0;
            switch (taille) {
                case "G":
                    t = Taille.GRANDE;
                    size = 60;
                    break;
                case "M":
                    t = Taille.MOYENNE;
                    size = 45;
                    break;
                case "P":
                    t = Taille.PETITE;
                    size = 35;
                    break;
                default:
                    break;
            }
            try {
                if (j.aPieceDeTaille(t)) {
                    choice = j.enlevePiece(t);
                    dummy = new Circle(size);
                    this.getChildren().add(dummy);
                    dummy.setCenterX(evt.getSceneX());
                    dummy.setCenterY(evt.getSceneY());
                }
            } catch (Exception e) {
            }
        }
    }

    @FXML
    protected void makeChoice(MouseEvent evt) {
        if (dummy != null) {
            this.getChildren().remove(dummy);
            dummy = null;
            Point2D p = new Point2D(evt.getSceneX(), evt.getSceneY());
            HBox caseDrop = null;
            int x = 0, y = 0;
            for (int i = 0; i < plateau.length && caseDrop == null; i++) {
                for (int j = 0; j < plateau[i].length && caseDrop == null; j++) {
                     if (plateau[i][j].localToScene(plateau[i][j].getBoundsInLocal()).contains(p)) {
                        caseDrop = plateau[i][j];
                        x = i;
                        y = j;
                    }
                }
            }
            if (caseDrop == null) {
                try {
                    jeu.getJoueurActif().ajoutPiece(choice);
                } catch (Exception e) {}
            }
            else {
                try {
                    System.out.println(caseDrop);
                    jeu.getPlateau().placePiece(choice, x, y);
                    jeu.changeJoueur();
                    update();
                } catch (Exception e) {
                    bEtat.setText(e.getMessage());
                }
            }
        }
    }

    @FXML
    protected void moveDummy(MouseEvent evt) {
        if (dummy != null) {
            dummy.setCenterX(evt.getSceneX());
            dummy.setCenterY(evt.getSceneY());
        }
    }
}
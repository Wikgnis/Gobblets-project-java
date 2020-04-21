package app;

import java.util.Arrays;

import gobblets.IHM.*;
import gobblets.IHM.texte.*;
import gobblets.data.*;
import gobblets.logic.Jeu;

public class App {
    public static void main(String[] args) throws Exception {
        new App();
    }

    public App() {
        Jeu gobblets = new Jeu();
        System.out.println("Jeu initialisé :\n"+ gobblets);
        IHM ihm = new SaisieConsole();
        ihm.display(gobblets.getPlateau(), gobblets.getJoueurActif());
        System.out.println(Arrays.toString(ihm.saisirCoordonnees()));
    }
}
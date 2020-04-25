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
        try {
            jouer(gobblets, ihm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jouer(Jeu gobblets, IHM ihm) throws Exception {
        while (gobblets.getEtat() == Etat.JEUENCOURS) {
            System.out.println("Etat jeu : " + gobblets.getEtat());
            ihm.display(gobblets.getPlateau(), gobblets.getJoueurActif());
            gobblets.setEtat(gobblets.play());
        }
    }
}
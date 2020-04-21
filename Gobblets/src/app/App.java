package app;

import gobblets.data.*;
import gobblets.logic.Jeu;

public class App {
    public static void main(String[] args) throws Exception {
        new App();
    }

    public App() {
        Jeu gobblets = new Jeu();
        System.out.println(gobblets);
    }
}
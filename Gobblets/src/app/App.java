package app;

import gobblets.data.*;

public class App {
    public static void main(String[] args) throws Exception {
        for (Couleur c : Couleur.values()) {
            System.out.println(c);
        }
    }
}
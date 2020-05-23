package gobblets.IHM;

public enum Menu {
MENU_FICHIER,
MENU_OUVRIR,
MENU_ENREGISTRER,
MENU_NOUVEAU,
MENU_QUITTER,
MENU_AIDE,
MENU_APROPOS,
MENU_LANGUE,
MENU_ACCEUIL;

private Object dataLink;
Menu() {}

public Object getDataLink() {
    return dataLink;
}

public void setDataLink(Object dataLink) {
    this.dataLink = dataLink;
}
}
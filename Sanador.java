package rpgmanager.interfaces;

import rpgmanager.model.Personaje;

public interface Sanador {
    void sanar(Personaje objetivo);
    int getPotenciaSanacion();
}

package rpgmanager.interfaces;

import rpgmanager.model.Personaje;

public interface Habilidoso {
    void usarHabilidadEspecial(Personaje objetivo);
    int getCostoHabilidad();
    String getNombreHabilidad();
}

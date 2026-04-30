package rpgmanager.model;

import rpgmanager.interfaces.Habilidoso;
import rpgmanager.interfaces.Sanador;

public class Mago extends Personaje implements Habilidoso, Sanador {

    private int mana;
    private int manaMax;

    public Mago(String nombre, int nivel) {
        super(nombre, nivel, 60 + nivel * 5);
        this.manaMax = 80 + nivel * 10;
        this.mana    = this.manaMax;
    }

    @Override
    public void atacar(Personaje objetivo) {
        if (mana >= 20) {
            int dano = 25 + nivel * 5;
            System.out.println("🔮 " + nombre + " lanza un hechizo sobre " + objetivo.getNombre() + "! (-20 mana)");
            mana -= 20;
            objetivo.recibirDano(dano);
        } else {
            System.out.println("🔮 " + nombre + " no tiene suficiente mana para atacar. (mana: " + mana + ")");
        }
    }

    @Override
    public String getTipoPersonaje() { return "Mago"; }

    public void recuperarMana(int cantidad) {
        mana = Math.min(manaMax, mana + cantidad);
        System.out.println("✨ " + nombre + " recupera " + cantidad + " de mana. Mana: " + mana + "/" + manaMax);
    }

    // --- Habilidoso ---
    @Override
    public void usarHabilidadEspecial(Personaje objetivo) {
        if (mana >= getCostoHabilidad()) {
            System.out.println("🔥 " + nombre + " lanza ¡" + getNombreHabilidad() + "! sobre " + objetivo.getNombre() + "!");
            mana -= getCostoHabilidad();
            objetivo.recibirDano(40);
        } else {
            System.out.println("🔮 " + nombre + " no tiene mana para usar " + getNombreHabilidad() + ". (mana: " + mana + ")");
        }
    }

    @Override
    public int getCostoHabilidad() { return 20; }

    @Override
    public String getNombreHabilidad() { return "Bola de Fuego"; }

    // --- Sanador ---
    @Override
    public void sanar(Personaje objetivo) {
        objetivo.restaurarVida(getPotenciaSanacion());
        System.out.println("💚 " + nombre + " sana a " + objetivo.getNombre()
                + " por " + getPotenciaSanacion() + " HP. HP ahora: "
                + objetivo.getPuntosVida() + "/" + objetivo.getPuntosVidaMax());
    }

    @Override
    public int getPotenciaSanacion() { return 25; }
}

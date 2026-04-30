package rpgmanager;

import rpgmanager.interfaces.Equipable;
import rpgmanager.interfaces.Habilidoso;
import rpgmanager.interfaces.Sanador;
import rpgmanager.model.Arquero;
import rpgmanager.model.Guerrero;
import rpgmanager.model.Mago;
import rpgmanager.model.Personaje;

import java.util.ArrayList;

public class BatallaCompleta {

    public static void main(String[] args) {

        // ─── Crear personajes ───────────────────────────────────────────
        Guerrero thorin  = new Guerrero("Thorin",  3);
        Mago     gandalf = new Mago    ("Gandalf", 5);
        Arquero  legolas = new Arquero ("Legolas", 4);

        // Verificacion 2: imprimir personajes
        System.out.println("=== Estado inicial ===");
        System.out.println(thorin);
        System.out.println(gandalf);
        System.out.println(legolas);
        System.out.println();

        // ─── FASE 1: Equipar ────────────────────────────────────────────
        System.out.println("=== FASE 1: Equipamiento ===");
        ((Equipable) thorin).equiparItem("Espada Legendaria");
        ((Equipable) legolas).equiparItem("Arco Elfico");
        System.out.println();

        // ─── Crear heroes en ArrayList<Personaje> (polimorfismo) ────────
        ArrayList<Personaje> heroes = new ArrayList<>();
        heroes.add(thorin);
        heroes.add(gandalf);
        heroes.add(legolas);

        Personaje orco = new Guerrero("Orco", 1);

        // ─── FASE 2: Batalla por turnos ─────────────────────────────────
        System.out.println("=== FASE 2: Batalla ===");
        System.out.println("Enemigo: " + orco);
        System.out.println();

        int turno = 1;
        while (orco.estaVivo()) {
            System.out.println("--- Turno " + turno + " ---");

            for (Personaje h : heroes) {
                if (!orco.estaVivo()) break;

                // En turno 2: los Habilidosos usan su habilidad especial primero
                if (turno == 2 && h instanceof Habilidoso) {
                    ((Habilidoso) h).usarHabilidadEspecial(orco);
                } else {
                    h.atacar(orco);
                }
            }
            turno++;
        }

        System.out.println();
        System.out.println("🏆 ¡El Orco fue derrotado en " + (turno - 1) + " turno(s)!");
        System.out.println();

        // ─── FASE 3: Sanacion post-batalla ──────────────────────────────
        System.out.println("=== FASE 3: Sanacion post-batalla ===");

        // Buscar el heroe con menos vida
        Personaje masHerido = heroes.get(0);
        for (Personaje h : heroes) {
            if (h.getPuntosVida() < masHerido.getPuntosVida()) {
                masHerido = h;
            }
        }

        System.out.println("El heroe con menos vida es: " + masHerido.getNombre()
                + " (" + masHerido.getPuntosVida() + " HP)");

        for (Personaje h : heroes) {
            if (h instanceof Sanador) {
                ((Sanador) h).sanar(masHerido);
            }
        }

        // ─── Estado final ────────────────────────────────────────────────
        System.out.println();
        System.out.println("=== Estado final de los heroes ===");
        for (Personaje h : heroes) {
            System.out.println(h);
        }
        System.out.println(orco);
    }
}

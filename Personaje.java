package rpgmanager.model;

public abstract class Personaje {

    protected String nombre;
    protected int nivel;
    protected int puntosVida;
    protected int puntosVidaMax;

    public Personaje(String nombre, int nivel, int puntosVidaMax) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.puntosVidaMax = puntosVidaMax;
        this.puntosVida = puntosVidaMax;
    }

    // Metodo concreto: recibe daño sin bajar de 0
    public void recibirDano(int dano) {
        puntosVida = Math.max(0, puntosVida - dano);
        System.out.println("  💥 " + nombre + " recibe " + dano + " de daño. HP: " + puntosVida + "/" + puntosVidaMax);
    }

    // Metodo concreto: verifica si sigue vivo
    public boolean estaVivo() {
        return puntosVida > 0;
    }

    // Metodo para restaurar vida (usado por Sanador)
    public void restaurarVida(int cantidad) {
        puntosVida = Math.min(puntosVidaMax, puntosVida + cantidad);
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getNivel()     { return nivel; }
    public int getPuntosVida(){ return puntosVida; }
    public int getPuntosVidaMax() { return puntosVidaMax; }

    // Metodos abstractos
    public abstract void atacar(Personaje objetivo);
    public abstract String getTipoPersonaje();

    @Override
    public String toString() {
        return String.format("[%-7s] %-8s Nv.%d │ HP: %d/%d",
                getTipoPersonaje(), nombre, nivel, puntosVida, puntosVidaMax);
    }
}

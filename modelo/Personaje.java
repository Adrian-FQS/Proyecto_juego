package modelo;

public class Personaje {
    private final String nombre;
    private int fuerza;
    private int velocidad;
    private int vida;
    private boolean defendiendo;

    public Personaje(String nombre, int fuerza, int velocidad, int vida) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.velocidad = velocidad;
        this.vida = vida;
    }

    /** Ataca al objetivo y devuelve el dano real infligido (ya con defensa aplicada). */
    public int atacar(Personaje objetivo, int multiplicador) {
        int dano = (fuerza + velocidad) * multiplicador;
        if (objetivo.defendiendo) {
            dano /= 2;
            objetivo.defendiendo = false;
        }
        objetivo.vida = Math.max(0, objetivo.vida - dano);
        return dano;
    }

    public void defender()          { defendiendo = true; }
    public void curar(int cantidad) { vida += cantidad; }
    public void mejorar(int puntos) { fuerza += puntos; velocidad += puntos; }
    public boolean estaVivo()       { return vida > 0; }

    public String getNombre()       { return nombre; }
    public int getFuerza()          { return fuerza; }
    public int getVelocidad()       { return velocidad; }
    public int getVida()            { return vida; }
    public boolean isDefendiendo()  { return defendiendo; }
}

package datos;

import modelo.Personaje;

public final class Catalogo {

    private static final String[] HEROES_NOMBRES = {"Thunderman", "Furia Roja", "Sombra veloz"};
    private static final String[] VILLANOS_NOMBRES = {"Dr. Sombra", "Lord Toxico", "Cero Absoluto"};

    // Estadisticas: [Fuerza, Velocidad, Vida]
    private static final int[][] HEROES_STATS = {
        {10, 8, 100},
        {12, 6, 110},
        {8, 12, 90}
    };

    private static final int[][] VILLANOS_STATS = {
        {9, 7, 100},
        {11, 5, 120},
        {7, 11, 95}
    };

    private Catalogo() {}

    /** Crea una instancia nueva (con stats originales) del heroe indicado. */
    public static Personaje crearHeroe(int i) {
        return new Personaje(HEROES_NOMBRES[i],
                HEROES_STATS[i][0], HEROES_STATS[i][1], HEROES_STATS[i][2]);
    }

    public static Personaje crearVillano(int i) {
        return new Personaje(VILLANOS_NOMBRES[i],
                VILLANOS_STATS[i][0], VILLANOS_STATS[i][1], VILLANOS_STATS[i][2]);
    }
}

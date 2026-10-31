package combate;

import datos.Catalogo;
import datos.Constantes;
import modelo.Personaje;
import ui.Consola;

public class Simulacion {

    /** Simula los combates de los heroes y villanos que no participaron en el combate del jugador. */
    public static void simularOtros(int heroeJugador, int villanoRival, Consola ui) {
        ui.mostrarTituloOtrosCombates();

        int numCombate = 1;
        int j = 0;
        for (int i = 0; i < Constantes.CANTIDAD_PERSONAJES; i++) {
            if (i == heroeJugador) continue;

            // siguiente villano libre
            while (j == villanoRival) j++;

            Personaje h = Catalogo.crearHeroe(i);
            Personaje v = Catalogo.crearVillano(j);

            while (h.estaVivo() && v.estaVivo()) {
                h.atacar(v, 1);
                if (!v.estaVivo()) break;
                v.atacar(h, 1);
            }

            ui.mostrarCombateSimulado(numCombate, h.getNombre(), v.getNombre(), h.estaVivo());
            numCombate++;
            j++;
        }
    }
}

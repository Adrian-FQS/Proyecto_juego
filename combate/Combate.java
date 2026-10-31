package combate;

import datos.Constantes;
import modelo.Personaje;
import ui.Consola;

public class Combate {
    private final Personaje heroe;
    private final Personaje villano;
    private final Consola ui;

    private int numRonda = 1;          // contador
    private int danoTotalHeroe = 0;    // acumuladores
    private int danoTotalVillano = 0;

    public Combate(Personaje heroe, Personaje villano, Consola ui) {
        this.heroe = heroe;
        this.villano = villano;
        this.ui = ui;
    }

    public void jugar() {
        while (heroe.estaVivo() && villano.estaVivo()) {
            int eleccion = ui.pedirAccion(numRonda);

            boolean consumeTurno = turnoJugador(eleccion);
            if (!consumeTurno) continue;     // "Consultar Estado" no gasta turno

            if (!villano.estaVivo()) break;

            danoTotalVillano += TurnoEnemigo.ejecutar(villano, heroe, ui);
            numRonda++;
        }
        ui.mostrarResultadoFinal(heroe, villano, numRonda, danoTotalHeroe, danoTotalVillano);
    }

    /** Devuelve false si la accion no consume turno. */
    private boolean turnoJugador(int eleccion) {
        switch (eleccion) {
            case 1:
                ui.mostrarMensaje(heroe.getNombre() + " ataca a " + villano.getNombre() + ".");
                danoTotalHeroe += golpear(1);
                break;
            case 2:
                ui.mostrarMensaje("\n*** ¡" + heroe.getNombre() + " usa su superpoder especial! ***");
                ui.mostrarMensaje(heroe.getNombre() + " lanza un golpe potenciado (x3).");
                danoTotalHeroe += golpear(3);
                break;
            case 3:
                heroe.defender();
                ui.mostrarMensaje(heroe.getNombre() + " adopta una postura defensiva.");
                break;
            case 4:
                heroe.curar(Constantes.CURACION_VALOR);
                ui.mostrarMensaje(heroe.getNombre() + " recupera " + Constantes.CURACION_VALOR + " puntos de vida.");
                break;
            case 5:
                heroe.mejorar(Constantes.MEJORA_ATRIBUTO);
                ui.mostrarMensaje(heroe.getNombre() + " aumento sus atributos (+" + Constantes.MEJORA_ATRIBUTO
                    + " Fuerza / +" + Constantes.MEJORA_ATRIBUTO + " Velocidad).");
                break;
            case 6:
                ui.mostrarEstado(heroe);
                ui.mostrarEstado(villano);
                return false;
            default:
                ui.mostrarMensaje("Opcion no valida. Pierdes tu turno.");
                break;
        }
        return true;
    }

    private int golpear(int multiplicador) {
        boolean frenado = villano.isDefendiendo();
        int dano = heroe.atacar(villano, multiplicador);
        ui.mostrarGolpe(heroe, villano, dano, frenado);
        return dano;
    }
}

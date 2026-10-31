package combate;

import datos.Constantes;
import modelo.Personaje;
import ui.Consola;

public class TurnoEnemigo {

    /** Ejecuta una accion aleatoria del villano. Devuelve el dano infligido al heroe. */
    public static int ejecutar(Personaje villano, Personaje heroe, Consola ui) {
        ui.mostrarMensaje("\nTurno del enemigo...");
        int accion = (int) (Math.random() * 5);
        int dano = 0;

        switch (accion) {
            case 0:
                ui.mostrarMensaje(villano.getNombre() + " ataca a " + heroe.getNombre() + ".");
                dano = golpear(villano, heroe, 1, ui);
                break;
            case 1:
                ui.mostrarMensaje("\n¡" + villano.getNombre() + " hace un movimiento sucio!");
                ui.mostrarMensaje(villano.getNombre() + " lanza un golpe potenciado (x2).");
                dano = golpear(villano, heroe, 2, ui);
                break;
            case 2:
                villano.defender();
                ui.mostrarMensaje(villano.getNombre() + " adopta una postura defensiva.");
                break;
            case 3:
                villano.curar(Constantes.CURACION_VALOR);
                ui.mostrarMensaje(villano.getNombre() + " recupera " + Constantes.CURACION_VALOR + " puntos de vida.");
                break;
            case 4:
                villano.mejorar(Constantes.MEJORA_ATRIBUTO);
                ui.mostrarMensaje(villano.getNombre() + " aumento sus atributos (+" + Constantes.MEJORA_ATRIBUTO
                    + " Fuerza / +" + Constantes.MEJORA_ATRIBUTO + " Velocidad).");
                break;
        }
        return dano;
    }

    private static int golpear(Personaje atacante, Personaje objetivo, int mult, Consola ui) {
        boolean frenado = objetivo.isDefendiendo();
        int dano = atacante.atacar(objetivo, mult);
        ui.mostrarGolpe(atacante, objetivo, dano, frenado);
        return dano;
    }
}

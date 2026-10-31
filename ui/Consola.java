package ui;

import java.util.Scanner;
import datos.Catalogo;
import datos.Constantes;
import modelo.Personaje;

public class Consola {
    private static final String LINEA = "========================================";
    private final Scanner in = new Scanner(System.in);

    public int seleccionarHeroe() {
        System.out.println(LINEA);
        System.out.println("         SELECCIONA TU HEROE            ");
        System.out.println(LINEA);

        for (int i = 0; i < Constantes.CANTIDAD_PERSONAJES; i++) {
            Personaje h = Catalogo.crearHeroe(i);
            System.out.println((i + 1) + ". " + h.getNombre()
                + " | HP: " + h.getVida()
                + " | Fuerza: " + h.getFuerza()
                + " | Velocidad: " + h.getVelocidad());
        }

        System.out.print("\nOpcion (1-" + Constantes.CANTIDAD_PERSONAJES + "): ");
        int opcion = in.nextInt() - 1;

        if (opcion < 0 || opcion >= Constantes.CANTIDAD_PERSONAJES) {
            System.out.println("Opcion invalida, se asignara a " + Catalogo.crearHeroe(0).getNombre() + " por defecto.");
            opcion = 0;
        }
        return opcion;
    }

    public void mostrarEmparejamiento(Personaje heroe, Personaje villano) {
        System.out.println("\nHas elegido a: " + heroe.getNombre());
        System.out.println("¡Te ha tocado combatir contra: " + villano.getNombre() + "!");
    }

    public int pedirAccion(int ronda) {
        System.out.println("\n----------------------------------------");
        System.out.println("RONDA " + ronda);
        System.out.println("1. Atacar");
        System.out.println("2. Ataque Especial");
        System.out.println("3. Defender");
        System.out.println("4. Curarse");
        System.out.println("5. Mejorar Estadisticas");
        System.out.println("6. Consultar Estado");
        System.out.print("Accion: ");
        return in.nextInt();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarGolpe(Personaje atacante, Personaje objetivo, int dano, boolean frenado) {
        if (frenado) {
            System.out.println(objetivo.getNombre() + " frena parte del golpe y solo recibe " + dano + " de dano.");
        } else {
            System.out.println(objetivo.getNombre() + " recibe " + dano + " de dano.");
        }
    }

    public void mostrarEstado(Personaje p) {
        System.out.println("[" + p.getNombre() + "] -> HP: " + p.getVida()
            + " | Fuerza: " + p.getFuerza() + " | Velocidad: " + p.getVelocidad());
    }

    public void mostrarResultadoFinal(Personaje heroe, Personaje villano, int rondas,
                                      int danoHeroe, int danoVillano) {
        System.out.println("\n" + LINEA);
        System.out.println("         RESULTADO DE TU BATALLA        ");
        System.out.println(LINEA);

        if (heroe.estaVivo()) {
            System.out.println("¡GANASTE! " + heroe.getNombre() + " derroto a " + villano.getNombre() + ".");
        } else {
            System.out.println("PERDISTE... " + villano.getNombre() + " vencio a " + heroe.getNombre() + ".");
        }

        System.out.println("\nEstadisticas de tu combate:");
        System.out.println(" - Rondas jugadas: " + rondas);
        System.out.println(" - Dano total por " + heroe.getNombre() + ": " + danoHeroe);
        System.out.println(" - Dano total por " + villano.getNombre() + ": " + danoVillano);
    }

    public void mostrarTituloOtrosCombates() {
        System.out.println("\n" + LINEA);
        System.out.println("   RESULTADOS DE LOS OTROS COMBATES     ");
        System.out.println(LINEA);
    }

    public void mostrarCombateSimulado(int numero, String heroe, String villano, boolean ganaHeroe) {
        System.out.println("COMBATE " + numero + ":");
        System.out.println("----------------------------------------");
        System.out.println(" Heroe:   " + heroe);
        System.out.println(" Villano: " + villano);
        System.out.println("----------------------------------------");
        if (ganaHeroe) {
            System.out.println(" RESULTADO: GANADOR -> Heroe (" + heroe + ")");
            System.out.println("            PERDEDOR -> Villano (" + villano + ")");
        } else {
            System.out.println(" RESULTADO: GANADOR -> Villano (" + villano + ")");
            System.out.println("            PERDEDOR -> Heroe (" + heroe + ")");
        }
        System.out.println(LINEA + "\n");
    }

    public void cerrar() {
        in.close();
    }
}

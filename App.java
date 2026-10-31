import combate.Combate;
import combate.Simulacion;
import datos.Catalogo;
import datos.Constantes;
import modelo.Personaje;
import ui.Consola;

public class App {
    public static void main(String[] args) {
        Consola ui = new Consola();

        int opcionHeroe = ui.seleccionarHeroe();
        int indexVillano = (int) (Math.random() * Constantes.CANTIDAD_PERSONAJES);

        Personaje heroe = Catalogo.crearHeroe(opcionHeroe);
        Personaje villano = Catalogo.crearVillano(indexVillano);

        ui.mostrarEmparejamiento(heroe, villano);

        new Combate(heroe, villano, ui).jugar();
        Simulacion.simularOtros(opcionHeroe, indexVillano, ui);

        ui.cerrar();
    }
}

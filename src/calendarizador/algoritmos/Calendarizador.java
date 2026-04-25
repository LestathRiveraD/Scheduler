package calendarizador;

import java.util.List;
import 

public interface Calendarizador {
    String getNombre();
    PCB seleccionarProceso(List<PCB> colaListos, int tiempoActual);
    boolean esApropiativo();
    boolean debeExpulsar(PCB enEjecucion, List<PCB> colaListos, int tiempoActual);
}

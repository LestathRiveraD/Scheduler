package calendarizador.algoritmos;

import calendarizador.modelo.PCB;
import java.util.List;


public class CalendarizadorFCFS implements Calendarizador {

    @Override
    public String getNombre() {
        return "First Come First Served (FCFS)";
    }


     // Selecciona el primer proceso en la lista de listos.
     // Al usar una LinkedList en GestorColas, el índice 0 siempre representa al proceso que lleva más tiempo esperando.
    @Override
    public PCB seleccionarProceso(List<PCB> colaListos, int tiempoActual) {
        if (colaListos == null || colaListos.isEmpty()) {
            return null;
        }
        // tomamos el primer elemento disponible.
        return colaListos.get(0);
    }

    @Override
    public boolean esApropiativo() {
        // FCFS es un algoritmo no apropiativo
        return false;
    }

    @Override
    public boolean debeExpulsar(PCB enEjecucion, List<PCB> colaListos, int tiempoActual) {
        // el proceso en ejecución nunca es expulsado por otro.
        return false;
    }
}
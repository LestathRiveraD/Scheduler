package algoritmos;

import java.util.List;
import algoritmos.*;
import modelo.*;
import simulacion.*; 

public class CalendarizadorFCFS implements Calendarizador {

    @Override
    public String getNombre() {
        return "FCFS";
    }

     // Selecciona el primer proceso en la lista de listos
    @Override
    public PCB seleccionarProceso(List<PCB> colaListos, int tiempoActual) {
        if (colaListos == null || colaListos.isEmpty()) {
            return null;
        }
        // toma el primer elemento disponible.
        return colaListos.get(0);
    }

    @Override
    public boolean esApropiativo() {
        return false;
    }

    @Override
    public boolean debeExpulsar(PCB enEjecucion, List<PCB> colaListos, int tiempoActual) {
        // el proceso en ejecución nunca es expulsado por otro.
        return false;
    }
}

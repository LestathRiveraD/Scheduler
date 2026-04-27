package calendarizador.algoritmos;

import java.util.Iterator;
import java.util.List;
import calendarizador.modelo.PCB;

public class CalendarizadorSJF implements Calendarizador {

    @Override
    public String getNombre() {
        return "SJF";
    }

    @Override
    public PCB seleccionarProceso(List<PCB> colaListos, int tiempoActual) {
        if (colaListos == null || colaListos.isEmpty()) {
            return null;
        }

        PCB seleccionado = colaListos.get(0);

        for (PCB proceso : colaListos) {
            if (proceso.getTiempoRestante() < seleccionado.getTiempoRestante()) {
                seleccionado = proceso;
            }
            else if (proceso.getTiempoRestante() == seleccionado.getTiempoRestante()) {
                // desempate por PID
                if (proceso.getPid() < seleccionado.getPid()) {
                    seleccionado = proceso;
                }
            }
        }

        return seleccionado;
    }

    @Override
    public boolean esApropiativo() {
        return false;
    }

    @Override
    public boolean debeExpulsar(PCB enEjecucion, List<PCB> colaListos, int tiempoActual) {
        return false;
    }
}
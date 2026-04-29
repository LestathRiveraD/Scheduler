package algoritmos;

import java.util.List;
import modelo.PCB;

public class CalendarizadorSRTF implements Calendarizador {

    @Override
    public String getNombre() {
        return "SRTF";
    }

    // Selecciona el proceso con el menor tiempo restante en la cola de listos
    @Override
    public PCB seleccionarProceso(List<PCB> colaListos, int tiempoActual) {
        if (colaListos == null || colaListos.isEmpty()) {
            return null;
        }

        PCB seleccionado = colaListos.get(0);

        for (PCB proceso : colaListos) {
            // Menor tiempo restante
            if (proceso.getTiempoRestante() < seleccionado.getTiempoRestante()) {
                seleccionado = proceso;
            }
            // Menor PID en caso de empate
            else if (proceso.getTiempoRestante() == seleccionado.getTiempoRestante()) {
                if (proceso.getPid() < seleccionado.getPid()) {
                    seleccionado = proceso;
                }
            }
        }
        return seleccionado;
    }

    @Override
    public boolean esApropiativo() {
        // Permite la expulsion del proceso actual si llega uno mas corto
        return true;
    }

    // Evalua si el proceso actual debe ser expulsado de la CPU
    @Override
    public boolean debeExpulsar(PCB enEjecucion, List<PCB> colaListos, int tiempoActual) {
        if (enEjecucion == null || colaListos == null || colaListos.isEmpty()) {
            return false;
        }

        for (PCB proceso : colaListos) {
            if (proceso.getTiempoRestante() < enEjecucion.getTiempoRestante()) {
                // Si se encuentra un proceso mas corto, se solicita expulsion
                return true;
            }
        }
        return false;
    }
}

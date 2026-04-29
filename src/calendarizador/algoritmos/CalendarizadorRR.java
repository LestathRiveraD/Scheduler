package algoritmos;

import java.util.Iterator;
import java.util.List;
import modelo.PCB;

public class CalendarizadorRR implements Calendarizador {
    private int quantum = 1;
    private int curQuantum = 1;

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
        this.curQuantum = quantum;
    }

    @Override
    public String getNombre() {
        return "RR";
    }

    @Override
    public PCB seleccionarProceso(List<PCB> colaListos, int tiempoActual) {
        if (colaListos == null || colaListos.isEmpty())
            return null;
        return colaListos.get(0);
    }

    @Override
    public boolean esApropiativo() {
        return true;
    }

    @Override
    public boolean debeExpulsar(PCB enEjecucion, List<PCB> colaListos, int tiempoActual) {
        curQuantum--;

        // Si el quantum ha expirado, movemos proceso al final de la fila
        if (curQuantum <= 0 && !colaListos.isEmpty()) {
            curQuantum = quantum;
            colaListos.add(colaListos.remove(0));
            return true;
        }
        return false;
    }
}
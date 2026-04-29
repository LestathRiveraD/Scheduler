package algoritmos;

import java.util.Iterator;
import java.util.List;
import modelo.PCB;

public class CalendarizadorPrioridades implements Calendarizador {

    public CalendarizadorPrioridades(int intervalo) {
        this.intervalo = intervalo;
    }
    private int intervalo;

    public int getIntervalo()
    {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    private int getPrioridadEfectiva(int aging, int prioridad)
    {
        return Math.max(1, prioridad - aging / intervalo);
    }

    @Override
    public String getNombre() {
        return "Prioridades con Aging";
    }

    @Override
    public PCB seleccionarProceso(List<PCB> colaListos, int tiempoActual) {
        if (colaListos == null || colaListos.isEmpty()) {
            return null;
        }


        PCB seleccionado = colaListos.get(0);

        for (PCB proceso : colaListos) {
            proceso.setContadorAging(proceso.getContadorAging()+1);

            if (getPrioridadEfectiva(proceso.getContadorAging(), proceso.getPrioridad()) <
                    getPrioridadEfectiva(seleccionado.getContadorAging(), seleccionado.getPrioridad())) {
                seleccionado = proceso;
            }
            else if (getPrioridadEfectiva(proceso.getContadorAging(), proceso.getPrioridad()) ==
                    getPrioridadEfectiva(seleccionado.getContadorAging(), seleccionado.getPrioridad())) {
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
        return true;
    }

    @Override
    public boolean debeExpulsar(PCB enEjecucion, List<PCB> colaListos, int tiempoActual) {
        if (colaListos == null || colaListos.isEmpty()) {
            return false;
        }
        PCB seleccionado = colaListos.get(0);

        for (PCB proceso : colaListos) {
            if (getPrioridadEfectiva(proceso.getContadorAging(), proceso.getPrioridad()) <
                    getPrioridadEfectiva(enEjecucion.getContadorAging(), enEjecucion.getPrioridad())) {
                return true;
            }
        }
        return false;
    }
}
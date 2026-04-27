package simulacion;

import java.util.PriorityQueue;
import java.util.Queue;
import modelo.PCB;

public class GestorColas(String[] args)
{
    private Queue<PCB> nuevos = new LinkedList<PCB>();
    private Queue<PCB> listos = new LinkedList<PCB>();
    private Queue<PCB> bloqueados = new LinkedList<PCB>();
    private Queue<PCB> terminados = new LinkedList<PCB>();
    private PCB procesoActual = null;

    // ===== GETTERS =====
    public Queue<PCB> getNuevos() {
        return nuevos;
    }

    public Queue<PCB> getListos() {
        return listos;
    }

    public Queue<PCB> getBloqueados() {
        return bloqueados;
    }

    public Queue<PCB> getTerminados() {
        return terminados;
    }

    public PCB getProcesoActual() {
        return procesoActual;
    }

    // ===== SETTERS =====
    public void setNuevos(Queue<PCB> nuevos) {
        this.nuevos = nuevos;
    }

    public void setListos(Queue<PCB> listos) {
        this.listos = listos;
    }

    public void setBloqueados(Queue<PCB> bloqueados) {
        this.bloqueados = bloqueados;
    }

    public void setTerminados(Queue<PCB> terminados) {
        this.terminados = terminados;
    }

    public void setProcesoActual(PCB procesoActual) {
        this.procesoActual = procesoActual;
    }

    @Override
    public String toString() {
    return      "  Estado      | PID\n" + 
                "  Nuevos      | " + Nuevos + "\n" +
                "  Listos      | " + Listos + "\n" +
                "  Bloqueados  | " + Bloqueados + "\n" +
                "  Terminados  | " + Terminados + "\n";
    }
} 

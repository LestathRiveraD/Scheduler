package simulacion;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import modelo.PCB;

public class GestorColas
{
    private Queue<PCB> nuevos = new LinkedList<PCB>();
    private Queue<PCB> listos = new LinkedList<PCB>();
    private Queue<PCB> bloqueados = new LinkedList<PCB>();
    private Queue<PCB> terminados = new LinkedList<PCB>();
    private PCB procesoActual = null;

    // Metodo para el GanttRenderer
    public  PCB getProcesoActual() {
        return procesoActual;
    }

    public void setProcesoActual(PCB proceso) {
        this.processoActual = proceso;
    }

    @Override
    public String toString() {
    return      "  Estado      | PID\n" + 
                "  Nuevos      | " + nuevos + "\n" +
                "  Listos      | " + listos + "\n" +
                "  Bloqueados  | " + bloqueados + "\n" +
                "  Terminados  | " + terminados + "\n";
    }
} 

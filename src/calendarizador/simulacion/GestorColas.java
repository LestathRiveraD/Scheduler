package simulacion;

import java.util.PriorityQueue;
import java.util.Queue;
import modelo.PCB;

public class GestorColas(String[] args)
{
    Queue<PCB> nuevos = new LinkedList<PCB>();
    Queue<PCB> listos = new LinkedList<PCB>();
    Queue<PCB> bloqueados = new LinkedList<PCB>();
    Queue<PCB> terminados = new LinkedList<PCB>();
    PCB procesoActual = null;

    @Override
    public String toString() {
    return      "  Estado      | PID\n" + 
                "  Nuevos      | " + Nuevos + "\n" +
                "  Listos      | " + Listos + "\n" +
                "  Bloqueados  | " + Bloqueados + "\n" +
                "  Terminados  | " + Terminados + "\n";
    }
} 

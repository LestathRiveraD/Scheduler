package simulacion;
import java.util.*;
import Calendarizador
import GestorColas

public class Simulador implements Calendarizador
{
    private Calendarizador calendarizador;
    private GestorColas gestorColas
    private current_tick;
    public Simulador(Calendarizador calendarizador, GestorColas gestorColas)
    {
        this.calendarizador = calendarizador;
        this.gestorColas = gestorColas;
        current_tick = 0;
    }

    public void tick()
    {
        //Verificar si llegan nuevos procesos y admitirlos en la cola de listo
        private Queue<PCB> nuevos = gestorColas.getNuevos();
        Iterator<PCB> iterator = pq.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }

}
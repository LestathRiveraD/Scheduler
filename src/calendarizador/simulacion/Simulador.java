package simulacion;

import java.util.*;
import calendarizador.Calendarizador;
import simulacion.GestorColas;

public class Simulador
{
    private Calendarizador calendarizador;
    private GestorColas gestorColas;
    private int current_tick;
    private boolean paso_a_paso = false;
    private Scanner scanner = new Scanner(System.in);
    public Simulador(Calendarizador calendarizador, GestorColas gestorColas, int paso_a_paso)
    {
        this.calendarizador = calendarizador;
        this.gestorColas = gestorColas;
        current_tick = 0;
    }

    public boolean getIs_Paso_a_Paso()
    {
        return this.paso_a_paso;
    }

    public void setIs_Paso_a_Paso(boolean paso_a_paso)
    {
        this.paso_a_paso = paso_a_paso;
    }
    
    public void tick()
    {
        PCB procesoActual = gestorColas.getProcesoActual();

        Queue<PCB> nuevos = gestorColas.getNuevos();
        Queue<PCB> listos = gestorColas.getListos();
        Queue<PCB> bloqueados = gestorColas.getBloqueados();
        Queue<PCB> terminados = gestorColas.getTerminados();

        // 1. Llegadas
        Iterator<PCB> iterator = nuevos.iterator();
        while (iterator.hasNext()) {
            PCB process = iterator.next();
            if (process.getTiempoLlegada() == current_tick)
            {
                process.listo();
                listos.add(process);
                iterator.remove(); // ✔️ correcto
            }
        }

        // 2. Desbloqueo
        iterator = bloqueados.iterator();
        while (iterator.hasNext()) {
            PCB process = iterator.next();
            if (process.getTiempoRestante() == 0)
            {
                iterator.remove();
                listos.add(process);
                process.desbloquear();
            }
            else
            {
                process.setTiempoRestante(process.getTiempoRestante() - 1);
            }
        }

        // 3. Expulsión
        if (procesoActual != null && calendarizador.esApropiativo()) {
            if (calendarizador.debeExpulsar(procesoActual, listos, current_tick)) {
                procesoActual.listo();
                listos.add(procesoActual);
                gestorColas.setProcesoActual(null);
                procesoActual = null;
            }
        }

        // 4. Selección
        if (procesoActual == null && !listos.isEmpty()) {
            gestorColas.setProcesoActual(
                    calendarizador.seleccionarProceso(listos, current_tick)
            );
            procesoActual = gestorColas.getProcesoActual();

            listos.remove(procesoActual);
            procesoActual.ejecutando();

            // ✔️ Solo la primera vez
            if (procesoActual.getTiempoInicio() == 0) {
                procesoActual.setTiempoInicio(current_tick);
            }
        }

        // 5. Ejecutar CPU
        if (procesoActual != null) {
            procesoActual.setTiempoRestante(
                    procesoActual.getTiempoRestante() - 1
            );
        }

        // 6. Terminación (CORRECTA)
        if (procesoActual != null && procesoActual.getTiempoRestante() == 0)
        {
            procesoActual.terminar();
            procesoActual.setTiempoFin(current_tick + 1);
            procesoActual.setTiempoRetorno(
                    procesoActual.getTiempoFin() - procesoActual.getTiempoLlegada()
            );

            terminados.add(procesoActual); // ✔️ ahora sí correcto
            gestorColas.setProcesoActual(null);
            procesoActual = null;
        }

        // 7. Tiempo de espera
        for (PCB p : listos) {
            p.setTiempoEspera(p.getTiempoEspera() + 1);
        }

        // 8. GANTT no se

        // 9. Estado
        System.out.println(gestorColas.toString());

        if (getIs_Paso_a_Paso())
        {
            System.out.println("Pulse ENTER para ir al siguiente tick.");
            scanner.nextLine();
        }
        current_tick++; // ✔️

    }

}

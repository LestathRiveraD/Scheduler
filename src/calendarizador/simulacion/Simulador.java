package simulacion;

import java.util.*;
import algoritmos.*;
import simulacion.*;
import modelo.*;

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
        PCB procesoEnCPU = gestorColas.getProcesoActual();

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
        if (procesoEnCPU != null && calendarizador.esApropiativo()) {
            if (calendarizador.debeExpulsar(procesoEnCPU, listos, current_tick)) {
                procesoEnCPU.listo();
                listos.add(procesoEnCPU);
                gestorColas.setProcesoActual(null);
                procesoEnCPU = null;
            }
        }

        // 4. Selección
        if (procesoEnCPU == null && !listos.isEmpty()) {
            procesoEnCPU = calendarizador.seleccionarProceso(listos, current_tick);
            gestorColas.setProcesoActual(procesoEnCPU);
            listos.remove(procesoEnCPU);
            procesoEnCPU.ejecutando();

            if (procesoEnCPU.getTiempoInicio() == -1) {
                procesoEnCPU.setTiempoInicio(current_tick);
            }
        }

        // 5. Ejecutar CPU
        if (procesoEnCPU != null) {
            procesoEnCPU.setTiempoRestante(procesoEnCPU.getTiempoRestante() -1);
        }

        // 6. Terminación (CORRECTA)
        if (procesoEnCPU != null && procesoEnCPU.getTiempoRestante() == 0)
        {
            procesoEnCPU.terminar();
            procesoEnCPU.setTiempoFin(current_tick + 1);
            procesoEnCPU.setTiempoRetorno(procesoEnCPU.getTiempoFin() - procesoEnCPU.getTiempoLlegada());
            terminados.add(procesoEnCPU); // ✔️ ahora sí correcto
            gestorColas.setProcesoActual(null);
            // Se guarda la referencia para el Gantt antes de limpiar
            String nombreFinalizado = procesoEnCPU.getNombre();
            ganttRenderer.registrarTick(nombreFinalizado);
            procesoEnCPU = null;
        } else {
            // Registro en Gantt
            if (procesoEnCPU != null) {
                ganttRenderer.registrarTick(procesoEnCPU.getNombre());
            } else {
                ganttRenderer.registrarTick(null); // CPU ociosa
            }
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

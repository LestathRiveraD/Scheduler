package simulacion;

import java.util.*;
import algoritmos.*;
import modelo.*;

public class Simulador {

    private GanttRenderer renderer;
    private Calendarizador calendarizador;
    private GestorColas gestorColas;
    private int current_tick;
    private boolean paso_a_paso = false;
    private Scanner scanner = new Scanner(System.in);

    public Simulador(Calendarizador calendarizador, GestorColas gestorColas, int paso_a_paso) {
        this.calendarizador = calendarizador;
        this.gestorColas = gestorColas;
        this.current_tick = 0;
        this.renderer = new GanttRenderer();
    }

    public void setIs_Paso_a_Paso(boolean paso_a_paso) {
        this.paso_a_paso = paso_a_paso;
    }

    public void tick() {

        PCB procesoEnCPU = gestorColas.getProcesoActual();
        Queue<PCB> nuevos = gestorColas.getNuevos();
        Queue<PCB> listos = gestorColas.getListos();
        Queue<PCB> bloqueados = gestorColas.getBloqueados();
        Queue<PCB> terminados = gestorColas.getTerminados();

        // 1. Llegadas
        Iterator<PCB> it = nuevos.iterator();
        while (it.hasNext()) {
            PCB p = it.next();
            if (p.getTiempoLlegada() <= current_tick) {
                p.listo();
                listos.add(p);
                it.remove();
            }
        }

        // 2. Desbloqueo (simplificado)
        it = bloqueados.iterator();
        while (it.hasNext()) {
            PCB p = it.next();
            p.setTiempoRestante(p.getTiempoRestante() - 1);

            if (p.getTiempoRestante() <= 0) {
                it.remove();
                p.desbloquear();
                listos.add(p);
            }
        }

        // 3. Expulsión
        if (procesoEnCPU != null && calendarizador.esApropiativo()) {
            if (calendarizador.debeExpulsar(procesoEnCPU, new ArrayList<>(listos), current_tick)) {
                procesoEnCPU.listo();
                listos.add(procesoEnCPU);
                gestorColas.setProcesoActual(null);
                procesoEnCPU = null;
            }
        }

        // 4. Selección
        if (procesoEnCPU == null && !listos.isEmpty()) {
            procesoEnCPU = calendarizador.seleccionarProceso(new ArrayList<>(listos), current_tick);
            listos.remove(procesoEnCPU);
            gestorColas.setProcesoActual(procesoEnCPU);
            procesoEnCPU.ejecutando();

            if (procesoEnCPU.getTiempoInicio() == -1) {
                procesoEnCPU.setTiempoInicio(current_tick);
            }
        }

        // 5. Ejecutar CPU
        if (procesoEnCPU != null) {
            procesoEnCPU.setTiempoRestante(procesoEnCPU.getTiempoRestante() - 1);
        }

        // 6. Terminación
        if (procesoEnCPU != null && procesoEnCPU.getTiempoRestante() <= 0) {

            procesoEnCPU.terminar();
            procesoEnCPU.setTiempoFin(current_tick + 1);
            procesoEnCPU.setTiempoRetorno(
                    procesoEnCPU.getTiempoFin() - procesoEnCPU.getTiempoLlegada()
            );

            terminados.add(procesoEnCPU);
            renderer.registrarTick(procesoEnCPU.getNombre());

            gestorColas.setProcesoActual(null);
            procesoEnCPU = null;

        } else {
            renderer.registrarTick(procesoEnCPU != null ? procesoEnCPU.getNombre() : null);
        }

        // 7. Espera
        for (PCB p : listos) {
            p.setTiempoEspera(p.getTiempoEspera() + 1);
        }

        // 8. Mostrar estado
        System.out.println("Tick: " + current_tick);
        System.out.println(gestorColas);

        // 9. Paso a paso
        if (paso_a_paso) {
            System.out.println("ENTER para continuar...");
            scanner.nextLine();
        }

        current_tick++;
    }

    public boolean terminado() {
        return gestorColas.getNuevos().isEmpty() &&
                gestorColas.getListos().isEmpty() &&
                gestorColas.getBloqueados().isEmpty() &&
                gestorColas.getProcesoActual() == null;
    }

    public void ejecutar() {
        while (!terminado()) {
            tick();
        }

        renderer.mostrarDiagrama();
    }
}
package simulacion;

import java.util.ArrayList;
import java.util.List;

public class GanttRenderer {
    // Lista para guardar el nombre o PID del proceso ejecutado en cada segundo
    private List<String> historialEjecucion;

    public GanttRenderer() {
        this.historialEjecucion = new ArrayList<>();
    }

    // Registra que proceso ocupo la CPU en un tick especifico
    // Si no hay proceso, se puede pasar NULL
    public void registrarTick(String nombreProceso) {
        if (nombreProceso == null) {
            historialEjecucion.add("--"); // Representacion de CPU ociosa
        } else {
            historialEjecucion.add(nombreProceso);
        }
    }

    // Imprime el diagrama de Gantt en consola al finalizar la simulacion
    public void mostrarDiagrama() {
        if (historialEjecucion.isEmpty()) return;

        System.out.println("\n--- Diagrama de Gantt ---");

        // Fila de tiempos
        System.out.print("t= ");
        for (int i = 0; i < historialEjecucion.size(); i++) {
            System.out.printf("%-3d", i);
        }
        System.out.println();

        // Fila de procesos
        System.out.print("   ");
        for (String p : historialEjecucion) {
            System.out.printf("%-3s", p);
        }
        System.out.println("\n");
    }
}

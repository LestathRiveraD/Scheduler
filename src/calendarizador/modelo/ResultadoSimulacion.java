package modelo;

import java.util.*;

public class ResultadoSimulacion {
    private final List<PCB> procesosTerminados;
    private final int totalTicks;
    private final int ticksOcupados;
    private final int totalCambiosContexto;

    public ResultadoSimulacion(List<PCB> terminados, int totalTicks, int ticksOcupados, int cambiosContexto) {
        this.procesosTerminados = new ArrayList<>(terminados);
        this.totalTicks = totalTicks;
        this.ticksOcupados = ticksOcupados;
        this.totalCambiosContexto = cambiosContexto;
        // Ordenar por PID para que la tabla sea consistente
        this.procesosTerminados.sort(Comparator.comparingInt(PCB::getPid));
    }

    public void imprimirReporte() {
        System.out.println("\n" + "=".repeat(95));
        System.out.printf("%-5s | %-10s | %-8s | %-7s | %-7s | %-5s | %-7s | %-8s\n",
                "PID", "Nombre", "Llegada", "Ráfaga", "Inicio", "Fin", "Espera", "Retorno");
        System.out.println("-".repeat(95));

        double sumaEspera = 0;
        double sumaRetorno = 0;

        for (PCB p : procesosTerminados) {
            // Aseguramos el cálculo del retorno: fin - llegada
            int retorno = p.getTiempoFin() - p.getTiempoLlegada();
            p.setTiempoRetorno(retorno);

            System.out.printf("%-5d | %-10s | %-8d | %-7d | %-7d | %-5d | %-7d | %-8d\n",
                    p.getPid(), p.getNombre(), p.getTiempoLlegada(), p.getTiempoRafaga(),
                    p.getTiempoInicio(), p.getTiempoFin(), p.getTiempoEspera(), p.getTiempoRetorno());

            sumaEspera += p.getTiempoEspera();
            sumaRetorno += p.getTiempoRetorno();
        }

        double n = procesosTerminados.size();
        double esperaPromedio = n > 0 ? sumaEspera / n : 0;
        double retornoPromedio = n > 0 ? sumaRetorno / n : 0;
        double usoCPU = totalTicks > 0 ? ((double) ticksOcupados / totalTicks) * 100 : 0;

        System.out.println("-".repeat(95));
        System.out.printf("Tiempo de Espera Promedio: %.2f\n", esperaPromedio);
        System.out.printf("Tiempo de Retorno Promedio: %.2f\n", retornoPromedio);
        System.out.printf("Uso de CPU: %.2f%%\n", usoCPU);
        System.out.println("Total de Cambios de Contexto: " + totalCambiosContexto);
        System.out.println("=".repeat(95));
    }
}



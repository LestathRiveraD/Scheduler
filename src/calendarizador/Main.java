import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import modelo.PCB;
import simulacion.GanttRenderer;

public class Main
{
    public static void main(String[] args)
    {
        /*
        TODO:
        Manejar valores negativos o cero en tiempo de rafaga
        pids duplicados
        lista de procesos vacia
        archivo de entrada con formato incorrecto
        */
        // Obtener los datos
        List<String> lineas = new ArrayList<>();
        List<PCB> procesos = new ArrayList<>();
        if (args.length == 1) // Leer txt con los datos
        {
            // Leer lineas del archivo proporcionado por el usuario
            try {
                lineas = Files.readAllLines(Paths.get(args[0]));
            } 
            catch (Exception e) {
                System.out.println("Lectura de " + args[0] + " falló");
                return;
            }
                
            // Convertir las lineas del archivo de texto a objetos PCB
            int pid, llegada, rafaga, prioridad, tiempoIO;
            String nombre;
            for (int i = 1; i < lineas.size(); i++) 
            {
                String[] cur = lineas.get(i).split(",");

                if (cur.length < 6) {
                    System.out.println("Linea invalida");
                    continue;
                }

                PCB p = new PCB(
                    Integer.parseInt(cur[0]),
                    cur[1],
                    Integer.parseInt(cur[2]),
                    Integer.parseInt(cur[3])
                );
        
                procesos.add(p);
            }

            System.out.println(procesos);
        }
        else // Leer datos por consola
        {

        }
        // BLOQUE DE PRUEBA (GANTT RENDERER)
        System.out.println("\n--- Iniciando prueba del visualizador ---");
        GanttRenderer renderer = new GanttRenderer();

        // Simular el comportamiento del CPU tick por tick
        renderer.registrarTick("P1");
        renderer.registrarTick("P1");
        renderer.registrarTick("P2");
        renderer.registrarTick(null);
        renderer.registrarTick("P3");
        renderer.registrarTick("P3");

        // Imprime resultado en consola
        renderer.mostrarDiagrama();
    }
}

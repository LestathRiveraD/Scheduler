import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import modelo.PCB;
import simulacion.*;
import algoritmos.*;

public class Main
{
    public static void main(String[] args)
    {
        List<String> lineas = new ArrayList<>();
        List<PCB> procesos = new ArrayList<>();

        if (args.length == 1) // Leer txt
        {
            if (!args[0].matches("[a-zA-Z0-9_\\-]+\\.txt")) {
                System.out.println("Error: El archivo debe tener formato nombre_archivo.txt");
                return;
            }

            try {
                lineas = Files.readAllLines(Paths.get(args[0]));
            }
            catch (Exception e) {
                System.out.println("Lectura de " + args[0] + " falló");
                return;
            }

            for (int i = 1; i < lineas.size(); i++)
            {
                String[] cur = lineas.get(i).split(",");

                if (cur.length < 4) {
                    System.out.println("Linea invalida");
                    continue;
                }

                PCB p = new PCB(
                        Integer.parseInt(cur[0].trim()),
                        cur[1].trim(),
                        Integer.parseInt(cur[2].trim()),
                        Integer.parseInt(cur[3].trim())
                );

                procesos.add(p);
            }
        }
        else if (args.length == 0) // Consola
        {
            Scanner sc = new Scanner(System.in);

            System.out.print("¿Cuántos procesos desea ingresar?: ");
            int n = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < n; i++)
            {
                System.out.println("\nProceso " + (i + 1));

                System.out.print("PID: ");
                int pid = sc.nextInt();
                sc.nextLine();

                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Tiempo de llegada: ");
                int llegada = sc.nextInt();

                System.out.print("Ráfaga CPU: ");
                int rafaga = sc.nextInt();
                sc.nextLine();

                PCB p = new PCB(pid, nombre, llegada, rafaga);
                procesos.add(p);
            }
        }
        else
        {
            System.out.println("Uso: java Main [archivo.txt] o java Main");
            return;
        }

        Set<Integer> s = new HashSet<>();
        for (PCB p : procesos)
        {
            if (p.getTiempoRafaga() <= 0) {
                System.out.println("Error: ráfaga debe ser > 0");
                return;
            }

            if (!s.add(p.getPid())) {
                System.out.println("Error: PID duplicado");
                return;
            }
        }

        if (procesos.isEmpty()) {
            System.out.println("Error: no hay procesos");
            return;
        }


        Scanner sc = new Scanner(System.in);

        System.out.println("\nSeleccione algoritmo:");
        System.out.println("1. FCFS");
        System.out.println("2. SJF");
        int opcion = sc.nextInt();

        Calendarizador algoritmo;

        switch (opcion) {
            case 1:
                algoritmo = new CalendarizadorFCFS();
                break;
            case 2:
                algoritmo = new CalendarizadorSJF();
                break;
            default:
                System.out.println("Opción inválida");
                return;
        }

        // Crear gestor de colas
        GestorColas gestor = new GestorColas();

        // Cargar procesos a cola de nuevos
        for (PCB p : procesos) {
            gestor.getNuevos().add(p);
        }

        // Crear simulador
        Simulador simulador = new Simulador(algoritmo, gestor, 0);

        // Preguntar modo paso a paso
        System.out.print("¿Modo paso a paso? (1 = sí, 0 = no): ");
        int paso = sc.nextInt();
        simulador.setIs_Paso_a_Paso(paso == 1);

        while (gestor.getTerminados().size() < procesos.size()) {
            simulador.tick();
        }

        // Mostrar Gantt
        System.out.println("\nSimulación terminada.");

        sc.close();
    }
}
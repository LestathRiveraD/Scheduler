import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import modelo.PCB;

public class Main
{
    public static void main(String[] args)
    {
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
        else if (args.length == 0) // Leer datos por consola
        {
            Scanner sc = new Scanner(System.in);

            System.out.print("¿Cuántos procesos desea ingresar?: ");
            int n = sc.nextInt();
            sc.nextLine(); // limpiar buffer

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
            sc.close();
        }
        else
            System.out.println("Uso: Java Main [nombre_archivo.txt] / Java Main");

        Set<Integer> s = new HashSet<>();
        List<Integer> l = new ArrayList<>(); 

        for (PCB p : procesos)
        {
            s.add(p.getPid());
            l.add(p.getPid());
        }
        if (s.size() != l.size() || procesos.size() == 0)
        {
            System.out.println("Error: PIDS duplicadas.");
            return;
        }
    }
}

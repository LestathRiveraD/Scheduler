import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        // Obtener los datos
        List<String> lineas = new ArrayList<>();
        if (args.length == 1) // Leer txt con los datos
        {
            try {
                lineas = Files.readAllLines(Paths.get(args[0]));
            } 
            catch (Exception e) {
                System.out.println("Lectura de " + args[0] + " falló");
                return;
            }
            
            for (String linea : lineas)
            {
                String[] proceso = linea.split(","); 
                if ()
            }
        }
        else // Leer datos por consola
        {

        }
    }
}

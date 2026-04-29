Simulador de Calendarización de Procesos - Java

Este proyecto consiste en un simulador de gestión de procesos que emula el comportamiento de un planificador de corto plazo en un Sistema Operativo. El sistema modela el ciclo de vida de los procesos, gestiona colas de estado y ejecuta diversos algoritmos de calendarización para reportar métricas de rendimiento y visualizaciones gráficas.
Características Principales

Modelado Completo de PCB: Cada proceso se gestiona mediante un Process Control Block (PCB) que almacena atributos como PID, ráfaga de CPU, tiempos de llegada, prioridad y estado actual.

Ciclo de Vida del Proceso: Soporta las transiciones entre estados: NUEVO, LISTO, EJECUTANDO, BLOQUEADO y TERMINADO.

Visualización de Resultados: Genera un diagrama de Gantt en consola para observar la ocupación de la CPU tick a tick.

Reporte de Métricas: Calcula automáticamente el tiempo de espera promedio, tiempo de retorno promedio, uso total de CPU y número de cambios de contexto.

Algoritmos Implementados

El simulador incluye los siguientes algoritmos de planificación:

FCFS (First-Come, First-Served): Planificación no apropiativa basada en el orden de llegada.

SJF (Shortest Job First): Selección no apropiativa del proceso con la ráfaga más corta.

SRTF (Shortest Remaining Time First): Versión apropiativa de SJF que interrumpe la ejecución si llega un proceso con menor tiempo restante.

Round Robin (RR): Algoritmo apropiativo que utiliza un quantum de tiempo configurable para alternar procesos en la CPU.

Prioridades con Aging: Selección basada en prioridad numérica con un mecanismo de envejecimiento para evitar la inanición de procesos de baja prioridad.

Estructura del Proyecto

El código está organizado en paquetes siguiendo principios de diseño orientado a objetos:

calendarizador.modelo: Contiene la lógica del PCB, el enum Estado y la gestión de resultados.

calendarizador.algoritmos: Define la interfaz Calendarizador y las implementaciones de cada política de planificación.

calendarizador.simulacion: Motor central que ejecuta los "ticks" de reloj, gestiona las colas y renderiza el diagrama de Gantt.

Requisitos de Ejecución

Lenguaje: Java 11 o superior.

Entorno: No requiere librerías externas adicionales.

Uso

El programa admite dos modos de entrada de datos:

Modo Interactivo
Ejecuta el programa y sigue las instrucciones en consola para ingresar los procesos manualmente: Bash

java Main

Modo por Archivo
Puedes cargar una lista de procesos desde un archivo .txt con el formato pid,nombre,llegada,rafaga,prioridad,tiempoIO:
Bash

java Main procesos.txt


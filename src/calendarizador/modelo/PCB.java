package modelo;

import java.util.*;

public class PCB implements Comparable<PCB> {
    private final int pid;
    private final String nombre;
    private Estado estado;

    public enum Estado {
        NUEVO,
        LISTO,
        EJECUTANDO,
        BLOQUEADO,
        TERMINADO
    }

    private int prioridad;
    private final int tiempoLlegada;
    private final int tiempoRafaga;
    private int tiempoRestante;
    private int tiempoEspera;
    private int tiempoRetorno;
    private int tiempoInicio;
    private int tiempoFin;
    private int contadorAging;
    private Map<String, Integer> contextoCPU;

    public PCB(int pid, String nombre, int tiempoLlegada, int tiempoRafaga) {
        this.pid = pid;
        this.nombre = nombre;
        this.tiempoLlegada = tiempoLlegada;
        this.tiempoRafaga = tiempoRafaga;
        this.contextoCPU = new HashMap<>();
    }

    public int getPid() {
        return pid;
    }

    public String getNombre() {
        return nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public int getTiempoRafaga() {
        return tiempoRafaga;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public int getTiempoRetorno() {
        return tiempoRetorno;
    }

    public void setTiempoRetorno(int tiempoRetorno) {
        this.tiempoRetorno = tiempoRetorno;
    }

    public int getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(int tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public int getTiempoFin() {
        return tiempoFin;
    }

    public void setTiempoFin(int tiempoFin) {
        this.tiempoFin = tiempoFin;
    }

    public int getContadorAging() {
        return contadorAging;
    }

    public void setContadorAging(int contadorAging) {
        this.contadorAging = contadorAging;
    }

    public Map<String, Integer> getContextoCPU() {
        return contextoCPU;
    }

    public void setContextoCPU(Map<String, Integer> contextoCPU) {
        this.contextoCPU = contextoCPU;
    }

    // Transiciones de estado
    public void listo() {
        if (estado == Estado.NUEVO)
            estado = Estado.LISTO;
    }
    
    public void ejecutando() {
        if (estado == Estado.LISTO)
            estado = Estado.EJECUTANDO;
    }

    public void bloquear() {
        if (estado == Estado.EJECUTANDO)
            estado = Estado.BLOQUEADO;
    }

    public void desbloquear() {
        if (estado == Estado.BLOQUEADO)
            estado = Estado.EJECUTANDO;
    }

    public void terminar() {
        if (estado == Estado.EJECUTANDO)
            estado = Estado.TERMINADO;
    }

    // Comparaciones para cola de prioridad
    @Override
    public int compareTo(PCB other) {
        // Checar que no sea null
        if (other == null)
            throw new NullPointerException("No se puede comparar con null");
        return Integer.compare(this.getPrioridad(), other.getPrioridad());
    }
    
    // Metodo toString
    @Override
    public String toString() {
        return "PCB{" +
            "pid=" + pid +
            ", nombre='" + nombre + '\'' +
            ", estado=" + estado +
            ", prioridad=" + prioridad +
            ", tiempoLlegada=" + tiempoLlegada +
            ", tiempoRafaga=" + tiempoRafaga +
            ", tiempoRestante=" + tiempoRestante +
            ", tiempoEspera=" + tiempoEspera +
            ", tiempoRetorno=" + tiempoRetorno +
            ", tiempoInicio=" + tiempoInicio +
            ", tiempoFin=" + tiempoFin +
            ", contadorAging=" + contadorAging +
            ", contextoCPU=" + contextoCPU +
            '}';
    }

    public static void main(String[] args) {
        System.out.println("Hello world");
    }
}

import java.util.Map;
import java.util.HashMap;

import java.util.Map;
import java.util.HashMap;

public class PCB {

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

    public void setEstado(Estado estado) {
        this.estado = estado;
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

    public static void main(String[] args) {
        System.out.println("Hello world");
    }
}

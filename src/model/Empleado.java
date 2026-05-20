package model;
import interfaces.Calculable;
/**
 * Clase abstracta base que representa la estructura general de un Empleado.
 * Implementa Calculable para asegurar el diseño basado en contratos.
 */
public abstract class Empleado implements Calculable {

    protected String nombre;
    protected String identificacion;
    protected double salarioBase;

    public Empleado(String nombre, String identificacion, double salarioBase) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.salarioBase = salarioBase;
    }

    public String getNombre() { return nombre; }
    public String getIdentificacion() { return identificacion; }
    public double getSalarioBase() { return salarioBase; }
}
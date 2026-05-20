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
    protected int aniosEmpresa;

    public Empleado(String nombre, String identificacion, double salarioBase, int aniosEmpresa) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.salarioBase = salarioBase;
        this.aniosEmpresa = aniosEmpresa;
    }

    public String getNombre() { return nombre; }
    public String getIdentificacion() { return identificacion; }
    public double getSalarioBase() { return salarioBase; }
    public int getAniosEmpresa(){ return aniosEmpresa; }

    @Override
    public abstract double calcularSalario();

    @Override
    public abstract double calcularDeducciones();

    @Override
    public abstract double calcularBeneficios();

    @Override
    public double calcularSalarioNeto() {

        return calcularSalario()
                + calcularBeneficios()
                - calcularDeducciones();
    }

}
package model;

public abstract class Empleado {

    protected String nombre;
    protected String identificacion;
    protected double salarioBase;

    // Constructor para inicializar los datos comunes
    public Empleado(String nombre, String identificacion, double salarioBase) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.salarioBase = salarioBase;
    }

    /**
     * Método abstracto que cada subtipo de empleado debe implementar
     * para calcular su salario bruto según sus propias reglas de negocio.
     */
    public abstract double calcularSalario();

    // Getters necesarios para el reporte final de nómina
    public String getNombre() { return nombre; }
    public String getIdentificacion() { return identificacion; }
    public double getSalarioBase() { return salarioBase; }
}
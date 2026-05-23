package model;

public class EmpleadoPorComision extends Empleado {
    private double ventas;
    private static final double PORCENTAJE_COMISION = 0.08;
    private static final double BONO_ADICIONAL = 0.03;
    private static final double BONO_ALIMENTACION = 120000;

    public EmpleadoPorComision(String nombre, String identificacion, double salarioBase, int aniosEmpresa, double ventas) {
        super(nombre, identificacion, salarioBase, aniosEmpresa);
        if (ventas < 0) {
            throw new IllegalArgumentException("Las ventas no pueden ser negativas");
        }
        this.ventas = ventas;
    }

    public double getVentas() {
        return ventas;
    }

    public void setVentas(double ventas) {
        if (ventas < 0) {
            throw new IllegalArgumentException("Las ventas no pueden ser negativas");
        }
        this.ventas = ventas;
    }

    public double calcularComision() {
        return ventas * PORCENTAJE_COMISION;
    }

    public double calcularBonoAdicional() {
        if (ventas > 5000000) {
            return ventas * BONO_ADICIONAL;
        }
        return 0;
    }

    @Override
    public double calcularBeneficios() {
        return calcularBonoAdicional() + BONO_ALIMENTACION;
    }

    @Override
    public double calcularDeducciones() {
        return salarioBase * 0.08;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + calcularComision();
    }

    @Override
    public String toString() {
        return "EmpleadoPorComision{" + "nombre='" + nombre + '\'' + ", identificacion='" + identificacion + '\'' + ", salarioBase=" + salarioBase + ", ventas=" + ventas + ", salarioFinal=" + calcularSalarioNeto() + '}';
    }
}
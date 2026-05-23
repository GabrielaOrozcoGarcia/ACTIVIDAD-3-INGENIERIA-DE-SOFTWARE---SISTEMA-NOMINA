package model;

// Clase que representa un empleado temporal
public class EmpleadoTemporal extends Empleado {

    // Constructor de la clase
    public EmpleadoTemporal(String nombre,
                            String identificacion,
                            double salarioBase,
                            int aniosEmpresa) {

        // Llamada al constructor de la clase padre
        super(nombre, identificacion, salarioBase, aniosEmpresa);
    }

    // Metodo que calcula el salario del empleado
    @Override
    public double calcularSalario() {

        return salarioBase;
    }

    // Metodo que calcula las deducciones
    @Override
    public double calcularDeducciones() {

        // Calculo salud
        double salud = salarioBase * 0.04;

        // Calculo pension
        double pension = salarioBase * 0.04;

        // Calculo ARL
        double arl = salarioBase * 0.02;

        // Retorna total deducciones
        return salud + pension + arl;
    }

    // Metodo que calcula beneficios
    @Override
    public double calcularBeneficios() {

        // Los empleados temporales no tienen beneficios
        return 0;
    }

    // Metodo que calcula salario neto
    @Override
    public double calcularSalarioNeto() {

        double salarioNeto = calcularSalario()
                + calcularBeneficios()
                - calcularDeducciones();

        // Validacion salario negativo
        if (salarioNeto < 0) {

            throw new IllegalArgumentException(
                    "El salario neto no puede ser negativo"
            );
        }

        return salarioNeto;
    }

    // Metodo para mostrar informacion del empleado
    @Override
    public String toString() {

        return "\n===== EMPLEADO TEMPORAL =====" +
                "\nNombre: " + nombre +
                "\nIdentificacion: " + identificacion +
                "\nSalario Base: " + salarioBase +
                "\nAnios Empresa: " + aniosEmpresa +
                "\nDeducciones: " + calcularDeducciones() +
                "\nBeneficios: " + calcularBeneficios() +
                "\nSalario Neto: " + calcularSalarioNeto();
    }
}
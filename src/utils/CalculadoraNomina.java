package utils;

/**
 * Clase de utilidad encargada de centralizar las deducciones obligatorias,
 * las constantes institucionales y las validaciones globales de nómina.
 */
public class CalculadoraNomina {

    // Constantes especificadas en la actividad
    public static final double PORCENTAJE_SALUD = 0.04;       // 4% Seguro Social
    public static final double PORCENTAJE_PENSION = 0.04;     // 4% Pensión
    public static final double BONO_ALIMENTACION = 1000000.0; // $1.000.000 fijo para permanentes

    // Tasa estándar en Colombia para Riesgo I (Administrativos/Oficina)
    public static final double PORCENTAJE_ARL_RIESGO_1 = 0.00522; // 0.522%

    public static double calcularSalud(double salarioBruto) {
        return salarioBruto * PORCENTAJE_SALUD;
    }

    public static double calcularPension(double salarioBruto) {
        return salarioBruto * PORCENTAJE_PENSION;
    }

    /**
     * Calcula la ARL permitiendo manejar el porcentaje base (Riesgo 1).
     */
    public static double calcularArl(double salarioBruto) {
        return salarioBruto * PORCENTAJE_ARL_RIESGO_1;
    }

    /**
     * Valida y calcula el salario neto final.
     * Alerta si el neto es negativo lanzando una excepción matemática.
     */
    public static double calcularSalarioNeto(double salarioBruto, double otrosBeneficios, double otrasDeducciones) {
        double deduccionesObligatorias = calcularSalud(salarioBruto) +
                calcularPension(salarioBruto) +
                calcularArl(salarioBruto);

        double salarioNeto = (salarioBruto + otrosBeneficios) - (deduccionesObligatorias + otrasDeducciones);

        // Regla de negocio: Ningún empleado puede tener un salario neto negativo
        if (salarioNeto < 0) {
            throw new ArithmeticException("Error de Nómina: El salario neto no puede ser negativo.");
        }

        return salarioNeto;
    }
}
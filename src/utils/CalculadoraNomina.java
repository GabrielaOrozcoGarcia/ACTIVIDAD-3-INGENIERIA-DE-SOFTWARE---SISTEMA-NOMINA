package utils;

/**
 * Clase de utilidad encargada de procesar las deducciones obligatorias
 * y validar la integridad del salario neto de cualquier empleado.
 */
public class CalculadoraNomina {

    // Constantes basadas en las Reglas de Negocio
    public static final double PORCENTAJE_SALUD = 0.04;    // 4% del salario bruto
    public static final double PORCENTAJE_PENSION = 0.04;  // 4% del salario bruto
    public static final double PORCENTAJE_ARL = 0.00522;   // Ejemplo: Riesgo 1 (0.522%) para ARL

    //Calcula la deducción por Salud (Seguro Social).
    public static double calcularSalud(double salarioBruto) {
        return salarioBruto * PORCENTAJE_SALUD;
    }

    //Calcula la deducción por Pensión.
    public static double calcularPension(double salarioBruto) {
        return salarioBruto * PORCENTAJE_PENSION;
    }


    //Calcula la deducción de ARL.
    public static double calcularArl(double salarioBruto) {
        return salarioBruto * PORCENTAJE_ARL;
    }

    /**
     * Procesa el salario neto final restando deducciones y sumando beneficios,
     * aplicando la validación obligatoria de no salarios negativos.
     * @param salarioBruto Salario calculado por cada tipo de empleado.
     * @param otrosBeneficios Suma de bonos (alimentación, comisiones, etc.).
     * @param otrasDeducciones Suma de otros descuentos (ej. Fondo de ahorro).
     * @return Salario neto final.
     * @throws ArithmeticException Si el salario neto resultante es menor a 0.
     */
    public static double calcularSalarioNeto(double salarioBruto, double otrosBeneficios, double otrasDeducciones) {
        double deduccionesObligatorias = calcularSalud(salarioBruto) + calcularPension(salarioBruto) + calcularArl(salarioBruto);

        double salarioNeto = (salarioBruto + otrosBeneficios) - (deduccionesObligatorias + otrasDeducciones);

        // Regla de negocio: Ningún empleado puede tener un salario neto negativo
        if (salarioNeto < 0) {
            throw new ArithmeticException("Error Crítico: El salario neto no puede ser negativo.");
        }

        return salarioNeto;
    }
}
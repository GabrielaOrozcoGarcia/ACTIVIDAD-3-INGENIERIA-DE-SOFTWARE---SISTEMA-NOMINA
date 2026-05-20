package model;

import interfaces.Beneficiable;
import utils.Constantes;

public class EmpleadoAsalariado extends Empleado implements Beneficiable {

    // Constructor de la clase
    public EmpleadoAsalariado(String nombre,
                              String identificacion,
                              double salarioBase,
                              int aniosEmpresa) {

        super(nombre, identificacion, salarioBase, aniosEmpresa);
    }

    // Metodo que calcula el salario base
    @Override
    public double calcularSalario() {

        return salarioBase;
    }

    // Metodo que calcula las deducciones
    @Override
    public double calcularDeducciones() {

        // Calculo de salud
        double salud = salarioBase * Constantes.PORCENTAJE_SALUD;

        // Calculo de pension
        double pension = salarioBase * Constantes.PORCENTAJE_PENSION;

        // Calculo de ARL
        double arl = salarioBase * Constantes.PORCENTAJE_ARL;

        return salud + pension + arl;
    }

    // Metodo que calcula los beneficios
    @Override
    public double calcularBeneficios() {

        double beneficios = 0;

        // Bono de alimentacion
        beneficios += Constantes.BONO_ALIMENTACION;

        // Bono por antiguedad
        if (aniosEmpresa > 5) {

            beneficios += salarioBase * Constantes.BONO_ANTIGUEDAD;
        }

        return beneficios;
    }

    // Metodo que valida si tiene beneficios
    @Override
    public boolean tieneBeneficios() {

        return true;
    }

    // Metodo que calcula el salario neto
    @Override
    public double calcularSalarioNeto() {

        double salarioNeto = calcularSalario()
                + calcularBeneficios()
                - calcularDeducciones();

        // Validacion de salario negativo
        if (salarioNeto < 0) {

            throw new IllegalArgumentException(
                    "El salario neto no puede ser negativo"
            );
        }

        return salarioNeto;
    }

    // Metodo que muestra la informacion del empleado
    @Override
    public String toString() {

        return "\n===== EMPLEADO ASALARIADO =====" +
                "\nNombre: " + nombre +
                "\nIdentificacion: " + identificacion +
                "\nSalario Base: " + salarioBase +
                "\nAnios Empresa: " + aniosEmpresa +
                "\nDeducciones: " + calcularDeducciones() +
                "\nBeneficios: " + calcularBeneficios() +
                "\nSalario Neto: " + calcularSalarioNeto();
    }
}
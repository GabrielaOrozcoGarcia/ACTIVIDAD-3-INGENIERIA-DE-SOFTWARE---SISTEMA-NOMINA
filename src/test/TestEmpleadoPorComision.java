package test;

import model.EmpleadoPorComision;

public class TestEmpleadoPorComision {

    public static void main(String[] args) {

        EmpleadoPorComision empleado =
                new EmpleadoPorComision(
                        "Emiro Puello",
                        "10456789",
                        1800000,
                        3,
                        7000000
                );

        System.out.println("====== EMPLEADO POR COMISION ======");

        System.out.println("Nombre: " + empleado.getNombre());

        System.out.println("Ventas: " + empleado.getVentas());

        System.out.println("Comision: "
                + empleado.calcularComision());

        System.out.println("Beneficios: "
                + empleado.calcularBeneficios());

        System.out.println("Deducciones: "
                + empleado.calcularDeducciones());

        System.out.println("Salario Neto: "
                + empleado.calcularSalarioNeto());
    }
}

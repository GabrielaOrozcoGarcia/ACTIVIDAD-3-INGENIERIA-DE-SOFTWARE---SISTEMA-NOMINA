package test;

import model.EmpleadoTemporal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoTemporalTest {

    // Prueba calculo salario
    @Test
    public void calcularSalarioCorrectamente() {

        EmpleadoTemporal empleado =
                new EmpleadoTemporal(
                        "Gabriela",
                        "123",
                        2500000,
                        1
                );

        assertEquals(
                2500000,
                empleado.calcularSalario()
        );
    }

    // Prueba calculo deducciones
    @Test
    public void calcularDeduccionesCorrectamente() {

        EmpleadoTemporal empleado =
                new EmpleadoTemporal(
                        "Gabriela",
                        "123",
                        2500000,
                        1
                );

        double deduccionesEsperadas =
                (2500000 * 0.04) +
                        (2500000 * 0.04) +
                        (2500000 * 0.02);

        assertEquals(
                deduccionesEsperadas,
                empleado.calcularDeducciones()
        );
    }

    // Prueba beneficios
    @Test
    public void calcularBeneficiosCorrectamente() {

        EmpleadoTemporal empleado =
                new EmpleadoTemporal(
                        "Gabriela",
                        "123",
                        2500000,
                        1
                );

        assertEquals(
                0,
                empleado.calcularBeneficios()
        );
    }

    // Prueba salario neto
    @Test
    public void calcularSalarioNetoCorrectamente() {

        EmpleadoTemporal empleado =
                new EmpleadoTemporal(
                        "Gabriela",
                        "123",
                        2500000,
                        1
                );

        double salarioEsperado =
                empleado.calcularSalario()
                        + empleado.calcularBeneficios()
                        - empleado.calcularDeducciones();

        assertEquals(
                salarioEsperado,
                empleado.calcularSalarioNeto()
        );
    }
}
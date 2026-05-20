package test;

import model.EmpleadoAsalariado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoAsalariadoTest {

    @Test
    public void calcularSalarioCorrectamente() {

        EmpleadoAsalariado empleado =
                new EmpleadoAsalariado(
                        "Gabriela",
                        "123",
                        5000000,
                        6
                );

        assertEquals(
                5000000,
                empleado.calcularSalario()
        );
    }

    @Test
    public void calcularBeneficiosCorrectamente() {

        EmpleadoAsalariado empleado =
                new EmpleadoAsalariado(
                        "Gabriela",
                        "123",
                        5000000,
                        6
                );

        double beneficiosEsperados =
                1000000 + (5000000 * 0.10);

        assertEquals(
                beneficiosEsperados,
                empleado.calcularBeneficios()
        );
    }

    @Test
    public void calcularDeduccionesCorrectamente() {

        EmpleadoAsalariado empleado =
                new EmpleadoAsalariado(
                        "Gabriela",
                        "123",
                        5000000,
                        6
                );

        double deduccionesEsperadas =
                (5000000 * 0.04) +
                        (5000000 * 0.04) +
                        (5000000 * 0.02);

        assertEquals(
                deduccionesEsperadas,
                empleado.calcularDeducciones()
        );
    }

    @Test
    public void calcularSalarioNetoCorrectamente() {

        EmpleadoAsalariado empleado =
                new EmpleadoAsalariado(
                        "Gabriela",
                        "123",
                        5000000,
                        6
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
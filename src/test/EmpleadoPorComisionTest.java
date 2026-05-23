package test;

import model.EmpleadoPorComision;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoPorComisionTest {

    // ─── Constructor y validaciones ──────────────────────────────────────

    @Test
    void testConstructorConDatosValidos() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                10000000
        );
        assertEquals("Juan", empleado.getNombre());
        assertEquals("456", empleado.getIdentificacion());
        assertEquals(3000000, empleado.getSalarioBase());
        assertEquals(5, empleado.getAniosEmpresa());
        assertEquals(10000000, empleado.getVentas());
    }

    @Test
    void testConstructorConVentasNegativasLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () ->
                new EmpleadoPorComision("Juan", "456", 3000000, 5, -100000)
        );
    }

    @Test
    void testSetVentasValidas() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                5000000
        );
        empleado.setVentas(8000000);
        assertEquals(8000000, empleado.getVentas());
    }

    @Test
    void testSetVentasNegativasLanzaExcepcion() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                5000000
        );
        assertThrows(IllegalArgumentException.class, () ->
                empleado.setVentas(-500000)
        );
    }

    // ─── Cálculo de Comisión ─────────────────────────────────────────────

    @Test
    void testCalcularComisionCorrectamente() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                10000000
        );
        double comisionEsperada = 10000000 * 0.08; // 800000
        assertEquals(comisionEsperada, empleado.calcularComision());
    }

    @Test
    void testCalcularComisionConVentasCero() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                0
        );
        assertEquals(0, empleado.calcularComision());
    }

    // ─── Cálculo de Bono Adicional ───────────────────────────────────────

    @Test
    void testCalcularBonoAdicionalSinVentasAltas() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                3000000  // Menos de 5000000
        );
        assertEquals(0, empleado.calcularBonoAdicional());
    }

    @Test
    void testCalcularBonoAdicionalConVentasAltas() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                6000000  // Mayor a 5000000
        );
        double bonoEsperado = 6000000 * 0.03; // 180000
        assertEquals(bonoEsperado, empleado.calcularBonoAdicional());
    }

    @Test
    void testCalcularBonoAdicionalEnExactamente5Millones() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                5000000  // Exactamente 5000000
        );
        assertEquals(0, empleado.calcularBonoAdicional());
    }

    @Test
    void testCalcularBonoAdicionalSuperandoLimite() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                5000001  // Un peso más que 5000000
        );
        double bonoEsperado = 5000001 * 0.03; // 150000.03
        assertEquals(bonoEsperado, empleado.calcularBonoAdicional(), 0.01);
    }

    // ─── Cálculo de Beneficios ──────────────────────────────────────────

    @Test
    void testCalcularBeneficiosSinBonoAdicional() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                3000000
        );
        double beneficiosEsperados = 0 + 120000; // Sin bono adicional + bono alimentación
        assertEquals(beneficiosEsperados, empleado.calcularBeneficios());
    }

    @Test
    void testCalcularBeneficiosConBonoAdicional() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                7000000
        );
        double bonoAdicional = 7000000 * 0.03; // 210000
        double beneficiosEsperados = bonoAdicional + 120000; // 330000
        assertEquals(beneficiosEsperados, empleado.calcularBeneficios());
    }

    // ─── Cálculo de Deducciones ─────────────────────────────────────────

    @Test
    void testCalcularDeduccionesCorrectamente() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                5000000
        );
        double deduccionesEsperadas = 3000000 * 0.08; // 240000
        assertEquals(deduccionesEsperadas, empleado.calcularDeducciones());
    }

    @Test
    void testCalcularDeduccionesConSalarioBase() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                5000000,
                5,
                10000000
        );
        double deduccionesEsperadas = 5000000 * 0.08; // 400000
        assertEquals(deduccionesEsperadas, empleado.calcularDeducciones());
    }

    // ─── Cálculo de Salario ─────────────────────────────────────────────

    @Test
    void testCalcularSalarioBaseMasComision() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                10000000
        );
        double salarioEsperado = 3000000 + (10000000 * 0.08); // 3800000
        assertEquals(salarioEsperado, empleado.calcularSalario());
    }

    @Test
    void testCalcularSalarioSinVentas() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                0
        );
        assertEquals(3000000, empleado.calcularSalario());
    }

    // ─── Cálculo de Salario Neto ────────────────────────────────────────

    @Test
    void testCalcularSalarioNetoConTodasLasComisiones() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                7000000
        );
        double salario = empleado.calcularSalario(); // 3000000 + (7000000 * 0.08) = 3560000
        double beneficios = empleado.calcularBeneficios(); // (7000000 * 0.03) + 120000 = 330000
        double deducciones = empleado.calcularDeducciones(); // 3000000 * 0.08 = 240000
        double netoEsperado = salario + beneficios - deducciones; // 3650000

        assertEquals(netoEsperado, empleado.calcularSalarioNeto());
    }

    @Test
    void testCalcularSalarioNetoSinComisiones() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                2000000
        );
        double salario = empleado.calcularSalario(); // 3000000 + (2000000 * 0.08) = 3160000
        double beneficios = empleado.calcularBeneficios(); // 0 + 120000 = 120000
        double deducciones = empleado.calcularDeducciones(); // 3000000 * 0.08 = 240000
        double netoEsperado = salario + beneficios - deducciones; // 3040000

        assertEquals(netoEsperado, empleado.calcularSalarioNeto());
    }

    // ─── toString ────────────────────────────────────────────────────────

    @Test
    void testToStringFormatoCorrecto() {
        EmpleadoPorComision empleado = new EmpleadoPorComision(
                "Juan",
                "456",
                3000000,
                5,
                5000000
        );
        String resultado = empleado.toString();
        assertTrue(resultado.contains("Juan"));
        assertTrue(resultado.contains("456"));
        assertTrue(resultado.contains("3000000"));
        assertTrue(resultado.contains("5000000"));
    }
}